package com.hullo.service;

import java.math.BigDecimal;
import java.util.List;

import org.joda.time.DateTime;
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
	
	public List<AulaRealizadaImpl> getAulasRealizadas(){
		return aulaRealizadaDAO.getAulasRealizadas();
	}
	
/*	@Transactional
	public void saveAulaRealizada(AulaRealizadaImpl aulaRealizada)
	{
		aulaRealizadaDAO.saveAulaRealizada(aulaRealizada);
	}*/
	
	//montar a primeira aula do curso
	@Transactional
	public void montarPrimeiraAulaRealizada(String email){
		ModuloImpl modulo = moduloService.getPrimeiroModulo();
		
		AulaImpl aula = aulaService.getPrimeiraAula(modulo.getId_modulo());
		
		AlunoImpl aluno = alunoService.getUsuario(email);
		
		AulaRealizadaImpl aulaRealizada = new AulaRealizadaImpl(aula.getId_aula(),aluno.getId_usuario());
		
		aulaRealizadaDAO.savePrimeiraAulaRealizada(aulaRealizada);
	}
	
	//buscar proxima aula
	@Transactional
	public AulaRealizadaImpl getProximaAula() {		
		return aulaRealizadaDAO.getProximaAula();
	}

	//buscar aula_realizada por ID
	@Transactional
	public AulaRealizadaImpl getAulaRealizada(Integer id_aula_realizada) {
		return aulaRealizadaDAO.getAulaRealizada(id_aula_realizada);
	}
	
	//atualiza SID da aula quando a chamada é iniciada
	@Transactional
	public void updateAulaRealizada(int id_aula, String callSid) {
		aulaRealizadaDAO.updateAulaRealizada(id_aula, callSid);
		
	}
	
	//atualiza dados da aula quando a chamada é finalizada
	@Transactional
	public void updateAulaRealizada(String callSid, String callDuration, Status status, DateTime startTime,
			DateTime endTime, BigDecimal price) {
		aulaRealizadaDAO.updateAulaRealizada(callSid, callDuration, status, startTime, endTime, price);
		
	}
	
	/* METODO ENCERRAR AULA + CRIAR NOVA AULA
	
	@Transactional
	public void concludedAulaRealizada(AulaRealizadaImpl aulaRealizadaAtual) {
		
		System.out.println("concludedAulaRealizada service");
		
		// salvas os dados na aula atual
		aulaRealizadaDAO.concludedAulaRealizada(aulaRealizadaAtual);		
			
	}
	
	//descobrir qual numero e indice
	
	@Transactional
	public void createProximaAulaRealizada(AulaRealizadaImpl aulaRealizadaAtual) {
	
			AulaImpl aulaAtual = new AulaImpl();
			aulaAtual.setId_aula(aulaRealizadaAtual.getId_aula_aula_realizada());
			
			System.out.println("getId_aula_aula_realizada" + aulaRealizadaAtual.getId_aula_aula_realizada());
				
			AulaImpl proximaAula = new AulaImpl(); 
			AulaRealizadaImpl proximaAulaRealizada = new AulaRealizadaImpl();
			// cria proxima aula
			if(aulaRealizadaAtual.getNota_model_aula_realizada() >=3 && 
			   aulaRealizadaAtual.getNota_practice_aula_realizada() >=3 && 
			   aulaRealizadaAtual.getNota_production_aula_realizada()>=3 ){
				
				// manda o modulo e numero de aula atual pra buscar no dao o proximo
				proximaAula = aulaService.getProximoAulaPorNumero(aulaAtual.getId_modulo_aula(), aulaAtual.getNumero_aula());			
				System.out.println("entrou no if"+proximaAula.getId_aula());
				
				
			}else{
				proximaAula = aulaService.getProximoAulaPorIndice(aulaAtual.getId_modulo_aula(), aulaAtual.getNumero_aula(), aulaAtual.getIndice_aula());
				System.out.println("entrou no else"+proximaAula.getId_aula());
				
				
			}	
			
			proximaAulaRealizada.setId_aula_aula_realizada(proximaAula.getId_aula());
			proximaAulaRealizada.setId_aluno_aula_realizada(aulaRealizadaAtual.getId_aluno_aula_realizada());
			System.out.println("proxima aula "+proximaAula.getId_aula());
			
			aulaRealizadaDAO.saveProximaAulaRealizada(proximaAulaRealizada);
	}	
	*/
}
