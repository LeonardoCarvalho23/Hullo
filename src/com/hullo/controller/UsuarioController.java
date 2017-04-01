package com.hullo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hullo.entity.AlunoImpl;
import com.hullo.entity.ProfessorImpl;
import com.hullo.entity.Usuario;
import com.hullo.entity.UsuarioImpl;
import com.hullo.service.LogServiceImpl;
import com.hullo.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	

	// --Abaixo, dados para disparo de email
	@Autowired
	private MailSender mailSender;

	@Autowired
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
	// -- fim do código para email

	// with the service, inject the service here
	@Autowired
	@Qualifier("usuarioServiceImpl")
	private UsuarioService<UsuarioImpl> usuarioService;

	@Autowired
	@Qualifier("alunoServiceImpl")
	private UsuarioService<AlunoImpl> alunoService;

	@Autowired
	@Qualifier("professorServiceImpl")
	private UsuarioService<ProfessorImpl> professorService;
	
	@Autowired
	private LogServiceImpl logService;

	@GetMapping("/showFormNewUsuario")
	public String showFormNovoUsuario(Model theModel) {

		// create model attribute to bind form data
		UsuarioImpl theUsuario = new UsuarioImpl();
		theModel.addAttribute("usuario", theUsuario); // name,value

		return "usuario-form";
	}

	@GetMapping("/usuarioLogin")
	public String usuarioLogin(Model theModel) {
		Usuario oUsuario = new UsuarioImpl();
		theModel.addAttribute("usuarioLogin", oUsuario);

		return "usuario-login";
	}

	@PostMapping("/getUsuario")
	public String loginUsuario(@ModelAttribute("usuarioLogin") UsuarioImpl theUsuario, HttpSession session, Model model) {
		// Acima, acrescentei o "Model model" para poder repassar a mensagem de erro quando o login falha
		// O HttpSession session é para guardar a sessão do usuário logado.
		// Pega o Model e retira os parâmetros para variáveis
		String email = theUsuario.getEmail_usuario();
		String senha = theUsuario.getSenha_usuario();

		// novo metodo de busca de usuarios para login

		// primeiro busca por aluno
		AlunoImpl loggedAluno = alunoService.getUsuario(email, senha);

		if (loggedAluno != null) {
			//login feito com sucesso
			//Abaixo, adiciona o objeto AlunoImpl à sessão Http
			session.setAttribute("usuario", loggedAluno);
			//Guarda no log o horário do login
			logService.saveAlunoLog(loggedAluno.getId_usuario());
			return "home-aluno";
		}

		// busca por professor
		ProfessorImpl loggedProfessor = professorService.getUsuario(email, senha);

		if (loggedProfessor != null) {
			// login feito com sucesso
			//Abaixo, adiciona o objeto ProfessorImpl à sessão Http
			session.setAttribute("usuario", loggedProfessor);
			//Guarda no log o horário do login
			logService.saveProfessorLog(loggedProfessor.getId_usuario());
			return "home-professor";
		}

		// verifica se eh o adm
		if (senha.equals("adm") & email.equals("adm")) {
			UsuarioImpl loggedAdmin = new UsuarioImpl();
			loggedAdmin.setNome_usuario("Administrador");
			session.setAttribute("usuario", loggedAdmin);
			return "main";
		}

		// erro de login
		final String errorMessage = "<div class='alert alert-danger fade in'> <a href='#' class='close' data-dismiss='alert'>&times;</a> Usuário ou senha incorretos. </div>";
		model.addAttribute("errorMessage", errorMessage);
		return "usuario-login";
	}

	@GetMapping("/retrievePassword")
	public String retrievePassword(Model theModel) {
		Usuario oUsuario = new UsuarioImpl();
		theModel.addAttribute("usuario", oUsuario);
		return "usuario-password-recover";
	}

	@PostMapping("/sendPassword")
	public String sendPassword(@ModelAttribute("usuario") UsuarioImpl theUsuario, Model model) {
		// Pega o email passado no modelo e busca o objeto
		String email = theUsuario.getEmail_usuario();
		String senha = null;
		String nome = null;
		
		// coloca um usuario no model pra retornar ao login
		Usuario oUsuario = new UsuarioImpl();
		model.addAttribute("usuarioLogin", oUsuario);
		
		//busca por aluno e depois por professor e se encontrar, coloca os valores
		AlunoImpl aluno = alunoService.getUsuario(email);
				
		if (aluno == null){
			ProfessorImpl professor = professorService.getUsuario(email);
				if  (professor != null){
					senha = professor.getSenha_usuario();
					email = professor.getEmail_usuario();
					nome = professor.getNome_usuario();
				}
		}
		
		else{
			senha = aluno.getSenha_usuario();
			email = aluno.getEmail_usuario();
			nome = aluno.getNome_usuario();
		}
		
		
		// Se o usuario nao foi encontrado, retorna erro. Do contrario, envia email.
		if (senha == null) {
			// erro
			final String errorMessage = "<div class='alert alert-danger fade in'> <a href='#' class='close' data-dismiss='alert'>&times;</a> Usuário não encontrado. </div>";
			model.addAttribute("errorMessage", errorMessage);
			return "usuario-password-recover";

		} else {

			SimpleMailMessage msg = new SimpleMailMessage();

			msg.setTo(email);
			msg.setFrom("noreply@hullo.com.br");
			msg.setSubject("Recuperação de senha");
			msg.setText(nome + ", sua senha é " + senha + ".");

			try {
				this.mailSender.send(msg);
				// System.out.println(msg.toString());
			} catch (MailException e) {
				// TODO Auto-generated catch block
			}
			final String okPasswordMessage = "<div class='alert alert-success fade in'> <a href='#' class='close' data-dismiss='alert'>&times;</a> Senha enviada com sucesso. </div>";
			model.addAttribute("okPasswordMessage", okPasswordMessage);
			return "usuario-login";
		}
	}

}
