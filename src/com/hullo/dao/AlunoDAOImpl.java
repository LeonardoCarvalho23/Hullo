package com.hullo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hullo.entity.AlunoImpl;

@Repository
public class AlunoDAOImpl implements UsuarioDAO<AlunoImpl> {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<AlunoImpl> getUsuarios() {
		Session currentSession = sessionFactory.getCurrentSession();

		Query<AlunoImpl> theQuery = currentSession.createQuery("from AlunoImpl where tipo_usuario = 'aluno' order by nome_usuario", AlunoImpl.class);

		List<AlunoImpl> usuarios = theQuery.getResultList();

		return usuarios;
	}

	@Override
	public void saveUsuario(AlunoImpl theUsuario) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.saveOrUpdate(theUsuario);
	}

	//metodo para validar se o aluno ja existe no banco
	@Override
	public AlunoImpl getUsuario(String email, String cpf) {
		
		//get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// Cria query que faz busca no banco
		Query<AlunoImpl> theQuery;
		//tirei a busca por aluno para nao correr o risco de um prof se cadastrar como aluno e zoar o barraco
		theQuery = currentSession.createQuery("from AlunoImpl where email_usuario = '" + email + "' or cpf_usuario = '" + cpf + "'", AlunoImpl.class);
		
		try {
			//ve se tem mais de um aluno com os dados
			List<AlunoImpl> alunos = theQuery.getResultList();
			//devolve o primeiro da lista
			AlunoImpl validaAluno = alunos.get(0);
			return validaAluno;
			
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public AlunoImpl getUsuario(int id_usuario) {
		//get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// Cria query que faz busca no banco
		Query<AlunoImpl> theQuery;
		theQuery = currentSession.createQuery("from AlunoImpl where id_usuario='" + id_usuario + "'", AlunoImpl.class);
		
		//executa query
		AlunoImpl theUsuario = theQuery.getSingleResult();
		
		return theUsuario;
	}

	@Override
	public AlunoImpl getUsuario(String email) {
		// TODO Auto-generated method stub
		return null;
	}




}
