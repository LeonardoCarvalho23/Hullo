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

		Query<ModuloImpl> Query = currentSession.createQuery("from ModuloImpl order by dt_last_update_modulo DESC",
				ModuloImpl.class);

		return Query.getResultList();

	}

	public List<ModuloImpl> getModulos(String nomeBusca) {

		Session currentSession = sessionFactory.getCurrentSession();

		// busca por nome, tudo em lower case
		Query<ModuloImpl> query = currentSession.createQuery(
				"from ModuloImpl where lower(nm_modulo) like :nomeModulo order by nm_modulo",
				ModuloImpl.class);
		query.setParameter("nomeModulo", "%" + nomeBusca.toLowerCase() + "%");

		try {
			return query.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	public boolean validaModulo(float indice_modulo) {
		Session currentSession = sessionFactory.getCurrentSession();

		// busca por indice do modulo
		Query<ModuloImpl> query = currentSession.createQuery("from ModuloImpl where indice_modulo= " + indice_modulo,
				ModuloImpl.class);

		List<ModuloImpl> result = query.getResultList();
		if (result.size() > 0) {
			return true;
		}
		return false;

	}

	public ModuloImpl getModulo(int id_modulo) {

			Session currentSession = sessionFactory.getCurrentSession();

			// Cria query que faz busca no banco
			Query<ModuloImpl> theQuery;
			theQuery = currentSession.createQuery("from ModuloImpl where id_modulo='" + id_modulo + "'", ModuloImpl.class);

			// executa query
			ModuloImpl modulo = theQuery.getSingleResult();

			return modulo;

	}

	public boolean validaModulo(float indice_modulo, int id_modulo) {
		Session currentSession = sessionFactory.getCurrentSession();

		// busca por indice, se nao for do mesmo id do modulo
		Query<ModuloImpl> query = currentSession.createQuery("from ModuloImpl where id_modulo <> " + id_modulo + " and indice_modulo= " + indice_modulo,
				ModuloImpl.class);

		List<ModuloImpl> result = query.getResultList();
		if (result.size() > 0) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public void updateModulo(ModuloImpl modulo) {
		
		Session currentSession = sessionFactory.getCurrentSession();

		// Cria query que faz busca no banco
		Query<ModuloImpl> theQuery;

		// para fazer update apenas dos capos editaveis
		String hql = "UPDATE ModuloImpl set nm_modulo = :nome, indice_modulo= :indice, dt_last_update_modulo = :lastUpdate "
				+ "WHERE id_modulo = :id";
		theQuery = currentSession.createQuery(hql);

		// adicionando valores para as variaveis do update
		theQuery.setParameter("nome", modulo.getNm_modulo());
		theQuery.setParameter("indice", modulo.getIndice_modulo());
		//theQuery.setParameter("ativo", modulo.getAtivo_modulo());
		theQuery.setParameter("lastUpdate", modulo.getDt_last_update_modulo());
		theQuery.setParameter("id", modulo.getId_modulo());

		theQuery.executeUpdate();
		
	}

	@SuppressWarnings("unchecked")
	public void deleteModulo(int id_modulo) {

		Session currentSession = sessionFactory.getCurrentSession();

		// Cria query que faz busca no banco
		Query<ModuloImpl> theQuery;

		// para fazer update apenas dos capos editaveis
		String hql = "DELETE from ModuloImpl WHERE id_modulo = :id";
		
		theQuery = currentSession.createQuery(hql);

		// adicionando valores para as variaveis do update
		theQuery.setParameter("id", id_modulo);

		int result = theQuery.executeUpdate();
		System.out.println(result + " linhas atualizadas");
		
	}
	
	public ModuloImpl getPrimeiroModulo() {

		Session currentSession = sessionFactory.getCurrentSession();

		Query<ModuloImpl> Query = currentSession.createQuery("from ModuloImpl order by indice_modulo",
				ModuloImpl.class);

		List<ModuloImpl> modulo = Query.getResultList();
		
		return modulo.get(0);

	}

}
