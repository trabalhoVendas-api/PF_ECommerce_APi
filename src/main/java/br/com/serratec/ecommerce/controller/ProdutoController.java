package br.com.serratec.ecommerce.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.ecommerce.dto.ProdutoRequestDTO;
import br.com.serratec.ecommerce.dto.ProdutoResponseDTO;
import br.com.serratec.ecommerce.repository.ProdutoRepository;
import br.com.serratec.ecommerce.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@PostMapping
	public ResponseEntity<Object> inserir(@RequestBody ProdutoRequestDTO dto) throws IOException {
		ProdutoResponseDTO dtoResponse = produtoService.inserir(dto);
		return ResponseEntity.created(null).body(dtoResponse);
	}
	
	@GetMapping
	public ResponseEntity<List<ProdutoResponseDTO>> listar(){
		return ResponseEntity.ok(produtoService.listar());
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Object> editarProduto(@RequestBody ProdutoRequestDTO dto, @PathVariable Long id){
		ProdutoResponseDTO dtoResponse = produtoService.editarProduto(dto, id);
		return ResponseEntity.ok(dtoResponse);
	}
}
