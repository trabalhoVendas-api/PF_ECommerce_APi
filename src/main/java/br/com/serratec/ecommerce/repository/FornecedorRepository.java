package br.com.serratec.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.ecommerce.entity.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

	Optional<Fornecedor> findByNome(String nome);

	Optional<Fornecedor> findByEmail(String email);

	Optional<Fornecedor> findByCnpj(String cnpj);

	Optional<Fornecedor> findByTelefone(String telefone);


}
