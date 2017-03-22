package com.hullo.controller;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.*;

import com.hullo.entity.AlunoImpl;
import com.hullo.entity.CidadeImpl;
import com.hullo.entity.EstadoImpl;
import com.hullo.entity.UsuarioImpl;
import com.hullo.entity.AlunoModel;
import com.hullo.service.CidadeServiceImpl;
import com.hullo.service.EstadoServiceImpl;
import com.hullo.service.UsuarioService;

@Controller
@RequestMapping("/aluno")
public class AlunoController {
	
	@Autowired
	@Qualifier("alunoServiceImpl")
	private UsuarioService<AlunoImpl> alunoService;
	
	 	
	@Autowired
	private EstadoServiceImpl estadoService;
	// --Abaixo, dados para disparo de email
	@Autowired
	private MailSender mailSender;
	@Autowired
	public void setMailSender(MailSender mailSender){
		this.mailSender = mailSender;
	}
	// -- fim do código para email
	
	@Autowired
	private CidadeServiceImpl cidadeService;
	
	@GetMapping("/formAluno")
	public String showFormNovoUsuario(Model theModel){
		
		
		AlunoImpl theAluno = new AlunoImpl();
		
		List<EstadoImpl> estados = estadoService.getEstados();
		
		AlunoModel alunoModel = new AlunoModel();
		
		alunoModel.setEstado(estados);
		alunoModel.setUsuario(theAluno);
		
		theModel.addAttribute("usuarioModel", alunoModel);
		
		return "aluno-form";
	}
	

	//Metodo para gravar novo aluno
	@PostMapping("/newAluno")
	public String saveUsuario(@ModelAttribute("usuarioModel") AlunoModel usuarioModel, ModelMap modelMap) throws JsonParseException, JsonMappingException, IOException{
		Date current_date = new Date();
		
		//pega o aluno do objeto alunoModel
		AlunoImpl theAluno = usuarioModel.getUsuario();
		
		//pega a cidade do objeto alunoModel (cast de JSON para Objeto)
		ObjectMapper mapper = new ObjectMapper();
		CidadeImpl cidade = mapper.readValue(usuarioModel.getCidade(), CidadeImpl.class);
		
		//seta o id da cidade no usuario
		theAluno.setCd_cidade_usuario(cidade.getId_Cidade());
		
		//validar se ja existe usuario com esse email ou senha
		AlunoImpl validaAluno = alunoService.getUsuario(theAluno.getEmail_usuario(), theAluno.getCpf_usuario());
		
		System.out.println("validou o usuario");
		
		//se retornar que existe, exibe mensagem de erro
		if (validaAluno != null){
			System.out.println("viu que ha usuario com os dados");
			//exibe mensagem de erro
			final String errorMessage = 
					"<div class='alert alert-danger fade in'> <a href='#' class='close' data-dismiss='alert'>&times;</a> Ja exite usuario com esses dados </div>"; 
		    modelMap.addAttribute("errorMessage", errorMessage);
			
			return "aluno-form";
			
		//se nao existe aluno com esses dados, cria o ususario
		} else {
			System.out.println("viu que nao ha usuario com os dados");
			theAluno.setAtivo_usuario("1");
			theAluno.setDt_insert_usuario(current_date);
			theAluno.setDt_last_update_usuario(current_date);
			theAluno.setTipo_usuario("ALUNO");
			
			//save the aluno
			alunoService.saveUsuario(theAluno);
			
			//Envia email de confirmação
			SimpleMailMessage msg = new SimpleMailMessage();
			
			msg.setTo(theAluno.getEmail_usuario());
			msg.setFrom("noreply@hullo.com.br");
			msg.setSubject("Confirmação de cadastro");
			msg.setText(theAluno.getNome_usuario()+", seu cadastro de aluno foi realizado com sucesso.");
			
			try {
				this.mailSender.send(msg);
				//System.out.println(msg.toString());
			} catch (MailException e) {
				// TODO Auto-generated catch block
			}
			
			return "redirect:/usuario/usuarioLogin";
		}
		
	}
	
	
	@RequestMapping(value = "/formAluno/cidades", method = RequestMethod.POST)
	public @ResponseBody List<CidadeImpl> obterCidade(@RequestBody EstadoImpl estado){
		
		//List<CidadeImpl> cidade = cidadeService.getCidades();
		
		List<CidadeImpl> cidade = cidadeService.obterCidadesDoEstado(estado);
		
		return cidade;
	}
	
	//metodo para abrir pagina de update do aluno
	@PostMapping("/showFormUpdateAluno")
	public String showFormUpdateAluno(@RequestParam("id_usuario") int id_usuario, Model theModel){

		// este método depende de eu colocar o id do usuario no link "atualizar", no jsp
		//get aluno form database
		AlunoImpl theUsuario = alunoService.getUsuario(id_usuario);
		
		//adiciona o usuario ao modelo
		theModel.addAttribute("usuario", theUsuario);
		
		// retorna
		return "aluno-update-form";
		
	}
	
	//metodo para atualizar aluno
	@PostMapping("/updateAluno")
	public String updateAluno(@ModelAttribute("usuario") AlunoImpl theUsuario, Model theModel){
		Date current_date = new Date();

		theUsuario.setDt_last_update_usuario(current_date);
		
		alunoService.updateUsuario(theUsuario);
		
		theModel.addAttribute(theUsuario);
		
		return "home-aluno";
		
	}
	
	//metodo para abrir pagina de update do aluno
		@PostMapping("/showPerfilAluno")
		public String showPerfilAluno(@RequestParam("id_usuario") int id_usuario, Model theModel){

			// este método depende de eu colocar o id do usuario no link "atualizar", no jsp
			//get aluno form database
			AlunoImpl theUsuario = alunoService.getUsuario(id_usuario);
			
			//adiciona o usuario ao modelo
			theModel.addAttribute("usuario", theUsuario);
			
			// retorna
			return "perfil-aluno";
			
		}
		
		//metodo para inativar aluno
		@PostMapping("/inactivateAluno")
		public String inactivateAluno(@ModelAttribute("usuario") AlunoImpl theUsuario, Model theModel){
			Date current_date = new Date();

			theUsuario.setDt_last_update_usuario(current_date);
			
			alunoService.inactivateUsuario(theUsuario);
			
			theModel.addAttribute(theUsuario);
			
			return "redirect:/usuario/usuarioLogin";
			
		}
	
		
}
