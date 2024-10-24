package br.com.serratec.ecommerce.controller;

import br.com.serratec.ecommerce.entity.RelatorioVendas;
import br.com.serratec.ecommerce.service.RelatorioVendasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/relatorios-vendas")
public class RelatorioVendasController {

    @Autowired
    private RelatorioVendasService relatorioVendasService;

    @GetMapping
    public ResponseEntity<List<RelatorioVendas>> listarRelatorios() {
        return ResponseEntity.ok(relatorioVendasService.listarRelatorios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RelatorioVendas> obterRelatorioPorId(@PathVariable Long id) {
        return ResponseEntity.ok(relatorioVendasService.obterRelatorioPorId(id));
    }

    @PostMapping
    public ResponseEntity<RelatorioVendas> criarRelatorio(@RequestBody RelatorioVendas relatorioVendas) {
        return ResponseEntity.ok(relatorioVendasService.criarRelatorio(relatorioVendas));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RelatorioVendas> atualizarRelatorio(@PathVariable Long id, @RequestBody RelatorioVendas relatorioVendas) {
        return ResponseEntity.ok(relatorioVendasService.atualizarRelatorio(id, relatorioVendas));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRelatorio(@PathVariable Long id) {
        relatorioVendasService.deletarRelatorio(id);
        return ResponseEntity.noContent().build();
    }
}