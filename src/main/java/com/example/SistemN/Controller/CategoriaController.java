package com.example.SistemN.Controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.SistemN.Model.Categoria;
import com.example.SistemN.Repository.CategoriaRepository;
import com.example.SistemN.evet.RecursoCriadoEvent;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository repositorio;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<Categoria> listar(){
		return repositorio.findAll();
		
	}
	
	@PostMapping
	public ResponseEntity<Categoria> criar(@Valid @RequestBody Categoria categoria, HttpServletResponse response) {
		Categoria categoriaSalva = repositorio.save(categoria);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getCategoriaId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
	}
	
	/*@GetMapping("/{idCategoria}")
	public Categoria buscarPeloCodigo(@PathVariable Long idCategoria) {
		return repositorio.findOne(idCategoria);
		
	} exercicio*/
	
	
	@GetMapping("/{codigo}")
	public ResponseEntity<?> buscaPorID(@PathVariable Long codigo) {
		Categoria cod = repositorio.findOne(codigo);
		
		return cod != null ? ResponseEntity.ok(cod ) : ResponseEntity.notFound().build();
	}
	
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo){
		repositorio.delete(codigo);
		
		
	}
	
	
	
}
