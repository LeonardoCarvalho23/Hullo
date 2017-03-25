package com.hullo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hullo.dao.UsuarioDAO;
import com.hullo.entity.UsuarioImpl;

@Service
public class UsuarioServiceImpl implements UsuarioService<UsuarioImpl> {
	
	@Autowired
	@Qualifier("usuarioDAOImpl")
	private UsuarioDAO<UsuarioImpl> usuarioDAO;
	
	@Override
	@Transactional
	public List<UsuarioImpl> getUsuarios() {
		return usuarioDAO.getUsuarios();
	}

	@Override
	@Transactional
	public void saveUsuario(UsuarioImpl oUsuario) {
		usuarioDAO.saveUsuario(oUsuario);
	}

	@Override
	@Transactional
	public UsuarioImpl getUsuario(String email, String senha) {
		
		return usuarioDAO.getUsuario(email, senha);
		
	}
	
	@Override
	@Transactional
	public UsuarioImpl getUsuario(int id){
		return usuarioDAO.getUsuario(id);
	}

	@Override
	@Transactional
	public UsuarioImpl getUsuario(String email) {
		return usuarioDAO.getUsuario(email);
	}

	//implementado por orbigacao
	@Override
	@Transactional
	public void updateUsuario(UsuarioImpl theUsuario) {
	}
		
	@Override
	@Transactional
	public void inactivateUsuario(UsuarioImpl theUsuario){		
	}

	@Override
	public UsuarioImpl validaUsuario(String email, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UsuarioImpl validaUsuario(String email, String cpf) {
		// TODO Auto-generated method stub
		return null;
	}

}
