/**
* classe para ProfessorImpl
* @author Hullo Team 
* @version 1.0
 */

package com.hullo.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="professor")
public class ProfessorImpl implements Usuario {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_professor")
	private int id_usuario;
	
	@Column(name="nome_professor")
	private String nome_usuario;
	
	@Column(name="sobrenome_professor")
	private String sobrenome_usuario;
	
	@Column(name="cpf_professor")
	private String cpf_usuario;
	
	@Column(name="cnpj_professor")
	private String cnpj_usuario;
	
	@Column(name="email_professor")
	private String email_usuario;
	
	@Column(name="senha_professor")
	private String senha_usuario;
	
	@Column(name="sexo_professor")
	private String sexo_usuario;
	
	@Column(name="data_nascimento_professor")
	private Date data_nascimento_usuario;
	
	@Column(name="telefone_professor")
	private String telefone_usuario;

	@Column(name="profissao_professor")
	private String profissao_usuario;
	
	@Column(name="ativo_professor")
	private String ativo_usuario;
	
	@Column(name="dt_insert_professor")
	private Date dt_insert_usuario;
	
	@Column(name="dt_last_update_professor")
	private Date dt_last_update_usuario;
	
	@Column(name="cd_cidade_professor")
	private int cd_cidade_usuario;
	
	
	public ProfessorImpl(){
	}
	
	/* **
	 * construtor para transformar um usuarioImpl em um alunoImpl
	 */
	public ProfessorImpl(UsuarioImpl usuario) {
		this.id_usuario = usuario.getId_usuario();
		this.nome_usuario = usuario.getNome_usuario();
		this.sobrenome_usuario = usuario.getSobrenome_usuario();
		this.cpf_usuario = usuario.getCpf_usuario();
		this.cnpj_usuario = usuario.getCnpj_usuario();
		this.email_usuario = usuario.getEmail_usuario();
		this.senha_usuario = usuario.getSenha_usuario();
		this.sexo_usuario = usuario.getSexo_usuario();
		this.data_nascimento_usuario = usuario.getData_nascimento_usuario();
		this.telefone_usuario = usuario.getTelefone_usuario();
		this.profissao_usuario = usuario.getProfissao_usuario();
		this.ativo_usuario = usuario.getAtivo_usuario();
		this.dt_insert_usuario = usuario.getDt_insert_usuario();
		this.dt_last_update_usuario = usuario.getDt_last_update_usuario();
	}
	

	public int getCidade() {
		return cd_cidade_usuario;
	}

	public void setCidade(int cd_cidade_usuario) {
		this.cd_cidade_usuario = cd_cidade_usuario;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getNome_usuario() {
		return nome_usuario;
	}

	public void setNome_usuario(String nome_usuario) {
		this.nome_usuario = nome_usuario;
	}

	public String getSobrenome_usuario() {
		return sobrenome_usuario;
	}

	public void setSobrenome_usuario(String sobrenome_usuario) {
		this.sobrenome_usuario = sobrenome_usuario;
	}

	public String getCpf_usuario() {
		return cpf_usuario;
	}

	public void setCpf_usuario(String cpf_usuario) {
		this.cpf_usuario = cpf_usuario;
	}

	public String getCnpj_usuario() {
		return cnpj_usuario;
	}

	public void setCnpj_usuario(String cnpj_usuario) {
		this.cnpj_usuario = cnpj_usuario;
	}

	public String getEmail_usuario() {
		return email_usuario;
	}

	public void setEmail_usuario(String email_usuario) {
		this.email_usuario = email_usuario;
	}

	public String getSenha_usuario() {
		return senha_usuario;
	}

	public void setSenha_usuario(String senha_usuario) {
		this.senha_usuario = senha_usuario;
	}

	public String getSexo_usuario() {
		return sexo_usuario;
	}

	public void setSexo_usuario(String sexo_usuario) {
		this.sexo_usuario = sexo_usuario;
	}

	public Date getData_nascimento_usuario() {
		return data_nascimento_usuario;
	}

	public void setData_nascimento_usuario(Date data_nascimento_usuario) {
		this.data_nascimento_usuario = data_nascimento_usuario;
	}

	public String getTelefone_usuario() {
		return telefone_usuario;
	}

	public void setTelefone_usuario(String telefone_usuario) {
		this.telefone_usuario = telefone_usuario;
	}

	public String getProfissao_usuario() {
		return profissao_usuario;
	}

	public void setProfissao_usuario(String profissao_usuario) {
		this.profissao_usuario = profissao_usuario;
	}

	public String getAtivo_usuario() {
		return ativo_usuario;
	}

	public void setAtivo_usuario(String ativo_usuario) {
		this.ativo_usuario = ativo_usuario;
	}

	public Date getDt_insert_usuario() {
		return dt_insert_usuario;
	}

	public void setDt_insert_usuario(Date dt_insert_usuario) {
		this.dt_insert_usuario = dt_insert_usuario;
	}

	public Date getDt_last_update_usuario() {
		return dt_last_update_usuario;
	}

	public void setDt_last_update_usuario(Date dt_last_update_usuario) {
		this.dt_last_update_usuario = dt_last_update_usuario;
	}

	@Override
	public String toString() {
		return "ProfessorImpl [id_usuario=" + id_usuario + ", nome_usuario=" + nome_usuario + ", sobrenome_usuario="
				+ sobrenome_usuario + ", cpf_usuario=" + cpf_usuario + ", cnpj_usuario=" + cnpj_usuario
				+ ", email_usuario=" + email_usuario + ", senha_usuario=" + senha_usuario + ", sexo_usuario="
				+ sexo_usuario + ", data_nascimento_usuario=" + data_nascimento_usuario + ", telefone_usuario="
				+ telefone_usuario + ", profissao_usuario=" + profissao_usuario
				+ ", ativo_usuario=" + ativo_usuario + ", dt_insert_usuario=" + dt_insert_usuario
				+ ", dt_last_update_usuario=" + dt_last_update_usuario + "]";
	}



}
