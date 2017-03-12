package com.hullo.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hullo.dao.EstadoDAOImpl;
import com.hullo.entity.EstadoImpl;

@Service
public class EstadoServiceImpl {
	
	@Autowired
	private EstadoDAOImpl estadoDAO;
	
	@Transactional
	public List<EstadoImpl> getEstados(){
		
		return estadoDAO.getEstados();
	}

}
