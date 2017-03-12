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
import org.springframework.web.bind.annotation.RequestParam;

import com.hullo.entity.AlunoImpl;
import com.hullo.entity.Usuario;
import com.hullo.entity.UsuarioImpl;
import com.hullo.service.UsuarioService;

@Controller
@RequestMapping("/aluno")
public class AlunoController {
	
	@Autowired
	@Qualifier("alunoServiceImpl")
	private UsuarioService<AlunoImpl> alunoService;
	
	@GetMapping("/showFormNewAluno")
	public String showFormNovoUsuario(Model theModel){
		
		//create model attribute to bind form data
		UsuarioImpl theUsuario = new UsuarioImpl();
		theModel.addAttribute("usuario", theUsuario); //name,value
		
		return "aluno-form";
	}
	

	//Metodo em desenvolvimento
	@PostMapping("/newAluno")
	public String saveUsuario(@ModelAttribute("aluno") AlunoImpl theAluno){
		Date current_date = new Date();
		
		theAluno.setAtivo_usuario("1");
		theAluno.setDt_insert_usuario(current_date);
		theAluno.setDt_last_update_usuario(current_date);
		theAluno.setTipo_usuario("ALUNO");
		theAluno.setData_nascimento_usuario(current_date);
		
		//save the aluno
		alunoService.saveUsuario(theAluno);
		
		return "redirect:../adm/listaAlunos";
	}
	
}
