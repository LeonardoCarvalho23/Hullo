package com.hullo.dao;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hullo.entity.LogImpl;

@Repository
public class LogDAOImpl implements LogDAO {
	
	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void saveAlunoLog(int id_aluno){
		Date date = new Date();
		LogImpl theLogImpl = new LogImpl();
		theLogImpl.setId_aluno(id_aluno);
		theLogImpl.setDt_login(date);
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		//save the current log
		currentSession.save(theLogImpl);
	}
	
	@Override
	public void saveProfessorLog(int id_professor){
		Date date = new Date();
		LogImpl theLogImpl = new LogImpl();
		theLogImpl.setId_professor(id_professor);
		theLogImpl.setDt_login(date);
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		//save the current log
		currentSession.save(theLogImpl);
	}
	
}
