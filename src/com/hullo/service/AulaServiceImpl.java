package com.hullo.service;

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
		aulaDAO.saveModulo(aula);
	}
}
