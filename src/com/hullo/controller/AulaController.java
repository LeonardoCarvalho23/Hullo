package com.hullo.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hullo.entity.AulaImpl;

import com.hullo.service.AulaServiceImpl;

@Controller
@RequestMapping("/modulos")
public class AulaController {
	
	@Autowired
	private AulaServiceImpl aulaService;

	// abrir pagina para casdatrar nova aula
	@PostMapping("/formAula")
	public String formNovaAula(@RequestParam("modulo.id_modulo") int id_modulo, Model theModel) {

		// crio o objeto que estara dentro desse model
		AulaImpl aula = new AulaImpl();

		// adiciono o modulo da aula
		aula.setId_modulo_aula(id_modulo);

		// coloco essa aula no model que vai para a pagina
		theModel.addAttribute("aula", aula);

		//retorna pagina de cadastro de aulas
		return "aula-form";
	}

	// gravar nova aula
	@PostMapping("/newAula")
	public String saveAula(@ModelAttribute("aula") AulaImpl model) {

		//data atual para gravar no insert
		Date current_date = new Date();
		
		//pego aula que vem do model
		AulaImpl aula = model;

		//tem que adicionar validacao de numero/indice
		
		
		
		//adiciono algumas infos
		aula.setAtivo_aula(true);
		aula.setDt_insert_aula(current_date);
		aula.setDt_last_update_aula(current_date);

		//salvo a aula
		aulaService.saveAula(aula);

		// direcionamento provisorio - tem que mudar para exibir o modulo da aula
		return "lista-modulos";
	}

}
