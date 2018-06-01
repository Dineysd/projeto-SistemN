package com.example.SistemN.Repository.Lancamento;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.SistemN.Model.Lancamento;
import com.example.SistemN.Repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery {
	
	public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);

}
