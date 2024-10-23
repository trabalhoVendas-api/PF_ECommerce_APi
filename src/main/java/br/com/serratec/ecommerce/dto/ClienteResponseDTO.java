package br.com.serratec.ecommerce.dto;

import br.com.serratec.ecommerce.entity.Cliente;

public class ClienteResponseDTO {
	private String nome;
	private String email;
	private String cep;

	public ClienteResponseDTO(Cliente cliente) {
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
		this.cep =cliente.getEndereco().getCep();
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
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

	
}

