package com.hullo.service;

import java.util.List;

public interface UsuarioService<T> {
	
	public List<T> getUsuarios(); //import java util

	public void saveUsuario(T theUsuario);

	public T getUsuario(String email, String senha);
	
	

}
