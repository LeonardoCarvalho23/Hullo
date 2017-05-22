package com.hullo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hullo.entity.UsuarioImpl;


/**
* classe para UsuarioAOImpl
* @author Hullo Team 
* @version 1.0
 */

@Repository
public class UsuarioDAOImpl implements UsuarioDAO<UsuarioImpl> {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	/**
	 * metodo que lista usuarios 
	 * @Transactional //so you don't need to start and commit, import from spring(non-Javadoc)
	 * @see com.hullo.dao.UsuarioDAO#getUsuarios()
	 * @return List<UsuarioImpl>
	 */
	
	@Override
	public List<UsuarioImpl> getUsuarios() {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession(); //import from hibernate
		
		//create a query, import hibernate.query
		Query<UsuarioImpl> theQuery = 
				currentSession.createQuery("from UsuarioImpl order by nome_usuario", UsuarioImpl.class);
		
		//execute query and get result list
		List<UsuarioImpl> usuarios = theQuery.getResultList();
		
		//return the results
		 return usuarios;
	}

	/**
	 * metodo para salvar usuario
	 * @param theUsuario 
	 */
	@Override
	public void saveUsuario(UsuarioImpl theUsuario) {
		
		//get current hibernate session	
		Session currentSession = sessionFactory.getCurrentSession();
		
		//save the usuario
		currentSession.saveOrUpdate(theUsuario);
	}

	/**
	 * metodo para buscar usuario
	 * @param email
	 * @param senha
	 * @return theUsuario
	 * @return null
	 */
	@Override
	public UsuarioImpl getUsuario(String email, String senha) {
		
		//get current hibernate session
		 
		Session currentSession = sessionFactory.getCurrentSession();

		//Cria query que faz busca no banco
		 
		Query<UsuarioImpl> theQuery;
		theQuery = currentSession.createQuery("from UsuarioImpl where ativo_usuario = '1' and email_usuario='" + email + "'", UsuarioImpl.class);
		
		//Testa com try catch a execução da query e se foi encontrado algo, é obrigatorio o uso do try catch
		 
		UsuarioImpl result = null;
		boolean empty = false;
		try {
			result = theQuery.getSingleResult();
		} catch (Exception e) {
			//TODO Auto-generated catch block
			 
			empty = true;
		}

		// Se a variável empty for verdadeira, significa que usuário não foi encontrado e retorna null
		 
		if (empty){
			return null;
		} else {
			//Do contrário, guardo o usuário na variavel theUsuario e testo agora a senha
			 
			UsuarioImpl theUsuario = result;
			if (theUsuario.getSenha_usuario().equals(senha)){
				return theUsuario;
			} else {
				return null;
			}
		}		
	}

	/**
	 * metodo para buscar usuario por id
	 * @param id
	 * @return theUsuario
	 */
	@Override
	public UsuarioImpl getUsuario(int id) {
		//get current hibernate session
		 
		Session currentSession = sessionFactory.getCurrentSession();
		
		//Cria query que faz busca no banco
		 
		Query<UsuarioImpl> theQuery;
		theQuery = currentSession.createQuery("from UsuarioImpl where id_usuario='" + id + "'", UsuarioImpl.class);
		
		//executa query
		 
		UsuarioImpl theUsuario = theQuery.getSingleResult();
		
		return theUsuario;
	}

	/**
	 * metodo para buscar usuario por email
	 * @param email
	 * @return theUsuario
	 * @return null
	 * 
	 */
	@Override
	public UsuarioImpl getUsuario(String email) {
		//get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//Cria query que faz busca no banco
		Query<UsuarioImpl> theQuery;
			theQuery = currentSession.createQuery("from UsuarioImpl where email_usuario='" + email + "'", UsuarioImpl.class);
				
		//Testa com try catch a execução da query e se foi encontrado algo, é obrigatorio o uso do try catch
		 
		UsuarioImpl result = null;
		boolean empty = false;
			try {
					result = theQuery.getSingleResult();
				} catch (Exception e) {
					//TODO Auto-generated catch block
					 
					empty = true;
				}

				// Se a variável empty for verdadeira, significa que usuário não foi encontrado e retorna null
				 
		if (empty){
			return null;
				} else {
					//Do contrário, guardo o usuário na variavel theUsuario e testo agora a senha
					 
					UsuarioImpl theUsuario = result;
						return theUsuario;
					} 
				}

	
	
	/**
	 * metodo implementado apenas por obrigacao(non-Javadoc)
	 * @see com.hullo.dao.UsuarioDAO#udateUsuario(java.lang.Object)
	 * @param theUsuario
	 */
	@Override
	public void udateUsuario(UsuarioImpl theUsuario) {
		/* **
		 *  TODO Auto-generated method stub
		 */
		
	}
	
	/**
	 * metodo implementado apenas por obrigacao(non-Javadoc)
	 * @see com.hullo.dao.UsuarioDAO#udateUsuario(java.lang.Object)
	 * @param theUsuario
	 */
	@Override
	public void inactivateUsuario(UsuarioImpl theUsuario){
		
	}

	/**
	 * metodo implementado apenas por obrigacao(non-Javadoc)
	 * @see com.hullo.dao.UsuarioDAO#udateUsuario(java.lang.Object)
	 * @param email
	 * @param id_usuario
	 * @return null
	 */
	@Override
	public UsuarioImpl validaUsuario(String email, int id_usuario) {
		/* **
		 *  TODO Auto-generated method stub
		 */
		return null;
	}
	
	/**
	 * metodo implementado apenas por obrigacao(non-Javadoc)
	 * @see com.hullo.dao.UsuarioDAO#udateUsuario(java.lang.Object)
	 * @param email
	 * @param cpf
	 * @return null
	 */
	@Override
	public UsuarioImpl validaUsuario(String email, String cpf) {
		/* **
		 *  TODO Auto-generated method stub
		 */
		return null;
	}
	
}
