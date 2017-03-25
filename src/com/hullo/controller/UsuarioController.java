package com.hullo.controller;

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
	// -- fim do c�digo para email

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
		theModel.addAttribute("usuario", oUsuario);

		return "usuario-login";
	}

	@PostMapping("/getUsuario")
	public String loginUsuario(@ModelAttribute("usuario") UsuarioImpl theUsuario, Model model) {
		// Acima, acrescentei o "Model model" para poder repassar a mensagem de
		// erro quando o login falha
		// Pega o Model e retira os par�metros para vari�veis
		String email = theUsuario.getEmail_usuario();
		String senha = theUsuario.getSenha_usuario();

		// novo metodo de busca de usuarios para login

		// primeiro busca por aluno
		AlunoImpl loggedAluno = alunoService.getUsuario(email, senha);

		if (loggedAluno != null) {
			// login feito com sucesso
			model.addAttribute("usuario", loggedAluno);
			return "home-aluno";
		}

		// busca por professor
		ProfessorImpl loggedProfessor = professorService.getUsuario(email, senha);

		if (loggedProfessor != null) {
			// login feito com sucesso
			model.addAttribute("usuario", loggedProfessor);
			return "home-professor";
		}

		// verifica se eh o adm
		if (senha.equals("adm") & email.equals("adm")) {
			return "main";
		}

		// erro de login
		final String errorMessage = "<div class='alert alert-danger fade in'> <a href='#' class='close' data-dismiss='alert'>&times;</a> Usu�rio ou senha incorretos. </div>";
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
		
		//busca por aluno e depois por professor e se encontrar, coloca os valores
		AlunoImpl visitor = alunoService.getUsuario(email);
				
		if (visitor == null){
			ProfessorImpl visitor2 = professorService.getUsuario(email);
				if  (visitor2 != null){
					senha = visitor2.getSenha_usuario();
					email = visitor2.getEmail_usuario();
				}
		}
		
		else{
			senha = visitor.getSenha_usuario();
			email = visitor.getEmail_usuario();
		}
		
		
		// Se o usuario nao foi encontrado, retorna erro. Do contrario, envia email.
		if (senha == null) {
			// erro
			final String errorMessage = "<div class='alert alert-danger fade in'> <a href='#' class='close' data-dismiss='alert'>&times;</a> Usu�rio n�o encontrado. </div>";
			model.addAttribute("errorMessage", errorMessage);
			return "usuario-password-recover";

		} else {

			SimpleMailMessage msg = new SimpleMailMessage();

			msg.setTo(email);
			msg.setFrom("noreply@hullo.com.br");
			msg.setSubject("Recupera��o de senha");
			msg.setText(visitor.getNome_usuario() + ", sua senha � " + senha + ".");

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
