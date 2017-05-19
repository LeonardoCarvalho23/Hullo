package com.hullo.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

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
@SessionAttributes("aulaRealizada")
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
	public String ministrarAula(HttpSession session, Model model, ModelMap modelMap) {
		ProfessorImpl professor = (ProfessorImpl) session.getAttribute("usuario");
				
		// achar o dia para saber se e fim de semana
		Calendar date = new GregorianCalendar();

		if (date.get(Calendar.DAY_OF_WEEK) == 1 || date.get(Calendar.DAY_OF_WEEK) == 7){
			String errorMessage = "<div class='alert alert-danger fade in' align='center'> Weekend is for resting only, go have some fun!</div>";
			modelMap.addAttribute("errorMessage", errorMessage);
			return "sem-aulas-disponiveis";
		}

		// crio o model que vai receber a infos para exibir na pagina
		AulaRealizadaModel aulaRealizadaModel = new AulaRealizadaModel();

		// buscar qual a proxima aula realizada e adiciona ao model
		AulaRealizadaImpl aulaRealizadaAtual = aulaRealizadaService.getProximaAula();

		// se nao retornou nnehuma aula, abre pagina que nao ha aulas
		// disponiveis
		if (aulaRealizadaAtual == null) {
				String errorMessage = "<div class='alert alert-danger fade in' align='center'>Sorry, there are no classes available ate the moment</div>";
				modelMap.addAttribute("errorMessage", errorMessage);
				return "sem-aulas-disponiveis";
		}

		// se voltou aula realizada, coloco o id do professor atual, faz update no banco e adiciono
		// ao model
		aulaRealizadaAtual.setId_professor_aula_realizada(professor.getId_usuario());
		aulaRealizadaService.updateProfessorAulaRealizada(aulaRealizadaAtual.getId_aula_realizada(), aulaRealizadaAtual.getId_professor_aula_realizada());
		aulaRealizadaModel.setAulaRealizadaAtual(aulaRealizadaAtual);

		// pego o aluno dessa aula e coloco no model
		AlunoImpl aluno = alunoService.getUsuario(aulaRealizadaAtual.getId_aluno_aula_realizada());
		aulaRealizadaModel.setAluno(aluno);

		// pego o conteudo da aula atual e adiciono ao model
		AulaImpl aulaAtual = aulaService.getAula(aulaRealizadaAtual.getId_aula_aula_realizada());
		aulaRealizadaModel.setAulaAtual(aulaAtual);

		// verifico se tem aula anterior, pego o conteudo e adiciono ao model
		if (aulaRealizadaAtual.getId_anterior_aula_realizada() != null) {
			// buscar resultados da aula anterior e adiciona ao model
			AulaRealizadaImpl aulaRealizadaAnterior = aulaRealizadaService
					.getAulaRealizada(aulaRealizadaAtual.getId_anterior_aula_realizada());
			aulaRealizadaModel.setAulaRealizadaAnterior(aulaRealizadaAnterior);
			// conteudo da aula anterior
			AulaImpl aulaAnterior = aulaService.getAula(aulaRealizadaAnterior.getId_aula_aula_realizada());
			aulaRealizadaModel.setAulaAnterior(aulaAnterior);

		}

		model.addAttribute("aulaRealizadaModel", aulaRealizadaModel);

		return "ministrarAula";
	}

	// metodo para encerrar aula
	@PostMapping("/encerrarAula")
	public String concludedAulaRealizada(@ModelAttribute("aulaRealizadaModel") AulaRealizadaModel aulaRealizadaModel,
			Model theModel, HttpSession session) {

		Date current_date = new Date();

		// pego modulo do objeto Model
		AulaRealizadaImpl aulaRealizadaAtual = aulaRealizadaModel.getAulaRealizadaAtual();
		ProfessorImpl professor = (ProfessorImpl) session.getAttribute("usuario");

		// atualizo com o id do professor
		aulaRealizadaAtual.setId_professor_aula_realizada(professor.getId_usuario());

		// salvo updates no banco
		aulaRealizadaService.concludedAulaRealizada(aulaRealizadaAtual);

		// inicio a busca pela proxima aula realizada
		AulaImpl proxAula = new AulaImpl();

		// valida se aluno atingiu a nota
		if (aulaRealizadaAtual.getNota_model_aula_realizada() >= 3
				&& aulaRealizadaAtual.getNota_practice_aula_realizada() >= 3
				&& aulaRealizadaAtual.getNota_production_aula_realizada() >= 3) {

			// caso sim, proxima aula_realizada vai ser proxima do curso
			proxAula = aulaService.getProximaAulaLinear(aulaRealizadaAtual.getId_aula_aula_realizada());
		}

		else {
			// caso nao, proxima_aula realizada vai ser a paralela
			proxAula = aulaService.getProximaAulaParalela(aulaRealizadaAtual.getId_aula_aula_realizada());
		}

		// validacao se nao ha proxima aula e o curso simplesmente acabou
		if (proxAula == null) {
			return "home-professor";
		}

		// coloca os dados na proxima aula_realizada
		AulaRealizadaImpl proxAulaRealizada = new AulaRealizadaImpl();
		proxAulaRealizada.setId_aluno_aula_realizada(aulaRealizadaAtual.getId_aluno_aula_realizada());
		proxAulaRealizada.setId_aula_aula_realizada(proxAula.getId_aula());
		proxAulaRealizada.setDt_criacao_aula_realizada(current_date);
		proxAulaRealizada.setId_anterior_aula_realizada(aulaRealizadaAtual.getId_aula_realizada());

		// salva prox aula_realizada no banco
		aulaRealizadaService.createProximaAulaRealizada(proxAulaRealizada);

		return "home-professor";

	}
	
	//metodo para salvar que aula nao foi atendida
	@RequestMapping("/updateAulaNaoAtendida")
	public String updateAulaNaoAtendida(HttpSession session) {
		
		return "home-professor";
	}

}
