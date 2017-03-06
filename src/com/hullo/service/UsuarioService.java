package com.hullo.service;

import java.util.List;

import com.hullo.entity.UsuarioImpl;;

public interface UsuarioService<T> {
	
	public List<T> getUsuarios(); //import java util

	public void saveUsuario(UsuarioImpl theUsuario);
	

}
