package br.com.serratec.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.dto.CategoriaRequestDTO;
import br.com.serratec.ecommerce.dto.CategoriaResponseDTO;
import br.com.serratec.ecommerce.dto.ProdutoResponseDTO;
import br.com.serratec.ecommerce.entity.Categoria;
import br.com.serratec.ecommerce.entity.Produto;
import br.com.serratec.ecommerce.repository.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;

	@Transactional
	public CategoriaResponseDTO inserir(CategoriaRequestDTO dto) {
        
		if (repository.existsByNomeIgnoreCase(dto.getNome())) {
            throw new IllegalArgumentException("Já existe uma categoria com este nome");
        }
		
		Categoria categoria = new Categoria();
		categoria.setNome(dto.getNome());

		categoria = repository.save(categoria);
		
		return new CategoriaResponseDTO(categoria);
	}
	
	public List<CategoriaResponseDTO> listar() {
		List<Categoria> categorias = repository.findAll();
		List<CategoriaResponseDTO> dtos = new ArrayList<>();
		for (Categoria categoria : categorias) {
			dtos.add(new CategoriaResponseDTO(categoria));
		}
		return dtos;
	}
	
	public CategoriaResponseDTO listarPorId(Long id) {
	    List<Categoria> categorias = repository.findAll(); 
	    for (Categoria categoria : categorias) {
	        if (categoria.getId().equals(id)) { 
	            return new CategoriaResponseDTO(categoria);
	        }
	    }
	    throw new EntityNotFoundException("Categoria não encontrada: " + id);
	}

	
	@Transactional
	public CategoriaResponseDTO editar(CategoriaRequestDTO dto, Long id){
		 if (repository.existsById(id)) {    
	        Categoria categoria = new Categoria();
	        categoria.setId(id);
	        categoria.setNome(dto.getNome());
    		
    		Categoria categoriaAtualizado = repository.save(categoria);
    		
    		 return new CategoriaResponseDTO(categoriaAtualizado);
	        }else {
	        	throw new EntityNotFoundException("Categoria com o ID " + id + " não encontrado");
	        }
	 
	}
	
	public List<ProdutoResponseDTO> listarProdutosPorCategoria(Long categoriaId) {
	    Optional<Categoria> optionalCategoria = repository.findById(categoriaId);
	    
	    if (!optionalCategoria.isPresent()) {
	        throw new EntityNotFoundException("Categoria não encontrada: " + categoriaId);
	    }
	    
	    Categoria categoria = optionalCategoria.get(); 
	    List<ProdutoResponseDTO> produtosResponse = new ArrayList<>();
	    
	    for (Produto produto : categoria.getProdutos()) {
	        produtosResponse.add(new ProdutoResponseDTO(produto)); 
	    }
	    
	    return produtosResponse; 
	}

	
}