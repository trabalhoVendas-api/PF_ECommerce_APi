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

import br.com.serratec.ecommerce.dto.CategoriaRequestDTO;
import br.com.serratec.ecommerce.dto.CategoriaResponseDTO;
import br.com.serratec.ecommerce.dto.ProdutoResponseDTO;
import br.com.serratec.ecommerce.repository.CategoriaRepository;
import br.com.serratec.ecommerce.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository repository;
	
	@Autowired
	private CategoriaService service;
	
	@PostMapping
	public ResponseEntity<Object> inserir(@RequestBody CategoriaRequestDTO dto) throws IOException {
		CategoriaResponseDTO dtoResponse = service.inserir(dto);
		return ResponseEntity.created(null).body(dtoResponse);
	}
	
	@GetMapping
	public ResponseEntity<List<CategoriaResponseDTO>> listar(){
		return ResponseEntity.ok(service.listar());
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Object> editarCategoria(@RequestBody CategoriaRequestDTO dto, @PathVariable Long id){
		CategoriaResponseDTO dtoResponse = service.editar(dto, id);
		return ResponseEntity.ok(dtoResponse);
	}
	
    @GetMapping("/{id}/produtos")
    public ResponseEntity<List<ProdutoResponseDTO>> listarProdutosPorCategoria(@PathVariable Long id) {
        return ResponseEntity.ok(service.listarProdutosPorCategoria(id));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.listarPorId(id));
    }
}