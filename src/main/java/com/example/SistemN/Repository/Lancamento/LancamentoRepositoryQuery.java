package com.example.SistemN.Repository.Lancamento;

import java.util.List;

import com.example.SistemN.Model.Lancamento;
import com.example.SistemN.Repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery {
	
	public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter);

}
