package com.hullo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hullo.entity.ProfessorImpl;

@Repository
public class ProfessorDAOImpl implements UsuarioDAO<ProfessorImpl> {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<ProfessorImpl> getUsuarios() {

		Session currentSession = sessionFactory.getCurrentSession();

		Query<ProfessorImpl> theQuery = currentSession.createQuery(
				"from ProfessorImpl order by nome_usuario", ProfessorImpl.class);

		List<ProfessorImpl> usuarios = theQuery.getResultList();

		return usuarios;
	}

	@Override
	public void saveUsuario(ProfessorImpl theUsuario) {

		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.saveOrUpdate(theUsuario);
	}

	// metodo para buscar aluno para login
	@Override
	public ProfessorImpl getUsuario(String email, String senha) {

		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		System.out.println("chegou no DAO");
		// Cria query que faz busca no banco
		Query<ProfessorImpl> theQuery;
		theQuery = currentSession.createQuery("from ProfessorImpl where ativo_usuario = '1' and senha_usuario='" + senha
				+ "' and email_usuario='" + email + "'", ProfessorImpl.class);

		// Testa com try catch a execução da query e se foi encontrado algo, é
		// obrigatorio o uso do try catch
		ProfessorImpl result = null;

		try {
			result = theQuery.getSingleResult();
			System.out.println("fez a query e achou o prof");
			return result;
		} catch (Exception e) {
			return result;
		}
	}

	@Override
	public ProfessorImpl validaUsuario(String email, String cpf) {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		System.out.println("chegou no DAO");

		// Cria query que faz busca no banco
		Query<ProfessorImpl> theQuery;
		// tirei a busca por aluno para nao correr o risco de um prof se
		// cadastrar como aluno e zoar o barraco
		theQuery = currentSession.createQuery(
				"from ProfessorImpl where email_usuario = '" + email + "' or cpf_usuario = '" + cpf + "'",
				ProfessorImpl.class);

		try {

			// ve se tem mais de um professor com os dados
			List<ProfessorImpl> professores = theQuery.getResultList();
			// devolve o primeiro da lista
			ProfessorImpl validaProfessor = professores.get(0);
			System.out.println("chegou no valida");
			return validaProfessor;

		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public ProfessorImpl getUsuario(int id) {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// Cria query que faz busca no banco
		Query<ProfessorImpl> theQuery;
		theQuery = currentSession.createQuery("from ProfessorImpl where id_usuario='" + id + "'", ProfessorImpl.class);

		// executa query
		ProfessorImpl theUsuario = theQuery.getSingleResult();

		return theUsuario;
	}

	@Override
	public ProfessorImpl getUsuario(String email) {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// Cria query que faz busca no banco
		Query<ProfessorImpl> theQuery;
		theQuery = currentSession.createQuery("from ProfessorImpl where email_usuario='" + email + "'",
				ProfessorImpl.class);

		// Testa com try catch a execução da query e se foi encontrado algo, é
		// obrigatorio o uso do try catch
		ProfessorImpl result = null;

		try {
			result = theQuery.getSingleResult();
			return result;
		} catch (Exception e) {
			return result;
		}
	}

	// para fazer update do professor
	@SuppressWarnings("unchecked")
	@Override
	public void udateUsuario(ProfessorImpl theUsuario) {

		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// Cria query que faz busca no banco
		Query<ProfessorImpl> theQuery;

		// para fazer update apenas dos capos editaveis
		String hql = "UPDATE ProfessorImpl set nome_usuario = :nome, sobrenome_usuario = :sobrenome, email_usuario = :email, "
				+ "senha_usuario = :senha, sexo_usuario = :sexo, data_nascimento_usuario = :data, telefone_usuario = :telefone, "
				+ "profissao_usuario = :profissao, cnpj_usuario = :cnpj, dt_last_update_usuario = :lastUpdate "
				+ "WHERE id_usuario = :id";
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
		theQuery.setParameter("cnpj", theUsuario.getCnpj_usuario());
		theQuery.setParameter("lastUpdate", theUsuario.getDt_last_update_usuario());

		theQuery.setParameter("id", theUsuario.getId_usuario());

		int result = theQuery.executeUpdate();

		System.out.println(result + " linha atualizada");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void inactivateUsuario(ProfessorImpl theUsuario) {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// Cria query que faz busca no banco
		Query<ProfessorImpl> theQuery;

		// para fazer update apenas dos capos editaveis
		String hql = "UPDATE ProfessorImpl set ativo_usuario = 0, dt_last_update_usuario = :lastUpdate "
				+ "WHERE id_usuario = :id";

		theQuery = currentSession.createQuery(hql);
		theQuery.setParameter("lastUpdate", theUsuario.getDt_last_update_usuario());
		theQuery.setParameter("id", theUsuario.getId_usuario());

		int result = theQuery.executeUpdate();

		System.out.println(result + " linha atualizada");
	}

	// para validar na troca de email, se email ja existe em outro cadastro
	public ProfessorImpl validaUsuario(String email, int id) {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		System.out.println("chegou na validacao de email");
		// Cria query que faz busca no banco pelo email colocado
		Query<ProfessorImpl> theQuery;
		// faz busca por outro aluno que tenha o email digitado
		theQuery = currentSession.createQuery(
				"from ProfessorImpl where email_usuario = '" + email + "' and id_usuario <> '" + id + "'",
				ProfessorImpl.class);

		try {

			ProfessorImpl validaProfessor = theQuery.getSingleResult();
			return validaProfessor;

		} catch (Exception e) {
			return null;
		}
	}

}
