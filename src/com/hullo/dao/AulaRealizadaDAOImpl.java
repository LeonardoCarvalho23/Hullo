package com.hullo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.hullo.entity.AulaRealizadaImpl;

@Repository
public class AulaRealizadaDAOImpl {

	@Autowired
	private SessionFactory sessionFactory;
	
	public List<AulaRealizadaImpl>getAulasRealizadas(){
		Session currentSession = sessionFactory.getCurrentSession();

		Query<AulaRealizadaImpl> theQuery = currentSession.createQuery("from AulaRealizada", AulaRealizadaImpl.class);

		List<AulaRealizadaImpl> aulasRealizadas = theQuery.getResultList();
		
		return aulasRealizadas;
	}

	public void saveAulaRealizada(AulaRealizadaImpl aulaRealizada) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(aulaRealizada);
	}
}
