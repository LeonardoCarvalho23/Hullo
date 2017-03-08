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

	@Override
	public UsuarioImpl getUsuario(String email, String senha) {
		// confere se o sistema chegou até aqui
		System.out.println("UsuarioDAOImpl: o email e " + email);
		
		//get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// Retrieve user
		//UsuarioImpl theUsuario = currentSession.get(UsuarioImpl.class, email);
		
		Query<UsuarioImpl> theQuery = 
				currentSession.createQuery("from UsuarioImpl where email_usuario='" + email + "'", UsuarioImpl.class);
		
		if (theQuery.getSingleResult() == null){
			return null;
		} else {
			UsuarioImpl theUsuario = theQuery.getSingleResult();
			// confere se a busca foi realizada no banco
			System.out.println("o sobrenome do usuario e " + theUsuario.getSobrenome_usuario());
			
			// checa se email e senha conferem. 
			if (theUsuario.getSenha_usuario() == senha){
				return theUsuario;
			} else {
				return null;
		}
		
		}
		
		
	}

}
