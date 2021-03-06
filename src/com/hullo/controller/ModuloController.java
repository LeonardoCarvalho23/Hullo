package com.hullo.controller;

import java.util.Date;
import java.util.List;

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
import com.hullo.service.AulaServiceImpl;
import com.hullo.service.ModuloServiceImpl;

@Controller
@RequestMapping("/modulos")
public class ModuloController {

	@Autowired
	private ModuloServiceImpl moduloService;

	@Autowired
	private AulaServiceImpl aulaService;

	// lista de todos os modulos cadastrados
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

	// gravar novo modulo
	@PostMapping("/newModulo")
	public String saveModulo(@ModelAttribute("modulo") ModuloImpl model, ModelMap modelMap, Model newModel) {
		Date current_date = new Date();

		// pega o objeto modulo do model
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

		// abre a pagina de edicao de modulo onde pode adicionar as aulas
		return showModulo(modulo.getId_modulo(), newModel, modelMap);
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

	// abrir detalhes do modulo e para fazer update
	@GetMapping("/showModulo")
	public String showModulo(@RequestParam("id_modulo") int id_modulo, Model theModel, ModelMap modelMap) {

		// get modulo do banco
		ModuloImpl modulo = moduloService.getModulo(id_modulo);

		// cria ModuloModel, objeto com modulo e lista de aulas
		ModuloModel moduloModel = new ModuloModel();

		// busca as aulas desse modulo no banco	
		List<AulaImpl> listaAulas = aulaService.getAulas(id_modulo);

		// adiciona os objetos ao modeloModel
		moduloModel.setModulo(modulo);
		moduloModel.setListaAulas(listaAulas);

		theModel.addAttribute("moduloModel", moduloModel);

		// retorna pagina de update de modulo, com lista de aulas ja cadastradas
		return "modulo-update-form";

	}

	// metodo para atualizar modulo
	@RequestMapping("/updateModulo")
	public String updateModulo(@ModelAttribute("moduloModel") ModuloModel moduloModel, ModelMap modelMap,
			Model newModel) {

		// pego modulo do objeto moduloModel
		ModuloImpl modulo = moduloModel.getModulo();

		// validar se ja existe modulo com esse indice
		boolean validaModulo = moduloService.validaModulo(moduloModel.getModulo().getIndice_modulo(),
				moduloModel.getModulo().getId_modulo());

		if (validaModulo) {

			// exibe mensagem de erro
			String errorMessage = "<div class='alert alert-danger fade in'> <a href='#' class='close' data-dismiss='alert'>&times;</a> Existe outro modulo com esse �ndice </div>";
			modelMap.addAttribute("errorMessage", errorMessage);
			return showModulo(modulo.getId_modulo(), newModel, modelMap);

		} else {

			// Atualiza com os dados inseridos no formulario
			Date current_date = new Date();
			modulo.setDt_last_update_modulo(current_date);

			// boolean para validar se tem 5 aulas base
			boolean validaAulas = true;

			// se eu quero ativar o modulo, tenho que validar a aula
			if (modulo.getAtivo_modulo()) {
				validaAulas = validaAulasBase(aulaService.getAulas(modulo.getId_modulo()));
			}

			// se nao quero ativar modulo, vai direto salvar
			// e de quiser ativar, ja validou se tem 5 aulas base
			if (validaAulas) {

				moduloService.updateModulo(modulo);

				String errorMessage = "<div class='alert alert-success fade in'> <a href='#' class='close' data-dismiss='alert'>&times;</a> Modulo salvo com sucesso</div>";
				modelMap.addAttribute("errorMessage", errorMessage);

				return showModulo(modulo.getId_modulo(), newModel, modelMap);
			}

			// se quis ativar modulo, mas o valida aulas retornou False
			else {
				String errorMessage = "<div class='alert alert-danger fade in'> <a href='#' class='close' data-dismiss='alert'>&times;</a>Para ativar o m�dulo, primeiro cadastre as 5 aulas base</div>";
				modelMap.addAttribute("errorMessage", errorMessage);

				return showModulo(modulo.getId_modulo(), newModel, modelMap);
			}
		}
	}

	// validacao se tem 5 aulas base no modulo
	private boolean validaAulasBase(List<AulaImpl> listaAulas) {
		boolean um = false;
		boolean dois = false;
		boolean tres = false;
		boolean quatro = false;
		boolean cinco = false;

		for (AulaImpl aula : listaAulas) {
			if (aula.getNumero_aula() == 1) {
				System.out.println("achou 1");
				um = true;
			}
			if (aula.getNumero_aula() == 2) {
				System.out.println("achou 2");
				dois = true;
			}
			if (aula.getNumero_aula() == 3)
				tres = true;
			if (aula.getNumero_aula() == 4)
				quatro = true;
			if (aula.getNumero_aula() == 5)
				cinco = true;
		}

		System.out.println("aula 5 = " + cinco);
		if (um == dois && dois == tres && tres == quatro && quatro == cinco && cinco == true) {
			return true;
		}

		return false;
	}

	//para excluir modulo e todas as suas aulas
	@PostMapping("/deleteModulo")
	public String formNovaAula(@RequestParam("modulo.id_modulo") int id_modulo, Model theModel) {
		
		aulaService.deleteAulasModulo(id_modulo);
		
		moduloService.deleteModulo(id_modulo);
		
		return listarModulos(theModel);
	}
}
