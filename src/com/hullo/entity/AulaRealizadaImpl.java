package com.hullo.entity;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * classe para AulaRealizadaImpl
 * 
 * @author Hullo Team
 * @version 1.0
 */
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
	
	@Column(name = "comentario_aula_realizada")
	private String comentario_aula_realizada;
	
	@Column(name = "status_aula_realizada")
	private String status_aula_realizada;
	
	@Column(name = "dt_criacao_aula_realizada")
	private Date dt_criacao_aula_realizada;
	
	@Column(name = "dt_inicio_chamada_aula_realizada")
	private Date dt_inicio_aula_realizada;
	
	@Column(name = "dt_fim_chamada_aula_realizada")
	private Date dt_fim_aula_realizada;
	
	@Column(name = "id_aula_aula_realizada")
	private int id_aula_aula_realizada;
	
	@Column(name = "id_aluno_aula_realizada")
	private int id_aluno_aula_realizada;
	
	@Column(name = "id_professor_aula_realizada")
	private Integer id_professor_aula_realizada;
	
	@Column(name = "sid_chamada_aula_realizada")
	private String sid_chamada_aula_realizada;
	
	@Column(name = "url_gravacao_aula_realizada")
	private String url_gravacao_aula_realizada;
	
	@Column(name = "duracao_chamada_aula_realizada")
	private String duracao_chamada_aula_realizada;
	
	@Column (name = "custo_chamada_aula_realizada")
	private BigInteger custo_chamada_aula_realizada;
	
	@Column (name = "status_chamada_aula_realizada")
	private String status_chamada_aula_realizada;

	public AulaRealizadaImpl(){
		
	}
	
	/**
	 * 
	 * @param id_aula_aula_realizada
	 * @param id_aluno_aula_realizada
	 */
	public AulaRealizadaImpl(int id_aula_aula_realizada, int id_aluno_aula_realizada){
		this.dt_criacao_aula_realizada = new Date();
		this.id_aula_aula_realizada = id_aula_aula_realizada;
		this.id_aluno_aula_realizada = id_aluno_aula_realizada;
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

	public String getComentario_aula_realizada() {
		return comentario_aula_realizada;
	}
	public void setComentario_aula_realizada(String comentario_aula_realizada) {
		this.comentario_aula_realizada = comentario_aula_realizada;
	}
	public String getSid_chamada_aula_realizada() {
		return sid_chamada_aula_realizada;
	}
	public void setSid_chamada_aula_realizada(String sid_chamada_aula_realizada) {
		this.sid_chamada_aula_realizada = sid_chamada_aula_realizada;
	}
	public String getDuracao_chamada_aula_realizada() {
		return duracao_chamada_aula_realizada;
	}
	public void setDuracao_chamada_aula_realizada(String duracao_chamada_aula_realizada) {
		this.duracao_chamada_aula_realizada = duracao_chamada_aula_realizada;
	}
	public BigInteger getCusto_chamada_aula_realizada() {
		return custo_chamada_aula_realizada;
	}
	public void setCusto_chamada_aula_realizada(BigInteger custo_chamada_aula_realizada) {
		this.custo_chamada_aula_realizada = custo_chamada_aula_realizada;
	}
	public String getStatus_chamada_aula_realizada() {
		return status_chamada_aula_realizada;
	}
	public void setStatus_chamada_aula_realizada(String status_chamada_aula_realizada) {
		this.status_chamada_aula_realizada = status_chamada_aula_realizada;
	}
	public String getStatus_ligacao_aula_realizada() {
		return status_aula_realizada;
	}

	public void setStatus_ligacao_aula_realizada(String status_ligacao_aula_realizada) {
		this.status_aula_realizada = status_ligacao_aula_realizada;
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

	public Integer getId_professor_aula_realizada() {
		return id_professor_aula_realizada;
	}

	public void setId_professor_aula_realizada(Integer id_professor_aula_realizada) {
		this.id_professor_aula_realizada = id_professor_aula_realizada;
	}

	public String getStatus_aula_realizada() {
		return status_aula_realizada;
	}
	public void setStatus_aula_realizada(String status_aula_realizada) {
		this.status_aula_realizada = status_aula_realizada;
	}
	public String getUrl_gravacao_aula_realizada() {
		return url_gravacao_aula_realizada;
	}
	public void setUrl_gravacao_aula_realizada(String url_gravacao_aula_realizada) {
		this.url_gravacao_aula_realizada = url_gravacao_aula_realizada;
	}
	@Override
	public String toString() {
		return "AulaRealizadaImpl [id_aula_realizada=" + id_aula_realizada + ", nota_model_aula_realizada="
				+ nota_model_aula_realizada + ", nota_practice_aula_realizada=" + nota_practice_aula_realizada
				+ ", nota_production_aula_realizada=" + nota_production_aula_realizada
				+ ", status_ligacao_aula_realizada=" + status_aula_realizada + ", dt_criacao_aula_realizada="
				+ dt_criacao_aula_realizada + ", dt_inicio_aula_realizada=" + dt_inicio_aula_realizada
				+ ", dt_fim_aula_realizada=" + dt_fim_aula_realizada + ", id_aula_aula_realizada="
				+ id_aula_aula_realizada + ", id_aluno_aula_realizada=" + id_aluno_aula_realizada
				+ ", id_professor_aula_realizada=" + id_professor_aula_realizada + "]";
	}
	
}
