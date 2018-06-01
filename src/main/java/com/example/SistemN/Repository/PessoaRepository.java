package com.example.SistemN.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SistemN.Model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
