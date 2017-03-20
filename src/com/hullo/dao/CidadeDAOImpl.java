package com.hullo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hullo.entity.CidadeImpl;
import com.hullo.entity.EstadoImpl;

@Repository
public class CidadeDAOImpl {

	@Autowired
	private SessionFactory sessionFactory;
	
	public List<CidadeImpl> getCidades() {
		Session currentSession = sessionFactory.getCurrentSession();

		Query<CidadeImpl> theQuery = currentSession.createQuery("from CidadeImpl order by nm_cidade", CidadeImpl.class);

		List<CidadeImpl> cidades = theQuery.getResultList();
		
		return cidades;
	}
	
	public List<CidadeImpl> getCidades(EstadoImpl estado) {
		Session currentSession = sessionFactory.getCurrentSession();

		Query<CidadeImpl> theQuery = currentSession.createQuery("from CidadeImpl where id_Estado = "+ estado.getId_estado() +" order by nm_cidade", CidadeImpl.class);

		List<CidadeImpl> cidades = theQuery.getResultList();
		
		return cidades;
	}

}
