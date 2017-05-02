package com.hullo.dao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hullo.entity.AulaRealizadaImpl;
import com.twilio.rest.api.v2010.account.Call.Status;

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

	// buscar proxima aula realizada, baseadi na sua data de criacao
	public AulaRealizadaImpl getProximaAula() {
		Session currentSession = sessionFactory.getCurrentSession();

		// achar o dia anterior paa consulta das aulas
		Calendar cal = GregorianCalendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -2);
		Date date = cal.getTime();

		System.out.println("data que uso para busca " + date);

		// busca proxima aula baseado na data atual
		Query<AulaRealizadaImpl> query = currentSession.createQuery(
				"from AulaRealizadaImpl where dt_criacao_aula_realizada >= :date and status_aula_realizada = NULL order by dt_criacao_aula_realizada",
				AulaRealizadaImpl.class);
		query.setParameter("date", date);

		try {
			//busca a proxima aula no banco
			List<AulaRealizadaImpl> listaAulas = query.getResultList();
			return listaAulas.get(0);
		} catch (Exception e) {
			//se nao tem aula, retorna null
			return null;
		}
	}

	// pegar uma aula especifica
	public AulaRealizadaImpl getAulaRealizada(int id_aula_realizada) {

		Session currentSession = sessionFactory.getCurrentSession();

		// busca por indice da aula realizada
		Query<AulaRealizadaImpl> query = currentSession.createQuery(
				"from AulaRealizadaImpl where id_aula_realizada= " + id_aula_realizada, AulaRealizadaImpl.class);

			return query.getSingleResult();
		}
		
	//Atualiza aula acrescentando CALLSID quando chamada é iniciada
		@SuppressWarnings("unchecked")
		public void updateAulaRealizada(int id_aula, String callSid) {
			System.out.println("Sid chegou no DAO: "+callSid);
			Session currentSession = sessionFactory.getCurrentSession();
			Query<AulaRealizadaImpl> theQuery;
			String sql = "UPDATE AulaRealizadaImpl set sid_chamada_aula_realizada='"+callSid+"' WHERE id_aula_realizada="+id_aula;
			theQuery = currentSession.createQuery(sql);
			int result = theQuery.executeUpdate();
			System.out.println(result + " linha atualizada");
		}
		
		//Atualiza aula APÓS conclusão da chamada
				@SuppressWarnings("unchecked")
				public void updateAulaRealizada(String callSid, String callDuration, Status status, DateTime startTime,
				DateTime endTime, BigDecimal price) {
					Session currentSession = sessionFactory.getCurrentSession();
					Query<AulaRealizadaImpl> theQuery;
					
					////Converte as datas recebidas
					DateTime.parse("2016-01-01'T'09:28:00Z");
					
					
					String sql = "UPDATE AulaRealizadaImpl set duracao_chamada_aula_realizada="+callDuration+", status_chamada_aula_realizada='"+status+"', dt_inicio_chamada_aula_realizada='"+startTime+"', dt_fim_chamada_aula_realizada='"+endTime+"', custo_chamada_aula_realizada="+price+"  WHERE sid_chamada_aula_realizada='"+callSid+"'";
					theQuery = currentSession.createQuery(sql);
					int result = theQuery.executeUpdate();
					System.out.println(result + " linha atualizada");
				}
		
		/* METODO ENCERRAR AULA + CRIAR NOVA AULA
				//@Override
				public void concludedAulaRealizada(AulaRealizadaImpl aulaRealizadaAtual) {
					// get current hibernate session
					Session currentSession = sessionFactory.getCurrentSession();
					
					System.out.println("concludedAulaRealizada dao");

					// Cria query que faz busca no banco
					Query<AulaRealizadaImpl> theQuery;

					// para fazer update apenas dos capos editaveis
					String hql = "UPDATE AulaRealizadaImpl set sid_chamada_aula_realizada = :sid_chamada_aula_realizada, duracao_chamada_aula_realizada = :duracao_chamada_aula_realizada,"
							+ "custo_chamada_aula_realizada = :custo_chamada_aula_realizada, "
							+ "status_chamada_aula_realizada = :status_chamada_aula_realizada, nota_model_aula_realizada = :nota_model_aula_realizada, nota_practice_aula_realizada = :nota_practice_aula_realizada,"
							+ "nota_production_aula_realizada = :nota_production_aula_realizada, "
							+ "comentario_aula_realizada = :comentario_aula_realizada, status_ligacao_aula_realizada = :status_ligacao_aula_realizada, id_professor_aula_realizada = :id_professor_aula_realizada " 
							+ "WHERE id_aula_realizada = :id";
					theQuery = currentSession.createQuery(hql);

					// adicionando valores para as variaveis do update
					theQuery.setParameter("sid_chamada_aula_realizada", aulaRealizadaAtual.getSid_chamada_aula_realizada());
					theQuery.setParameter("duracao_chamada_aula_realizada", aulaRealizadaAtual.getDuracao_chamada_aula_realizada());
					theQuery.setParameter("custo_chamada_aula_realizada", aulaRealizadaAtual.getCusto_chamada_aula_realizada());
					theQuery.setParameter("status_chamada_aula_realizada", aulaRealizadaAtual.getStatus_chamada_aula_realizada());
					theQuery.setParameter("nota_model_aula_realizada", aulaRealizadaAtual.getNota_model_aula_realizada());
					theQuery.setParameter("nota_practice_aula_realizada", aulaRealizadaAtual.getNota_practice_aula_realizada());
					theQuery.setParameter("nota_production_aula_realizada", aulaRealizadaAtual.getNota_production_aula_realizada());
					theQuery.setParameter("comentario_aula_realizada", aulaRealizadaAtual.getComentario_aula_realizada());
					theQuery.setParameter("status_ligacao_aula_realizada", aulaRealizadaAtual.getStatus_ligacao_aula_realizada());
					theQuery.setParameter("id_professor_aula_realizada", aulaRealizadaAtual.getId_professor_aula_realizada());
					
					theQuery.setParameter("id", aulaRealizadaAtual.getId_aula_realizada());

					int result = theQuery.executeUpdate();
					
					System.out.println("id aula realizada" + aulaRealizadaAtual.getId_aula_realizada() );

					System.out.println(result + " linha atualizada");
					
				}
				
				public void saveProximaAulaRealizada(AulaRealizadaImpl aulaRealizada) {
					Session currentSession = sessionFactory.getCurrentSession();
					Date current_date = new Date();

					aulaRealizada.setDt_criacao_aula_realizada(current_date);		
					

					currentSession.saveOrUpdate(aulaRealizada);
					
				}
				
		*/

}
