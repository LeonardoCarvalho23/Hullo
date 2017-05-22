package com.hullo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hullo.dao.UsuarioDAO;
import com.hullo.entity.ProfessorImpl;

/**
* classe para ProfessorServiceImpl
* @author Hullo Team 
* @version 1.0
 */

@Service
public class ProfessorServiceImpl implements UsuarioService<ProfessorImpl> {

	@Autowired
	@Qualifier("professorDAOImpl")
	private UsuarioDAO<ProfessorImpl> professorDAO;
	
	/**
	 * para listar professores
	 * @return List<ProfessorImpl>
	 */
	@Override
	@Transactional
	public List<ProfessorImpl> getUsuarios() {
		return professorDAO.getUsuarios();
	}

	/**
	 * para salvar professores
	 * @param ProfessorImpl
	 */
	@Override
	@Transactional
	public void saveUsuario(ProfessorImpl theUsuario) {
		professorDAO.saveUsuario(theUsuario);

	}
	
	/**
	 * para buscar professores login
	 * @param email
	 * @param senha
	 * @return ProfessorImpl
	 */
	@Override
	@Transactional
	public ProfessorImpl getUsuario(String email, String senha) {
		return professorDAO.getUsuario(email, senha);
	}
	
	/**
	 * para buscar professores por id
	 * @param id
	 * @return ProfessorImpl
	 */
	@Override
	@Transactional
	public ProfessorImpl getUsuario(int id_usuario) {
		return professorDAO.getUsuario(id_usuario);
	}

	/**
	 * para validar professores 
	 * @param email
	 * @param id
	 * @return ProfessorImpl
	 */
	@Override
	@Transactional
	public ProfessorImpl validaUsuario(String email, int id_usuario) {
		return professorDAO.validaUsuario(email, id_usuario);
	}
	
	/**
	 * para buscar professores por email
	 * @param email
	 * @return ProfessorImpl
	 */
	@Override
	@Transactional
	public ProfessorImpl getUsuario(String email) {
		return professorDAO.getUsuario(email);
	}

	/**
	 * para atualizar professores
	 * @param ProfessorImpl
	 */
	@Override
	@Transactional
	public void updateUsuario(ProfessorImpl theUsuario) {
		professorDAO.udateUsuario(theUsuario);
	}

	/**
	 * para inativar professores
	 * @param ProfessorImpl
	 */
	@Override
	@Transactional
	public void inactivateUsuario(ProfessorImpl theUsuario) {
		professorDAO.inactivateUsuario(theUsuario);
		
	}

	/**
	 * para validar professores por email e cpf
	 * @param email
	 * @param cpf
	 * @return ProfessorImpl
	 */
	@Override
	@Transactional
	public ProfessorImpl validaUsuario(String email, String cpf) {
		return professorDAO.validaUsuario(email, cpf);
	}


}
