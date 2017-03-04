package com.hullo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hullo.entity.Usuario;
import com.hullo.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	/*inject the usuario DAO, it will look for an implementation of this interface
	@Autowired
	private UsuarioDAO usuarioDAO;*/
	
	//with the service, inject the service here
	@Autowired
	@Qualifier("usuarioServiceImpl")
	private UsuarioService usuarioService;
		
	@GetMapping("/showFormNewUsuario")
	public String showFormNovoUsuario(Model theModel){
		
		//create model attribute to bind form data
		Usuario theUsuario = new Usuario();
		theModel.addAttribute("usuario", theUsuario); //name,value
		
		return "usuario-form";
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
