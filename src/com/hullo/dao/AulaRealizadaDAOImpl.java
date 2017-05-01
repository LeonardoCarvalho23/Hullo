package com.hullo.dao;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hullo.entity.AulaImpl;
import com.hullo.entity.AulaRealizadaImpl;

@Repository
public class AulaRealizadaDAOImpl {

	@Autowired
	private SessionFactory sessionFactory;

	public List<AulaRealizadaImpl> getAulasRealizadas() {
		Session currentSession = sessionFactory.getCurrentSession();

		Query<AulaRealizadaImpl> theQuery = currentSession.createQuery("from AulaRealizada", AulaRealizadaImpl.class);

		List<AulaRealizadaImpl> aulasRealizadas = theQuery.getResultList();

		return aulasRealizadas;
	}

	public void savePrimeiraAulaRealizada(AulaRealizadaImpl aulaRealizada) {
		Session currentSession = sessionFactory.getCurrentSession();
		Date current_date = new Date();

		aulaRealizada.setDt_criacao_aula_realizada(current_date);

		currentSession.saveOrUpdate(aulaRealizada);
	}

	//buscar proxima aula realizada, baseadi na sua data de criacao
	public AulaRealizadaImpl getProximaAula() {
		Session currentSession = sessionFactory.getCurrentSession();
		
		//achar o dia anterior paa consulta das aulas
		Calendar cal = GregorianCalendar.getInstance();
		cal.add( Calendar.DAY_OF_YEAR, -2);
		Date date = cal.getTime();
		
		System.out.println("data que uso para busca " + date);
			
		// busca proxima aula baseado na data atual
		Query<AulaRealizadaImpl> query = currentSession.createQuery("from AulaRealizadaImpl where dt_criacao_aula_realizada >= :date order by dt_criacao_aula_realizada", AulaRealizadaImpl.class);
		query.setParameter("date", date);
		
		List<AulaRealizadaImpl> listaAulas = query.getResultList();
		
		System.out.println("dt criacao aula_realizada que veio do banco = " + listaAulas.get(0).getDt_criacao_aula_realizada());
		
		return listaAulas.get(0);
	}

	//pegar uma aula especifica
		public AulaRealizadaImpl getAulaRealizada(int id_aula_realizada) {

			Session currentSession = sessionFactory.getCurrentSession();

			// busca por indice da aula realizada
			Query<AulaRealizadaImpl> query = currentSession.createQuery("from AulaRealizadaImpl where id_aula_realizada= " + id_aula_realizada, AulaRealizadaImpl.class);

			return query.getSingleResult();
		}
}
