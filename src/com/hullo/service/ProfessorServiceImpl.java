package com.hullo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hullo.dao.UsuarioDAO;
import com.hullo.entity.ProfessorImpl;
import com.hullo.entity.UsuarioImpl;

@Service
public class ProfessorServiceImpl implements UsuarioService<ProfessorImpl> {

	@Autowired
	@Qualifier("professorDAOImpl")
	private UsuarioDAO<ProfessorImpl> professorDAO;
	
	@Override
	@Transactional
	public List<ProfessorImpl> getUsuarios() {
		return professorDAO.getUsuarios();
	}

	@Override
	@Transactional
	public void saveUsuario(ProfessorImpl theUsuario) {
		professorDAO.saveUsuario(theUsuario);

	}

}
