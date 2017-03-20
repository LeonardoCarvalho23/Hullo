package com.hullo.dao;

public class EstadoDTO {
	
	private String value;
	private String label;
	public EstadoDTO(String sg_estado, String nm_estado) {
		value = sg_estado;
		label = nm_estado;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
}
