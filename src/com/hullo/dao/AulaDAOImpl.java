package com.hullo.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hullo.entity.AulaImpl;

@Repository
public class AulaDAOImpl {

	@Autowired
	private SessionFactory sessionFactory;

	public void saveModulo(AulaImpl aula) {

		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.saveOrUpdate(aula);
	}
}
