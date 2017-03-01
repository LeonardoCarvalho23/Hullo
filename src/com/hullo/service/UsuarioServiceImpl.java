package com.hullo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hullo.dao.UsuarioDAO;
import com.hullo.entity.Usuario;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	@Qualifier("usuarioDAOImpl")
	private UsuarioDAO usuarioDAO;
	
	@Override
	@Transactional
	public List<Usuario> getUsuarios() {
		return usuarioDAO.getUsuarios();
	}

	@Override
	@Transactional
	public void saveUsuario(Usuario theUsuario) {
		usuarioDAO.saveUsuario(theUsuario);
	}

}
