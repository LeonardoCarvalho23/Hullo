package com.hullo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hullo.entity.AlunoImpl;
import com.hullo.entity.UsuarioImpl;

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

	@Override
	public AlunoImpl getUsuario(String email, String cpf) {
		
		//get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		System.out.println("chegou no DAO");
		
				
		// Cria query que faz busca no banco
		Query<AlunoImpl> theQuery;
		theQuery = currentSession.createQuery("from AlunoImpl where tipo_usuario = 'aluno' and email_usuario = '" + email + 
				"' or cpf_usuario = '" + cpf + "'", AlunoImpl.class);
		
		try {
			AlunoImpl validaAluno = theQuery.getSingleResult();
			
			System.out.println("fez a query");
			return validaAluno;
			
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public UsuarioImpl getUsuario(int id) {
		// TODO Auto-generated method stub
		return null;
	}




}
