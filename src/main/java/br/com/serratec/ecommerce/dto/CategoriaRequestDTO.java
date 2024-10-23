package br.com.serratec.ecommerce.dto;

import br.com.serratec.ecommerce.entity.Categoria;

public class CategoriaRequestDTO {
	private String nome;

	public CategoriaRequestDTO() {}

	public CategoriaRequestDTO(Categoria categoria) {
		this.nome = categoria.getNome();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}