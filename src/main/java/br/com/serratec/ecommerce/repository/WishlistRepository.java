package br.com.serratec.ecommerce.repository;

import br.com.serratec.ecommerce.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    List<Wishlist> findByClienteId(Long clienteId);
}
