package br.com.serratec.ecommerce.entity;

import java.time.LocalDateTime;

import br.com.serratec.ecommerce.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Pagamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//@NotNull(message = "Insira um valor válido")
	private Double valor;
	private LocalDateTime dataPagamento;
	
	//@NotNull(message = "Insira um método de pagamento válido")
	private String metodoPagamento;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
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

	public String getMetodoPagamento() {
		return metodoPagamento;
	}

	public void setMetodoPagamento(String metodoPagamento) {
		this.metodoPagamento = metodoPagamento;
	}

	@Override
	public String toString() {
		return "Pagamento [id=" + id + ", valor=" + valor + ", dataPagamento=" + dataPagamento + ", metodoPagamento="
				+ metodoPagamento + ", status=" + status + "]";
	}
	
	
	
}