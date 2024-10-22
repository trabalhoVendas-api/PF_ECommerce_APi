package br.com.serratec.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.ecommerce.entity.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    Endereco findByCep(String cep);
}

