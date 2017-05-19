package com.hullo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hullo.dao.AulaDAOImpl;
import com.hullo.entity.AulaImpl;

@Service
public class AulaServiceImpl {

	@Autowired
	private AulaDAOImpl aulaDAO;
	
	@Transactional
	public void saveAula(AulaImpl aula) {
		System.out.println("chegou no service");
		aulaDAO.saveAula(aula);
	}
	
	//para buscar todas as aulas de um modulo
	@Transactional
	public List<AulaImpl> getAulas(int id_modulo) {
		return aulaDAO.getAulas(id_modulo);
	}

	@Transactional
	public AulaImpl getAula(int id_aula) {
		return aulaDAO.getAula(id_aula);
	}

	@Transactional
	public void updateAula(AulaImpl aula) {
		aulaDAO.updateAula(aula);
	}

	@Transactional
	public AulaImpl getPrimeiraAula(int id_modulo){
		return aulaDAO.getPrimeiraAula(id_modulo);
	}
	
	@Transactional
	public AulaImpl validaAula(char indice_aula, int numero_aula, int id_modulo_aula, int id_aula) {
		return aulaDAO.validaAula(indice_aula, numero_aula, id_modulo_aula, id_aula );
	}

	@Transactional
	public void deleteAula(AulaImpl aula) {
		aulaDAO.deleteAula(aula);	
	}
	
	@Transactional
	public void deleteAulasModulo(int id_modulo){
		aulaDAO.deleteAulasModulo(id_modulo);
	}

	//metodo para buscar proxima aula linear pelo id da atual
	@Transactional
	public AulaImpl getProximaAulaLinear(int id_aula_aula_realizada) {
		return aulaDAO.getProximaAulaLinear(id_aula_aula_realizada);
	}

	@Transactional
	public AulaImpl getProximaAulaParalela(int id_aula_aula_realizada) {
		return aulaDAO.getProximaAulaParalela(id_aula_aula_realizada);
	}
	
	@Transactional
	public String getNomeAula (int id_aula){
		return aulaDAO.getNomeAula(id_aula);
	}
		
	
}
