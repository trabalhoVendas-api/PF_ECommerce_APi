package br.com.serratec.ecommerce.repository;


import br.com.serratec.ecommerce.entity.RelatorioVendas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelatorioVendasRepository extends JpaRepository<RelatorioVendas, Long> {
}