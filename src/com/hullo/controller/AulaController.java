
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

import com.hullo.entity.AulaImpl;
import com.hullo.service.AulaServiceImpl;

/**
* classe para controlar o que é exibido nas telas de home e CRUD do aluno
* @author Hullo Team 
* @version 1.0
 */

@Controller
@RequestMapping("/modulos")
public class AulaController {

	@Autowired
	private AulaServiceImpl aulaService;

	@Autowired
	private ModuloController moduloController;

	/**
	 * abrir pagina para casdatrar nova aula
	 * @param id_modulo em que vou inserir a aula
	 * @param theModel
	 * @return pagina de cadastro de aula
	 */
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

	/**
	 * Gravar nova aula
	 * @param model
	 * @param theModel
	 * @param modelMap
	 * @return
	 */
	@PostMapping("/newAula")
	public String saveAula(@ModelAttribute("aula") AulaImpl model, 
			Model theModel, ModelMap modelMap) {

		// data atual para gravar no insert
		Date current_date = new Date();

		// pego aula que vem do model
		AulaImpl aula = model;

		// tem que adicionar validacao de numero/indice
		AulaImpl validaAula = aulaService.validaAula(model.getIndice_aula(), model.getNumero_aula(),
				model.getId_modulo_aula(), model.getId_aula());

		if (validaAula != null) {

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

	/**
	 * Abrir detalhes da aula e para fazer update
	 * @param id_aula
	 * @param theModel
	 * @param modelMap
	 * @return
	 */
	@GetMapping("/showAula")
	public String showAula(@RequestParam("id_aula") int id_aula, Model theModel, ModelMap modelMap) {

		// get aula do banco
		AulaImpl aula = aulaService.getAula(id_aula);

		// adiciona ao model
		theModel.addAttribute("aula", aula);

		// retorna pagina que exibe a aula
		return "aula-update-form";
		// return "view-aula";

	}

	/**
	 * Metodo para abrir pagina de update da aula
	 * @param session
	 * @param model
	 * @param theModel
	 * @return
	 */
	@RequestMapping("/showFormUpdateAula")
	public String showFormUpdateAula(HttpSession session, AulaImpl model, Model theModel) {
		AulaImpl aula = model;
		theModel.addAttribute("aula", aula);

		return "aula-update-form";
	}

	/**
	 * Metodo para atualizar aula
	 * @param model
	 * @param theModel
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/updateAula")
	public String updateAula(@ModelAttribute("aula") AulaImpl model, Model theModel, ModelMap modelMap) {
		System.out.println("metodo errado");
		// pego aula que vem do model
		AulaImpl aula = model;

		// // validar se ja existe aula com esse id
		AulaImpl validaAula = aulaService.validaAula(model.getIndice_aula(), model.getNumero_aula(),
				model.getId_modulo_aula(), model.getId_aula());

		if (validaAula != null) {

			// exibe mensagem de erro
			final String errorMessage = "<div class='alert alert-danger fade in'> <a href='#' class='close' data-dismiss='alert'>&times;</a> Existe outra aula com esse Numero e Indice </div>";
			modelMap.addAttribute("errorMessage", errorMessage);
			return "aula-update-form";

		} else {

			// Atualiza a sessão com os dados inseridos no formulario
			Date current_date = new Date();
			model.setDt_last_update_aula(current_date);
			model.setAtivo_aula(true);
			aulaService.updateAula(model);

			return moduloController.showModulo(aula.getId_modulo_aula(), theModel, modelMap);
		}
	}

	/**
	 * Metodo para inativar aula
	 * @param aula
	 * @param theModel
	 * @return
	 */
	@PostMapping("/deleteAula")
	public String deleteAula(@ModelAttribute("aula") AulaImpl aula, Model theModel) {

		int id_modulo = aula.getId_modulo_aula();
		
		aulaService.deleteAula(aula);

		ModelMap modelMap = new ModelMap();
		
		return moduloController.showModulo(id_modulo, theModel, modelMap);

	}

}
