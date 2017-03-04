package com.hullo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hullo.entity.UsuarioImpl;
import com.hullo.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class AlunoController {
	
	@Autowired
	@Qualifier("alunoServiceImpl")
	private UsuarioService alunoService;
	
	@GetMapping("/listaAlunos")
	public String listarUsuarios(Model theModel){
		
		//get usuarios from the DAO
		List<UsuarioImpl> theUsuarios = alunoService.getUsuarios();
		
		//add the usuarios to the model
		theModel.addAttribute("usuarios", theUsuarios); //name and value
		
		return "list-aluno";
	}
	
	@GetMapping("/showFormNewAluno")
	public String showFormNovoUsuario(Model theModel){
		
		//create model attribute to bind form data
		UsuarioImpl theUsuario = new UsuarioImpl();
		theModel.addAttribute("usuario", theUsuario); //name,value
		
		return "aluno-form";
	}
	
	/*
	Metodo em desenvolvimento
  @PostMapping("/aluno/newAluno")
	public String saveUsuario(@ModelAttribute("usuario") Usuario theUsuario){
		theUsuario.setAtivo_usuario("1");
		theUsuario.setDt_insert_usuario("now()");
		
		//save the usuario
		alunoService.saveUsuario(theUsuario);
		
		return "redirect:/aluno/lista";
	}*/
	
}
