/**
* classe para ProfessorServiceImpl
* @author Hullo Team 
* @version 1.0
 */

package com.hullo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hullo.dao.UsuarioDAO;
import com.hullo.entity.ProfessorImpl;


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
	public ProfessorImpl getUsuario(String email, String senha) {
		System.out.println("chegou no service");
		return professorDAO.getUsuario(email, senha);
	}
	
	
	@Override
	@Transactional
	public ProfessorImpl getUsuario(int id_usuario) {
		return professorDAO.getUsuario(id_usuario);
	}

	@Override
	@Transactional
	public ProfessorImpl validaUsuario(String email, int id_usuario) {
		return professorDAO.validaUsuario(email, id_usuario);
	}
	
	@Override
	@Transactional
	public ProfessorImpl getUsuario(String email) {
		return professorDAO.getUsuario(email);
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

	@Override
	@Transactional
	public ProfessorImpl validaUsuario(String email, String cpf) {
		return professorDAO.validaUsuario(email, cpf);
	}


}
