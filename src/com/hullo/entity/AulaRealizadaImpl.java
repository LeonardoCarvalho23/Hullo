package com.hullo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "aula_realizada")
public class AulaRealizadaImpl {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_aula_realizada")
	private int id_aula_realizada;
	
	@Column(name = "id_anterior_aula_realizada")
	private Integer id_anterior_aula_realizada;
	
	@Column(name = "nota_model_aula_realizada")
	private Integer nota_model_aula_realizada;
	
	@Column(name = "nota_practice_aula_realizada")
	private Integer nota_practice_aula_realizada;
	
	@Column(name = "nota_production_aula_realizada")
	private Integer nota_production_aula_realizada;
	
	@Column(name = "status_ligacao_aula_realizada")
	private String status_ligacao_aula_realizada;
	
	@Column(name = "dt_criacao_aula_realizada")
	private Date dt_criacao_aula_realizada;
	
	@Column(name = "dt_inicio_aula_realizada")
	private Date dt_inicio_aula_realizada;
	
	@Column(name = "dt_fim_aula_realizada")
	private Date dt_fim_aula_realizada;
	
	@Column(name = "id_aula_aula_realizada")
	private int id_aula_aula_realizada;
	
	@Column(name = "id_aluno_aula_realizada")
	private int id_aluno_aula_realizada;
	
	@Column(name = "id_professor_aula_realizada")
	private int id_professor_aula_realizada;

	public AulaRealizadaImpl(){
		
	}
	public AulaRealizadaImpl(int id_aula_aula_realizada, int id_aluno_aula_realizada){
		this.dt_criacao_aula_realizada = new Date();
		this.id_aula_aula_realizada = id_aula_aula_realizada;
		this.id_aluno_aula_realizada = id_aluno_aula_realizada;
		this.id_professor_aula_realizada = 1;
	}
	
	public int getId_aula_realizada() {
		return id_aula_realizada;
	}

	public void setId_aula_realizada(int id_aula_realizada) {
		this.id_aula_realizada = id_aula_realizada;
	}

	public Integer getId_anterior_aula_realizada() {
		return id_anterior_aula_realizada;
	}
	
	public void setId_anterior_aula_realizada(Integer id_anterior_aula_realizada) {
		this.id_anterior_aula_realizada = id_anterior_aula_realizada;
	}
	
	public Integer getNota_model_aula_realizada() {
		return nota_model_aula_realizada;
	}

	public void setNota_model_aula_realizada(Integer nota_model_aula_realizada) {
		this.nota_model_aula_realizada = nota_model_aula_realizada;
	}

	public Integer getNota_practice_aula_realizada() {
		return nota_practice_aula_realizada;
	}

	public void setNota_practice_aula_realizada(Integer nota_practice_aula_realizada) {
		this.nota_practice_aula_realizada = nota_practice_aula_realizada;
	}

	public Integer getNota_production_aula_realizada() {
		return nota_production_aula_realizada;
	}

	public void setNota_production_aula_realizada(Integer nota_production_aula_realizada) {
		this.nota_production_aula_realizada = nota_production_aula_realizada;
	}

	public String getStatus_ligacao_aula_realizada() {
		return status_ligacao_aula_realizada;
	}

	public void setStatus_ligacao_aula_realizada(String status_ligacao_aula_realizada) {
		this.status_ligacao_aula_realizada = status_ligacao_aula_realizada;
	}

	public Date getDt_criacao_aula_realizada() {
		return dt_criacao_aula_realizada;
	}

	public void setDt_criacao_aula_realizada(Date dt_criacao_aula_realizada) {
		this.dt_criacao_aula_realizada = dt_criacao_aula_realizada;
	}

	public Date getDt_inicio_aula_realizada() {
		return dt_inicio_aula_realizada;
	}

	public void setDt_inicio_aula_realizada(Date dt_inicio_aula_realizada) {
		this.dt_inicio_aula_realizada = dt_inicio_aula_realizada;
	}

	public int getId_aula_aula_realizada() {
		return id_aula_aula_realizada;
	}

	public void setId_aula_aula_realizada(int id_aula_aula_realizada) {
		this.id_aula_aula_realizada = id_aula_aula_realizada;
	}

	public Date getDt_fim_aula_realizada() {
		return dt_fim_aula_realizada;
	}

	public void setDt_fim_aula_realizada(Date dt_fim_aula_realizada) {
		this.dt_fim_aula_realizada = dt_fim_aula_realizada;
	}

	public int getId_aluno_aula_realizada() {
		return id_aluno_aula_realizada;
	}

	public void setId_aluno_aula_realizada(int id_aluno_aula_realizada) {
		this.id_aluno_aula_realizada = id_aluno_aula_realizada;
	}

	public int getId_professor_aula_realizada() {
		return id_professor_aula_realizada;
	}

	public void setId_professor_aula_realizada(int id_professor_aula_realizada) {
		this.id_professor_aula_realizada = id_professor_aula_realizada;
	}

	@Override
	public String toString() {
		return "AulaRealizadaImpl [id_aula_realizada=" + id_aula_realizada + ", nota_model_aula_realizada="
				+ nota_model_aula_realizada + ", nota_practice_aula_realizada=" + nota_practice_aula_realizada
				+ ", nota_production_aula_realizada=" + nota_production_aula_realizada
				+ ", status_ligacao_aula_realizada=" + status_ligacao_aula_realizada + ", dt_criacao_aula_realizada="
				+ dt_criacao_aula_realizada + ", dt_inicio_aula_realizada=" + dt_inicio_aula_realizada
				+ ", dt_fim_aula_realizada=" + dt_fim_aula_realizada + ", id_aula_aula_realizada="
				+ id_aula_aula_realizada + ", id_aluno_aula_realizada=" + id_aluno_aula_realizada
				+ ", id_professor_aula_realizada=" + id_professor_aula_realizada + "]";
	}
	
}
