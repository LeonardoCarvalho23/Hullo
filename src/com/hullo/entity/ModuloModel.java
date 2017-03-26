package com.hullo.entity;

import java.util.List;

public class ModuloModel {

	private ModuloImpl modulo;
	private List<AulaImpl> listaAulas;
	public ModuloImpl getModulo() {
		return modulo;
	}
	public void setModulo(ModuloImpl modulo) {
		this.modulo = modulo;
	}
	public List<AulaImpl> getListaAulas() {
		return listaAulas;
	}
	public void setListaAulas(List<AulaImpl> listaAulas) {
		this.listaAulas = listaAulas;
	}
	
	
}
