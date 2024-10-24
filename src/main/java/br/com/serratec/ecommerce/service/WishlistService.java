package br.com.serratec.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.entity.Cliente;
import br.com.serratec.ecommerce.entity.Wishlist;
import br.com.serratec.ecommerce.repository.WishlistRepository;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    public Wishlist getOrCreateWishlist(Cliente cliente) {
        List<Wishlist> wishlists = wishlistRepository.findByClienteId(cliente.getId());

        if (wishlists.isEmpty()) {
            Wishlist wishlist = new Wishlist();
            wishlist.setCliente(cliente);
            wishlistRepository.save(wishlist);
            return wishlist;
        }

        return wishlists.get(0);
    }

    public Wishlist getWishlistByCliente(Cliente cliente) {
        List<Wishlist> wishlists = wishlistRepository.findByClienteId(cliente.getId());
        return wishlists.isEmpty() ? null : wishlists.get(0);
    }

    public void saveWishlist(Wishlist wishlist) {
        wishlistRepository.save(wishlist);
    }
}
