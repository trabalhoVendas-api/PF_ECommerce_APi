package br.com.serratec.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.ecommerce.entity.Cliente;
import br.com.serratec.ecommerce.entity.Produto;
import br.com.serratec.ecommerce.entity.Wishlist;
import br.com.serratec.ecommerce.service.ClienteService;
import br.com.serratec.ecommerce.service.ProdutoService;
import br.com.serratec.ecommerce.service.WishlistService;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private WishlistService wishlistService;

    @PostMapping("/{clienteId}/add/{produtoId}")
    public ResponseEntity<String> addProdutoToWishlist(@PathVariable Long clienteId, @PathVariable Long produtoId) {
        try {
            Cliente cliente = clienteService.findClienteById(clienteId);
            Produto produto = produtoService.findProdutoById(produtoId);

            if (cliente == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
            }

            if (produto == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
            }

            Wishlist wishlist = wishlistService.getOrCreateWishlist(cliente);

            wishlist.addProduto(produto);

            wishlistService.saveWishlist(wishlist);

            return ResponseEntity.status(HttpStatus.OK).body("Produto adicionado à wishlist com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao adicionar produto à wishlist");
        }
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<?> getWishlist(@PathVariable Long clienteId) {
        Cliente cliente = clienteService.findClienteById(clienteId);

        if (cliente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }

        Wishlist wishlist = wishlistService.getWishlistByCliente(cliente);

        if (wishlist == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Wishlist não encontrada");
        }

        return ResponseEntity.ok(wishlist.getProdutos());
    }

    @DeleteMapping("/{clienteId}/remove/{produtoId}")
    public ResponseEntity<String> removeProdutoFromWishlist(@PathVariable Long clienteId, @PathVariable Long produtoId) {
        try {
            Cliente cliente = clienteService.findClienteById(clienteId);

            if (cliente == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
            }

            Produto produto = produtoService.findProdutoById(produtoId);

            if (produto == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
            }

            Wishlist wishlist = wishlistService.getWishlistByCliente(cliente);

            if (wishlist == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Wishlist não encontrada");
            }

            wishlist.removeProduto(produto);

            wishlistService.saveWishlist(wishlist);

            return ResponseEntity.ok("Produto removido da wishlist com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao remover produto da wishlist");
        }
    }
}
