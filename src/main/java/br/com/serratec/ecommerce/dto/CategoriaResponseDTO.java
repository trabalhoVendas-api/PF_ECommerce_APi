package br.com.serratec.ecommerce.dto;

import br.com.serratec.ecommerce.entity.Categoria;

public class CategoriaResponseDTO {
	private Long id;
	private String nome;

	public CategoriaResponseDTO() {}

	public CategoriaResponseDTO(Categoria categoria) {
		this.id = categoria.getId();
		this.nome = categoria.getNome();
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
}