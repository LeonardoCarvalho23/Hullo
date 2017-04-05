package com.hullo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="log_usuario")
public class LogImpl {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_log_usuario")
	private int id;
	
	@Column(name="id_professor_log_usuario")
	private int id_professor;
	
	@Column(name="id_aluno_log_usuario")
	private int id_aluno;
	
	@Column(name="dt_login_log_usuario")
	private Date dt_login;
	
	@Column(name="dt_logout_log_usuario")
	private Date dt_logout;

	public void setId_professor(int id_professor) {
		this.id_professor = id_professor;
	}

	public void setId_aluno(int id_aluno) {
		this.id_aluno = id_aluno;
	}

	public void setDt_login(Date dt_login) {
		this.dt_login = dt_login;
	}

	public void setDt_logout(Date dt_logout) {
		this.dt_logout = dt_logout;
	}

	public int getId_professor() {
		return id_professor;
	}

	public int getId_aluno() {
		return id_aluno;
	}

	public Date getDt_login() {
		return dt_login;
	}

	public Date getDt_logout() {
		return dt_logout;
	}
	
	
}
