package br.com.serratec.ecommerce.dto;

import br.com.serratec.ecommerce.entity.Endereco;

public class EnderecoResponseDTO {

	private String cep;
	private String logradouro;
	private String bairro;
	private String cidade;
	private String ur;
	
	public EnderecoResponseDTO() {
		//construtor vazio
	}

	public EnderecoResponseDTO(Endereco endereco) {
		super();
		this.cep = endereco.getCep();
		this.logradouro = endereco.getLogradouro();
		this.bairro = endereco.getBairro();
		this.cidade = endereco.getCidade();
		this.ur = endereco.getUr();
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUr() {
		return ur;
	}

	public void setUr(String ur) {
		this.ur = ur;
	}
	
}
