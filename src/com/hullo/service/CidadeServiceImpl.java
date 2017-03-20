package com.hullo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hullo.dao.CidadeDAOImpl;
import com.hullo.entity.CidadeImpl;
import com.hullo.entity.EstadoImpl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Service
public class CidadeServiceImpl {

	@Autowired
	private CidadeDAOImpl cidadeDAO;
	
	@Transactional
	public List<CidadeImpl> getCidades(){
		
		return cidadeDAO.getCidades();
	}
	
	@Transactional
	public List<CidadeImpl> getCidades(EstadoImpl estado){
		
		return cidadeDAO.getCidades(estado);
	}

	@Transactional
	public List<CidadeImpl> obterCidadesDoEstado(EstadoImpl estado) {
		
		return cidadeDAO.getCidades(estado);
	}
	
}
