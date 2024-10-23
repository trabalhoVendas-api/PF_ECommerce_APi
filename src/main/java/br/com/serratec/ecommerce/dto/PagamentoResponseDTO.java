package br.com.serratec.ecommerce.dto;

import java.time.LocalDateTime;

import br.com.serratec.ecommerce.entity.Pagamento;
import br.com.serratec.ecommerce.enums.Status;
import jakarta.validation.constraints.NotNull;

public class PagamentoResponseDTO {

	private Long id;
	
	//@NotNull(message = "insira um valor válido")
	private Double valor;
	
	//@NotNull(message = "insira um método de pagamento válido")
	private String metodoPagamento;
	private LocalDateTime dataPagamento;
	private Status status;
	
	public PagamentoResponseDTO(Pagamento pagamento) {
		this.id = pagamento.getId();
		this.valor = pagamento.getValor();
		this.metodoPagamento = pagamento.getMetodoPagamento();
		this.dataPagamento = pagamento.getDataPagamento();
		this.status = pagamento.getStatus();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getMetodoPagamento() {
		return metodoPagamento;
	}

	public void setMetodoPagamento(String metodoPagamento) {
		this.metodoPagamento = metodoPagamento;
	}

	public LocalDateTime getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDateTime dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}