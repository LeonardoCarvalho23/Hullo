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

import com.hullo.entity.AulaImpl;
import com.hullo.entity.ModuloImpl;
import com.hullo.entity.ModuloModel;
import com.hullo.entity.ProfessorImpl;
import com.hullo.service.ModuloServiceImpl;

@Controller
@RequestMapping("/adm/modulos")
public class ModuloController {

	@Autowired
	private ModuloServiceImpl moduloService;

	@GetMapping("/lista")
	public String listarModulos(Model theModel) {

		// get modulos from the DAO
		List<ModuloImpl> modulos = moduloService.getModulos();

		// add the usuarios to the model
		theModel.addAttribute("modulos", modulos); // name and value

		return "lista-modulos";
	}

	// abrir pagina para casdatrar novo modulo
	@GetMapping("/formModulo")
	public String formNovoModulo(Model theModel) {

		// crio o objeto que esta dentro desse model
		ModuloImpl modulo = new ModuloImpl();

		// coloco esse modulo no que vai para a pagina
		theModel.addAttribute("modulo", modulo);

		return "modulo-form";
	}

	// gravrar novo modulo
	@PostMapping("/newModulo")
	public String saveModulo(@ModelAttribute("modulo") ModuloImpl model, ModelMap modelMap) {
		Date current_date = new Date();

		// pega os objetos do ModuloModel
		ModuloImpl modulo = model;

		// validar se ja existe modulo com esse indice
		if (moduloService.validaModulo(modulo.getIndice_modulo())) {

			// exibe mensagem de erro
			final String errorMessage = "<div class='alert alert-danger fade in'> <a href='#' class='close' data-dismiss='alert'>&times;</a> Ja exite modulo com esse �ndice </div>";
			modelMap.addAttribute("errorMessage", errorMessage);

			return "modulo-form";
		}

		// adiciona infos ao modulo
		modulo.setAtivo_modulo(false);
		modulo.setDt_insert_modulo(current_date);
		modulo.setDt_last_update_modulo(current_date);

		// salva o modulo
		moduloService.saveModulo(modulo);

		return "redirect:/adm/modulos/lista";
	}

	// buscar no banco por nome
	@PostMapping("/search")
	public String searchCustomers(@RequestParam("nomeBusca") String nomeBusca, Model theModel) {

		// search customers from the service
		List<ModuloImpl> modulos = moduloService.getModulos(nomeBusca);

		// add the customers to the model
		theModel.addAttribute("modulos", modulos);

		return "lista-modulos";
	}

	// abrir detalhes do modulo
	@GetMapping("/showModulo")
	public String showModulo(@RequestParam("id_modulo") int id_modulo, Model theModel) {

		// get modulo do banco
		ModuloImpl modulo = moduloService.getModulo(id_modulo);
		
		//cria ModuloModel, objeto com modulo e lista de aulas
		ModuloModel moduloModel = new ModuloModel();
		
		//aqui vai entrar o metodo que busca as aulas desse modulo no banco
		List<AulaImpl> listaAulas = null;
		
		// adiciona os objetos ao modeloModel
		moduloModel.setModulo(modulo);
		moduloModel.setListaAulas(listaAulas);
		
		theModel.addAttribute("moduloModel", moduloModel);

		// retorna
		return "view-modulo";

	}
	
	//mostrar form de update do modulo
	@PostMapping("/formUpdateModulo")
	public String showFormUpdateModulo(@RequestParam("id_modulo") int id_modulo, Model theModel) {
		
		ModuloImpl modulo = moduloService.getModulo(id_modulo);
		
		theModel.addAttribute(modulo);
		
		return "modulo-update-form";
	}
}
