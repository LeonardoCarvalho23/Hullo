package com.hullo.dao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hullo.entity.AulaRealizadaImpl;
import com.twilio.rest.api.v2010.account.Call.Status;

/**
 * camada de persistencia para objetos AulaRealizadaImpl
 * 
 * @author Hullo Team
 * @version 1.1
 */

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
		LocalDateTime current_date = LocalDateTime.now();

		aulaRealizada.setDt_criacao_aula_realizada(current_date);

		currentSession.saveOrUpdate(aulaRealizada);
	}

	// DESA buscar proxima aula realizada, baseado na sua data de criacao
	public AulaRealizadaImpl getProximaAula() {
		Session currentSession = sessionFactory.getCurrentSession();

		// achar os dias anteriores para consulta das aulas
		LocalDateTime data = LocalDateTime.now();
		LocalDateTime date = data.minusDays(3).withHour(0).withMinute(0).withSecond(0);

		System.out.println("data buscada para aula = " + date.toString());

		// busca proxima aula baseado na data atual
		Query<AulaRealizadaImpl> query = currentSession.createQuery(
				"from AulaRealizadaImpl where dt_criacao_aula_realizada >= :date and status_aula_realizada = NULL order by dt_criacao_aula_realizada",
				AulaRealizadaImpl.class);
		query.setParameter("date", date);

		try {
			// busca a proxima aula no banco
			List<AulaRealizadaImpl> listaAulas = query.getResultList();
			return listaAulas.get(0);
		} catch (Exception e) {
			// se nao tem aula, retorna null
			return null;
		}
	}

	// PROD buscar proxima aula realizada, baseado na sua data de criacao
	/*
	 * public AulaRealizadaImpl getProximaAula() { Session currentSession =
	 * sessionFactory.getCurrentSession();
	 * 
	 * // achar os dias anteriores para consulta das aulas Calendar date = new
	 * GregorianCalendar();
	 * 
	 * // reset hour, minutes, seconds and millis date.set(Calendar.HOUR_OF_DAY,
	 * 0); date.set(Calendar.MINUTE, 0); date.set(Calendar.SECOND, 0);
	 * date.set(Calendar.MILLISECOND, 0);
	 * 
	 * //ate 0h de hoje Date dataFim = date.getTime();
	 * 
	 * // tres dias atras date.add(Calendar.DAY_OF_MONTH, -3); Date dataInicio =
	 * date.getTime();
	 * 
	 * 
	 * // busca proxima aula baseado na data atual Query<AulaRealizadaImpl>
	 * query = currentSession.createQuery(
	 * "from AulaRealizadaImpl where dt_criacao_aula_realizada >= :dataInicio "
	 * + "and dt_criacao_aula_realizada <= :dataFim " +
	 * "and status_aula_realizada = NULL order by dt_criacao_aula_realizada",
	 * AulaRealizadaImpl.class); query.setParameter("dataInicio", dataInicio);
	 * query.setParameter("dataFim", dataFim);
	 * 
	 * try { // busca a proxima aula no banco List<AulaRealizadaImpl> listaAulas
	 * = query.getResultList(); return listaAulas.get(0); } catch (Exception e)
	 * { // se nao tem aula, retorna null return null; } }
	 */
	// pegar uma aula especifica
	public AulaRealizadaImpl getAulaRealizada(int id_aula_realizada) {

		Session currentSession = sessionFactory.getCurrentSession();

		// busca por indice da aula realizada
		Query<AulaRealizadaImpl> query = currentSession.createQuery(
				"from AulaRealizadaImpl where id_aula_realizada= " + id_aula_realizada, AulaRealizadaImpl.class);

		return query.getSingleResult();
	}

	// Atualiza aula acrescentando CALLSID quando chamada é iniciada
	@SuppressWarnings("unchecked")
	public void updateAulaRealizada(int id_aula, String callSid) {

		Session currentSession = sessionFactory.getCurrentSession();
		Query<AulaRealizadaImpl> theQuery;
		String sql = "UPDATE AulaRealizadaImpl set sid_chamada_aula_realizada='" + callSid
				+ "' WHERE id_aula_realizada=" + id_aula;
		theQuery = currentSession.createQuery(sql);
		int result = theQuery.executeUpdate();
		System.out.println(result + " linha atualizada");
	}

	// Atualiza aula APÓS conclusão da chamada
	@SuppressWarnings("unchecked")
	public void updateAulaRealizada(String callSid, String callDuration, Status status, String startTimeConv,
			String endTimeConv, BigDecimal price) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<AulaRealizadaImpl> theQuery;

		String sql = "UPDATE AulaRealizadaImpl set duracao_chamada_aula_realizada=" + callDuration
				+ ", status_chamada_aula_realizada='" + status + "', dt_inicio_chamada_aula_realizada='" + startTimeConv
				+ "', dt_fim_chamada_aula_realizada='" + endTimeConv + "', custo_chamada_aula_realizada=" + price
				+ "  WHERE sid_chamada_aula_realizada='" + callSid + "'";
		theQuery = currentSession.createQuery(sql);
		theQuery.executeUpdate();

	}

	// atualiza a aula_realizada apos ser concluida pelo professor
	@SuppressWarnings("unchecked")
	public void concludedAulaRealizada(AulaRealizadaImpl aulaRealizadaAtual) {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// Cria query que faz busca no banco
		Query<AulaRealizadaImpl> theQuery;

		// para fazer update apenas dos capos editaveis
		String hql = "UPDATE AulaRealizadaImpl set nota_model_aula_realizada = :nota_model_aula_realizada, "
				+ "nota_practice_aula_realizada = :nota_practice_aula_realizada,"
				+ "nota_production_aula_realizada = :nota_production_aula_realizada, "
				+ "comentario_aula_realizada = :comentario_aula_realizada, "
				+ "status_aula_realizada = :status_aula_realizada, "
				+ "id_professor_aula_realizada = :id_professor_aula_realizada " + "WHERE id_aula_realizada = :id";
		theQuery = currentSession.createQuery(hql);

		// adicionando valores para as variaveis do update
		theQuery.setParameter("nota_model_aula_realizada", aulaRealizadaAtual.getNota_model_aula_realizada());
		theQuery.setParameter("nota_practice_aula_realizada", aulaRealizadaAtual.getNota_practice_aula_realizada());
		theQuery.setParameter("nota_production_aula_realizada", aulaRealizadaAtual.getNota_production_aula_realizada());
		theQuery.setParameter("comentario_aula_realizada", aulaRealizadaAtual.getComentario_aula_realizada());
		theQuery.setParameter("status_aula_realizada", "Realizada"); // significa
																		// que
		// foi concluida
		// pelo
		// professor
		theQuery.setParameter("id_professor_aula_realizada", aulaRealizadaAtual.getId_professor_aula_realizada());
		theQuery.setParameter("id", aulaRealizadaAtual.getId_aula_realizada());

		theQuery.executeUpdate();

	}

	public void saveProximaAulaRealizada(AulaRealizadaImpl aulaRealizada) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.saveOrUpdate(aulaRealizada);
	}

	/**
	 * lista aulas realizadas pelo aluno
	 * 
	 * @param id_aluno
	 * @return lista de aulas realizadas
	 */

	public List<AulaRealizadaImpl> getAulasRealizadasAluno(int id_aluno) {

		Session currentSession = sessionFactory.getCurrentSession();

		Query<AulaRealizadaImpl> Query = currentSession.createQuery("from AulaRealizadaImpl "
				+ "where id_aluno_aula_realizada = " + id_aluno + "and dt_inicio_chamada_aula_realizada <> 'null'"
				+ " order by dt_inicio_chamada_aula_realizada DESC", AulaRealizadaImpl.class);

		return Query.getResultList();
	}

	/**
	 * Salva dados da gravação da chamada no banco
	 * 
	 * @param callSid
	 * @param recordingUrl
	 */
	@SuppressWarnings("unchecked")
	public void updateAulaRealizada(String callSid, String recordingUrl) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<AulaRealizadaImpl> theQuery;
		String sql = "UPDATE AulaRealizadaImpl set url_gravacao_aula_realizada='" + recordingUrl
				+ "' WHERE sid_chamada_aula_realizada='" + callSid + "'";
		theQuery = currentSession.createQuery(sql);
		int result = theQuery.executeUpdate();
		System.out.println(result + " linha atualizada");

	}

	/**
	 * Faz update da aulaRealizada para status de nao atendida
	 * 
	 * @param aulaRealizada
	 */
	@SuppressWarnings("unchecked")
	public void updateAulaNaoAtendida(AulaRealizadaImpl aulaRealizada) {
		Session currentSession = sessionFactory.getCurrentSession();

		Query<AulaRealizadaImpl> theQuery;

		String sql = "UPDATE AulaRealizadaImpl set status_aula_realizada='Não Atendida' WHERE id_aula_realizada="
				+ aulaRealizada.getId_aula_realizada();
		theQuery = currentSession.createQuery(sql);
		int result = theQuery.executeUpdate();
		System.out.println(result + " linha atualizada");

	}

	/**
	 * criação de próxima aulaRealizada igual à anterior não atendida +2h da
	 * criação
	 * 
	 * @param aulaRealizada
	 */
	public void saveProximaAulaNaoAtendida(AulaRealizadaImpl aulaRealizada) {
		Session currentSession = sessionFactory.getCurrentSession();

		// mudar data de criacao para +2h
		AulaRealizadaImpl aulaNova = new AulaRealizadaImpl();

		aulaNova.setDt_criacao_aula_realizada(aulaRealizada.getDt_criacao_aula_realizada().plusHours(2));
		aulaNova.setId_aula_aula_realizada(aulaRealizada.getId_aula_aula_realizada());
		aulaNova.setId_anterior_aula_realizada(aulaRealizada.getId_anterior_aula_realizada());
		aulaNova.setId_aluno_aula_realizada(aulaRealizada.getId_aluno_aula_realizada());

		System.out.println(
				"novo horario de criacao da aula nao atendida = " + aulaNova.getDt_criacao_aula_realizada().toString());

		currentSession.saveOrUpdate(aulaNova);

	}

	/**
	 * mudar status de aula agendada para cancelada
	 * @param id_aluno
	 */
	@SuppressWarnings("unchecked")
	public void cancelAulaRealizada(int id_aluno) {

		Session currentSession = sessionFactory.getCurrentSession();
		Query<AulaRealizadaImpl> theQuery;
		String sql = "UPDATE AulaRealizadaImpl set status_aula_realizada = 'Cancelada'"
				+ " WHERE status_aula_realizada = NULL and id_aluno_aula_realizada =" + id_aluno;
		theQuery = currentSession.createQuery(sql);
		int result = theQuery.executeUpdate();
		System.out.println(result + " linha atualizada");
	}

	/*	*//**
			 * metodo para atualizar o professor asignado a aula e seu status
			 * 
			 * @param id_aula_realizada
			 * @param id_professor_aula_realizada
			 *//*
			 * @SuppressWarnings("unchecked") public void
			 * updateProfessorAulaRealizada(int id_aula_realizada, Integer
			 * id_professor_aula_realizada) { Session currentSession =
			 * sessionFactory.getCurrentSession();
			 * 
			 * Query<AulaRealizadaImpl> theQuery; String sql =
			 * "UPDATE AulaRealizadaImpl set id_professor_aula_realizada='" +
			 * id_professor_aula_realizada +
			 * "' , status_aula_realizada='Asignada' " +
			 * "WHERE id_aula_realizada='" + id_aula_realizada +"'"; theQuery =
			 * currentSession.createQuery(sql); int result =
			 * theQuery.executeUpdate(); System.out.println(result +
			 * " linha atualizada"); }
			 */

}
