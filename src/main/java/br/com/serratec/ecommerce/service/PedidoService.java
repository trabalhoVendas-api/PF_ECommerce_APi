package br.com.serratec.ecommerce.service;

import br.com.serratec.ecommerce.entity.ItemPedido;
import br.com.serratec.ecommerce.entity.Pedido;
import br.com.serratec.ecommerce.entity.Produto;
import br.com.serratec.ecommerce.repository.PedidoRepository;
import br.com.serratec.ecommerce.repository.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	public Pedido inserirPedido(Pedido pedido) {
	    for (ItemPedido item : pedido.getItensPedido()) {
	        item.setPedido(pedido);
	        Produto produto = produtoRepository.findById(item.getProduto().getId())
	            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
	        item.setProduto(produto);
	    }
	    return pedidoRepository.save(pedido);
	}

	public Pedido editarPedido(Long id, Pedido pedido) {
		Pedido pedidoExistente = pedidoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

		pedidoExistente.setStatus(pedido.getStatus());
		pedidoExistente.setItensPedido(pedido.getItensPedido());
		pedidoExistente.setDataPedido(pedido.getDataPedido());

		for (ItemPedido item : pedido.getItensPedido()) {
			item.setPedido(pedidoExistente);
			Produto produto = produtoRepository.findById(item.getProduto().getId())
					.orElseThrow(() -> new RuntimeException("Produto não encontrado"));
			item.setProduto(produto);
		}

		return pedidoRepository.save(pedidoExistente);
	}

	public Double calcularTotalPedido(Long id) {
		Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
		return pedido.getItensPedido().stream()
				.mapToDouble(item -> (item.getPreco() - item.getDesconto()) * item.getQuantidade()).sum();
	}
}
