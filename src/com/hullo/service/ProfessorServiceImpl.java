package com.hullo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hullo.dao.UsuarioDAO;
import com.hullo.entity.ProfessorImpl;
import com.hullo.entity.Usuario;


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

	@Override
	@Transactional
	public ProfessorImpl getUsuario(String email, String cpf) {
		return professorDAO.getUsuario(email, cpf);
	}
	
	
	@Override
	@Transactional
	public ProfessorImpl getUsuario(int id_usuario) {
		return professorDAO.getUsuario(id_usuario);
	}

	@Override
	public ProfessorImpl getUsuario(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void updateUsuario(ProfessorImpl theUsuario) {
		professorDAO.udateUsuario(theUsuario);
	}

	@Override
	@Transactional
	public void inactivateUsuario(ProfessorImpl theUsuario) {
		professorDAO.inactivateUsuario(theUsuario);
		
	}



}
