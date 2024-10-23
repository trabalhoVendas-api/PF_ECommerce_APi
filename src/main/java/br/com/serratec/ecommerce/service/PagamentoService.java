package br.com.serratec.ecommerce.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.dto.PagamentoRequestDTO;
import br.com.serratec.ecommerce.dto.PagamentoResponseDTO;
import br.com.serratec.ecommerce.dto.ProdutoRequestDTO;
import br.com.serratec.ecommerce.dto.ProdutoResponseDTO;
import br.com.serratec.ecommerce.entity.Pagamento;
import br.com.serratec.ecommerce.entity.Produto;
import br.com.serratec.ecommerce.enums.Status;
import br.com.serratec.ecommerce.repository.PagamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

 @Service
 public class PagamentoService {
	
	@Autowired
	private PagamentoRepository PagamentoRepository;
	
	@Transactional
    public PagamentoResponseDTO processarPagamento(PagamentoRequestDTO dto) {
        Pagamento pagamento = new Pagamento();
        pagamento.setValor(dto.getValor());
        pagamento.setMetodoPagamento(dto.getMetodoPagamento());
        pagamento.setDataPagamento(LocalDateTime.now());

        if (dto.getMetodoPagamento() != null && dto.getMetodoPagamento().equalsIgnoreCase("CARTAO")) {
            pagamento.setStatus(Status.APROVADO); 
        } else {
            pagamento.setStatus(Status.PENDENTE);
        }

        Pagamento pagamentoSalvo = PagamentoRepository.save(pagamento);

        return new PagamentoResponseDTO(pagamentoSalvo);
    }
	
	public List<PagamentoResponseDTO> listarPagamento() {
		List<Pagamento> pagamentos = PagamentoRepository.findAll();
		List<PagamentoResponseDTO> dtos = new ArrayList<>();
		for (Pagamento pagamento : pagamentos) {
			dtos.add(new PagamentoResponseDTO(pagamento));
		}
		return dtos;
	}
	
	@Transactional
	public PagamentoResponseDTO editarPagamento(PagamentoRequestDTO dto, Long id){
		
		Pagamento pagamento = PagamentoRepository.findById(id)
			.orElseThrow(()-> new EntityNotFoundException("Pagamento com o ID " + id + " não encontrado"));
			if(dto.getValor() != null) {
				pagamento.setValor(dto.getValor());
			}
			if(dto.getMetodoPagamento() != null) {
				pagamento.setMetodoPagamento(dto.getMetodoPagamento());
			}
			PagamentoRepository.save(pagamento);
			
			return new PagamentoResponseDTO(pagamento);
		
	}
	
	@Transactional
	public void deletarPagamento(Long id) {
	    PagamentoRepository.deleteById(id);
	}
}