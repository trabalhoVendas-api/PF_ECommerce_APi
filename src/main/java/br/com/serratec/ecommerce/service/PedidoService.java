package br.com.serratec.ecommerce.service;

import br.com.serratec.ecommerce.dto.PedidoDTO;
import br.com.serratec.ecommerce.dto.ItemPedidoDTO;
import br.com.serratec.ecommerce.entity.Pedido;
import br.com.serratec.ecommerce.entity.ItemPedido;
import br.com.serratec.ecommerce.entity.Produto;
import br.com.serratec.ecommerce.repository.PedidoRepository;
import br.com.serratec.ecommerce.repository.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public PedidoDTO inserirPedido(Pedido pedido) {
        for (ItemPedido item : pedido.getItensPedido()) {
            Produto produto = produtoRepository.findById(item.getProduto().getId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
            item.setProduto(produto);
        }

        Pedido novoPedido = pedidoRepository.save(pedido);

        return convertToDTO(novoPedido);
    }

    public PedidoDTO editarPedido(Long id, Pedido pedido) {
        Pedido pedidoExistente = pedidoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        pedidoExistente.setStatus(pedido.getStatus());
        pedidoExistente.setItensPedido(pedido.getItensPedido());
        pedidoExistente.setDataPedido(pedido.getDataPedido());

        for (ItemPedido item : pedido.getItensPedido()) {
            Produto produto = produtoRepository.findById(item.getProduto().getId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
            item.setProduto(produto);
        }

        Pedido pedidoEditado = pedidoRepository.save(pedidoExistente);

        return convertToDTO(pedidoEditado);
    }

    public Double calcularTotalPedido(Long id) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        return pedido.getItensPedido().stream()
            .mapToDouble(item -> (item.getPreco() - item.getDesconto()) * item.getQuantidade()).sum();
    }

    private PedidoDTO convertToDTO(Pedido pedido) {
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setId(pedido.getId());
        pedidoDTO.setClienteId(pedido.getCliente().getId());
        pedidoDTO.setStatus(pedido.getStatus().name());
        pedidoDTO.setDataPedido(pedido.getDataPedido());

        List<ItemPedidoDTO> itensPedidoDTO = pedido.getItensPedido().stream()
            .map(item -> {
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
