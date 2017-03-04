package com.hullo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hullo.entity.UsuarioImpl;

@Repository
public class UsuarioDAOImpl implements UsuarioDAO<UsuarioImpl> {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	//@Transactional //so you don't need to start and commit, import from spring
	public List<UsuarioImpl> getUsuarios() {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession(); //import from hibernate
		
		//create a query, import hibernate.query
		Query<UsuarioImpl> theQuery = 
				currentSession.createQuery("from UsuarioImpl order by nome_usuario", UsuarioImpl.class);
		
		//execute query and get result list
		List<UsuarioImpl> usuarios = theQuery.getResultList();
		
		//return the results
		return usuarios;
	}

	@Override
	public void saveUsuario(UsuarioImpl theUsuario) {
		//get current hibernate session	
		Session currentSession = sessionFactory.getCurrentSession();
		
		//save the usuario
		currentSession.save(theUsuario);
	}

}
