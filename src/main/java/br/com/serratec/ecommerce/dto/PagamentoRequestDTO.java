package br.com.serratec.ecommerce.dto;

import br.com.serratec.ecommerce.enums.Status;

public class PagamentoRequestDTO {
	
	//@NotNull(message = "Insira um valor válido")
    private Double valor;
    
    //@NotNull(message = "Método de pagamento invalido")
    private String metodoPagamento;
    
    private Status status;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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

	@Override
	public String toString() {
		return "PagamentoRequestDTO [valor=" + valor + ", metodoPagamento=" + metodoPagamento + "]";
	}
    
    
}
