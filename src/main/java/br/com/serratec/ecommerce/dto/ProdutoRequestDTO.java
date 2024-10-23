package br.com.serratec.ecommerce.dto;

import br.com.serratec.ecommerce.entity.Categoria;
import br.com.serratec.ecommerce.entity.Produto;

public class ProdutoRequestDTO {
    private Long id;
    private String nome;
    private Integer quantidade;
    private Double preco;
    public Categoria categoria;

    public ProdutoRequestDTO() {}

    public ProdutoRequestDTO(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.quantidade = produto.getQuantidade();
        this.preco = produto.getPreco();
        this.categoria = produto.getCategoria();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
    
    
}
