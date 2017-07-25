package com.hullo.controller;

import java.time.LocalDateTime;
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
import com.hullo.utility.DataConversion;

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
	 *            para enviar infos para a view
	 * @return pagina com lista de alunos cadastrados
	 */
	@GetMapping("/listaAlunos")
	public String listarAlunos(HttpSession session) {

		//objeto para converter datas para hora BR
		DataConversion conversor = new DataConversion();
		// get usuarios from the DAO
		List<AlunoImpl> theUsuarios = alunoService.getUsuarios();

		for (int i = 0; i < theUsuarios.size(); i++) {
			AlunoImpl aluno = theUsuarios.get(i);
			LocalDateTime date = aluno.getDt_insert_usuario();
			aluno.setDt_insert_usuario(conversor.banco2br(date));
		}

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
		//objeto para converter datas para hora BR
		DataConversion conversor = new DataConversion();
		// get usuarios from the DAO
		List<ProfessorImpl> theUsuarios = professorService.getUsuarios();

		for (int i = 0; i < theUsuarios.size(); i++) {
			ProfessorImpl professor = theUsuarios.get(i);
			LocalDateTime date = professor.getDt_insert_usuario();
			professor.setDt_insert_usuario(conversor.banco2br(date));
		}

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
	
	/**
	 * sair da sessão
	 * @param session
	 * @return
	 */
	@GetMapping("logout")
	public String logout(HttpSession session){
		//formas de tirar a sessão, mas ainda não funciona
		/*session.removeAttribute("usuario_professor");
		session.removeAttribute("usuario_aluno");
		session.invalidate();*/
		return "redirect:/usuario/usuarioLogin";
	}


}
