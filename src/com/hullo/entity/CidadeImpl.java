package com.hullo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_estado_cidade")
	private EstadoImpl estado;

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

	public EstadoImpl getEstado() {
		return estado;
	}

	public void setEstado(EstadoImpl estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "{id_Cidade=" + id_Cidade + ", nm_cidade=" + nm_cidade + ", estado=" + estado + "}";
	}

}
