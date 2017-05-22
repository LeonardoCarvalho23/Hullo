package com.hullo.dao;

import java.util.List;

/**
* classe para UsuarioAO
* @author Hullo Team 
* @version 1.0
 */


public interface UsuarioDAO<T> {
	
	public List<T> getUsuarios();

	public void saveUsuario(T theUsuario);

	public T getUsuario(String email, String senha);

	public T getUsuario(int id);

	public T getUsuario(String email);

	public void udateUsuario(T theUsuario);

	public void inactivateUsuario(T theUsuario);

	public T validaUsuario(String email, int id_usuario);

	public T validaUsuario(String email, String cpf);

}
