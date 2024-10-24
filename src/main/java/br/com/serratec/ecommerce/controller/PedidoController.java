package br.com.serratec.ecommerce.controller;

import br.com.serratec.ecommerce.dto.PedidoDTO;
import br.com.serratec.ecommerce.entity.Pedido;
import br.com.serratec.ecommerce.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoDTO> inserirPedido(@RequestBody Pedido pedido) {
        PedidoDTO novoPedido = pedidoService.inserirPedido(pedido);
        return ResponseEntity.ok(novoPedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> editarPedido(@PathVariable Long id, @RequestBody Pedido pedido) {
        PedidoDTO pedidoEditado = pedidoService.editarPedido(id, pedido);
        return ResponseEntity.ok(pedidoEditado);
    }

    @GetMapping("/{id}/total")
    public ResponseEntity<Double> calcularTotalPedido(@PathVariable Long id) {
        Double total = pedidoService.calcularTotalPedido(id);
        return ResponseEntity.ok(total);
    }
}
