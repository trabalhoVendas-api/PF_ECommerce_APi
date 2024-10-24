package br.com.serratec.ecommerce.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PedidoDTO {
	private Long id;
	private Long clienteId;
	private String status;
	private LocalDateTime dataPedido;
	private List<ItemPedidoDTO> itensPedido;

	/*public PedidoDTO(Long id, Long clienteId, String status, LocalDateTime dataPedido,
			List<ItemPedidoDTO> itensPedido) {
		super();
		this.id = id;
		this.clienteId = clienteId;
		this.status = status;
		this.dataPedido = dataPedido;
		this.itensPedido = itensPedido;
	}*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDateTime dataPedido) {
		this.dataPedido = dataPedido;
	}

	public List<ItemPedidoDTO> getItensPedido() {
		return itensPedido;
	}

	public void setItensPedido(List<ItemPedidoDTO> itensPedido) {
		this.itensPedido = itensPedido;
	}
}
