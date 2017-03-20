package com.hullo.controller;



import java.util.Date;

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


import com.hullo.entity.ProfessorImpl;
import com.hullo.entity.UsuarioImpl;
import com.hullo.service.UsuarioService;

@Controller
@RequestMapping("/professor")
public class ProfessorController {

	@Autowired
	@Qualifier("professorServiceImpl")
	private UsuarioService<ProfessorImpl> professorService;
	
	// --Abaixo, dados para disparo de email
		@Autowired
		private MailSender mailSender;
		@Autowired
		public void setMailSender(MailSender mailSender){
			this.mailSender = mailSender;
		}
		// -- fim do código para email
	
	@GetMapping("/formProfessor")
	public String showFormNovoUsuario(Model theModel){
		
		//create model attribute to bind form data
		UsuarioImpl theUsuario = new UsuarioImpl();
		theModel.addAttribute("usuario", theUsuario); //name,value
		
		return "professor-form";
	}
	
//para gravar novo professor
  @PostMapping("/newProfessor")
	public String saveUsuario(@ModelAttribute("usuario") ProfessorImpl theProfessor, ModelMap modelMap){
	  	  
	  Date current_date = new Date();
	  
	  ProfessorImpl validaProfessor = new ProfessorImpl();
		
		//validar se ja existe usuario com esse email ou senha
	  validaProfessor = professorService.getUsuario(theProfessor.getEmail_usuario(), theProfessor.getCpf_usuario());
		
		//se retornar que existe, exibe mensagem de erro
	  
	
		if (validaProfessor != null){
			System.out.println("viu que ha usuario com os dados");
			//exibe mensagem de erro
			final String errorMessage = 
					"<div class='alert alert-danger fade in'> <a href='#' class='close' data-dismiss='alert'>&times;</a> Ja exite usuario com esses dados </div>"; 
		    modelMap.addAttribute("errorMessage", errorMessage);
		    System.out.println(errorMessage);
			return "professor-form";
			
			//se nao existe professor com esses dados, cria o ususario
		} else {
			theProfessor.setAtivo_usuario("1");
			theProfessor.setDt_insert_usuario(current_date);
			theProfessor.setDt_last_update_usuario(current_date);
			theProfessor.setTipo_usuario("PROFESSOR");

			//save the professor
			professorService.saveUsuario(theProfessor);
			
			//Envia email de confirmação
			SimpleMailMessage msg = new SimpleMailMessage();
			
			msg.setTo(theProfessor.getEmail_usuario());
			msg.setFrom("noreply@hullo.com.br");
			msg.setSubject("Confirmação de cadastro");
			msg.setText(theProfessor.getNome_usuario()+", seu cadastro de professor foi realizado com sucesso.");
			
			try {
				this.mailSender.send(msg);
				//System.out.println(msg.toString());
			} catch (MailException e) {
				// TODO Auto-generated catch block
			}
		  
			return "redirect:/usuario/usuarioLogin";
		}   
  } 
  
//metodo para abrir pagina de perfil professor
	@PostMapping("/showPerfilProfessor")
	public String showPerfilProfessor(@RequestParam("id_usuario") int id_usuario, Model theModel){

		// este método depende de eu colocar o id do usuario no link perfil, no jsp
		//get professor form database
		ProfessorImpl theUsuario = professorService.getUsuario(id_usuario);
		
		//adiciona o usuario ao modelo
		theModel.addAttribute("usuario", theUsuario);

		// retorna
		return "perfil-professor";
		
	}
	
	
	//metodo para inativar professor
		@PostMapping("/inactivateProfessor")
		public String inactivateProfessor(@ModelAttribute("usuario") ProfessorImpl theUsuario, Model theModel){
			Date current_date = new Date();

			theUsuario.setDt_last_update_usuario(current_date);
			
			professorService.inactivateUsuario(theUsuario);
				
			theModel.addAttribute(theUsuario);
			System.out.println("entrou aqui inativar");
			return "redirect:/usuario/usuarioLogin";
				
		}
}
