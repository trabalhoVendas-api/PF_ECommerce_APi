package br.com.serratec.ecommerce.dto;

import br.com.serratec.ecommerce.entity.Produto;

public class ProdutoResponseDTO {
	
	private String nome;
	private Integer quantidade;
	private Double preco;

	public ProdutoResponseDTO() {
		//construtor vazio
	}
	
	public ProdutoResponseDTO(Produto produto) {
		super();
		this.nome = produto.getNome();
		this.quantidade = produto.getQuantidade();
		this.preco = produto.getPreco();
	}
	
	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
}
