package br.com.serratec.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.dto.ProdutoRequestDTO;
import br.com.serratec.ecommerce.dto.ProdutoResponseDTO;
import br.com.serratec.ecommerce.entity.Produto;
import br.com.serratec.ecommerce.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repository;

	@Transactional
	public ProdutoResponseDTO inserir (ProdutoRequestDTO dto) {
		
		Produto produto = new Produto();
		produto.setNome(dto.getNome());
		produto.setQuantidade(dto.getQuantidade());
		produto.setPreco(dto.getPreco());
		//produto.setCategoria(dto.getCategoria);

		produto = repository.save(produto);
		
		return new ProdutoResponseDTO(produto);
	}
	
	public List<ProdutoResponseDTO> listar() {
		List<Produto> produtos = repository.findAll();
		List<ProdutoResponseDTO> dtos = new ArrayList<>();
		for (Produto produto : produtos) {
			dtos.add(new ProdutoResponseDTO(produto));
		}
		return dtos;
	}
	
	@Transactional
	public ProdutoResponseDTO editarProduto(ProdutoRequestDTO dto, Long id){
		 if (repository.existsById(id)) {    
	        Produto produto = new Produto();
	        produto.setId(id);
	        produto.setNome(dto.getNome());
    		produto.setQuantidade(dto.getQuantidade());
    		produto.setPreco(dto.getPreco());
    		
    		Produto produtoAtualizado = repository.save(produto);
    		
    		 return new ProdutoResponseDTO(produtoAtualizado);
	        }else {
	        	throw new EntityNotFoundException("Produto com o ID " + id + " não encontrado");
	        }
		
	}
}
