package br.com.serratec.ecommerce.dto;

import br.com.serratec.ecommerce.entity.Cliente;
import br.com.serratec.ecommerce.entity.Endereco;

public class ClienteResponseDTO {
	private Long id;
	private String nome;
	private String email;
	
private Endereco endereco;
	
	public ClienteResponseDTO(Cliente cliente) {
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
		this.id = cliente.getId();
		this.endereco = cliente.getEndereco();
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
}

