package com.hullo.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hullo.entity.AlunoImpl;
import com.hullo.entity.EstadoImpl;
import com.hullo.entity.UsuarioImpl;
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
	
	@GetMapping("/formAluno") 	
	public String showFormNovoUsuario(Model theModel){
		
		//create model attribute to bind form data
		UsuarioImpl theUsuario = new UsuarioImpl();
		
		List<EstadoImpl> estados = estadoService.getEstados();
		
		theModel.addAttribute("estados", estados);
		theModel.addAttribute("usuario", theUsuario); //name,value
		
		
		return "aluno-form";
	}
	

	//Metodo para gravar novo aluno
	@PostMapping("/newAluno")
	public String saveUsuario(@ModelAttribute("usuario") AlunoImpl theAluno, ModelMap modelMap){
		Date current_date = new Date();
		AlunoImpl validaAluno = new AlunoImpl();
		
		//validar se ja existe usuario com esse email ou senha
		validaAluno = alunoService.getUsuario(theAluno.getEmail_usuario(), theAluno.getCpf_usuario());
		
		//se retornar que existe, vai verificar se esta ativo
		if (validaAluno != null){
			
			final String errorMessage = 
					"<div class='alert alert-danger fade in'> <a href='#' class='close' data-dismiss='alert'>&times;</a> Já exite usuário com esses dados.</div>"; 
						modelMap.addAttribute("errorMessage", errorMessage);
				
			return "aluno-form";
			
		//se nao existe aluno com esses dados, cria o ususario
		} else {
			
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
