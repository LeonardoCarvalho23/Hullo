package com.hullo.controller;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.hullo.entity.AulaImpl;
import com.hullo.entity.ModuloImpl;
import com.hullo.entity.ModuloModel;
import com.hullo.entity.ProfessorImpl;
import com.hullo.entity.ProfessorModel;
import com.hullo.service.AulaServiceImpl;

@Controller
@RequestMapping("/adm/modulos")
public class AulaController {

//	@Autowired
	private AulaServiceImpl aulaService;
	
	// abrir pagina para casdatrar nova aula
		@GetMapping("/formAula")
		//public String formNovaAula(@RequestParam("id_modulo") int id_modulo, Model theModel) {
		public String formNovaAula(Model theModel) {
			// crio o objeto que esta dentro desse model
			AulaImpl aula = new AulaImpl();
			
			//aula.setId_modulo_aula(id_modulo);

			// coloco essa aula no model que vai para a pagina
			theModel.addAttribute("aula", aula);

			return "aula-form";
		}
		
		// gravar novo modulo
		@PostMapping("/newAula")
		public String saveUsuario(@ModelAttribute("aula") ModuloImpl model, ModelMap modelMap){

			Date current_date = new Date();
			ModuloImpl aula = model;
			/*
			aula.setAtivo_aula(false);
			aula.setDt_insert_aula(current_date);
			aula.setDt_last_update_aula(current_date);*/
			
			//aulaService.saveAula(aula);
			
			return "aula-form";
		}
	
}
