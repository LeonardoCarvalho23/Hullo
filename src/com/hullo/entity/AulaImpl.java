package com.hullo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="aula")
public class AulaImpl {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_aula")
	private int id_aula;
	
	@Column(name="nm_aula")
	private String nm_aula;
	
	@Column(name="numero_aula")
	private int numero_aula;
	
	@Column(name="indice_aula")
	private char indice_aula;

	@Column(name="revisao_aula")
	private String revisao_aula;
	
	@Column(name="coneudo_aula")
	private String conteudo_aula;
	
	@Column(name="atividade_aula")
	private String atividade_aula;
	
	@Column(name="teaser_aula")
	private String teaser_aula;
	
	@Column(name="ativo_aula")
	private boolean ativo_aula;
	
	@Column(name="dt_insert_aula")
	private Date dt_insert_aula;

	@Column(name="dt_last_update_aula")
	private Date dt_last_update_aula;
	
	@Column(name="id_modulo_aula")
	private int id_modulo_aula;
	

	public int getId_aula() {
		return id_aula;
	}

	public void setId_aula(int id_aula) {
		this.id_aula = id_aula;
	}

	public String getNm_aula() {
		return nm_aula;
	}

	public void setNm_aula(String nm_aula) {
		this.nm_aula = nm_aula;
	}

	public int getNumero_aula() {
		return numero_aula;
	}

	public void setNumero_aula(int numero_aula) {
		this.numero_aula = numero_aula;
	}

	public char getIndice_aula() {
		return indice_aula;
	}

	public void setIndice_aula(char indice_aula) {
		this.indice_aula = indice_aula;
	}

	public String getRevisao_aula() {
		return revisao_aula;
	}

	public void setRevisao_aula(String revisao_aula) {
		this.revisao_aula = revisao_aula;
	}

	public String getConteudo_aula() {
		return conteudo_aula;
	}

	public void setConteudo_aula(String conteudo_aula) {
		this.conteudo_aula = conteudo_aula;
	}

	public String getAtividade_aula() {
		return atividade_aula;
	}

	public void setAtividade_aula(String atividade_aula) {
		this.atividade_aula = atividade_aula;
	}

	public String getTeaser_aula() {
		return teaser_aula;
	}

	public void setTeaser_aula(String teaser_aula) {
		this.teaser_aula = teaser_aula;
	}

	public boolean isAtivo_aula() {
		return ativo_aula;
	}

	public void setAtivo_aula(boolean ativo_aula) {
		this.ativo_aula = ativo_aula;
	}

	public Date getDt_insert_aula() {
		return dt_insert_aula;
	}

	public void setDt_insert_aula(Date dt_insert_aula) {
		this.dt_insert_aula = dt_insert_aula;
	}

	public Date getDt_last_update_aula() {
		return dt_last_update_aula;
	}

	public void setDt_last_update_aula(Date dt_last_update_aula) {
		this.dt_last_update_aula = dt_last_update_aula;
	}

	public int getId_modulo_aula() {
		return id_modulo_aula;
	}

	public void setId_modulo_aula(int id_modulo_aula) {
		this.id_modulo_aula = id_modulo_aula;
	}

	
	
}
