package com.hullo.controller;

import java.util.Date;
import java.util.List;

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
		boolean validaAula = aulaService.validaAula(model.getIndice_aula(), model.getNumero_aula());
		
		if (validaAula) {

			// exibe mensagem de erro
			final String errorMessage = "<div class='alert alert-danger fade in'> <a href='#' class='close' data-dismiss='alert'>&times;</a> Existe outra aula com esse Numero e Indice </div>";
			modelMap.addAttribute("errorMessage", errorMessage);
			return "aula-form";
			
		} else {

			// adiciono algumas infos
			aula.setAtivo_aula(true);
			aula.setDt_insert_aula(current_date);
			aula.setDt_last_update_aula(current_date);
	
			// salvo a aula
			aulaService.saveAula(aula);
	
			// direciona para o modulo que acaba de gravar a aula
			return moduloController.showModulo(aula.getId_modulo_aula(), theModel, modelMap);
		}
	}

	// abrir detalhes da aula e para fazer update
	@GetMapping("/showAula")
	public String showAula(@RequestParam("id_aula") int id_aula, Model theModel, ModelMap modelMap) {
		
		// get aula do banco
		AulaImpl aula = aulaService.getAula(id_aula);
		System.out.println("valor do id_aula"+ aula.getId_aula());	
		//adiciona ao model
		theModel.addAttribute("aula", aula);

		// retorna pagina que exibe a aula
		return "aula-update-form";
		

	}	
		
	// metodo para abrir pagina de update da aula
		/*@RequestMapping("/showFormUpdateAula")
		public String showFormUpdateAula(@RequestParam("id_aula") int id_aula, Model theModel, ModelMap modelMap) {
			// get aula do banco
			theModel.get
			AulaImpl aula = aulaService.getAula(id_aula);

			//adiciona ao model
			theModel.addAttribute("aula", aula);

			
			System.out.println("valor do id_aula"+ aula.getId_aula());
			System.out.println("valor do id_modulo"+ aula.getId_modulo_aula());
			
			return "aula-update-form";
		}*/
	
	
		
		// metodo para atualizar aula
		@RequestMapping("/updateAula")
		public String updateAula(@ModelAttribute("aula") AulaImpl model, Model theModel, ModelMap modelMap) {

			
			// pego aula que vem do model
			AulaImpl aula = model;
			
			// // validar se ja existe aula com esse id
			boolean validaAula = aulaService.validaAula(model.getIndice_aula(), model.getNumero_aula());
			
			if (validaAula) {

				// exibe mensagem de erro
				final String errorMessage = "<div class='alert alert-danger fade in'> <a href='#' class='close' data-dismiss='alert'>&times;</a> Existe outra aula com esse Numero e Indice </div>";
				modelMap.addAttribute("errorMessage", errorMessage);
				return "aula-update-form";
				
			} else {
				
				// Atualiza a sessão com os dados inseridos no formulario
				Date current_date = new Date();
				model.setDt_last_update_aula(current_date);
				aulaService.updateAula(model);
				
				return moduloController.showModulo(aula.getId_modulo_aula(), theModel, modelMap);
			}
		}
		

}
