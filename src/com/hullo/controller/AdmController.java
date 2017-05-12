package com.hullo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hullo.entity.AlunoImpl;
import com.hullo.entity.ProfessorImpl;
import com.hullo.entity.UsuarioImpl;
import com.hullo.service.UsuarioService;

/**
 * classe para controlar o que é exibido na tela do administrador
 * 
 * @author Hullo Team
 * @version 1.0
 */

@Controller
@RequestMapping("/adm")
public class AdmController {

	@Autowired
	@Qualifier("professorServiceImpl")
	private UsuarioService<ProfessorImpl> professorService;

	@Autowired
	@Qualifier("alunoServiceImpl")
	private UsuarioService<AlunoImpl> alunoService;

	@Autowired
	@Qualifier("usuarioServiceImpl")
	private UsuarioService<UsuarioImpl> usuarioService;

	@GetMapping("/main")
	public String showMain(Model theModel) {
		return "main";
	}

	/**
	 * abre pagina com lista de todos os alunos cadastrados
	 * 
	 * @param session
	 *           para enviar infos para a view
	 * @return pagina com lista de alunos cadastrados
	 */
	@GetMapping("/listaAlunos")
	public String listarAlunos(HttpSession session) {

		// get usuarios from the DAO
		List<AlunoImpl> theUsuarios = alunoService.getUsuarios();

		// adiciona lista na sessao
		session.setAttribute("usuarios", theUsuarios); // name and value
		
		return "lista-aluno";
	}

	/**
	 * abre pagina com lista de todos os professores cadastrados
	 * 
	 * @param session
	 *            para enviar infos para a view
	 * @return pagina com lista de professores cadastrados
	 */
	@GetMapping("/listaProfessores")
	public String listarProfessores(HttpSession session) {

		// get usuarios from the DAO
		List<ProfessorImpl> theUsuarios = professorService.getUsuarios();

		// adiciona lista na sessao
		session.setAttribute("usuarios", theUsuarios); // name and value

		return "list-professor";
	}

	/**
	 * abre pagina de teste do cronometro
	 * 
	 * @return pagina do cronometro
	 */
	@GetMapping("/cronometro")
	public String testCronometro() {
		return "stopwatchTest";
	}

}
