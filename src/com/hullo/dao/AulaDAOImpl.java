package com.hullo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hullo.entity.AulaImpl;
import com.hullo.entity.ModuloImpl;
import com.hullo.service.ModuloServiceImpl;

/**
 * Classe para comunicação com o banco (DAO)
 * @author Hullo Team;
 *
 */
@Repository
public class AulaDAOImpl {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private ModuloServiceImpl moduloService;

	/**
	 * Valida se não existe aula com o mesmo índice no mesmo módulo
	 * @param indice_aula
	 * @return
	 */
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

	/**
	 * Método para salvar aula
	 * @param aula
	 */
	public void saveAula(AulaImpl aula) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.saveOrUpdate(aula);
	}

	/**
	 * Listar aulas de um modulo
	 * @param id_modulo
	 * @return
	 */
	public List<AulaImpl> getAulas(int id_modulo) {

		Session currentSession = sessionFactory.getCurrentSession();

		Query<AulaImpl> Query = currentSession.createQuery(
				"from AulaImpl where id_modulo_aula= " + id_modulo + " order by numero_aula, indice_aula",
				AulaImpl.class);

		return Query.getResultList();
	}

	/**
	 * Pegar uma aula especifica
	 * @param id_aula
	 * @return
	 */
	public AulaImpl getAula(int id_aula) {

		Session currentSession = sessionFactory.getCurrentSession();

		// busca por indice do modulo
		Query<AulaImpl> query = currentSession.createQuery("from AulaImpl where id_aula= " + id_aula, AulaImpl.class);

		AulaImpl result = query.getSingleResult();

		return result;
	}

	/**
	 * Pegar a primeira aula
	 * @param id_modulo
	 * @return
	 */
	public AulaImpl getPrimeiraAula(int id_modulo) {

		Session currentSession = sessionFactory.getCurrentSession();

		Query<AulaImpl> Query = currentSession.createQuery(
				"from AulaImpl where id_modulo_aula= " + id_modulo + " order by numero_aula, indice_aula",
				AulaImpl.class);

		List<AulaImpl> aulas = Query.getResultList();

		return aulas.get(0);
	}

	/**
	 * Para fazer update da aula ja existente
	 * @param aula
	 */
	@SuppressWarnings("unchecked")
	public void updateAula(AulaImpl aula) {

		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// Cria query que faz busca no banco
		Query<AulaImpl> theQuery;
		// para fazer update apenas dos capos editaveis
		String hql = "UPDATE AulaImpl set nm_aula = :nome, "
				+ "numero_aula= :numero, "
				+ "indice_aula= :indice, "
				+ "revisao_aula = :rev, "
				+ "conteudo_aula = :cont, "
				+ "atividade_aula = :atv, "
				+ "teaser_aula = :teaser, "
				+ "ativo_aula=:ativo, "
				+ "dt_last_update_aula = :lastUpdate, "
				+ "id_modulo_aula=:id_modulo "
				+ "WHERE id_aula = :id";
		theQuery = currentSession.createQuery(hql);
		// adicionando valores para as variaveis do update
		theQuery.setParameter("nome", aula.getNm_aula());
		theQuery.setParameter("numero", aula.getNumero_aula());
		theQuery.setParameter("indice", aula.getIndice_aula());
		theQuery.setParameter("ativo", aula.isAtivo_aula());
		theQuery.setParameter("rev", aula.getRevisao_aula());
		theQuery.setParameter("cont", aula.getConteudo_aula());
		theQuery.setParameter("atv", aula.getAtividade_aula());
		theQuery.setParameter("teaser", aula.getTeaser_aula());
		theQuery.setParameter("lastUpdate", aula.getDt_last_update_aula());
		theQuery.setParameter("id_modulo", aula.getId_modulo_aula());

		theQuery.setParameter("id", aula.getId_aula());
		int result = theQuery.executeUpdate();
		System.out.println(result + " linha atualizada");
	}

	/**
	 * Para valiar a aula
	 * @param indice_aula
	 * @param numero_aula
	 * @param id_modulo_aula
	 * @param id_aula
	 * @return
	 */
	public AulaImpl validaAula(char indice_aula, int numero_aula, int id_modulo_aula, int id_aula) {
		Session currentSession = sessionFactory.getCurrentSession();

		// busca por indice e NUMERO

		Query<AulaImpl> query = currentSession.createQuery(
				"from AulaImpl where indice_aula = '" + indice_aula + "' and numero_aula = '" + numero_aula
						+ "'and id_modulo_aula = '" + id_modulo_aula + "' and id_aula <> '" + id_aula + "'",
				AulaImpl.class);

		System.out.println("query" + query);
		try {
			// ve se tem mais de uma aula com os dados
			List<AulaImpl> aulas = query.getResultList();

			// devolve o primeiro da lista
			AulaImpl validaAula = aulas.get(0);
			return validaAula;

		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * Para deletar uma aula
	 * @param aula
	 */
	public void deleteAula(AulaImpl aula) {

		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.delete(aula);

	}

	/**
	 * Deletar todas as aulas de um modulo
	 * @param id_modulo
	 */
	@SuppressWarnings("unchecked")
	public void deleteAulasModulo(int id_modulo) {

		Session currentSession = sessionFactory.getCurrentSession();

		// Cria query que faz busca no banco
		Query<AulaImpl> theQuery;

		// para fazer update apenas dos capos editaveis
		String hql = "DELETE from AulaImpl where id_modulo_aula=:id_modulo";

		theQuery = currentSession.createQuery(hql);

		// adicionando valores para as variaveis do update
		theQuery.setParameter("id_modulo", id_modulo);

		int result = theQuery.executeUpdate();
		System.out.println(result + " linhas atualizadas");

	}

	/**
	 * Para buscar proxima aula linear do curso
	 * @param id_aula_aula_realizada
	 * @return
	 */
	public AulaImpl getProximaAulaLinear(int id_aula_aula_realizada) {

		Session currentSession = sessionFactory.getCurrentSession();

		// busca aula atual por id da aula
		Query<AulaImpl> query = currentSession.createQuery("from AulaImpl where id_aula= " + id_aula_aula_realizada,
				AulaImpl.class);

		AulaImpl aulaAtual = query.getSingleResult();

		// se minha aula atual e a ultima desse modulo
		if (aulaAtual.getNumero_aula() == 5) {

			try {
				// preciso buscar as informacoes do modulo para descobrir qual e
				// o proximo
				ModuloImpl moduloAtual = moduloService.getModulo(aulaAtual.getId_modulo_aula());

				// busco o proximo modulo de acordo com o indice do modulo atual
				ModuloImpl proxModulo = moduloService.getProxModulo(moduloAtual.getIndice_modulo());

				// pego a primeira aula do proximo modulo
				Query<AulaImpl> query2 = currentSession.createQuery(
						"from AulaImpl where id_modulo_aula= :modulo order by numero_aula, indice_aula",
						AulaImpl.class);
				query2.setParameter("modulo", proxModulo.getId_modulo());

				List<AulaImpl> aulas = query2.getResultList();

				return aulas.get(0);

			} catch (Exception e) {
				// se nao tem proximo modulo ou se da pau nas outras buscas
				return null;
			}
		}

		// se minha aula nao e a 5, pego a proxima aula do modulo
		Query<AulaImpl> query2 = currentSession.createQuery(
				"from AulaImpl where id_modulo_aula= :modulo and numero_aula > :numero order by numero_aula, indice_aula",
				AulaImpl.class);
		query2.setParameter("modulo", aulaAtual.getId_modulo_aula());
		query2.setParameter("numero", aulaAtual.getNumero_aula());

		List<AulaImpl> aulas = query2.getResultList();
		System.out.println("id da proxima aula no dao = " + aulas.get(0).getId_aula());
		return aulas.get(0);
	}

	/**
	 * Pega a próxima aula com o mesmo índice (paralela)
	 * @param id_aula_aula_realizada
	 * @return
	 */
	public AulaImpl getProximaAulaParalela(int id_aula_aula_realizada) {
		Session currentSession = sessionFactory.getCurrentSession();

		// busca aula atual por id da aula
		Query<AulaImpl> query = currentSession.createQuery("from AulaImpl where id_aula= " + id_aula_aula_realizada,
				AulaImpl.class);

		AulaImpl aulaAtual = query.getSingleResult();
		
		//verifico se ha aula paralela para esse numero desse modulo com indice maior que o atual
		try{
			
			Query<AulaImpl> query2 = currentSession.createQuery("from AulaImpl where id_modulo_aula= :modulo and "
					+ "numero_aula= :numero and indice_aula> :indice order by indice_aula",
					AulaImpl.class);
			query2.setParameter("modulo", aulaAtual.getId_modulo_aula());
			query2.setParameter("numero", aulaAtual.getNumero_aula());
			query2.setParameter("indice", aulaAtual.getIndice_aula());

			List<AulaImpl> aulas = query2.getResultList();
			
			return aulas.get(0);
			
		} catch (Exception e) {
			// se nao tem aula paralela o aluno faz a mesma aula
			return aulaAtual;
		}
	}

	/**
	 * Pega o nome da aula pelo Id
	 * @param id_aula
	 * @return
	 */
	public String getNomeAula(int id_aula) {
		
		Session currentSession = sessionFactory.getCurrentSession();

		// busca nome por id da aula
		Query<AulaImpl> query = currentSession.createQuery(" from AulaImpl where id_aula= " + id_aula, AulaImpl.class);

		AulaImpl result = query.getSingleResult();
		String nome = result.getNm_aula();
		
		return nome;
		
		
	}

}
