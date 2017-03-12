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
import com.hullo.entity.Usuario;
import com.hullo.entity.UsuarioImpl;
import com.hullo.service.UsuarioService;

@Controller
@RequestMapping("/aluno")
public class AlunoController {
	
	@Autowired
	@Qualifier("alunoServiceImpl")
	private UsuarioService<AlunoImpl> alunoService;
	
	@GetMapping("/formAluno") 	
	public String showFormNovoUsuario(Model theModel){
		
		//create model attribute to bind form data
		UsuarioImpl theUsuario = new UsuarioImpl();
		theModel.addAttribute("usuario", theUsuario); //name,value
		
		return "aluno-form";
	}
	

	//Metodo em desenvolvimento
	@PostMapping("/newAluno")
	public String saveUsuario(@ModelAttribute("usuario") AlunoImpl theAluno, ModelMap modelMap){
		Date current_date = new Date();
		AlunoImpl validaAluno = new AlunoImpl();
		
		System.out.println("chegou no controller");
		
		//validar se ja existe usuario com esse email ou senha
		validaAluno = alunoService.getUsuario(theAluno.getEmail_usuario(), theAluno.getCpf_usuario());
		
		System.out.println("validou o usuario");
		
		//se retornar que existe, exibe mensagem de erro
		if (validaAluno != null){
			System.out.println("viu que ha usuario com os dados");
			//exibe mensagem de erro
			final String errorMessage = 
					"<div class='alert alert-danger fade in'> <a href='#' class='close' data-dismiss='alert'>&times;</a> Ja exite usuario com esses dados </div>"; 
		    modelMap.addAttribute("errorMessage", errorMessage);
			
			return "aluno-form";
			
		//se nao existe aluno com esses dados, cria o ususario
		} else {
			System.out.println("viu que nao ha usuario com os dados");
			theAluno.setAtivo_usuario("1");
			theAluno.setDt_insert_usuario(current_date);
			theAluno.setDt_last_update_usuario(current_date);
			theAluno.setTipo_usuario("ALUNO");
			
			//save the aluno
			alunoService.saveUsuario(theAluno);
			
			return "redirect:/usuario/usuarioLogin";
		}
		
		
	}
	
}
