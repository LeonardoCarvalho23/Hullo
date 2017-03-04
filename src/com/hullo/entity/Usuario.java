package com.hullo.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="usuario")
public interface Usuario {
	
	public String toString();
	
}
