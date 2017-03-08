package com.hullo.dao;

import java.util.List;

import com.hullo.entity.UsuarioImpl;

public interface UsuarioDAO<T> {
	
	public List<T> getUsuarios();

	public void saveUsuario(T theUsuario);

	public void buscaUsuario(UsuarioImpl oUsuario);
	
}
