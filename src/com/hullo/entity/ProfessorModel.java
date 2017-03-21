package com.hullo.entity;

import java.util.List;

public class ProfessorModel {
	
	private ProfessorImpl usuario;
	private List<EstadoImpl> estado;
	private String cidade;
	
	public ProfessorImpl getUsuario() {
		return usuario;
	}
	public void setUsuario(ProfessorImpl usuario) {
		this.usuario = usuario;
	}
	public List<EstadoImpl> getEstado() {
		return estado;
	}
	public void setEstado(List<EstadoImpl> estado) {
		this.estado = estado;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	

}
