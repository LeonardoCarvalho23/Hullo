package com.hullo.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
* Objeto que estabalece mapeamente entre AlunoImpl e tabela aluno
* @author Hullo Team 
* @version 1.0
 */
@Entity
@Table(name="aluno")
public class AlunoImpl implements Usuario {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_aluno")
	private int id_usuario;
	
	@Column(name="nome_aluno")
	private String nome_usuario;
	
	@Column(name="sobrenome_aluno")
	private String sobrenome_usuario;
	
	@Column(name="cpf_aluno")
	private String cpf_usuario;
	
	@Column(name="email_aluno")
	private String email_usuario;
	
	@Column(name="senha_aluno")
	private String senha_usuario;
	
	@Column(name="sexo_aluno")
	private String sexo_usuario;
	
	@Column(name="data_nascimento_aluno")
	private Date data_nascimento_usuario;
	
	@Column(name="telefone_aluno")
	private String telefone_usuario;

	@Column(name="profissao_aluno")
	private String profissao_usuario;
	
	@Column(name="ativo_aluno")
	private String ativo_usuario;
	
	@Column(name="dt_insert_aluno")
	private Date dt_insert_usuario;
	
	@Column(name="dt_last_update_aluno")
	private Date dt_last_update_usuario;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="cd_cidade_aluno")
	private CidadeImpl cidade;
	
	
	public AlunoImpl() {
	}
	
	/**
	 * construtor para transformar um usuarioImpl em um alunoImpl
	 * @param usuario
	 */
	public AlunoImpl(UsuarioImpl usuario) {
		this.id_usuario = usuario.getId_usuario();
		this.nome_usuario = usuario.getNome_usuario();
		this.sobrenome_usuario = usuario.getSobrenome_usuario();
		this.cpf_usuario = usuario.getCpf_usuario();
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

	/**
	 * 
	 * @return id
	 */
	public int getId_usuario() {
		return id_usuario;
	}

	/**
	 * 
	 * @param id_usuario
	 */
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	/**
	 * 
	 * @return nome usuario
	 */
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

	public CidadeImpl getCidade() {
		return cidade;
	}

	public void setCidade(CidadeImpl cidade) {
		this.cidade = cidade;
	}

	@Override
	public String toString() {
		return "AlunoImpl [id_usuario=" + id_usuario + ", nome_usuario=" + nome_usuario + ", sobrenome_usuario="
				+ sobrenome_usuario + ", cpf_usuario=" + cpf_usuario
				+ ", email_usuario=" + email_usuario + ", senha_usuario=" + senha_usuario + ", sexo_usuario="
				+ sexo_usuario + ", data_nascimento_usuario=" + data_nascimento_usuario + ", telefone_usuario="
				+ telefone_usuario + ", profissao_usuario=" + profissao_usuario
				+ ", ativo_usuario=" + ativo_usuario + ", dt_insert_usuario=" + dt_insert_usuario
				+ ", dt_last_update_usuario=" + dt_last_update_usuario + "]";
	}
	
}
