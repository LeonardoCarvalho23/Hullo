package com.hullo.entity;

import java.util.List;

public class AulaRealizadaModel {
	
	private AlunoImpl aluno;
	private AulaImpl aulaAnterior;
	private AulaImpl aulaAtual;
	String nomeAula;
	private AulaRealizadaImpl aulaRealizadaAnterior;
	private AulaRealizadaImpl aulaRealizadaAtual;
	private AulaRealizadaImpl aulaRealizada;
	
	public AlunoImpl getAluno() {
		return aluno;
	}
	public void setAluno(AlunoImpl aluno) {
		this.aluno = aluno;
	}
	public AulaImpl getAulaAnterior() {
		return aulaAnterior;
	}
	public void setAulaAnterior(AulaImpl aulaAnterior) {
		this.aulaAnterior = aulaAnterior;
	}
	public AulaImpl getAulaAtual() {
		return aulaAtual;
	}
	public void setAulaAtual(AulaImpl aulaAtual) {
		this.aulaAtual = aulaAtual;
	}
	public AulaRealizadaImpl getAulaRealizadaAnterior() {
		return aulaRealizadaAnterior;
	}
	public void setAulaRealizadaAnterior(AulaRealizadaImpl aulaRealizadaAnterior) {
		this.aulaRealizadaAnterior = aulaRealizadaAnterior;
	}
	public AulaRealizadaImpl getAulaRealizadaAtual() {
		return aulaRealizadaAtual;
	}
	public void setAulaRealizadaAtual(AulaRealizadaImpl aulaRealizadaAtual) {
		this.aulaRealizadaAtual = aulaRealizadaAtual;
	}
	public String getNomeAula() {
		return nomeAula;
	}
	public void setNomeAula(String nomeAula) {
		this.nomeAula = nomeAula;
	}
	public AulaRealizadaImpl getAulaRealizada() {
		return aulaRealizada;
	}
	public void setAulaRealizada(AulaRealizadaImpl aulaRealizada) {
		this.aulaRealizada = aulaRealizada;
	}
	

	
}
