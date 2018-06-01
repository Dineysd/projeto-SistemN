package com.example.SistemN.Controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.SistemN.ExceptionHandler.SistemNExceptionHandler.Erro;
import com.example.SistemN.Model.Lancamento;
import com.example.SistemN.Repository.LancamentoRepository;
import com.example.SistemN.Repository.filter.LancamentoFilter;
import com.example.SistemN.evet.RecursoCriadoEvent;
import com.example.SistemN.service.LancamentoService;
import com.example.SistemN.service.Exception.PessoaInesistenteOuInativaException;

@RestController
@RequestMapping("/lancamento")
public class LancamentoResource {
	
	@Autowired
	private LancamentoRepository repository;
	
	@Autowired
	private LancamentoService service;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private MessageSource source;

	@GetMapping
	public Page<Lancamento> pesquisar(LancamentoFilter lancamentoFilter, Pageable pageable){
		return repository.filtrar(lancamentoFilter, pageable);
		
	}
	
	@PostMapping
	public ResponseEntity<Lancamento> criar(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response) {
		Lancamento lancamentoSalva = service.salvar(lancamento);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalva.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalva);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<?> buscaPorID(@PathVariable Long codigo) {
		Lancamento cod = repository.findOne(codigo);
		
		return cod != null ? ResponseEntity.ok(cod ) : ResponseEntity.notFound().build();
	}
	
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo){
		repository.delete(codigo);
		
		
	}
	
	@ExceptionHandler({PessoaInesistenteOuInativaException.class})
	public ResponseEntity<Object> HandlerPessoaInesistenteOuInativaException(PessoaInesistenteOuInativaException ex){
		
		String menssagemUsuario = source.getMessage("pessoa.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
		String menssagemDesenvolvedor = ex.toString();
		// retorna a mensagem do erro
		List<Erro> erros = Arrays.asList(new Erro(menssagemUsuario, menssagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
		
	}
	
	
	
}
