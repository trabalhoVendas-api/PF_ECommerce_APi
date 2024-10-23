package br.com.serratec.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.ecommerce.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
