package com.hullo.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hullo.entity.AlunoImpl;
import com.hullo.entity.AulaImpl;
import com.hullo.entity.AulaModel;
import com.hullo.entity.ModuloImpl;
import com.hullo.entity.ModuloModel;
import com.hullo.entity.ProfessorImpl;
import com.hullo.service.AulaServiceImpl;

@Controller
@RequestMapping("/modulos")
public class AulaController {

	@Autowired
	private AulaServiceImpl aulaService;

	@Autowired
	private ModuloController moduloController;

	// abrir pagina para casdatrar nova aula
	@PostMapping("/formAula")
	public String formNovaAula(@RequestParam("modulo.id_modulo") int id_modulo, Model theModel) {

		// crio o objeto que estara dentro desse model
		AulaImpl aula = new AulaImpl();

		// adiciono o modulo da aula
		aula.setId_modulo_aula(id_modulo);

		// coloco essa aula no model que vai para a pagina
		theModel.addAttribute("aula", aula);

		// retorna pagina de cadastro de aulas
		return "aula-form";
	}

	// gravar nova aula
	@PostMapping("/newAula")
	public String saveAula(@ModelAttribute("aula") AulaImpl model, Model theModel, ModelMap modelMap) {

		// data atual para gravar no insert
		Date current_date = new Date();

		// pego aula que vem do model
		AulaImpl aula = model;

		// tem que adicionar validacao de numero/indice

		// adiciono algumas infos
		aula.setAtivo_aula(true);
		aula.setDt_insert_aula(current_date);
		aula.setDt_last_update_aula(current_date);

		// salvo a aula
		aulaService.saveAula(aula);

		// direciona para o modulo que acaba de gravar a aula
		return moduloController.showModulo(aula.getId_modulo_aula(), theModel, modelMap);
	}

	// abrir detalhes da aula e para fazer update
	@GetMapping("/showAula")
	public String showAula(@RequestParam("id_aula") int id_aula, Model theModel, ModelMap modelMap) {
		
		// get aula do banco
		AulaImpl aula = aulaService.getAula(id_aula);

		//adiciona ao model
		theModel.addAttribute("aula", aula);

		// retorna pagina que exibe a aula
		return "view-aula";
		

	}	
		
	// metodo para abrir pagina de update da aula
		@RequestMapping("/showFormUpdateAula")
		public String showFormUpdateAula(@ModelAttribute("aula") AulaImpl theAula) {
			return "aula-update-form";
		}
	
	// metodo para atualizar aula
		@RequestMapping("/updateAula")
		public String updateAula(@ModelAttribute("aula") AulaImpl theAula, ModelMap modelMap) {

				Date current_date = new Date();
				theAula.setDt_last_update_aula(current_date);
				aulaService.updateAula(theAula);
				return "view-aula";				
			
		}	
		

}
