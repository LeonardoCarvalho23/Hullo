package com.hullo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hullo.dao.LogDAOImpl;

@Service
public class LogServiceImpl {
	@Autowired
	private LogDAOImpl logDAO;
	
	@Transactional
	public void saveAlunoLog(int id_aluno){
		logDAO.saveAlunoLog(id_aluno);
	}
	
	@Transactional
	public void saveProfessorLog(int id_professor){
		logDAO.saveProfessorLog(id_professor);
	}
}
