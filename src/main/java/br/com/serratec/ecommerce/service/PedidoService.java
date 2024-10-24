package br.com.serratec.ecommerce.service;

import br.com.serratec.ecommerce.dto.ItemPedidoDTO;
import br.com.serratec.ecommerce.dto.PedidoDTO;
import br.com.serratec.ecommerce.entity.ItemPedido;
import br.com.serratec.ecommerce.entity.Pedido;
import br.com.serratec.ecommerce.entity.Produto;
import br.com.serratec.ecommerce.entity.StatusPedido;
import br.com.serratec.ecommerce.repository.PedidoRepository;
import br.com.serratec.ecommerce.repository.ProdutoRepository;
import br.com.serratec.ecommerce.entity.Cliente;
import br.com.serratec.ecommerce.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	public PedidoDTO inserirPedido(PedidoDTO pedidoDTO) {
		Pedido pedido = new Pedido();
		Cliente cliente = clienteRepository.findById(pedidoDTO.getClienteId())
				.orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
		pedido.setCliente(cliente);
		pedido.setStatus(StatusPedido.valueOf(pedidoDTO.getStatus()));
		pedido.setDataPedido(LocalDateTime.now());

		List<ItemPedido> itens = pedidoDTO.getItensPedido().stream().map(itemDTO -> {
			Produto produto = produtoRepository.findById(itemDTO.getProdutoId())
					.orElseThrow(() -> new RuntimeException("Produto não encontrado"));

			ItemPedido item = new ItemPedido();
			item.setProduto(produto);
			item.setQuantidade(itemDTO.getQuantidade());
			item.setDesconto(itemDTO.getDesconto());
			item.setPreco(itemDTO.getPreco());
			item.setPedido(pedido);
			return item;
		}).collect(Collectors.toList());

		pedido.setItensPedido(itens);
		Pedido novoPedido = pedidoRepository.save(pedido);
		return convertToDTO(novoPedido);
	}

	public PedidoDTO editarPedido(Long id, PedidoDTO pedidoDTO) {
		Pedido pedidoExistente = pedidoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

		pedidoExistente.setStatus(StatusPedido.valueOf(pedidoDTO.getStatus()));

		List<ItemPedido> itens = pedidoDTO.getItensPedido().stream().map(itemDTO -> {
			Produto produto = produtoRepository.findById(itemDTO.getProdutoId())
					.orElseThrow(() -> new RuntimeException("Produto não encontrado"));

			ItemPedido item = new ItemPedido();
			item.setProduto(produto);
			item.setQuantidade(itemDTO.getQuantidade());
			item.setDesconto(itemDTO.getDesconto());
			item.setPreco(itemDTO.getPreco());
			item.setPedido(pedidoExistente);
			return item;
		}).collect(Collectors.toList());

		pedidoExistente.setItensPedido(itens);
		Pedido pedidoEditado = pedidoRepository.save(pedidoExistente);
		return convertToDTO(pedidoEditado);
	}

	public Double calcularTotalPedido(Long id) {
		Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

		return pedido.getItensPedido().stream()
				.mapToDouble(item -> (item.getPreco() - item.getDesconto()) * item.getQuantidade()).sum();
	}

	public void deletarPedido(Long id) {
		Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
		pedidoRepository.delete(pedido);
	}

	public List<PedidoDTO> listarPedidos() {
		return pedidoRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	private PedidoDTO convertToDTO(Pedido pedido) {
		PedidoDTO pedidoDTO = new PedidoDTO();
		pedidoDTO.setId(pedido.getId());
		pedidoDTO.setClienteId(pedido.getCliente().getId());
		pedidoDTO.setStatus(pedido.getStatus().name());
		pedidoDTO.setDataPedido(pedido.getDataPedido());

		List<ItemPedidoDTO> itensPedidoDTO = pedido.getItensPedido().stream().map(item -> {
			ItemPedidoDTO itemDTO = new ItemPedidoDTO();
			itemDTO.setProdutoId(item.getProduto().getId());
			itemDTO.setQuantidade(item.getQuantidade());
			itemDTO.setDesconto(item.getDesconto());
			itemDTO.setPreco(item.getPreco());
			return itemDTO;
		}).collect(Collectors.toList());

		pedidoDTO.setItensPedido(itensPedidoDTO);
		return pedidoDTO;
	}
}
