package com.hullo.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hullo.entity.CidadeImpl;
import com.hullo.entity.EstadoImpl;
import com.hullo.entity.ProfessorImpl;
import com.hullo.entity.ProfessorModel;
import com.hullo.service.CidadeServiceImpl;
import com.hullo.service.EstadoServiceImpl;
import com.hullo.service.UsuarioService;

@Controller
@RequestMapping("/professor")
public class ProfessorController {

	@Autowired
	@Qualifier("professorServiceImpl")
	private UsuarioService<ProfessorImpl> professorService;

	@Autowired
	private EstadoServiceImpl estadoService;

	@Autowired
	private CidadeServiceImpl cidadeService;

	// --Abaixo, dados para disparo de email
	@Autowired
	private MailSender mailSender;

	@Autowired
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
	// -- fim do código para email

	@GetMapping("/formProfessor")
	public String showFormNovoUsuario(Model theModel) {

		// create model attribute to bind form data
		ProfessorImpl theProfessor = new ProfessorImpl();

		List<EstadoImpl> estados = estadoService.getEstados();

		ProfessorModel professorModel = new ProfessorModel();

		professorModel.setEstado(estados);
		professorModel.setUsuario(theProfessor);

		theModel.addAttribute("professorModel", professorModel); // name,value

		return "professor-form";
	}

	// para gravar novo professor
	@PostMapping("/newProfessor")
	public String saveUsuario(@ModelAttribute("professorModel") ProfessorModel professorModel, ModelMap modelMap)
			throws JsonParseException, JsonMappingException, IOException {

		Date current_date = new Date();

		ProfessorImpl theProfessor = professorModel.getUsuario();

		ObjectMapper mapper = new ObjectMapper();
		CidadeImpl cidade = mapper.readValue(professorModel.getCidade(), CidadeImpl.class);

		// seta o id da cidade no usuario
		theProfessor.setCidade(cidade.getId_Cidade());

		// validar se ja existe usuario com esse email ou senha
		ProfessorImpl validaProfessor = professorService.getUsuario(theProfessor.getEmail_usuario(),
				theProfessor.getCpf_usuario());

		// se retornar que existe, exibe mensagem de erro

		if (validaProfessor != null) {

			// exibe mensagem de erro
			final String errorMessage = "<div class='alert alert-danger fade in'> <a href='#' class='close' data-dismiss='alert'>&times;</a> Ja exite usuario com esses dados </div>";
			modelMap.addAttribute("errorMessage", errorMessage);

			return "professor-form";

			// se nao existe professor com esses dados, cria o ususario
		} else {
			theProfessor.setAtivo_usuario("1");
			theProfessor.setDt_insert_usuario(current_date);
			theProfessor.setDt_last_update_usuario(current_date);

			// save the professor
			professorService.saveUsuario(theProfessor);

			// Envia email de confirmação
			SimpleMailMessage msg = new SimpleMailMessage();

			msg.setTo(theProfessor.getEmail_usuario());
			msg.setFrom("noreply@hullo.com.br");
			msg.setSubject("Confirmação de cadastro");
			msg.setText(theProfessor.getNome_usuario() + ", seu cadastro de professor foi realizado com sucesso.");

			try {
				this.mailSender.send(msg);
				// System.out.println(msg.toString());
			} catch (MailException e) {
				// TODO Auto-generated catch block
			}

			// envia mensagem de cadastro com sucesso
			final String okNewProfessorMessage = "<div class='alert alert-success fade in'> <a href='#' class='close' data-dismiss='alert'>&times;</a>Professor cadastrado com sucesso. Faça login. </div>";
			modelMap.addAttribute("okNewProfessorMessage", okNewProfessorMessage);
			return "redirect:/usuario/usuarioLogin";
		}
	}

	@RequestMapping(value = "/formProfessor/cidades", method = RequestMethod.POST)
	public @ResponseBody List<CidadeImpl> obterCidade(@RequestBody EstadoImpl estado) {

		// List<CidadeImpl> cidade = cidadeService.getCidades();

		List<CidadeImpl> cidade = cidadeService.obterCidadesDoEstado(estado);

		return cidade;
	}

	// metodo para abrir pagina de perfil professor
	@PostMapping("/showPerfilProfessor")
	public String showPerfilProfessor(@RequestParam("id_usuario") int id_usuario, Model theModel) {

		// este método depende de eu colocar o id do usuario no link perfil, no
		// jsp
		// get professor form database
		ProfessorImpl theUsuario = professorService.getUsuario(id_usuario);

		// adiciona o usuario ao modelo
		theModel.addAttribute("usuario", theUsuario);

		// retorna
		return "perfil-professor";

	}

	// metodo para abrir pagina de update do professor
	@PostMapping("/showFormUpdateProfessor")
	public String showFormUpdateProfessor(@RequestParam("id_usuario") int id_usuario, Model theModel) {

		// este método depende de eu colocar o id do usuario no link
		// "atualizar", no jsp
		// get aluno form database
		ProfessorImpl theUsuario = professorService.getUsuario(id_usuario);

		// adiciona o usuario ao modelo
		theModel.addAttribute("usuario", theUsuario);

		// retorna
		return "professor-update-form";
	}

	// metodo para atualizar professor
	@PostMapping("/updateProfessor")
	public String updateProfessor(@ModelAttribute("usuario") ProfessorImpl theUsuario, Model theModel,
			ModelMap modelMap) {
		Date current_date = new Date();

		theUsuario.setDt_last_update_usuario(current_date);

		// validar se ja existe usuario com esse email
		ProfessorImpl validaProfessor = professorService.validaUsuario(theUsuario.getEmail_usuario(),
				theUsuario.getId_usuario());
		System.out.println("validou email e voltou para controller");

		if (validaProfessor != null) {

			// exibe mensagem de erro
			final String errorMessage = "<div class='alert alert-danger fade in'> <a href='#' class='close' data-dismiss='alert'>&times;</a> Existe outro usuario com esse email </div>";
			modelMap.addAttribute("errorMessage", errorMessage);

			return "professor-update-form";
		} else {
			System.out.println("controller chegou null");
			professorService.updateUsuario(theUsuario);

			theModel.addAttribute(theUsuario);

			return "home-professor";
		}
	}

	// metodo para inativar professor
	@PostMapping("/inactivateProfessor")
	public String inactivateProfessor(@ModelAttribute("usuario") ProfessorImpl theUsuario, Model theModel) {
		Date current_date = new Date();

		theUsuario.setDt_last_update_usuario(current_date);

		professorService.inactivateUsuario(theUsuario);

		theModel.addAttribute(theUsuario);
		System.out.println("entrou aqui inativar");
		return "redirect:/usuario/usuarioLogin";

	}
}
