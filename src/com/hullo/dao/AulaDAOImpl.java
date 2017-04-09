package com.hullo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hullo.entity.AlunoImpl;
import com.hullo.entity.AulaImpl;
import com.hullo.entity.ModuloImpl;
import com.hullo.entity.ProfessorImpl;

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

	
	
	// para fazer update da aula ja existente
		@SuppressWarnings("unchecked")
		public void updateAula(AulaImpl aula) {			
			System.out.println("dao aula");	
			
			// get current hibernate session
			Session currentSession = sessionFactory.getCurrentSession();
			
			
			// Cria query que faz busca no banco
			Query<AulaImpl> theQuery;
			// para fazer update apenas dos capos editaveis
			String hql = "UPDATE AulaImpl set nm_aula = :nome, numero_aula= :numero, indice_aula= :indice, revisao_aula = :rev, "
					+ " conteudo_aula = :cont, ativo_aula=:ativo, dt_last_update_aula = :lastUpdate, id_modulo_aula=:id_modulo "
					+ "WHERE id_aula = :id";
			theQuery = currentSession.createQuery(hql);
			// adicionando valores para as variaveis do update
			theQuery.setParameter("nome", aula.getNm_aula());
			theQuery.setParameter("numero", aula.getNumero_aula());
			theQuery.setParameter("indice", aula.getIndice_aula());
			theQuery.setParameter("ativo", aula.isAtivo_aula());
			theQuery.setParameter("rev", aula.getRevisao_aula());
			theQuery.setParameter("cont", aula.getConteudo_aula());			
			theQuery.setParameter("lastUpdate", aula.getDt_last_update_aula());
			theQuery.setParameter("id_modulo", aula.getId_modulo_aula());
			
			theQuery.setParameter("id", aula.getId_aula());
			int result = theQuery.executeUpdate();			
			System.out.println(result + " linha atualizada");
		}
		
		
		
		public AulaImpl validaAula(char indice_aula, int numero_aula, int id_modulo_aula) {
			Session currentSession = sessionFactory.getCurrentSession();

			// busca por indice e NUMERO
						
			Query<AulaImpl> query = currentSession.createQuery(		
					"from AulaImpl where indice_aula = '" + indice_aula + "' and numero_aula = '" 
					+ numero_aula + "'and id_modulo_aula = '" + id_modulo_aula + "'",
					AulaImpl.class);
						
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
}
