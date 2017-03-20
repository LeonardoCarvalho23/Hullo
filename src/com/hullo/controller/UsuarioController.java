package com.hullo.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
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
import com.hullo.entity.ProfessorImpl;
import com.hullo.entity.Usuario;
import com.hullo.entity.UsuarioImpl;
import com.hullo.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	// --Abaixo, dados para disparo de email
	@Autowired
	private MailSender mailSender;
	@Autowired
	public void setMailSender(MailSender mailSender){
		this.mailSender = mailSender;
	}
	// -- fim do c�digo para email
	
	//with the service, inject the service here
	@Autowired
	@Qualifier("usuarioServiceImpl")
	private UsuarioService<UsuarioImpl> usuarioService;
		
	@GetMapping("/showFormNewUsuario")
	public String showFormNovoUsuario(Model theModel){
		
		//create model attribute to bind form data
		UsuarioImpl theUsuario = new UsuarioImpl();
		theModel.addAttribute("usuario", theUsuario); //name,value
		
		return "usuario-form";
	}
	
	@GetMapping("/usuarioLogin")
	public String usuarioLogin(Model theModel){
		Usuario oUsuario = new UsuarioImpl();
		theModel.addAttribute("usuario", oUsuario);
		
		return "usuario-login";
	}
	
	@PostMapping("/getUsuario")
	public String loginUsuario(@ModelAttribute("usuario") UsuarioImpl theUsuario, Model model){
		// Acima, acrescentei o "Model model" para poder repassar a mensagem de erro quando o login falha
		// Pega o Model e retira os par�metros para vari�veis
		String email = theUsuario.getEmail_usuario();
		String senha = theUsuario.getSenha_usuario();
		
		// busca o usu�rio com base nos dados retirados acima
		UsuarioImpl loggedUser = usuarioService.getUsuario(email, senha);
		if (loggedUser == null){
			// erro de login
			final String errorMessage = 
					"<div class='alert alert-danger fade in'> <a href='#' class='close' data-dismiss='alert'>&times;</a> Usu�rio ou senha incorretos. </div>"; 
		    model.addAttribute("errorMessage", errorMessage);
			return "usuario-login";
			
		} else {
			// login feito com sucesso
			
			// checa se o usu�rio � aluno ou professor e retorna p�gina apropriada
			if (loggedUser.getTipo_usuario().equals("ALUNO")){
				
				//transforma o UsuarioImpl em AlunoImpl e atualiza o modelo
				AlunoImpl loggedAluno = new AlunoImpl(loggedUser);
				model.addAttribute("usuario", loggedAluno);
				return "home-aluno";
				
			} else if (loggedUser.getTipo_usuario().equals("PROFESSOR")) {
				//se e professor, transforma o ususario e atualiza o modelo
				ProfessorImpl loggedProfessor = new ProfessorImpl(loggedUser);
				model.addAttribute("usuario", loggedProfessor);
				return "home-professor";
			}
			else{
				//sobrou adm
				model.addAttribute("usuario", loggedUser);
				return "main";
			}
		}
	}
	

	@PostMapping("/showFormUpdateAluno")
	public String showFormUpdateAluno(@RequestParam("id_usuario") int id_usuario, Model theModel){
		// o id_usuario deve estar especificado no JSP como campo oculto
		//get aluno form database
		Usuario theUsuario = usuarioService.getUsuario(id_usuario);
		
		//adiciona o usuario ao modelo
		theModel.addAttribute("usuario", theUsuario);
		
		// retorna
		return "aluno-update-form";
		
	}
	
	@PostMapping("/saveAluno")
	public String saveAluno(@ModelAttribute("usuario") UsuarioImpl theUsuario, Model theModel){
		Date current_date = new Date();
		
		theUsuario.setAtivo_usuario("1");
		theUsuario.setDt_insert_usuario(current_date);
		theUsuario.setDt_last_update_usuario(current_date);
		theUsuario.setTipo_usuario("ALUNO");
		
		usuarioService.saveUsuario(theUsuario);
		
		theModel.addAttribute(theUsuario);
		
		return "home-aluno";
		
	}
	
	@GetMapping("/retrievePassword")
	public String retrievePassword(Model theModel){
		Usuario oUsuario = new UsuarioImpl();
		theModel.addAttribute("usuario", oUsuario);
		return "usuario-password-recover";
	}
	
	@PostMapping("/sendPassword")
	public String sendPassword(@ModelAttribute("usuario") UsuarioImpl theUsuario, Model model){
		//Pega o email passado no modelo e busca o objeto
		String email = theUsuario.getEmail_usuario();
		UsuarioImpl visitor = usuarioService.getUsuario(email);
		//Se o usuario nao foi encontrado, retorna erro. Do contrario, envia email.
		if (visitor == null){
			// erro
			final String errorMessage = 
					"<div class='alert alert-danger fade in'> <a href='#' class='close' data-dismiss='alert'>&times;</a> Usu�rio n�o encontrado. </div>"; 
		    model.addAttribute("errorMessage", errorMessage);
		    return "usuario-password-recover";
			
		} else {
		
		SimpleMailMessage msg = new SimpleMailMessage();
		
		msg.setTo(visitor.getEmail_usuario());
		msg.setFrom("noreply@hullo.com.br");
		msg.setSubject("Recupera��o de senha");
		msg.setText(visitor.getNome_usuario()+", sua senha � "+visitor.getSenha_usuario()+".");
		
		try {
			this.mailSender.send(msg);
			//System.out.println(msg.toString());
		} catch (MailException e) {
			// TODO Auto-generated catch block
		}
		final String okPasswordMessage =
	    		"<div class='alert alert-success fade in'> <a href='#' class='close' data-dismiss='alert'>&times;</a> Senha enviada com sucesso. </div>";	
	    model.addAttribute("okPasswordMessage", okPasswordMessage);
		return "usuario-login";
		}
	}
	
	/*
	@PostMapping("novoUsuario")
	public String saveUsuario(@ModelAttribute("usuario") Usuario theUsuario){
		theUsuario.setAtivo_usuario("1");
		theUsuario.setDt_insert_usuario("now()");
		//save the usuario
		usuarioService.saveUsuario(theUsuario);
		
		return "redirect:/usuario/lista";
	}
	*/

}
