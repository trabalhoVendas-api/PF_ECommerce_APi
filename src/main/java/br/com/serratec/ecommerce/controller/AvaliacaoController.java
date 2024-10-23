package br.com.serratec.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.ecommerce.dto.AvaliacaoDTO;
import br.com.serratec.ecommerce.service.AvaliacaoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {
    @Autowired
    private AvaliacaoService service;

    @PostMapping
    public ResponseEntity<AvaliacaoDTO> criarAvaliacao(@RequestBody @Valid AvaliacaoDTO avaliacaoDTO) {
        AvaliacaoDTO novaAvaliacao = service.criarAvaliacao(avaliacaoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaAvaliacao);
    }

    @GetMapping("/produtos/{produtoId}")
    public ResponseEntity<List<AvaliacaoDTO>> listarAvaliacoesPorProduto(@PathVariable Long produtoId) {
        List<AvaliacaoDTO> avaliacoes = service.listarAvaliacoesPorProduto(produtoId);
        return ResponseEntity.ok(avaliacoes);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoDTO> buscarAvaliacao(@PathVariable Long id) {
        AvaliacaoDTO avaliacao = service.listarAvaliacaoPorId(id);
        return ResponseEntity.ok(avaliacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvaliacaoDTO> atualizarAvaliacao(
            @PathVariable Long id,
            @RequestBody @Valid AvaliacaoDTO avaliacaoDTO) {
        AvaliacaoDTO avaliacaoAtualizada = service.atualizarAvaliacao(id, avaliacaoDTO);
        return ResponseEntity.ok(avaliacaoAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAvaliacao(
            @PathVariable Long id,
            @RequestParam Long clienteId) {
        service.deletarAvaliacao(id, clienteId);
        return ResponseEntity.noContent().build();
    }
}