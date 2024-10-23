package br.com.serratec.ecommerce.service;

import br.com.serratec.ecommerce.entity.Pedido;
import br.com.serratec.ecommerce.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido inserirPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public Pedido editarPedido(Long id, Pedido pedido) {
		return pedido;
    }

    public Double calcularTotalPedido(Long id) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        double total = pedido.getItensPedido().stream()
                .mapToDouble(item -> (item.getPreco() - item.getDesconto()) * item.getQuantidade())
                .sum();
        return total;
    }
}
