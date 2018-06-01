package com.example.SistemN.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SistemN.Model.Categoria;
import com.example.SistemN.Model.Lancamento;
import com.example.SistemN.Repository.Lancamento.LancamentoRepositoryQuery;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {

}
