package com.hullo.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hullo.dao.AulaRealizadaDAOImpl;
import com.hullo.entity.AlunoImpl;
import com.hullo.entity.AulaImpl;
import com.hullo.entity.AulaRealizadaImpl;
import com.hullo.entity.ModuloImpl;
import com.twilio.rest.api.v2010.account.Call.Status;

@Service
public class AulaRealizadaServiceImpl {

	@Autowired
	private AulaRealizadaDAOImpl aulaRealizadaDAO;

	@Autowired
	private AulaServiceImpl aulaService;

	@Autowired
	private ModuloServiceImpl moduloService;

	@Autowired
	@Qualifier("alunoServiceImpl")
	private UsuarioService<AlunoImpl> alunoService;

	public List<AulaRealizadaImpl> getAulasRealizadas() {
		return aulaRealizadaDAO.getAulasRealizadas();
	}

	// montar a primeira aula do curso
	@Transactional
	public void montarPrimeiraAulaRealizada(String email) {
		ModuloImpl modulo = moduloService.getPrimeiroModulo();

		AulaImpl aula = aulaService.getPrimeiraAula(modulo.getId_modulo());

		AlunoImpl aluno = alunoService.getUsuario(email);

		AulaRealizadaImpl aulaRealizada = new AulaRealizadaImpl(aula.getId_aula(), aluno.getId_usuario());

		aulaRealizadaDAO.savePrimeiraAulaRealizada(aulaRealizada);
	}

	// buscar proxima aula
	@Transactional
	public AulaRealizadaImpl getProximaAula() {
		return aulaRealizadaDAO.getProximaAula();
	}

	// buscar aula_realizada por ID
	@Transactional
	public AulaRealizadaImpl getAulaRealizada(Integer id_aula_realizada) {
		return aulaRealizadaDAO.getAulaRealizada(id_aula_realizada);
	}

	// atualiza SID da aula quando a chamada � iniciada
	@Transactional
	public void updateAulaRealizada(int id_aula, String callSid) {
		aulaRealizadaDAO.updateAulaRealizada(id_aula, callSid);

	}

	// atualiza dados da aula quando a chamada � finalizada
	@Transactional
	public void updateAulaRealizada(String callSid, String callDuration, Status status, String startTimeConv,
			String endTimeConv, BigDecimal price) {
		aulaRealizadaDAO.updateAulaRealizada(callSid, callDuration, status, startTimeConv, endTimeConv, price);
		
	}

	// encerrar aula apos concluida pelo professor
	@Transactional
	public void concludedAulaRealizada(AulaRealizadaImpl aulaRealizadaAtual) {

		System.out.println("concludedAulaRealizada service");

		// salvas os dados na aula atual
		aulaRealizadaDAO.concludedAulaRealizada(aulaRealizadaAtual);

	}

	// metodo para criar proxima aula realizada, baseado na aula anterior
	@Transactional
	public void createProximaAulaRealizada(AulaRealizadaImpl proximaAulaRealizada) {
		 aulaRealizadaDAO.saveProximaAulaRealizada(proximaAulaRealizada);
	}

	@Transactional
	public List<AulaRealizadaImpl> getAulasRealizadasAluno(int id_aluno) {
		System.out.println("service aula realizada "+ id_aluno);
		return aulaRealizadaDAO.getAulasRealizadasAluno(id_aluno);
	}

	/**
	 * Salva dados da grava��o da chamada no banco
	 * @param callSid
	 * @param recordingUrl
	 */
	@Transactional
	public void updateAulaRealizada(String callSid, String recordingUrl) {
		aulaRealizadaDAO.updateAulaRealizada(callSid, recordingUrl);
		
	}

	/**
	 * faz update do status e professor da aula realizada
	 * @param id_aula_realizada
	 * @param id_professor_aula_realizada
	 */
	@Transactional
	public void updateProfessorAulaRealizada(int id_aula_realizada, Integer id_professor_aula_realizada) {
		aulaRealizadaDAO.updateProfessorAulaRealizada(id_aula_realizada, id_professor_aula_realizada);
	}

}
