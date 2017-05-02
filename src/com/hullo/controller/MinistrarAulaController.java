package com.hullo.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hullo.entity.AlunoImpl;
import com.hullo.entity.AulaImpl;
import com.hullo.entity.AulaRealizadaImpl;
import com.hullo.entity.AulaRealizadaModel;
import com.hullo.entity.ProfessorImpl;
import com.hullo.service.AulaRealizadaServiceImpl;
import com.hullo.service.AulaServiceImpl;
import com.hullo.service.UsuarioService;

@Controller
@RequestMapping("/professor")
public class MinistrarAulaController {

	ProfessorImpl professor;
	AulaRealizadaModel aulaRealizada;
	AulaImpl aula;

	@Autowired
	@Qualifier("alunoServiceImpl")
	private UsuarioService<AlunoImpl> alunoService;

	@Autowired
	private AulaRealizadaServiceImpl aulaRealizadaService;

	@Autowired
	private AulaServiceImpl aulaService;

	// abrir pagina com dados da aula a ministrar
	@PostMapping("/ministrarAula")
	public String ministrarAula(HttpSession session, Model model) {
		ProfessorImpl professor = (ProfessorImpl) session.getAttribute("usuario");
		System.out.println(professor.toString());
		
		//crio o model que vai receber a infos para exibir na pagina com os objetos:
		// aulaRealizadaAtual = 
		// aulaRealizadaAnterior =
		// aulaAtual =
		// aulatAnterior = 
		// aluno = 
		AulaRealizadaModel aulaRealizadaModel = new AulaRealizadaModel();
		
		System.out.println("chegou no controller ministrar aula");
		
		// buscar qual a proxima aula realizada e adiciona ao model
		AulaRealizadaImpl aulaRealizadaAtual = aulaRealizadaService.getProximaAula();
		
		//se nao retornou nnehuma aula, abre pagina que nao ha aulas disponiveis
		if(aulaRealizadaAtual == null){
			return "sem-aulas-disponiveis";
		}
		
		//se voltou aula realizada, coloco o id do professor atual e adiciono ao model
		aulaRealizadaAtual.setId_professor_aula_realizada(professor.getId_usuario());
		aulaRealizadaModel.setAulaRealizadaAtual(aulaRealizadaAtual);
		
		//pego o aluno dessa aula e coloco no model
		AlunoImpl aluno = alunoService.getUsuario(aulaRealizadaAtual.getId_aluno_aula_realizada());
		aulaRealizadaModel.setAluno(aluno);

		// pego o conteudo da aula atual e adiciono ao model
		AulaImpl aulaAtual = aulaService.getAula(aulaRealizadaAtual.getId_aula_aula_realizada());
		aulaRealizadaModel.setAulaAtual(aulaAtual);

		// verifico se tem aula anterior, pego o conteudo e adiciono ao model
		if (aulaRealizadaAtual.getId_anterior_aula_realizada() != null) {
			//buscar resultados da aula anterior e adiciona ao model
			AulaRealizadaImpl aulaRealizadaAnterior = aulaRealizadaService.getAulaRealizada(aulaRealizadaAtual.getId_anterior_aula_realizada());
			aulaRealizadaModel.setAulaRealizadaAnterior(aulaRealizadaAnterior);
			//conteudo da aula anterior
			AulaImpl aulaAnterior = aulaService.getAula(aulaRealizadaAnterior.getId_aula_aula_realizada());
			aulaRealizadaModel.setAulaAnterior(aulaAnterior);

		}

		model.addAttribute("aulaRealizadaModel", aulaRealizadaModel);

		return "ministrarAula";
	}
	
	/* METODO ENCERRAR AULA + CRIAR NOVA AULA
	// metodo para encerrar aula
			@PostMapping("/encerrarAula")
			public String concludedAulaRealizada(@ModelAttribute("aulaRealizadaModel") AulaRealizadaModel aulaRealizadaModel, Model theModel, HttpSession session) {
				
				// pego modulo do objeto moduloModel
				AulaRealizadaImpl aulaRealizadaImpl = aulaRealizadaModel.getAulaRealizadaAtual();			
				ProfessorImpl professor = (ProfessorImpl) session.getAttribute("usuario");
				
				aulaRealizadaImpl.setId_professor_aula_realizada(professor.getId_usuario());
					
				aulaRealizadaService.concludedAulaRealizada(aulaRealizadaImpl);		
				
				theModel.addAttribute(aulaRealizadaModel);
				
				aulaRealizadaService.createProximaAulaRealizada(aulaRealizadaImpl);	
				
				return "ministrarAula";

			}
	*/
}
