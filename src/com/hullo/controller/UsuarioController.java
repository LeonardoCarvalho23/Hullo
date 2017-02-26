package com.hullo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hullo.dao.UsuarioDAO;
import com.hullo.entity.Usuario;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	//inject the usuario DAO, it will look for an implementation of this interface
	@Autowired
	private UsuarioDAO usuarioDAO;

	@RequestMapping("/lista")
	public String listarUsuarios(Model theModel){
		
		//get usuarios from the DAO
		List<Usuario> theUsuarios = usuarioDAO.getUsuarios();
		
		//add the usuarios to the model
		theModel.addAttribute("usuarios", theUsuarios); //name and value
		
		return "lista-usuarios";
	}
}
