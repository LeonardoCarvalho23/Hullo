package com.hullo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="modulo")
public class ModuloImpl {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_modulo")
	private int id_modulo;
	
	@Column(name="nm_modulo")
	private String nm_modulo;
	
	@Column(name="indice_modulo")
	private float indice_modulo; //para definir ordem dos modulos
	
	@Column(name="ativo_modulo")
	private char ativo_modulo; // 0 = inativo ou 1 = ativo
	
	@Column(name="dt_insert_modulo")
	private Date dt_insert_modulo;
	
	@Column(name="dt_last_update_modulo")
	private Date dt_last_update_modulo;

	
	public int getId_modulo() {
		return id_modulo;
	}

	public void setId_modulo(int id_modulo) {
		this.id_modulo = id_modulo;
	}

	public String getNm_modulo() {
		return nm_modulo;
	}

	public void setNm_modulo(String nm_modulo) {
		this.nm_modulo = nm_modulo;
	}

	public float getIndice_modulo() {
		return indice_modulo;
	}

	public void setIndice_modulo(float indice_modulo) {
		this.indice_modulo = indice_modulo;
	}

	public char getAtivo_modulo() {
		return ativo_modulo;
	}

	public void setAtivo_modulo(char ativo_modulo) {
		this.ativo_modulo = ativo_modulo;
	}

	public Date getDt_insert_modulo() {
		return dt_insert_modulo;
	}

	public void setDt_insert_modulo(Date dt_insert_modulo) {
		this.dt_insert_modulo = dt_insert_modulo;
	}

	public Date getDt_last_update_modulo() {
		return dt_last_update_modulo;
	}

	public void setDt_last_update_modulo(Date dt_last_update_modulo) {
		this.dt_last_update_modulo = dt_last_update_modulo;
	}
	
	
}
