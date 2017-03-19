package com.hullo.controller;



import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hullo.entity.ProfessorImpl;
import com.hullo.entity.UsuarioImpl;
import com.hullo.service.UsuarioService;

@Controller
@RequestMapping("/professor")
public class ProfessorController {

	@Autowired
	@Qualifier("professorServiceImpl")
	private UsuarioService<ProfessorImpl> professorService;
	
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
		  
			return "redirect:/usuario/usuarioLogin";
		}   
  }  
}
