package com.hullo.dao;

import java.util.List;

public interface UsuarioDAO<T> {
	
	public List<T> getUsuarios();

	public void saveUsuario(T theUsuario);

	public T getUsuario(String email, String senha);

	public T getUsuario(int id);

	public T getUsuario(String email);

	public void udateUsuario(T theUsuario);

}
