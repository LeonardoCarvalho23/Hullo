package com.hullo.controller;

import java.io.IOException;
import java.util.Date;
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
import com.hullo.service.CidadeServiceImpl;
import com.hullo.service.EstadoServiceImpl;
import com.hullo.service.UsuarioService;

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
	
	//@Autowired
	//private AulaRealizadaServiceImpl aulaRealizadaService;

	@Autowired
	private EstadoServiceImpl estadoService;
	// --Abaixo, dados para disparo de email
	@Autowired
	private MailSender mailSender;

	@Autowired
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
	// -- fim do código para email

	@Autowired
	private CidadeServiceImpl cidadeService;

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

	// Metodo para gravar novo aluno
	@PostMapping("/newAluno")
	public String saveUsuario(@ModelAttribute("usuarioModel") AlunoModel usuarioModel, ModelMap modelMap) throws JsonParseException, JsonMappingException, IOException {
		Date current_date = new Date();

		// pega o aluno do objeto alunoModel
		AlunoImpl theAluno = usuarioModel.getUsuario();

		// pega a cidade do objeto alunoModel (cast de JSON para Objeto)
		ObjectMapper mapper = new ObjectMapper();
		CidadeImpl cidade = mapper.readValue(usuarioModel.getCidade(), CidadeImpl.class);

		// seta o id da cidade no usuario
		theAluno.setCd_cidade_usuario(cidade.getId_Cidade());

		// validar se ja existe usuario com esse email ou senha, tanto professor quanto aluno
		
		AlunoImpl validaAluno = alunoService.validaUsuario(theAluno.getEmail_usuario(), theAluno.getCpf_usuario());
		ProfessorImpl validaProfessor = professorService.getUsuario(theAluno.getEmail_usuario());
		
			if (isCPF(theAluno.getCpf_usuario())){
				
				
				
				// se retornar que existe, exibe mensagem de erro
				if (validaAluno != null) {
		
					// exibe mensagem de erro
					final String errorMessage = "<div class='alert alert-danger fade in'> <a href='#' class='close' data-dismiss='alert'>&times;</a>Já existe aluno com o mesmo e-mail ou CPF.</div>";
					modelMap.addAttribute("errorMessage", errorMessage);
		
					return "aluno-form";
					
				// se houver professor, checa se a senha é igual. Se não for, devolve erro	
				} else if ((validaProfessor != null) && !(validaProfessor.getSenha_usuario().equals(theAluno.getSenha_usuario()))) {
					
					// exibe mensagem de erro
					final String errorMessage = "<div class='alert alert-danger fade in'> <a href='#' class='close' data-dismiss='alert'>&times;</a>Senha deve ser a mesma do perfil de professor cadastrado.</div>";
					modelMap.addAttribute("errorMessage", errorMessage);
		
					return "aluno-form";
				}	
				// se nao existe aluno com esses dados, cria o ususario
				else {
					
					theAluno.setAtivo_usuario("1");
					theAluno.setDt_insert_usuario(current_date);
					theAluno.setDt_last_update_usuario(current_date);
		
					// save the aluno
					alunoService.saveUsuario(theAluno);
		
					//gera a primeira aula do aluno
					//aulaRealizadaService.montarAulaRealizada(theAluno.getEmail_usuario());
					 
					
					// Envia email de confirmação
					SimpleMailMessage msg = new SimpleMailMessage();
		
					msg.setTo(theAluno.getEmail_usuario());
					msg.setFrom("noreply@hullo.com.br");
					msg.setSubject("Confirmação de cadastro");
					msg.setText(theAluno.getNome_usuario() + ", seu cadastro de aluno foi realizado com sucesso.");
		
					try {
						this.mailSender.send(msg);
						// System.out.println(msg.toString());
					} catch (MailException e) {
						// TODO Auto-generated catch block
					}
					// envia mensagem de cadastro com sucesso
					final String okNewAlunoMessage = "<div class='alert alert-success fade in'> <a href='#' class='close' data-dismiss='alert'>&times;</a> Aluno cadastrado com sucesso. Faça login. </div>";
					modelMap.addAttribute("okNewAlunoMessage", okNewAlunoMessage);
					Usuario oUsuario = new UsuarioImpl();
					modelMap.addAttribute("usuario", oUsuario);
					return "redirect:/usuario/usuarioLogin";
				}
			}else{
				// exibe mensagem de erro
				final String errorMessage = "<div class='alert alert-danger fade in'> <a href='#' class='close' data-dismiss='alert'>&times;</a>CPF inválido.</div>";
				modelMap.addAttribute("errorMessage", errorMessage);
		
				return "aluno-form";
			}

	}

	@RequestMapping(value = "formAluno/cidades", method = RequestMethod.POST)
	public @ResponseBody List<CidadeImpl> obterCidade(@RequestBody EstadoImpl estado) {
		System.out.println("Estado: " + estado);
		// List<CidadeImpl> cidade = cidadeService.getCidades();

		List<CidadeImpl> cidade = cidadeService.obterCidadesDoEstado(estado);
		System.out.println("Cidade: " + cidade);
		return cidade;
	}
	
	// metodo para abrir pagina perfil do aluno
	@RequestMapping("/showPerfilAluno")
	public String showPerfilAluno(HttpSession session) {
		return "perfil-aluno";
	}

	// metodo para abrir a pagina de update do aluno
	@RequestMapping("/showFormUpdateAluno")
	public String showFormUpdateAluno(HttpSession session) {
		return "aluno-update-form";
	}

	// metodo para atualizar aluno
	@RequestMapping("/updateAluno")
	public String updateAluno(@ModelAttribute("usuario") AlunoImpl theUsuario, HttpSession session, ModelMap modelMap) {

		// // validar se ja existe usuario com esse email
		AlunoImpl validaAluno = alunoService.validaUsuario(theUsuario.getEmail_usuario(), theUsuario.getId_usuario());

		if (validaAluno != null) {

			// exibe mensagem de erro
			final String errorMessage = "<div class='alert alert-danger fade in'> <a href='#' class='close' data-dismiss='alert'>&times;</a> Existe outro usuario com esse email </div>";
			modelMap.addAttribute("errorMessage", errorMessage);
			return "aluno-update-form";
			
		} else {
			
			// Atualiza a sessão com os dados inseridos no formulario
			Date current_date = new Date();
			theUsuario.setDt_last_update_usuario(current_date);
			alunoService.updateUsuario(theUsuario);

			return "home-aluno";
		}
	}


	// metodo para inativar aluno
	@PostMapping("/inactivateAluno")
	public String inactivateAluno(@ModelAttribute("usuario") AlunoImpl theUsuario, Model theModel) {
		Date current_date = new Date();

		theUsuario.setDt_last_update_usuario(current_date);

		alunoService.inactivateUsuario(theUsuario);

		theModel.addAttribute(theUsuario);

		return "redirect:/usuario/usuarioLogin";

	}
	
	public static boolean isCPF(String CPF) {
		CPF = CPF.replaceAll("[.-]","");
		// considera-se erro CPF's formados por uma sequencia de numeros iguais
		    if (CPF.equals("00000000000") || CPF.equals("11111111111") ||
		        CPF.equals("22222222222") || CPF.equals("33333333333") ||
		        CPF.equals("44444444444") || CPF.equals("55555555555") ||
		        CPF.equals("66666666666") || CPF.equals("77777777777") ||
		        CPF.equals("88888888888") || CPF.equals("99999999999") ||
		       (CPF.length() != 11))
		       return(false);

		    char dig10, dig11;
		    int sm, i, r, num, peso;

		// "try" - protege o codigo para eventuais erros de conversao de tipo (int)
		    try {
		// Calculo do 1o. Digito Verificador
		      sm = 0;
		      peso = 10;
		      for (i=0; i<9; i++) {              
		// converte o i-esimo caractere do CPF em um numero:
		// por exemplo, transforma o caractere '0' no inteiro 0         
		// (48 eh a posicao de '0' na tabela ASCII)         
		        num = (int)(CPF.charAt(i) - 48); 
		        sm = sm + (num * peso);
		        peso = peso - 1;
		      }

		      r = 11 - (sm % 11);
		      if ((r == 10) || (r == 11))
		         dig10 = '0';
		      else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

		// Calculo do 2o. Digito Verificador
		      sm = 0;
		      peso = 11;
		      for(i=0; i<10; i++) {
		        num = (int)(CPF.charAt(i) - 48);
		        sm = sm + (num * peso);
		        peso = peso - 1;
		      }

		      r = 11 - (sm % 11);
		      if ((r == 10) || (r == 11))
		         dig11 = '0';
		      else dig11 = (char)(r + 48);

		// Verifica se os digitos calculados conferem com os digitos informados.
		      if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
		         return(true);
		      else return(true);
		    } catch (InputMismatchException erro) {
		        return(false);
		    }
		  }

}
