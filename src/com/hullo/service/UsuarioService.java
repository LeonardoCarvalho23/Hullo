package com.hullo.service;

import java.util.List;

/**
* classe para UsuarioService
* @author Hullo Team 
* @version 1.0
 */

public interface UsuarioService<T> {
	
	
	/**
	 * metodo lista usuarios
	 * @return T
	 */
	public List<T> getUsuarios();

	
	/**
	 * metodo para salvar
	 * @param theUsuario
	 */
	public void saveUsuario(T theUsuario);

	
	/**
	 * metodo para buscar no login
	 * @param email
	 * @param senha
	 * @return T
	 */
	public T getUsuario(String email, String senha);
	
	/**
	 * metodo valida usuario
	 * @param email
	 * @param id
	 * @return T
	 */
	public T validaUsuario(String email, int id);

	/**
	 * metodo para buscar usuario por id
	 * @param id_usuario
	 * @return T
	 */
	public T getUsuario(int id_usuario);

	/**
	 * metodo para buscar usuario por email
	 * @param email
	 * @return T
	 */
	public T getUsuario(String email);

	/**
	 * metodo para atualizar usuario
	 * @param theUsuario
	 */
	public void updateUsuario(T theUsuario);

	/**
	 * metodo para inativar usuario
	 * @param theUsuario
	 */
	public void inactivateUsuario(T theUsuario);

	
	/**
	 * metodo validacao na hora de cadastrar
	 * @param email
	 * @param cpf
	 * @return T
	 */
	public T validaUsuario(String email, String cpf);

}
