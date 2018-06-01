package com.example.SistemN.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SistemN.Model.Lancamento;
import com.example.SistemN.Model.Pessoa;
import com.example.SistemN.Repository.LancamentoRepository;
import com.example.SistemN.Repository.PessoaRepository;
import com.example.SistemN.service.Exception.PessoaInesistenteOuInativaException;

@Service
public class LancamentoService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private LancamentoRepository lançamentoRepository;

	public Lancamento salvar(Lancamento lancamento) {
		Pessoa pessoa = pessoaRepository.findOne(lancamento.getPessoa().getIdPessoa());
		
		if(pessoa == null || pessoa.isInativo()) {
			throw new PessoaInesistenteOuInativaException();
		}
		
		return lançamentoRepository.save(lancamento);
	}

}
