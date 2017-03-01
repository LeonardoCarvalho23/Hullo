package com.hullo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hullo.dao.ProfessorDAOImpl;
import com.hullo.dao.UsuarioDAO;
import com.hullo.entity.Usuario;

@Service
public class ProfessorServiceImpl implements UsuarioService {

	@Autowired
	@Qualifier("professorDAOImpl")
	private UsuarioDAO professorDAO;
	
	@Override
	@Transactional
	public List<Usuario> getUsuarios() {
		return professorDAO.getUsuarios();
	}

	@Override
	@Transactional
	public void saveUsuario(Usuario theUsuario) {
		professorDAO.saveUsuario(theUsuario);

	}

}
