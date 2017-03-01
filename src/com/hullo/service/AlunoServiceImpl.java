package com.hullo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.hullo.dao.AlunoDAO;
import com.hullo.entity.Usuario;

public class AlunoServiceImpl implements AlunoService {

	@Autowired
	private AlunoDAO alunoDAO;
	
	@Override
	@Transactional
	public List<Usuario> getUsuarios() {
		return alunoDAO.getUsuarios();
	}

	@Override
	@Transactional
	public void saveUsuario(Usuario theUsuario) {
		alunoDAO.saveUsuario(theUsuario);
	}

}
