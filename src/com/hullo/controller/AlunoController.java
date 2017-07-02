package com.hullo.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.List;

import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.*;

import com.hullo.entity.AlunoImpl;
import com.hullo.entity.CidadeImpl;
import com.hullo.entity.EstadoImpl;
import com.hullo.entity.ProfessorImpl;
import com.hullo.entity.Usuario;
import com.hullo.entity.UsuarioImpl;
import com.hullo.entity.AlunoModel;
import com.hullo.entity.AulaImpl;
import com.hullo.entity.AulaRealizadaImpl;
import com.hullo.entity.AulaRealizadaModel;
import com.hullo.service.AulaRealizadaServiceImpl;
import com.hullo.service.AulaServiceImpl;
import com.hullo.service.CidadeServiceImpl;
import com.hullo.service.EstadoServiceImpl;
import com.hullo.service.UsuarioService;
import com.hullo.utility.DataConversion;

/**
 * classe para controlar o que � exibido na home e CRUD do aluno
 * 
 * @author Hullo Team
 * @version 1.0
 */
@Controller
@RequestMapping("/aluno")
@SessionAttributes("usuario")
public class AlunoController {

	@Autowired
	@Qualifier("alunoServiceImpl")
	private UsuarioService<AlunoImpl> alunoService;

	@Autowired
	@Qualifier("professorServiceImpl")
	private UsuarioService<ProfessorImpl> professorService;

	@Autowired
	private AulaRealizadaServiceImpl aulaRealizadaService;
	
	@Autowired
	private AulaServiceImpl aulaService;

	@Autowired
	private EstadoServiceImpl estadoService;
	// --Abaixo, dados para disparo de email
	@Autowired
	private MailSender mailSender;

	@Autowired
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
	// -- fim do c�digo para email

	@Autowired
	private CidadeServiceImpl cidadeService;

	/**
	 * pagina para cadastro de usuario
	 * 
	 * @param theModel
	 *            model para guardar dados digitados
	 * @return pagina para cadastro de usuario
	 */
	@GetMapping("/formAluno")
	public String showFormNovoUsuario(Model theModel) {

		AlunoImpl theAluno = new AlunoImpl();

		List<EstadoImpl> estados = estadoService.getEstados();

		AlunoModel alunoModel = new AlunoModel();

		alunoModel.setEstado(estados);
		alunoModel.setUsuario(theAluno);

		theModel.addAttribute("usuarioModel", alunoModel);

		return "aluno-form";
	}

	/**
	 * cria novo usuario aluno
	 * 
	 * @param usuarioModel
	 *            model com dados do AlunoImpl
	 * @param modelMap
	 *            para exibi��o de mensagens de erro
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @return pagina de login de usuario, se tiver erro abre a pagina atual
	 */
	// Metodo para gravar novo aluno
	@PostMapping("/formAluno")
	public String saveUsuario(@ModelAttribute("usuarioModel") AlunoModel usuarioModel, ModelMap modelMap)
			throws JsonParseException, JsonMappingException, IOException {
		LocalDateTime current_date = LocalDateTime.now();

		// pega o aluno do objeto alunoModel
		AlunoImpl theAluno = usuarioModel.getUsuario();

		// seta o id da cidade no usuario
		theAluno.setCidade(cidadeService.getCidade(theAluno.getCidade().getId_Cidade()));

		// validar se ja existe usuario com esse email ou senha, tanto professor
		// quanto aluno

		AlunoImpl validaAluno = alunoService.validaUsuario(theAluno.getEmail_usuario(), theAluno.getCpf_usuario());
		ProfessorImpl validaProfessor = professorService.getUsuario(theAluno.getEmail_usuario());

		//pega os estados
		List<EstadoImpl> estados = estadoService.getEstados();
		//coloca cidades na model caso ocorra erro
		usuarioModel.setEstado(estados);
		
		// valida idade
		if (calculaIdade(theAluno.getData_nascimento_usuario())) {

			if (isCpf(theAluno.getCpf_usuario())) {

				// se retornar que existe, exibe mensagem de erro
				if (validaAluno != null) {

					// exibe mensagem de erro
					final String errorMessage = "<div class='alert alert-danger fade in'> <a href='#' class='close' data-dismiss='alert'>&times;</a>J� existe aluno com o mesmo e-mail ou CPF.</div>";
					modelMap.addAttribute("errorMessage", errorMessage);

					return "aluno-form";

					// se houver professor, checa se a senha � igual. Se n�o
					// for, devolve erro
				} else if ((validaProfessor != null)
						&& !(validaProfessor.getSenha_usuario().equals(theAluno.getSenha_usuario()))) {

					// exibe mensagem de erro
					final String errorMessage = "<div class='alert alert-danger fade in'> <a href='#' class='close' data-dismiss='alert'>&times;</a>Senha deve ser a mesma do perfil de professor cadastrado.</div>";
					modelMap.addAttribute("errorMessage", errorMessage);

					return "aluno-form";
				}
				// se nao existe aluno com esses dados, cria o ususario
				else {
					theAluno.setTelefone_usuario("55" + theAluno.getTelefone_usuario());
					theAluno.setAtivo_usuario("1");
					theAluno.setDt_insert_usuario(current_date);
					theAluno.setDt_last_update_usuario(current_date);

					// save the aluno
					alunoService.saveUsuario(theAluno);

					// gera a primeira aula do aluno
					aulaRealizadaService.montarPrimeiraAulaRealizada(theAluno.getEmail_usuario());

					// Envia email de confirma��o
					SimpleMailMessage msg = new SimpleMailMessage();

					msg.setTo(theAluno.getEmail_usuario());
					msg.setFrom("noreply@hullo.com.br");
					msg.setSubject("Confirma��o de cadastro");
					msg.setText(theAluno.getNome_usuario() + ", seu cadastro de aluno foi realizado com sucesso.");

					try {
						this.mailSender.send(msg);
					} catch (MailException e) {
					}
					// envia mensagem de cadastro com sucesso
					final String okNewAlunoMessage = "<div class='alert alert-success fade in'> <a href='#' class='close' data-dismiss='alert'>&times;</a> Aluno cadastrado com sucesso. Fa�a login. </div>";
					modelMap.addAttribute("okNewAlunoMessage", okNewAlunoMessage);
					Usuario oUsuario = new UsuarioImpl();
					modelMap.addAttribute("usuario", oUsuario);
					return "redirect:/usuario/usuarioLogin";
				}
			} else {
				// exibe mensagem de erro
				final String errorMessage = "<div class='alert alert-danger fade in'> <a href='#' class='close' data-dismiss='alert'>&times;</a>CPF inv�lido.</div>";
				modelMap.addAttribute("errorMessage", errorMessage);

				return "aluno-form";
			}
		} else {
			// exibe mensagem de erro
			final String errorMessage = "<div class='alert alert-danger fade in'> <a href='#' class='close' data-dismiss='alert'>&times;</a>Idade m�nima 18 anos.</div>";
			modelMap.addAttribute("errorMessage", errorMessage);

			return "aluno-form";
		}

	}

	/**
	 * mapeamento para trazer lista de cidades
	 * 
	 * @param estado
	 *            estado dos quais desejo saber a cidade
	 * @return lista de cidades
	 */
	@RequestMapping(value = "formAluno/cidades", method = RequestMethod.POST)
	public @ResponseBody List<CidadeImpl> obterCidade(@RequestBody EstadoImpl estado) {

		List<CidadeImpl> cidade = cidadeService.obterCidadesDoEstado(estado);

		return cidade;
	}
	
	/**
	 * mapeamento para trazer lista de cidades
	 * 
	 * @param estado
	 *            estado dos quais desejo saber a cidade
	 * @return lista de cidades
	 */
	@RequestMapping(value = "showFormUpdateAluno/cidades", method = RequestMethod.POST)
	public @ResponseBody List<CidadeImpl> obterCidades(@RequestBody EstadoImpl estado) {

		List<CidadeImpl> cidade = cidadeService.obterCidadesDoEstado(estado);

		return cidade;
	}

	/**
	 * pagina perfil do aluno
	 * 
	 * @param session
	 *            session em que est� o objeto AlunoImpl
	 * @return pagina de perfil do aluno
	 */
	@RequestMapping("/showPerfilAluno")
	public String showPerfilAluno(HttpSession session) {
		return "perfil-aluno";
	}

	/**
	 * metodo para abrir a pagina de update do aluno
	 * 
	 * @param session
	 *            session em que est� o objeto AlunoImpl
	 * @return pagina de update do aluno
	 */
	@RequestMapping("/showFormUpdateAluno")
	public String showFormUpdateAluno(HttpSession session) {
		
		List<EstadoImpl> estados = estadoService.getEstados();
		
		session.setAttribute("estados", estados);
		
		return "aluno-update-form";
	}
	
	/**
	 * metodo para abrir a pagina de home do aluno
	 * 
	 * @param session
	 *            session em que est� o objeto AlunoImpl
	 * @return pagina de home do aluno
	 */
	@RequestMapping("/showHomeAluno")
	public String showHomeAluno(HttpSession session) {
		return "home-aluno";
	}

	/**
	 * metodo para atualizar aluno
	 * 
	 * @param session
	 *            session em que est� o objeto AlunoImpl
	 * @param theUsuario
	 *            model com dados do AlunoImpl
	 * @param modelMap
	 *            para exibi��o de mensagens de erro
	 * @return pagina de update do aluno
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@RequestMapping("/updateAluno")
	public String updateAluno(@ModelAttribute("usuario") AlunoImpl theUsuario, HttpSession session, ModelMap modelMap) throws JsonParseException, JsonMappingException, IOException {

		// validar se ja existe usuario com esse email
		AlunoImpl validaAluno = alunoService.validaUsuario(theUsuario.getEmail_usuario(), theUsuario.getId_usuario());

		theUsuario.setCidade(cidadeService.getCidade(theUsuario.getCidade().getId_Cidade()));
		
		// valida idade
		if (calculaIdade(theUsuario.getData_nascimento_usuario())) {

			if (validaAluno != null) {

				// exibe mensagem de erro
				final String errorMessage = "<div class='alert alert-danger fade in'> <a href='#' class='close' data-dismiss='alert'>&times;</a> Existe outro usuario com esse email </div>";
				modelMap.addAttribute("errorMessage", errorMessage);
				return "aluno-update-form";

			} else {

				// Atualiza a sess�o com os dados inseridos no formulario
				//Date current_date = new Date();
				LocalDateTime current_date = LocalDateTime.now();
				theUsuario.setDt_last_update_usuario(current_date);
				alunoService.updateUsuario(theUsuario);

				return "home-aluno";
			}
		} else {
			// exibe mensagem de erro
			final String errorMessage = "<div class='alert alert-danger fade in'> <a href='#' class='close' data-dismiss='alert'>&times;</a>Idade m�nima 18 anos.</div>";
			modelMap.addAttribute("errorMessage", errorMessage);

			return "aluno-update-form";
		}
	}

	/**
	 * metodo para inativar aluno
	 * 
	 * @param theUsuario
	 *            n�o sei porque est� aqui
	 * @param theModel
	 *            para exibi��o de mensagens de erro
	 * @return pagina de update do aluno
	 */
	@PostMapping("/inactivateAluno")
	public String inactivateAluno(@ModelAttribute("usuario") AlunoImpl theUsuario, Model theModel) {
		//Date current_date = new Date();
		LocalDateTime current_date = LocalDateTime.now();
		
		theUsuario.setDt_last_update_usuario(current_date);

		alunoService.inactivateUsuario(theUsuario);

		theModel.addAttribute(theUsuario);

		return "redirect:/usuario/usuarioLogin";

	}

	/**
	 * metodo para validar CPF digtado por usuario
	 * 
	 * @param cpf
	 *            cpf digitado por usuario
	 * @return se cpf existe
	 */
	public static boolean isCpf(String cpf) {
		cpf = cpf.replaceAll("[.-]", "");
		// considera-se erro CPF's formados por uma sequencia de numeros iguais
		if (cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222")
				|| cpf.equals("33333333333") || cpf.equals("44444444444") || cpf.equals("55555555555")
				|| cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888")
				|| cpf.equals("99999999999") || (cpf.length() != 11))
			return (false);

		char dig10, dig11;
		int sm, i, r, num, peso;

		// "try" - protege o codigo para eventuais erros de conversao de tipo
		// (int)
		try {
			// Calculo do 1o. Digito Verificador
			sm = 0;
			peso = 10;
			for (i = 0; i < 9; i++) {
				// converte o i-esimo caractere do CPF em um numero:
				// por exemplo, transforma o caractere '0' no inteiro 0
				// (48 eh a posicao de '0' na tabela ASCII)
				num = (int) (cpf.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig10 = '0';
			else
				dig10 = (char) (r + 48); // converte no respectivo caractere
											// numerico

			// Calculo do 2o. Digito Verificador
			sm = 0;
			peso = 11;
			for (i = 0; i < 10; i++) {
				num = (int) (cpf.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig11 = '0';
			else
				dig11 = (char) (r + 48);

			// Verifica se os digitos calculados conferem com os digitos
			// informados.
			if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10)))
				return (true);
			else
				return (false);
		} catch (InputMismatchException erro) {
			return (false);
		}
	}

	/**
	 * metodo para validar idade digtada por usuario
	 * 
	 * @param dataNasc
	 *            data de nascimento digitada por usuario
	 * @return se aluno e maior de idade
	 */
	public static boolean calculaIdade(java.util.Date dataNasc) {

		Calendar dataNascimento = Calendar.getInstance();
		dataNascimento.setTime(dataNasc);
		Calendar hoje = Calendar.getInstance();

		int idade = hoje.get(Calendar.YEAR) - dataNascimento.get(Calendar.YEAR);

		if (hoje.get(Calendar.MONTH) < dataNascimento.get(Calendar.MONTH)) {
			idade--;
		} else {
			if (hoje.get(Calendar.MONTH) == dataNascimento.get(Calendar.MONTH)
					&& hoje.get(Calendar.DAY_OF_MONTH) < dataNascimento.get(Calendar.DAY_OF_MONTH)) {
				idade--;
			}
		}

		System.out.println("idade " + idade);

		if (idade >= 18)
			return true;
		else
			return false;
	}

	/**
	 * metodo para listar aulas realizadas pelo aluno
	 * @param session
	 * @param theModel
	 * @return pagina com a lista de ualas do aluno
	 */
	@GetMapping("/showAulaAluno")
	public String listarAulasRealizadas(HttpSession session, Model theModel) {

		AlunoImpl aluno = (AlunoImpl) session.getAttribute("usuario");
		
		// get aulas from the DAO
		List<AulaRealizadaImpl> aulasRealizadas = aulaRealizadaService.getAulasRealizadasAluno(aluno.getId_usuario());		
		
		List<AulaRealizadaModel> listaAulas = new ArrayList<AulaRealizadaModel>();
		
		
		for (int i = 0; i < aulasRealizadas.size(); i++) {
			
			//objeto para coversao de datas
			DataConversion conversor = new DataConversion();
			
			//objeto que vai pegar apenas o registro daquela itera��o, pq esta vindo como list
			AulaRealizadaModel aulaRealizadaModel = new AulaRealizadaModel();
			AulaRealizadaImpl aulaRealizada = new AulaRealizadaImpl();
			aulaRealizada = aulasRealizadas.get(i);
			
			String nomeAula = aulaService.getNomeAula(aulaRealizada.getId_aula_aula_realizada());
			
			ProfessorImpl professor = professorService.getUsuario(aulaRealizada.getId_professor_aula_realizada());				
			String nomeProfessor = professor.getNome_usuario();
			
			//transformardata em horario BR
			aulaRealizada.setDt_inicio_aula_realizada(conversor.banco2br(aulaRealizada.getDt_inicio_aula_realizada()));
			
			//adiciona no model
			aulaRealizadaModel.setNomeAula(nomeAula);
			aulaRealizadaModel.setNomeProfessor(nomeProfessor);
			aulaRealizadaModel.setAulaRealizadaAtual(aulaRealizada);
		
			
			listaAulas.add(aulaRealizadaModel);
			
			
		}
		
		// add the aulas to the model
		theModel.addAttribute("aulas", listaAulas);

		return "lista-aulas-realizadas";
	}
	
	/**
	 * metodo para popular tela de detalhes de aula
	 * @param id_aula_realizada
	 * @param modelMap
	 * @return tela de detalhes da aula selecionada
	 */
	
	@GetMapping("/showDetalhesAula")
	public String showDetalhesAula(@RequestParam("id_aula_realizada") int id_aula_realizada, ModelMap modelMap, HttpSession session ) {

		
		// get aula realizada do banco
		AulaRealizadaImpl aulaRealizada = aulaRealizadaService.getAulaRealizada(id_aula_realizada);
		String nomeAula = aulaService.getNomeAula(aulaRealizada.getId_aula_aula_realizada());
		AulaImpl aula = aulaService.getAula(aulaRealizada.getId_aula_aula_realizada());
		String atividade = aula.getAtividade_aula();
						
		ProfessorImpl professor = professorService.getUsuario(aulaRealizada.getId_professor_aula_realizada());				
		String nomeProfessor = professor.getNome_usuario();
		
		//apenas abre os detalhes se o aluno realizou aula
		if(aulaRealizada.getStatus_aula_realizada().toLowerCase().equals("realizada") ){
			modelMap.addAttribute("aulaRealizada", aulaRealizada);
			modelMap.addAttribute("nomeAula", nomeAula);
			modelMap.addAttribute("atividade", atividade);
			modelMap.addAttribute("professor", nomeProfessor);
			
			// retorna pagina de detalhe da aula
			return "aluno-aula";
		}else{
			final String errorMessage = "<div class='alert alert-danger fade in'> <a href='#' class='close' data-dismiss='alert'>&times;</a>Voc� n�o realizou essa aula.</div>";
			modelMap.addAttribute("errorMessage", errorMessage);
			
			//instaciado apena para ser chamado em caso de erro
			Model theModel = (Model) modelMap;
			
			return listarAulasRealizadas(session, theModel);
		}
		

	}
	
	
}
