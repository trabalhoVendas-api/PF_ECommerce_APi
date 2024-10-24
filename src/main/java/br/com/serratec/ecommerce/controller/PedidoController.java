package br.com.serratec.ecommerce.controller;

import br.com.serratec.ecommerce.dto.PedidoDTO;
import br.com.serratec.ecommerce.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	@PostMapping
	public ResponseEntity<PedidoDTO> inserirPedido(@RequestBody PedidoDTO pedidoDTO) {
		PedidoDTO novoPedido = pedidoService.inserirPedido(pedidoDTO);
		return ResponseEntity.ok(novoPedido);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PedidoDTO> editarPedido(@PathVariable Long id, @RequestBody PedidoDTO pedidoDTO) {
		PedidoDTO pedidoEditado = pedidoService.editarPedido(id, pedidoDTO);
		return ResponseEntity.ok(pedidoEditado);
	}

	@GetMapping("/{id}/total")
	public ResponseEntity<Double> calcularTotalPedido(@PathVariable Long id) {
		Double total = pedidoService.calcularTotalPedido(id);
		return ResponseEntity.ok(total);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarPedido(@PathVariable Long id) {
		pedidoService.deletarPedido(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping
	public ResponseEntity<List<PedidoDTO>> listarPedidos() {
		List<PedidoDTO> pedidos = pedidoService.listarPedidos();
		return ResponseEntity.ok(pedidos);
	}
}
