package br.com.serratec.ecommerce.dto;

import br.com.serratec.ecommerce.entity.Endereco;
import br.com.serratec.ecommerce.entity.Fornecedor;

public class FornecedorResponseDTO {
	
	private Long id;
    private String nome;
    private String email;
    private Endereco endereco;

    public FornecedorResponseDTO(Fornecedor fornecedor) {
        this.nome = fornecedor.getNome();
        this.email = fornecedor.getEmail();
        this.id = fornecedor.getId();
        this.endereco = fornecedor.getEndereco();
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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    
}