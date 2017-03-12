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
	public UsuarioImpl getUsuario(String email, String senha) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UsuarioImpl getUsuario(int id) {
		// TODO Auto-generated method stub
		return null;
	}




}
