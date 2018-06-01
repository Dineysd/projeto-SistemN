package com.example.SistemN.Repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class LancamentoFilter {

	private String descricao;
	
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	private LocalDate dataDeVencimentoDe;
	
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	private LocalDate dataDeVencimentoDeAte;
	
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public LocalDate getDataDeVencimentoDe() {
		return dataDeVencimentoDe;
	}
	public void setDataDeVencimentoDe(LocalDate dataDeVencimentoDe) {
		this.dataDeVencimentoDe = dataDeVencimentoDe;
	}
	public LocalDate getDataDeVencimentoDeAte() {
		return dataDeVencimentoDeAte;
	}
	public void setDataDeVencimentoDeAte(LocalDate dataDeVencimentoDeAte) {
		this.dataDeVencimentoDeAte = dataDeVencimentoDeAte;
	}
}
