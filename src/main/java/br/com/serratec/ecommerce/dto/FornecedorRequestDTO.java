package br.com.serratec.ecommerce.dto;

import br.com.serratec.ecommerce.entity.Endereco;
import br.com.serratec.ecommerce.entity.Fornecedor;

public class FornecedorRequestDTO {
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String cep;
    private String cnpj;
    private String telefone;
    private Endereco endereco;
    
    public FornecedorRequestDTO(Fornecedor fornecedor) {
        super();
        this.id = fornecedor.getId();
        this.nome = fornecedor.getNome();
        this.email = fornecedor.getEmail();
        this.senha = fornecedor.getSenha();
        this.telefone = fornecedor.getTelefone();
        this.cep = endereco.getCep(); 
        this.cnpj = fornecedor.getCnpj(); 
    }

    public FornecedorRequestDTO() {
        
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

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
}