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

		Query<ProfessorImpl> theQuery = 
				currentSession.createQuery("from ProfessorImpl where tipo_usuario = 'professor' order by nome_usuario", ProfessorImpl.class);

		List<ProfessorImpl> usuarios = theQuery.getResultList();

		return usuarios;
	}

	@Override
	public void saveUsuario(ProfessorImpl theUsuario) {

		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.saveOrUpdate(theUsuario);
	}

	@Override
	public ProfessorImpl getUsuario(String email, String senha) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProfessorImpl getUsuario(int id) {
		//get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// Cria query que faz busca no banco
		Query<ProfessorImpl> theQuery;
		theQuery = currentSession.createQuery("from ProfessorImpl where id_usuario='" + id + "'", ProfessorImpl.class);
		
		//executa query
		ProfessorImpl theUsuario = theQuery.getSingleResult();
		
		return theUsuario;
	}

	@Override
	public ProfessorImpl getUsuario(String email) {
		// TODO Auto-generated method stub
		return null;
	}


}
