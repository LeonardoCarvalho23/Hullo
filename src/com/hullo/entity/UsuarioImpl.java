package com.hullo.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="usuario")
public class UsuarioImpl implements Usuario{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_usuario")
	private int id_usuario;
	
	@Column(name="nome_usuario")
	private String nome_usuario;
	
	@Column(name="sobrenome_usuario")
	private String sobrenome_usuario;
	
	@Column(name="cpf_usuario")
	private String cpf_usuario;
	
	@Column(name="cnpj_usuario")
	private String cnpj_usuario;
	
	@Column(name="email_usuario")
	private String email_usuario;
	
	@Column(name="senha_usuario")
	private String senha_usuario;
	
	@Column(name="sexo_usuario")
	private String sexo_usuario;
	
	@Column(name="data_nascimento_usuario")
	private String data_nascimento_usuario;
	
	@Column(name="telefone_usuario")
	private String telefone_usuario;
	
	@Column(name="tipo_usuario")
	private String tipo_usuario;
	
	@Column(name="profissao_usuario")
	private String profissao_usuario;
	
	@Column(name="ativo_usuario")
	private String ativo_usuario;
	
	@Column(name="dt_insert_usuario")
	private String dt_insert_usuario;
	
	@Column(name="dt_last_update_usuario")
	private String dt_last_update_usuario;
	
	@Column(name="cd_cidade_usuario")
	private int cd_cidade_usuario;

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

	public String getData_nascimento_usuario() {
		return data_nascimento_usuario;
	}

	public void setData_nascimento_usuario(String data_nascimento_usuario) {
		this.data_nascimento_usuario = data_nascimento_usuario;
	}

	public String getTelefone_usuario() {
		return telefone_usuario;
	}

	public void setTelefone_usuario(String telefone_usuario) {
		this.telefone_usuario = telefone_usuario;
	}

	public String getTipo_usuario() {
		return tipo_usuario;
	}

	public void setTipo_usuario(String tipo_usuario) {
		this.tipo_usuario = tipo_usuario;
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

	public String getDt_insert_usuario() {
		return dt_insert_usuario;
	}

	public void setDt_insert_usuario(String dt_insert_usuario) {
		this.dt_insert_usuario = dt_insert_usuario;
	}

	public String getDt_last_update_usuario() {
		return dt_last_update_usuario;
	}

	public void setDt_last_update_usuario(String dt_last_update_usuario) {
		this.dt_last_update_usuario = dt_last_update_usuario;
	}

	@Override
	public String toString() {
		return "UsuarioImpl [id_usuario=" + id_usuario + ", nome_usuario=" + nome_usuario + ", sobrenome_usuario="
				+ sobrenome_usuario + ", cpf_usuario=" + cpf_usuario + ", cnpj_usuario=" + cnpj_usuario
				+ ", email_usuario=" + email_usuario + ", senha_usuario=" + senha_usuario + ", sexo_usuario="
				+ sexo_usuario + ", data_nascimento_usuario=" + data_nascimento_usuario + ", telefone_usuario="
				+ telefone_usuario + ", tipo_usuario=" + tipo_usuario + ", profissao_usuario=" + profissao_usuario
				+ ", ativo_usuario=" + ativo_usuario + ", dt_insert_usuario=" + dt_insert_usuario
				+ ", dt_last_update_usuario=" + dt_last_update_usuario + "]";
	}


	
}
