package br.com.serratec.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.ecommerce.dto.PagamentoRequestDTO;
import br.com.serratec.ecommerce.dto.PagamentoResponseDTO;
import br.com.serratec.ecommerce.service.PagamentoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {
	
	@Autowired
	private PagamentoService pagamentoService;
	
	@PostMapping
    public ResponseEntity<PagamentoResponseDTO> realizarPagamento(@Valid @RequestBody PagamentoRequestDTO pagamentoRequestDTO) {
		System.out.println(pagamentoRequestDTO);
        PagamentoResponseDTO response = pagamentoService.processarPagamento(pagamentoRequestDTO);
        return ResponseEntity.ok(response);
    }
	
	@GetMapping
    public ResponseEntity<List<PagamentoResponseDTO>> listarPagamentos() {
        List<PagamentoResponseDTO> pagamentos = pagamentoService.listarPagamento();
        return ResponseEntity.ok(pagamentos);
    }
	
	@PutMapping("{id}")
	public ResponseEntity<Object> editarPagamento(@RequestBody PagamentoRequestDTO dto, @PathVariable Long id){
		PagamentoResponseDTO dtoResponse = pagamentoService.editarPagamento(dto, id);
		return ResponseEntity.ok(dtoResponse);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarPagamento(@PathVariable Long id) {
	    pagamentoService.deletarPagamento(id);
	    return ResponseEntity.noContent().build();
	}
}
