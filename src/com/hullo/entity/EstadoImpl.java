package com.hullo.entity;

import java.io.IOException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
@Table(name="Estado")
public class EstadoImpl {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_estado")
	@JsonProperty("id_estado")
	private int id_estado;
	
	@Column(name="nm_estado")
	@JsonProperty("nm_estado")
	private String nm_estado;

	@Column(name="sg_estado")
	@JsonProperty("sg_estado")
	private String sg_estado;
	
	public EstadoImpl(){
		
	}
	
	public EstadoImpl(String json) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		EstadoImpl estado = mapper.readValue(json, EstadoImpl.class);
		
		id_estado = estado.id_estado;
		nm_estado = estado.nm_estado;
		sg_estado = estado.sg_estado;
	}

	public int getId_estado() {
		return id_estado;
	}

	public void setId_estado(int id_estado) {
		this.id_estado = id_estado;
	}

	public String getNm_estado() {
		return nm_estado;
	}

	public void setNm_estado(String nm_estado) {
		this.nm_estado = nm_estado;
	}

	public String getSg_estado() {
		return sg_estado;
	}

	public void setSg_estado(String sg_estado) {
		this.sg_estado = sg_estado;
	}

	@Override
	public String toString() {
		return "{\"id_estado\":\"" + id_estado + "\", \"nm_estado\":\"" + nm_estado + "\", \"sg_estado\":\"" + sg_estado + "\"}";
	}
	
	

}
