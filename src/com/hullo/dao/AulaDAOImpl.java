package com.hullo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hullo.entity.AulaImpl;
import com.hullo.entity.ModuloImpl;

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

	// listar aulas de um modulo
	public List<AulaImpl> getAulas(int id_modulo) {

		Session currentSession = sessionFactory.getCurrentSession();

		Query<AulaImpl> Query = currentSession.createQuery(
				"from AulaImpl where id_modulo_aula= " + id_modulo + " order by numero_aula", AulaImpl.class);

		return Query.getResultList();
	}

	public AulaImpl getAula(int id_aula) {

		Session currentSession = sessionFactory.getCurrentSession();

		// busca por indice do modulo
		Query<AulaImpl> query = currentSession.createQuery("from AulaImpl where id_aula= " + id_aula, AulaImpl.class);

		AulaImpl result = query.getSingleResult();

		return result;
	}

	public void updateAula(AulaImpl aula) {
		Session currentSession = sessionFactory.getCurrentSession();

		// Cria query que faz busca no banco
		Query<AulaImpl> theQuery;

		// para fazer update apenas dos capos editaveis
		String hql = "UPDATE AulaImpl set nm_aula = :nome, indice_aula= :indice, ativo_aula= :ativo, dt_last_update_aula = :lastUpdate "
				+ "WHERE id_aula = :id";
		theQuery = currentSession.createQuery(hql);

		// adicionando valores para as variaveis do update
		theQuery.setParameter("nome", aula.getNm_aula());
		theQuery.setParameter("indice", aula.getIndice_aula());
		theQuery.setParameter("ativo", aula.isAtivo_aula());
		theQuery.setParameter("lastUpdate", aula.getDt_last_update_aula());
		theQuery.setParameter("id", aula.getId_aula());

		int result = theQuery.executeUpdate();

		System.out.println(result + " linha atualizada");
	}
}
