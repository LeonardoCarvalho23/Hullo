package com.hullo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hullo.entity.AulaImpl;

@Repository
public class AulaDAOImpl {

	@Autowired
	private SessionFactory sessionFactory;

	
	public boolean validaAula(float indice_aula) {
		Session currentSession = sessionFactory.getCurrentSession();

		// busca por indice do modulo
		Query<AulaImpl> query = currentSession.createQuery("from AulaImpl where indice_aula= " + indice_aula,
				AulaImpl.class);

		List<AulaImpl> result = query.getResultList();
		if (result.size() > 0) {
			return true;
		}
		return false;

	}

	public void saveAula(AulaImpl aula) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.saveOrUpdate(aula);
		
	}

	//listar aulas de um modulo
	public List<AulaImpl> getAulas(int id_modulo) {
		
		Session currentSession = sessionFactory.getCurrentSession();

		Query<AulaImpl> Query = currentSession.createQuery("from AulaImpl where id_modulo_aula= " + id_modulo,
				AulaImpl.class);

		return Query.getResultList();
	}

	

	
}
