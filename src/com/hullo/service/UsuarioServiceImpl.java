package com.hullo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hullo.dao.UsuarioDAO;
import com.hullo.entity.UsuarioImpl;

/**
* classe para UsuarioServiceImpl
* @author Hullo Team 
* @version 1.0
 */

@Service
public class UsuarioServiceImpl implements UsuarioService<UsuarioImpl> {
	
	@Autowired
	@Qualifier("usuarioDAOImpl")
	private UsuarioDAO<UsuarioImpl> usuarioDAO;
	
	/**
	 * metodo lista usuarios
	 * @return usuarioDAO.getUsuarios()
	 */
	@Override
	@Transactional
	public List<UsuarioImpl> getUsuarios() {
		return usuarioDAO.getUsuarios();
	}

	/**
	 * metodo para salvar
	 * @param oUsuario
	 * @return usuarioDAO.saveUsuario(oUsuario)
	 */
	@Override
	@Transactional
	public void saveUsuario(UsuarioImpl oUsuario) {
		usuarioDAO.saveUsuario(oUsuario);
	}

	/**
	 * metodo para buscar no login
	 * @param email
	 * @param senha
	 * @return usuarioDAO.getUsuario(email, senha)
	 */
	@Override
	@Transactional
	public UsuarioImpl getUsuario(String email, String senha) {
		
		return usuarioDAO.getUsuario(email, senha);
		
	}
	
	/**
	 * metodo para buscar usuario por id
	 * @param id
	 * @return usuarioDAO.getUsuario(id)
	 */
	@Override
	@Transactional
	public UsuarioImpl getUsuario(int id){
		return usuarioDAO.getUsuario(id);
	}

	/**
	 * metodo para buscar usuario por email
	 * @param email
	 * @return usuarioDAO.getUsuario(email)
	 */
	@Override
	@Transactional
	public UsuarioImpl getUsuario(String email) {
		return usuarioDAO.getUsuario(email);
	}

	/**
	 * metodo para atualizar usuario
	 * @param theUsuario
	 */
	@Override
	@Transactional
	public void updateUsuario(UsuarioImpl theUsuario) {
	}
		
	/**
	 * metodo para inativar usuario
	 * @param theUsuario
	 */
	@Override
	@Transactional
	public void inactivateUsuario(UsuarioImpl theUsuario){		
	}

	/**
	 * metodo valida usuario
	 * @param email
	 * @param id
	 * @return null
	 */
	@Override
	public UsuarioImpl validaUsuario(String email, int id) {
		/* **
		 *  TODO Auto-generated method stub
		 */
		return null;
	}

	/**
	 * metodo validacao na hora de cadastrar
	 * @param email
	 * @param cpf
	 * @return null
	 */
	@Override
	public UsuarioImpl validaUsuario(String email, String cpf) {
		/* **
		 *  TODO Auto-generated method stub
		 */
		return null;
	}

}
