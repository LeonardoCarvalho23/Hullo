package com.hullo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hullo.dao.UsuarioDAO;
import com.hullo.entity.AlunoImpl;

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
	@Transactional
	public AlunoImpl getUsuario(String email, String cpf) {
		return alunoDAO.getUsuario(email, cpf);
	}

	@Override
	@Transactional
	public AlunoImpl getUsuario(int id_usuario) {
		return alunoDAO.getUsuario(id_usuario);
	}

	@Override
	@Transactional
	public AlunoImpl getUsuario(String email) {
		return alunoDAO.getUsuario(email);
	}

	@Override
	@Transactional
	public void updateUsuario(AlunoImpl theUsuario) {
		alunoDAO.udateUsuario(theUsuario);
	}

	@Override
	@Transactional
	public void inactivateUsuario(AlunoImpl theUsuario){
		alunoDAO.inactivateUsuario(theUsuario);
	}

	@Override
	@Transactional
	public AlunoImpl validaUsuario(String email, int id) {
		return alunoDAO.validaUsuario(email, id);
	}

	@Override
	@Transactional
	public AlunoImpl validaUsuario(String email, String cpf) {
		return alunoDAO.validaUsuario(email, cpf);
	}

}
