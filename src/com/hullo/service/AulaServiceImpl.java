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

	/*public boolean validaAula(float indice_aula) {
		return aulaDAO.validaAula(indice_aula, id_aula);
	}*/
}
