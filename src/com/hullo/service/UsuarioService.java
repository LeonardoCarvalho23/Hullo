package com.hullo.service;

import java.util.List;

import com.hullo.entity.AlunoImpl;
import com.hullo.entity.Usuario;

public interface UsuarioService<T> {
	
	public List<T> getUsuarios(); //import java util

	public void saveUsuario(T theUsuario);

	public T getUsuario(String email, String senha);

	public T getUsuario(int id_usuario);

	public T getUsuario(String email);

	public void updateUsuario(T theUsuario);

	public void inactivateUsuario(T theUsuario);

}
