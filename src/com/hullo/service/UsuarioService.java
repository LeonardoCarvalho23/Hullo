package com.hullo.service;

import java.util.List;


public interface UsuarioService<T> {
	
	public List<T> getUsuarios(); //import java util

	public void saveUsuario(T theUsuario);

	//metodo de busca do login
	public T getUsuario(String email, String senha);
	
	public T validaUsuario(String email, int id);

	public T getUsuario(int id_usuario);

	public T getUsuario(String email);

	public void updateUsuario(T theUsuario);

	public void inactivateUsuario(T theUsuario);

	//metodo validacao na hora de cadastrar
	public T validaUsuario(String email, String cpf);

}
