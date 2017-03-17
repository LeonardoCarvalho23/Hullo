package com.hullo.service;

import java.util.List;

import com.hullo.entity.Usuario;
import com.hullo.entity.UsuarioImpl;;

public interface UsuarioService<T> {
	
	public List<T> getUsuarios(); //import java util

	public void saveUsuario(T theUsuario);

	public T getUsuario(String email, String senha);

	public Usuario getUsuario(int id_usuario);

	public T getUsuario(String email);

}
