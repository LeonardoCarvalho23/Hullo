package com.hullo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hullo.entity.Usuario;

@Repository
public class ProfessorDAOImpl implements UsuarioDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Usuario> getUsuarios() {

		Session currentSession = sessionFactory.getCurrentSession();

		Query<Usuario> theQuery = 
				currentSession.createQuery("from Usuario where tipo_usuario = 'professor' order by nome_usuario", Usuario.class);

		List<Usuario> usuarios = theQuery.getResultList();

		return usuarios;
	}

	@Override
	public void saveUsuario(Usuario theUsuario) {

		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.save(theUsuario);
	}

}
