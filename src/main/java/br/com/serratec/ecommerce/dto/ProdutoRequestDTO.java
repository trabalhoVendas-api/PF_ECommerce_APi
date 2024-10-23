package br.com.serratec.ecommerce.dto;

import br.com.serratec.ecommerce.entity.Categoria;
import br.com.serratec.ecommerce.entity.Produto;

public class ProdutoRequestDTO {
	private String nome;
	private Integer quantidade;
	private Double preco;
	public Categoria categoria;

	public ProdutoRequestDTO() {
		// TODO Auto-generated constructor stub
	}

	public ProdutoRequestDTO(Produto produto) {
		this.nome = getNome();
		this.quantidade = getQuantidade();
		this.preco = getPreco();
		this.categoria = getCategoria();
	}
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
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
