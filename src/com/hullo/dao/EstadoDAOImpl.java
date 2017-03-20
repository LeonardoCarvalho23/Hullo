package com.hullo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hullo.entity.EstadoImpl;

@Repository
public class EstadoDAOImpl {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<EstadoImpl> getEstados() {
		Session currentSession = sessionFactory.getCurrentSession();

		Query<EstadoImpl> theQuery = currentSession.createQuery("from EstadoImpl order by nm_estado", EstadoImpl.class);

		List<EstadoImpl> estados = theQuery.getResultList();
		
		return estados;
	}

}
