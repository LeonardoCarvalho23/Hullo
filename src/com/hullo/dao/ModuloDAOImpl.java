package com.hullo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hullo.entity.ModuloImpl;
/**
 *classe para operações no banco relacionadas ao Modulo
 * 
 * @author Hullo Team
 * @version 1.0
 */
@Repository
public class ModuloDAOImpl {

	@Autowired
	private SessionFactory sessionFactory;
	/**
	 * salva modulo
	 * @param modulo
	 */
	public void saveModulo(ModuloImpl modulo) {

		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.saveOrUpdate(modulo);
	}
	/**
	 * pega todos os modulos
	 * @return lista de modulos
	 */
	public List<ModuloImpl> getModulos() {

		Session currentSession = sessionFactory.getCurrentSession();

		Query<ModuloImpl> Query = currentSession.createQuery("from ModuloImpl order by dt_last_update_modulo DESC",
				ModuloImpl.class);

		return Query.getResultList();

	}
	/**
	 * pega modulo por nome
	 * @return lista de modulos
	 */
	public List<ModuloImpl> getModulos(String nomeBusca) {

		Session currentSession = sessionFactory.getCurrentSession();

		// busca por nome, tudo em lower case
		Query<ModuloImpl> query = currentSession.createQuery(
				"from ModuloImpl where lower(nm_modulo) like :nomeModulo order by nm_modulo", ModuloImpl.class);
		query.setParameter("nomeModulo", "%" + nomeBusca.toLowerCase() + "%");

		try {
			return query.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * valida se modulo com aquele indice existe
	 * @param indice_modulo
	 * @return se modulo existe
	 */
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
/**
 * pega modulo pelo id
 * @param id_modulo
 * @return modulo
 */
	public ModuloImpl getModulo(int id_modulo) {

		Session currentSession = sessionFactory.getCurrentSession();

		// Cria query que faz busca no banco
		Query<ModuloImpl> theQuery;
		theQuery = currentSession.createQuery("from ModuloImpl where id_modulo='" + id_modulo + "'", ModuloImpl.class);

		// executa query
		ModuloImpl modulo = theQuery.getSingleResult();

		return modulo;

	}

	/**
	 * valida modulo
	 * @param indice_modulo
	 * @param id_modulo
	 * @return
	 */
	public boolean validaModulo(float indice_modulo, int id_modulo) {
		Session currentSession = sessionFactory.getCurrentSession();

		// busca por indice, se nao for do mesmo id do modulo
		Query<ModuloImpl> query = currentSession.createQuery(
				"from ModuloImpl where id_modulo <> " + id_modulo + " and indice_modulo= " + indice_modulo,
				ModuloImpl.class);

		List<ModuloImpl> result = query.getResultList();
		if (result.size() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * atualiza modulo
	 * @param modulo
	 */
	@SuppressWarnings("unchecked")
	public void updateModulo(ModuloImpl modulo) {

		Session currentSession = sessionFactory.getCurrentSession();

		// Cria query que faz busca no banco
		Query<ModuloImpl> theQuery;

		// para fazer update apenas dos capos editaveis
		String hql = "UPDATE ModuloImpl set nm_modulo = :nome, indice_modulo= :indice, ativo_modulo= :ativo, dt_last_update_modulo = :lastUpdate "
				+ "WHERE id_modulo = :id";
		theQuery = currentSession.createQuery(hql);

		// adicionando valores para as variaveis do update
		theQuery.setParameter("nome", modulo.getNm_modulo());
		theQuery.setParameter("indice", modulo.getIndice_modulo());
		theQuery.setParameter("ativo", modulo.getAtivo_modulo());
		theQuery.setParameter("lastUpdate", modulo.getDt_last_update_modulo());
		theQuery.setParameter("id", modulo.getId_modulo());

		theQuery.executeUpdate();

	}

	/**
	 * deleta modulo
	 * @param id_modulo
	 */
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

	/**
	 * pega o primeiro modulo
	 * @return modulo
	 */
	public ModuloImpl getPrimeiroModulo() {

		Session currentSession = sessionFactory.getCurrentSession();

		Query<ModuloImpl> Query = currentSession
				.createQuery("from ModuloImpl where ativo_modulo = 1 order by indice_modulo", ModuloImpl.class);

		List<ModuloImpl> modulo = Query.getResultList();

		return modulo.get(0);

	}

	/**
	 * retorna o proximo modulo do curso linear, de acordo com o indice do modulo atual
	 * @param indice_modulo
	 * @return modulo
	 */
	public ModuloImpl getProxModulo(float indice_modulo) {

		Session currentSession = sessionFactory.getCurrentSession();
		
		//busco os modulos ativos com indice maior que o atual
		Query<ModuloImpl> Query = currentSession
				.createQuery("from ModuloImpl where ativo_modulo = 1 and indice_modulo > " + indice_modulo
						+ " order by indice_modulo", ModuloImpl.class);
		try {
			//tento encontrar o proximo modulo
			List<ModuloImpl> modulos = Query.getResultList();
			return modulos.get(0);

		} catch (

		Exception e) {
			//se for o ultimo modulo, terminou o curso
			return null;
		}
	}

}
