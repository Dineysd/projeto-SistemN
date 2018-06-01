package com.example.SistemN.Repository.Lancamento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.util.StringUtils;

import com.example.SistemN.Model.Lancamento;
import com.example.SistemN.Model.Lancamento_;
import com.example.SistemN.Repository.filter.LancamentoFilter;

public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter) {
		CriteriaBuilder buider = manager.getCriteriaBuilder();
		CriteriaQuery<Lancamento> criteria = buider.createQuery(Lancamento.class);

		Root<Lancamento> root = criteria.from(Lancamento.class);

		// criar as retrições
		Predicate[] predicates = criarRestricoes(lancamentoFilter, buider, root);
		criteria.where(predicates);

		TypedQuery<Lancamento> query = manager.createQuery(criteria);

		return query.getResultList();
	}

	private Predicate[] criarRestricoes(LancamentoFilter lancamentoFilter, CriteriaBuilder buider,
			Root<Lancamento> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		// where descrição like '%asdkskd%';
		if (!StringUtils.isEmpty(lancamentoFilter.getDescricao())) {
			
			predicates.add(buider.like(buider.lower(root.get(Lancamento_.descricao)),
					"%" + lancamentoFilter.getDescricao().toLowerCase() + "%"));
		}

		if (lancamentoFilter.getDataDeVencimentoDe() != null) {
 
			predicates.add(
					buider.greaterThanOrEqualTo(root.get(Lancamento_.dataVencimento),
							lancamentoFilter.getDataDeVencimentoDe()));
		}

		if (lancamentoFilter.getDataDeVencimentoDeAte() != null) {
 
			predicates.add(
					buider.lessThanOrEqualTo(root.get(Lancamento_.dataVencimento),
							lancamentoFilter.getDataDeVencimentoDeAte()));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
