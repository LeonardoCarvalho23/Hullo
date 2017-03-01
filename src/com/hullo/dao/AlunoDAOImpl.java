package com.hullo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hullo.entity.Usuario;

public class AlunoDAOImpl implements AlunoDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Usuario> getUsuarios() {
		Session currentSession = sessionFactory.getCurrentSession();

		Query<Usuario> theQuery = currentSession.createQuery("from Usuario order by nome_usuario where tipo_usuario = 'aluno'", Usuario.class);

		List<Usuario> usuarios = theQuery.getResultList();

		return usuarios;
	}

	@Override
	public void saveUsuario(Usuario theUsuario) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.save(theUsuario);
	}

}
