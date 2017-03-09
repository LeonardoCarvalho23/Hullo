package com.hullo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@PostMapping("/getUsuario")
	public String loginUsuario(@ModelAttribute("usuario") UsuarioImpl theUsuario, ModelMap modelMap){
		// Acima, acrescentei o "ModelMap model" para poder repassar a mensagem de erro quando o login falha
		// Pega o Model e retira os parâmetros para variáveis
		String email = theUsuario.getEmail_usuario();
		String senha = theUsuario.getSenha_usuario();
		
		// busca o usuário com base nos dados retirados acima
		Usuario loggedUser = usuarioService.getUsuario(email, senha);
		if (loggedUser == null){
			// erro de login
			final String errorMessage = 
					"<div class='alert alert-danger fade in'> <a href='#' class='close' data-dismiss='alert'>&times;</a> Usuário ou senha incorretos. </div>"; 
		    modelMap.addAttribute("errorMessage", errorMessage);
			return "usuario-login";
		} else {
			// login feito com sucesso
			return "pos-login";
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
