package br.com.serratec.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.serratec.ecommerce.entity.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    boolean existsByNomeIgnoreCase(String nome);
//    List<Categoria> findByNomeContainingIgnoreCase(String nome);
//    List<Categoria> findByAtivo(boolean ativo);
//    List<Categoria> findByNomeContainingIgnoreCaseAndAtivo(String nome, boolean ativo);   
}