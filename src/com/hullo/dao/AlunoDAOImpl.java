package com.hullo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hullo.entity.UsuarioImpl;

@Repository
public class AlunoDAOImpl implements UsuarioDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<UsuarioImpl> getUsuarios() {
		Session currentSession = sessionFactory.getCurrentSession();

		Query<UsuarioImpl> theQuery = currentSession.createQuery("from Usuario where tipo_usuario = 'aluno' order by nome_usuario", UsuarioImpl.class);

		List<UsuarioImpl> usuarios = theQuery.getResultList();

		return usuarios;
	}

	@Override
	public void saveUsuario(UsuarioImpl theUsuario) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.save(theUsuario);
	}

}
