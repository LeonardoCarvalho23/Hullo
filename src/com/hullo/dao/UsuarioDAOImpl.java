package com.hullo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hullo.entity.Usuario;

@Repository
public class UsuarioDAOImpl implements UsuarioDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	//@Transactional //so you don't need to start and commit, import from spring
	public List<Usuario> getUsuarios() {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession(); //import from hibernate
		
		//create a query, import hibernate.query
		Query<Usuario> theQuery = 
				currentSession.createQuery("from Usuario order by nome_usuario", Usuario.class);
		
		//execute query and get result list
		List<Usuario> usuarios = theQuery.getResultList();
		
		//return the results
		return usuarios;
	}

	@Override
	public void saveUsuario(Usuario theUsuario) {
		//get current hibernate session	
		Session currentSession = sessionFactory.getCurrentSession();
		
		//save the usuario
		currentSession.save(theUsuario);
	}

}
