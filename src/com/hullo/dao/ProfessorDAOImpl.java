package com.hullo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hullo.entity.AlunoImpl;
import com.hullo.entity.ProfessorImpl;

@Repository
public class ProfessorDAOImpl implements UsuarioDAO<ProfessorImpl> {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<ProfessorImpl> getUsuarios() {

		Session currentSession = sessionFactory.getCurrentSession();

		Query<ProfessorImpl> theQuery = 
				currentSession.createQuery("from ProfessorImpl where tipo_usuario = 'professor' order by nome_usuario", ProfessorImpl.class);

		List<ProfessorImpl> usuarios = theQuery.getResultList();

		return usuarios;
	}

	@Override
	public void saveUsuario(ProfessorImpl theUsuario) {

		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.saveOrUpdate(theUsuario);
	}

	@Override
	public ProfessorImpl getUsuario(String email, String cpf) {
		//get current hibernate session
				Session currentSession = sessionFactory.getCurrentSession();
				
				System.out.println("chegou no DAO");
										
				// Cria query que faz busca no banco
				Query<ProfessorImpl> theQuery;
				//tirei a busca por aluno para nao correr o risco de um prof se cadastrar como aluno e zoar o barraco
				theQuery = currentSession.createQuery("from ProfessorImpl where email_usuario = '" + email + 
						"' or cpf_usuario = '" + cpf + "'", ProfessorImpl.class);
				
				try {
					
					//ve se tem mais de um professor com os dados
					List<ProfessorImpl> professores = theQuery.getResultList();
					//devolve o primeiro da lista
					ProfessorImpl validaProfessor = professores.get(0);
					return validaProfessor;
					
				} catch (Exception e) {
					return null;
				}
	}
	
	
	@Override
	public ProfessorImpl getUsuario(int id) {
		//get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// Cria query que faz busca no banco
		Query<ProfessorImpl> theQuery;
		theQuery = currentSession.createQuery("from ProfessorImpl where id_usuario='" + id + "'", ProfessorImpl.class);
		
		//executa query
		ProfessorImpl theUsuario = theQuery.getSingleResult();
		
		return theUsuario;
	}

	@Override
	public ProfessorImpl getUsuario(String email) {
		// TODO Auto-generated method stub
		return null;
	}


}
