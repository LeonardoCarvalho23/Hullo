package com.hullo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hullo.entity.AulaImpl;

@Controller
@RequestMapping("/adm/modulos")
public class AulaController {

//	@Autowired
//	private AulaServiceImpl aulaService;
	
	// abrir pagina para casdatrar nova aula
		@GetMapping("/formAula")
		public String formNovaAula(@RequestParam("id_modulo") int id_modulo, Model theModel) {

			// crio o objeto que esta dentro desse model
			AulaImpl aula = new AulaImpl();
			
			aula.setId_modulo_aula(id_modulo);

			// coloco essa aula no model que vai para a pagina
			theModel.addAttribute("aula", aula);

			return "aula-form";
		}
	
}
