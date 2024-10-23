package br.com.serratec.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.serratec.ecommerce.entity.Avaliacao;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

	List<Avaliacao> findByProdutoId(Long produtoId);

	Optional<Avaliacao> findByProdutoIdAndClienteId(Long produtoId, Long clienteId);

}