package com.hullo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hullo.dao.ModuloDAOImpl;
import com.hullo.entity.ModuloImpl;

@Service
public class ModuloServiceImpl {
	
	@Autowired
	private ModuloDAOImpl moduloDAO;
	
	@Transactional
	public void saveModulo(ModuloImpl modulo) {
		moduloDAO.saveModulo(modulo);
	}

	@Transactional
	public List<ModuloImpl> getModulos() {
		return moduloDAO.getModulos();
	}

	@Transactional
	public List<ModuloImpl> getModulos(String nomeBusca) {
		return moduloDAO.getModulos(nomeBusca);
	}

}
