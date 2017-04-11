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

	@Transactional
	public boolean validaModulo(float indice_modulo) {
		return moduloDAO.validaModulo(indice_modulo);
	}

	@Transactional
	public ModuloImpl getModulo(int id_modulo) {
		return moduloDAO.getModulo(id_modulo);
	}
	
	@Transactional
	public boolean validaModulo(float indice_modulo, int id_modulo) {
		return moduloDAO.validaModulo(indice_modulo, id_modulo);
	}

	@Transactional
	public void updateModulo(ModuloImpl modulo) {
		moduloDAO.updateModulo(modulo);
		
	}

	@Transactional
	public void deleteModulo(int id_modulo) {
		moduloDAO.deleteModulo(id_modulo);
	}

}
