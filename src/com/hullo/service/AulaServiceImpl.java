package com.hullo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hullo.dao.AulaDAOImpl;
import com.hullo.entity.AulaImpl;

/**
 * Aula Service
 * @author Hullo Team
 *
 */
@Service
public class AulaServiceImpl {

	@Autowired
	private AulaDAOImpl aulaDAO;
	
	/**
	 * Salvar aula
	 * @param aula
	 */
	@Transactional
	public void saveAula(AulaImpl aula) {
		System.out.println("chegou no service");
		aulaDAO.saveAula(aula);
	}
	
	/**
	 * Buscar todas as aulas de um modulo
	 * @param id_modulo
	 * @return
	 */
	@Transactional
	public List<AulaImpl> getAulas(int id_modulo) {
		return aulaDAO.getAulas(id_modulo);
	}

	/**
	 * Pegar aula por Id
	 * @param id_aula
	 * @return
	 */
	@Transactional
	public AulaImpl getAula(int id_aula) {
		return aulaDAO.getAula(id_aula);
	}

	/**
	 * Atualizar aula
	 * @param aula
	 */
	@Transactional
	public void updateAula(AulaImpl aula) {
		aulaDAO.updateAula(aula);
	}

	/**
	 * Pega a primeira aula
	 * @param id_modulo
	 * @return
	 */
	@Transactional
	public AulaImpl getPrimeiraAula(int id_modulo){
		return aulaDAO.getPrimeiraAula(id_modulo);
	}
	
	/**
	 * Valida a aula
	 * @param indice_aula
	 * @param numero_aula
	 * @param id_modulo_aula
	 * @param id_aula
	 * @return
	 */
	@Transactional
	public AulaImpl validaAula(char indice_aula, int numero_aula, int id_modulo_aula, int id_aula) {
		return aulaDAO.validaAula(indice_aula, numero_aula, id_modulo_aula, id_aula );
	}

	/**
	 * Deleta a aula
	 * @param aula
	 */
	@Transactional
	public void deleteAula(AulaImpl aula) {
		aulaDAO.deleteAula(aula);	
	}
	
	/**
	 * Deleta todas as aulas do módulo
	 * @param id_modulo
	 */
	@Transactional
	public void deleteAulasModulo(int id_modulo){
		aulaDAO.deleteAulasModulo(id_modulo);
	}

	/**
	 * Metodo para buscar proxima aula linear pelo id da atual
	 * @param id_aula_aula_realizada
	 * @return
	 */
	@Transactional
	public AulaImpl getProximaAulaLinear(int id_aula_aula_realizada) {
		return aulaDAO.getProximaAulaLinear(id_aula_aula_realizada);
	}

	/**
	 * Pega a próxima aula de um mesmo índice (paralela)
	 * @param id_aula_aula_realizada
	 * @return
	 */
	@Transactional
	public AulaImpl getProximaAulaParalela(int id_aula_aula_realizada) {
		return aulaDAO.getProximaAulaParalela(id_aula_aula_realizada);
	}
	
	/**
	 * Pega o nome da aula pelo id
	 * @param id_aula
	 * @return
	 */
	@Transactional
	public String getNomeAula (int id_aula){
		return aulaDAO.getNomeAula(id_aula);
	}
		
	
}
