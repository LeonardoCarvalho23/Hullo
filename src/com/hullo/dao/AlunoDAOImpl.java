package com.hullo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hullo.entity.AlunoImpl;

/**
 * classe para operações no banco relacionadas ao AlunoImpl
 * 
 * @author Hullo Team
 * @version 1.0
 */

@Repository
public class AlunoDAOImpl implements UsuarioDAO<AlunoImpl> {

	/**
	 * Objeto session que administra conexão com o banco
	 */
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * descrição do método
	 * 
	 * @return lista com todos os alunos do banco
	 */
	@Override
	public List<AlunoImpl> getUsuarios() {
		Session currentSession = sessionFactory.getCurrentSession();

		Query<AlunoImpl> theQuery = currentSession.createQuery("from AlunoImpl order by nome_usuario", AlunoImpl.class);

		List<AlunoImpl> usuarios = theQuery.getResultList();

		return usuarios;
	}

	/**
	 * salvar objeto aluno no banco
	 * 
	 * @param theUsuario
	 *            AlunoImpl que deseja salvar
	 */
	@Override
	public void saveUsuario(AlunoImpl theUsuario) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.saveOrUpdate(theUsuario);
	}

	/**
	 * validar que senha e email fronecidos pelo usuario para login estao
	 * corretos
	 * 
	 * @param email
	 *            email fornecido por usuario
	 * @param senha
	 *            senha fornecida por usuario
	 * @return AlunoImpl se foi encontrado aluno com esses dados
	 */
	@Override
	public AlunoImpl getUsuario(String email, String senha) {

		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// Cria query que faz busca no banco
		Query<AlunoImpl> theQuery;
		theQuery = currentSession.createQuery("from AlunoImpl where ativo_usuario = '1' and senha_usuario='" + senha
				+ "' and email_usuario='" + email + "'", AlunoImpl.class);

		// Testa com try catch a execução da query e se foi encontrado algo, é
		// obrigatorio o uso do try catch
		AlunoImpl result = null;

		try {
			result = theQuery.getSingleResult();
			return result;
		} catch (Exception e) {
			return result;
		}
	}

	/**
	 * validar se ja existe aluno cadatsrado com esses dados
	 * 
	 * @param email
	 *            email fornecido por usuario
	 * @param cpf
	 *            cpf fornecidoF por usuario
	 * @return AlunoImpl se foi encontrado aluno com esses dados
	 */
	@Override
	public AlunoImpl validaUsuario(String email, String cpf) {

		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// Cria query que faz busca no banco
		Query<AlunoImpl> theQuery;

		theQuery = currentSession.createQuery(
				"from AlunoImpl where email_usuario = '" + email + "' or cpf_usuario = '" + cpf + "'", AlunoImpl.class);
		System.out.println("Chegou no validaUsuario do AlunoDAO");
		try {
			// ve se tem mais de um aluno com os dados
			List<AlunoImpl> alunos = theQuery.getResultList();
			// devolve o primeiro da lista
			AlunoImpl validaAluno = alunos.get(0);
			return validaAluno;

		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * buscar objeto AlunoImpl baseado no seu ID
	 * 
	 * @param id_usuario
	 *            id usuario
	 * @return AlunoImpl se foi encontrado aluno com esse dado
	 */
	@Override
	public AlunoImpl getUsuario(int id_usuario) {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// Cria query que faz busca no banco
		Query<AlunoImpl> theQuery;
		theQuery = currentSession.createQuery("from AlunoImpl where id_usuario='" + id_usuario + "'", AlunoImpl.class);

		// executa query
		AlunoImpl theUsuario = theQuery.getSingleResult();

		return theUsuario;
	}

	/**
	 * validar na troca de email, se email ja existe em outro cadastro
	 * 
	 * @param id
	 *            id do usuario que quer fazer udpate de email
	 * @param email
	 *            novo email fornecido pelo usuario
	 * @return AlunoImpl se foi encontrado aluno com esse dado
	 */
	public AlunoImpl validaUsuario(String email, int id) {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// Cria query que faz busca no banco pelo email colocado
		Query<AlunoImpl> theQuery;
		// faz busca por outro aluno que tenha o email digitado
		theQuery = currentSession.createQuery(
				"from AlunoImpl where email_usuario = '" + email + "' and id_usuario <> '" + id + "'", AlunoImpl.class);

		try {

			AlunoImpl validaAluno = theQuery.getSingleResult();
			return validaAluno;

		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * fazer update de aluno ja existente, apenas de campos editaveis
	 * 
	 * @param theUsuario
	 *            AlunoImpl que irei atualizar
	 */
	// para fazer update do aluno ja existente
	@SuppressWarnings("unchecked")
	@Override
	public void udateUsuario(AlunoImpl theUsuario) {

		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// Cria query que faz busca no banco
		Query<AlunoImpl> theQuery;

		// para fazer update apenas dos capos editaveis
		String hql = "UPDATE AlunoImpl set nome_usuario = :nome, sobrenome_usuario = :sobrenome, email_usuario = :email, "
				+ "senha_usuario = :senha, sexo_usuario = :sexo, data_nascimento_usuario = :data, telefone_usuario = :telefone, "
				+ "profissao_usuario = :profissao, dt_last_update_usuario = :lastUpdate " + "WHERE id_usuario = :id";
		theQuery = currentSession.createQuery(hql);

		// adicionando valores para as variaveis do update
		theQuery.setParameter("nome", theUsuario.getNome_usuario());
		theQuery.setParameter("sobrenome", theUsuario.getSobrenome_usuario());
		theQuery.setParameter("email", theUsuario.getEmail_usuario());
		theQuery.setParameter("senha", theUsuario.getSenha_usuario());
		theQuery.setParameter("sexo", theUsuario.getSexo_usuario());
		theQuery.setParameter("data", theUsuario.getData_nascimento_usuario());
		theQuery.setParameter("profissao", theUsuario.getProfissao_usuario());
		theQuery.setParameter("telefone", theUsuario.getTelefone_usuario());
		theQuery.setParameter("lastUpdate", theUsuario.getDt_last_update_usuario());

		theQuery.setParameter("id", theUsuario.getId_usuario());

		int result = theQuery.executeUpdate();

		System.out.println(result + " linha atualizada");
	}

	/**
	 * inativar um aluno que não quer mias ter conta
	 * 
	 * @param theUsuario
	 *            AlunoImpl que dejesa encerrar conta
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void inactivateUsuario(AlunoImpl theUsuario) {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// Cria query que faz busca no banco
		Query<AlunoImpl> theQuery;

		// para fazer update apenas dos capos editaveis
		String hql = "UPDATE AlunoImpl set ativo_usuario = 0, dt_last_update_usuario = :lastUpdate "
				+ "WHERE id_usuario = :id";

		theQuery = currentSession.createQuery(hql);
		theQuery.setParameter("lastUpdate", theUsuario.getDt_last_update_usuario());
		theQuery.setParameter("id", theUsuario.getId_usuario());

		int result = theQuery.executeUpdate();

		System.out.println(result + " linha atualizada");
	}

	/**
	 * buscar aluno baseado apenas no email
	 * 
	 * @param email
	 *            email que estou fazendo a busca
	 * @return AlunoImpl se foi encontrado aluno com esse dado
	 */
	@Override
	public AlunoImpl getUsuario(String email) {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// Cria query que faz busca no banco
		Query<AlunoImpl> theQuery;
		theQuery = currentSession.createQuery("from AlunoImpl where email_usuario='" + email + "'", AlunoImpl.class);

		// Testa com try catch a execução da query e se foi encontrado algo, é
		// obrigatorio o uso do try catch
		AlunoImpl result = null;

		try {
			result = theQuery.getSingleResult();
			return result;
		} catch (Exception e) {
			return result;
		}
	}

}
