package com.hullo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cidade")
public class CidadeImpl {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cidade")
	private int id_Cidade;
	
	@Column(name="nm_cidade")
	private String nm_cidade;

	@Column(name="sg_cidade")
	private String sg_cidade;
	
	@Column(name="id_Estado")
	private int id_Estado;

	public int getId_Cidade() {
		return id_Cidade;
	}

	public void setId_Cidade(int id_Cidade) {
		this.id_Cidade = id_Cidade;
	}

	public String getNm_cidade() {
		return nm_cidade;
	}

	public void setNm_cidade(String nm_cidade) {
		this.nm_cidade = nm_cidade;
	}

	public String getSg_cidade() {
		return sg_cidade;
	}

	public void setSg_cidade(String sg_cidade) {
		this.sg_cidade = sg_cidade;
	}

	public int getEstado() {
		return id_Estado;
	}

	public void setEstado(int id_Estado) {
		this.id_Estado = id_Estado;
	}
	
	
}
