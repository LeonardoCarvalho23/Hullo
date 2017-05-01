package com.hullo.service;

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

}
