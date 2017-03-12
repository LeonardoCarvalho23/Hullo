package com.hullo.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hullo.entity.AlunoImpl;
import com.hullo.entity.ProfessorImpl;
import com.hullo.entity.Usuario;
import com.hullo.entity.UsuarioImpl;
import com.hullo.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	//with the service, inject the service here
	@Autowired
	@Qualifier("usuarioServiceImpl")
	private UsuarioService<UsuarioImpl> usuarioService;
		
	@GetMapping("/showFormNewUsuario")
	public String showFormNovoUsuario(Model theModel){
		
		//create model attribute to bind form data
		UsuarioImpl theUsuario = new UsuarioImpl();
		theModel.addAttribute("usuario", theUsuario); //name,value
		
		return "usuario-form";
	}
	
	@GetMapping("/usuarioLogin")
	public String usuarioLogin(Model theModel){
		Usuario oUsuario = new UsuarioImpl();
		theModel.addAttribute("usuario", oUsuario);
		
		return "usuario-login";
	}
	
	@PostMapping("/getUsuario")
	public String loginUsuario(@ModelAttribute("usuario") UsuarioImpl theUsuario, Model model){
		// Acima, acrescentei o "ModelMap model" para poder repassar a mensagem de erro quando o login falha
		// Pega o Model e retira os parâmetros para variáveis
		String email = theUsuario.getEmail_usuario();
		String senha = theUsuario.getSenha_usuario();
		
		// busca o usuário com base nos dados retirados acima
		UsuarioImpl loggedUser = usuarioService.getUsuario(email, senha);
		if (loggedUser == null){
			// erro de login
			final String errorMessage = 
					"<div class='alert alert-danger fade in'> <a href='#' class='close' data-dismiss='alert'>&times;</a> Usuário ou senha incorretos. </div>"; 
		    model.addAttribute("errorMessage", errorMessage);
			return "usuario-login";
			
		} else {
			// login feito com sucesso
			
			// checa se o usuário é aluno ou professor e retorna página apropriada
			if (loggedUser.getTipo_usuario().equals("ALUNO")){
				
				//transforma o UsuarioImpl em AlunoImpl e atualiza o modelo
				AlunoImpl loggedAluno = new AlunoImpl(loggedUser);
				model.addAttribute("usuario", loggedAluno);
				return "home-aluno";
				
			} else {
				//entao e professor, transforma o ususario e atualiza o modelo
				ProfessorImpl loggedProfessor = new ProfessorImpl(loggedUser);
				model.addAttribute("usuario", loggedProfessor);
				return "home-professor";
			}
		}
	}
	
}
