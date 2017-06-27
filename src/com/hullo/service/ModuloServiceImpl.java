package com.hullo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hullo.dao.ModuloDAOImpl;
import com.hullo.entity.ModuloImpl;

/**
 * serviço do objeto ModuloServiceImpl
 * 
 * @author Hullo Team
 * @version 1.0
 */
@Service
public class ModuloServiceImpl {
	
	@Autowired
	private ModuloDAOImpl moduloDAO;
	
	/**
	 * salva modulo
	 * @param modulo
	 */
	@Transactional
	public void saveModulo(ModuloImpl modulo) {
		moduloDAO.saveModulo(modulo);
	}

	/**
	 * pega todos os modulos
	 * @return lista de ModuloImpl
	 */
	@Transactional
	public List<ModuloImpl> getModulos() {
		return moduloDAO.getModulos();
	}

	/**
	 * pega todos os modulos por nome
	 * @param nomeBusca
	 * @return lista de ModuloImpl
	 */
	@Transactional
	public List<ModuloImpl> getModulos(String nomeBusca) {
		return moduloDAO.getModulos(nomeBusca);
	}

	/**
	 * valida modulo por indice
	 * @param indice_modulo
	 * @return se modulo existe ou não
	 */
	@Transactional
	public boolean validaModulo(float indice_modulo) {
		return moduloDAO.validaModulo(indice_modulo);
	}

	/**
	 * pega por id
	 * @param id_modulo
	 * @return ModuloImpl
	 */
	@Transactional
	public ModuloImpl getModulo(int id_modulo) {
		return moduloDAO.getModulo(id_modulo);
	}
	
	/**
	 * valida por indice e id
	 * @param indice_modulo
	 * @param id_modulo
	 * @return
	 */
	@Transactional
	public boolean validaModulo(float indice_modulo, int id_modulo) {
		return moduloDAO.validaModulo(indice_modulo, id_modulo);
	}

	/**
	 * atualiza modulo
	 * @param modulo
	 */
	@Transactional
	public void updateModulo(ModuloImpl modulo) {
		moduloDAO.updateModulo(modulo);
		
	}
	
	/**
	 * pega o primeiro modulo
	 * @return
	 */
	@Transactional
	public ModuloImpl getPrimeiroModulo() {
		return moduloDAO.getPrimeiroModulo();
	}

	/**
	 * deleta modulo
	 * @param id_modulo
	 */
	@Transactional
	public void deleteModulo(int id_modulo) {
		moduloDAO.deleteModulo(id_modulo);
	}
	
	/**
	 * pega a proxima aula do usuario
	 * @param indice_modulo
	 * @return
	 */
	@Transactional
	public ModuloImpl getProxModulo(float indice_modulo) {
		return moduloDAO.getProxModulo(indice_modulo);
	}

}
