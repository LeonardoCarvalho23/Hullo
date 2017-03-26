package com.hullo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hullo.entity.ModuloImpl;
import com.hullo.service.ModuloService;

@Controller
@RequestMapping("/adm/modulos")
public class ModuloController {
	
	@Autowired
	private ModuloService moduloService;

	@PostMapping("/lista")
	public String listarModulos(Model theModel){
		
		//get modulos from the DAO
		List<ModuloImpl> modulos = moduloService.getModulos();
		
		//add the usuarios to the model
		theModel.addAttribute("modulos", modulos); //name and value
		
		return "lista-modulos";
	}
}
