package com.hullo.service;

import java.util.List;

import com.hullo.entity.Usuario;;

public interface UsuarioService {
	
	public List<Usuario> getUsuarios(); //import java util

	public void saveUsuario(Usuario theUsuario);

}
