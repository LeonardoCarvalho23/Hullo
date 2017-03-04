package com.hullo.service;

import java.util.List;

import com.hullo.entity.UsuarioImpl;;

public interface UsuarioService {
	
	public List<UsuarioImpl> getUsuarios(); //import java util

	public void saveUsuario(UsuarioImpl theUsuario);

}
