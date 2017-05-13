package com.hullo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hullo.dao.UsuarioDAO;
import com.hullo.entity.AlunoImpl;

/**
 * serviço do objeto AlunoImpl
 * 
 * @author Hullo Team
 * @version 1.0
 */
@Service
public class AlunoServiceImpl implements UsuarioService<AlunoImpl> {

	@Autowired
	@Qualifier("alunoDAOImpl")
	private UsuarioDAO<AlunoImpl> alunoDAO;

	/**
	 * para exibir lista de usuarios do tipo aluno cadastrados no banco
	 * 
	 * @return lista de alunos
	 */
	@Override
	@Transactional
	public List<AlunoImpl> getUsuarios() {
		return alunoDAO.getUsuarios();
	}

	/**
	 * salvar aluno na hora de cadastrar
	 * 
	 * @param theUsuario
	 *            aluno que acaba de se cadastrar
	 */
	@Override
	@Transactional
	public void saveUsuario(AlunoImpl theUsuario) {
		alunoDAO.saveUsuario(theUsuario);
	}

	/**
	 * buscar usuario para login
	 * 
	 * @param email
	 * @param senha
	 * @return aluno se colocou credenciais corretas
	 */
	@Override
	@Transactional
	public AlunoImpl getUsuario(String email, String senha) {
		return alunoDAO.getUsuario(email, senha);
	}

	/**
	 * buscar todos os dados de determinado aluno
	 * 
	 * @param id_usuario
	 * @return objeto aluno
	 */
	@Override
	@Transactional
	public AlunoImpl getUsuario(int id_usuario) {
		return alunoDAO.getUsuario(id_usuario);
	}

	/**
	 * buscar todos os dados de determinado aluno
	 * 
	 * @param email
	 * @return objeto aluno
	 */
	@Override
	@Transactional
	public AlunoImpl getUsuario(String email) {
		return alunoDAO.getUsuario(email);
	}

	/**
	 * fazer update de alguns dados do aluno
	 * 
	 * @param theUsuario
	 */
	@Override
	@Transactional
	public void updateUsuario(AlunoImpl theUsuario) {
		alunoDAO.udateUsuario(theUsuario);
	}

	/**
	 * inativar aluno no banco
	 * 
	 * @param theUsuario
	 */
	@Override
	@Transactional
	public void inactivateUsuario(AlunoImpl theUsuario) {
		alunoDAO.inactivateUsuario(theUsuario);
	}

	/**
	 * validar usuario
	 * 
	 * @param email
	 * @param id
	 * @return se tem o tal aluno
	 */
	@Override
	@Transactional
	public AlunoImpl validaUsuario(String email, int id) {
		return alunoDAO.validaUsuario(email, id);
	}

	/**
	 * validar usuario por email e CPF informados
	 * 
	 * @param email
	 * @param cpf
	 * @return se tem o tal aluno
	 */
	@Override
	@Transactional
	public AlunoImpl validaUsuario(String email, String cpf) {
		return alunoDAO.validaUsuario(email, cpf);
	}

}
