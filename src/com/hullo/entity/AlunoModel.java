package com.hullo.entity;

import java.util.List;

public class AlunoModel {

	private AlunoImpl usuario;
	private List<EstadoImpl> estado;
	private String cidade;
	
	public AlunoImpl getUsuario() {
		return usuario;
	}
	public void setUsuario(AlunoImpl usuario) {
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
