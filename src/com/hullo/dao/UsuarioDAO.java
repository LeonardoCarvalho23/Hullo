package com.hullo.dao;

import java.util.List;

import com.hullo.entity.UsuarioImpl;

public interface UsuarioDAO {
	
	public List<UsuarioImpl> getUsuarios();

	public void saveUsuario(UsuarioImpl theUsuario);
	
}
