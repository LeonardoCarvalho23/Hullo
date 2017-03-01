package com.hullo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.hullo.dao.ProfessorDAO;
import com.hullo.entity.Usuario;

public class ProfessorServiceImpl implements ProfessorService {

	@Autowired
	private ProfessorDAO professorDAO;
	
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
