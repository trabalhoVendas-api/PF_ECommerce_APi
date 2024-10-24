package br.com.serratec.ecommerce.repository;

import br.com.serratec.ecommerce.entity.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long>{

}