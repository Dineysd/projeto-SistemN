package com.example.SistemN.evet.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.SistemN.evet.RecursoCriadoEvent;

public class RecursoCriadolistener  implements ApplicationListener<RecursoCriadoEvent>{

	@Override
	public void onApplicationEvent(RecursoCriadoEvent recurso) {
		
		HttpServletResponse response =recurso.getResponse();
		Long codigo = recurso.getCodigo();
		
		adicionarHeaderLocation(response, codigo);
	}

	private void adicionarHeaderLocation(HttpServletResponse response, Long codigo) {
		URI uri =ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{idPessoa}")
				.buildAndExpand(codigo).toUri();
				response.setHeader("location", uri.toASCIIString());
	}

}
