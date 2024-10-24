package br.com.serratec.ecommerce.dto;

import br.com.serratec.ecommerce.entity.Cliente;
import br.com.serratec.ecommerce.entity.Endereco;

public class ClienteRequestDTO {
	private Long id;
	private String nome;
	private String email;
	private String senha;
	private String cep;
	private String cpf;
	private String telefone;
	private String numero;
	private String complemento;
	private Endereco endereco;
	
	public ClienteRequestDTO(Cliente cliente) {
		super();
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
		this.senha = cliente.getSenha();
		this.numero = cliente.getNumero();
		this.complemento = cliente.getComplemento();
		
		this.telefone = cliente.getTelefone();
		this.cep= endereco.getCep();
		
	}

	public ClienteRequestDTO() {
		// TODO Auto-generated constructor stub
	}

	
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
