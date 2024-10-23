package br.com.serratec.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.ecommerce.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	Optional<Cliente> findByNome(String nome);

	Optional<Cliente> findByEmail(String email);

}