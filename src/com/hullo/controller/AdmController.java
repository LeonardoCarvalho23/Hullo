package com.hullo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hullo.entity.Usuario;
import com.hullo.service.UsuarioService;

@Controller
@RequestMapping("/adm")
public class AdmController {
	
	@Autowired
	@Qualifier("professorServiceImpl")
	private UsuarioService professorService;
	
	@Autowired
	@Qualifier("alunoServiceImpl")
	private UsuarioService alunoService;
	
	@Autowired
	@Qualifier("usuarioServiceImpl")
	private UsuarioService usuarioService;
	
	@GetMapping("/main")
	public String showMain(Model theModel){
		return "main";
	}
	
	@GetMapping("/listaUsuarios")
	public String listarUsuarios(Model theModel){
		
		//get usuarios from the DAO
		List<Usuario> theUsuarios = usuarioService.getUsuarios();
		
		//add the usuarios to the model
		theModel.addAttribute("usuarios", theUsuarios); //name and value
		
		return "lista-usuarios";
	}
	
	@GetMapping("/listaAlunos")
	public String listarAlunos(Model theModel){
		
		//get usuarios from the DAO
		List<Usuario> theUsuarios = alunoService.getUsuarios();
		
		//add the usuarios to the model
		theModel.addAttribute("usuarios", theUsuarios); //name and value
		
		return "list-aluno";
	}

	@GetMapping("/listaProfessores")
	public String listarProfessores(Model theModel){
		
		//get usuarios from the DAO
		List<Usuario> theUsuarios = professorService.getUsuarios();
		
		//add the usuarios to the model
		theModel.addAttribute("usuarios", theUsuarios); //name and value
		
		return "list-professor";
	}

}
