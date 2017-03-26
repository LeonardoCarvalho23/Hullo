package com.hullo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hullo.entity.ModuloImpl;

@Repository
public class ModuloDAOImpl {

	@Autowired
	private SessionFactory sessionFactory;

	public void saveModulo(ModuloImpl modulo) {

		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.saveOrUpdate(modulo);
	}

	public List<ModuloImpl> getModulos() {

		Session currentSession = sessionFactory.getCurrentSession();

		Query<ModuloImpl> Query = currentSession.createQuery("from ModuloImpl order by dt_last_update_modulo",
				ModuloImpl.class);

		return Query.getResultList();

	}


	public List<ModuloImpl> getModulos(String nomeBusca) {

		Session currentSession = sessionFactory.getCurrentSession();

		// busca por nome, tudo em lower case
		Query<ModuloImpl> query = currentSession.createQuery(
				"from ModuloImpl where lower(nm_modulo) like :nomeModulo order by dt_last_update_modulo",
				ModuloImpl.class);
		query.setParameter("nomeModulo", "%" + nomeBusca.toLowerCase() + "%");
		
		try{	
			return query.getResultList();
		}
		catch (Exception e) {
			return null;
		}
	}

}
