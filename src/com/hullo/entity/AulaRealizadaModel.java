package com.hullo.entity;

import java.util.List;

public class AulaRealizadaModel {
	
	private AlunoImpl aluno;
	private AulaImpl aulaAnterior;
	private AulaImpl aulaAtual;
	private AulaImpl nomeAula;
	private AulaRealizadaImpl aulaRealizadaAnterior;
	private AulaRealizadaImpl aulaRealizadaAtual;
	
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
	public AulaImpl getNomeAula() {
		return nomeAula;
	}
	public void setNomeAula(AulaImpl nomeAula) {
		this.nomeAula = nomeAula;
	}

	
}
