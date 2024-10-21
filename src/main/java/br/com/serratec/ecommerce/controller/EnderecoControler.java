package br.com.serratec.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.ecommerce.dto.EnderecoResponseDTO;
import br.com.serratec.ecommerce.service.EnderecoService;

@RestController
@RequestMapping("/enderecos")
public class EnderecoControler {
	
	@Autowired
	private EnderecoService service;
	
	@GetMapping("{cep}")
	public ResponseEntity<EnderecoResponseDTO>buscarCep(@PathVariable String cep) {
		return ResponseEntity.ok(service.buscar(cep));
	}

	
}
