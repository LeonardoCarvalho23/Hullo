package com.hullo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hullo.entity.UsuarioImpl;
import com.hullo.service.LoginService;
import com.hullo.service.UsuarioService;

@Controller
@RequestMapping("/")
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@GetMapping("/showLoginForm")
	public String showLoginForm(Model theModel){
		UsuarioImpl theUsuario = new UsuarioImpl();
		theModel.addAttribute("usuario", theUsuario);
		return "login-form";
	}
	
	@PostMapping("/performLogin")
	public String performLogin(@ModelAttribute("usuario") UsuarioImpl theUsuario){
		// fazer procedimentos de checagem
		loginService.checkUser(theUsuario);
		
		return "login-ok";
	}
	
	
}
