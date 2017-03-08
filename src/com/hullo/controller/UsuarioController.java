package com.hullo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hullo.entity.Usuario;
import com.hullo.entity.UsuarioImpl;
import com.hullo.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
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
	
	@PostMapping("buscaUsuario")
	public String buscaUsuario(@ModelAttribute("usuario") Usuario oUsuario){
		usuarioService.buscaUsuario((UsuarioImpl) oUsuario);
		return "pos-login";
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
