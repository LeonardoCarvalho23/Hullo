package com.hullo.controller;



import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@GetMapping("/showFormNewProfessor")
	public String showFormNovoUsuario(Model theModel){
		
		//create model attribute to bind form data
		UsuarioImpl theUsuario = new UsuarioImpl();
		theModel.addAttribute("usuario", theUsuario); //name,value
		
		return "professor-form";
	}
	
//	Metodo em desenvolvimento
  @PostMapping("/newProfessor")
	public String saveUsuario(@ModelAttribute("professor") ProfessorImpl theProfessor){
	  
	  Date current_date = new Date();
	  
	  theProfessor.setAtivo_usuario("1");
	  theProfessor.setDt_insert_usuario(current_date);
	  theProfessor.setDt_last_update_usuario(current_date);
	  theProfessor.setTipo_usuario("PROFESSOR");
	  theProfessor.setData_nascimento_usuario(current_date);
		
	  //save the professor
	  professorService.saveUsuario(theProfessor);
		
		return "redirect:../adm/listaProfessores";
	}
}
