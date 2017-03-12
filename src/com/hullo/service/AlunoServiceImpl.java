package com.hullo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hullo.dao.UsuarioDAO;
import com.hullo.entity.AlunoImpl;
import com.hullo.entity.Usuario;
import com.hullo.entity.UsuarioImpl;

@Service
public class AlunoServiceImpl implements UsuarioService<AlunoImpl> {

	@Autowired
	@Qualifier("alunoDAOImpl")
	private UsuarioDAO<AlunoImpl> alunoDAO;
	
	@Override
	@Transactional
	public List<AlunoImpl> getUsuarios() {
		return alunoDAO.getUsuarios();
	}

	@Override
	@Transactional
	public void saveUsuario(AlunoImpl theUsuario) {
		alunoDAO.saveUsuario(theUsuario);
	}

	@Override
	public UsuarioImpl getUsuario(String email, String senha) {
		return null;
		
	}

	@Override
	public Usuario getUsuario(int id_usuario) {
		// TODO Auto-generated method stub
		return null;
	}



}
