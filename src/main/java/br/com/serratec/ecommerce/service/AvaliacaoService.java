package br.com.serratec.ecommerce.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.dto.AvaliacaoDTO;
import br.com.serratec.ecommerce.entity.Avaliacao;
import br.com.serratec.ecommerce.entity.Cliente;
import br.com.serratec.ecommerce.entity.Produto;
import br.com.serratec.ecommerce.exception.ResourceNotFoundException;
import br.com.serratec.ecommerce.repository.AvaliacaoRepository;
import br.com.serratec.ecommerce.repository.ClienteRepository;
import br.com.serratec.ecommerce.repository.ProdutoRepository;
import jakarta.transaction.Transactional;

@Service
public class AvaliacaoService {
	@Autowired
	private AvaliacaoRepository avaliacaoRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private ClienteRepository clienteRepository;

	@Transactional
	public AvaliacaoDTO criarAvaliacao(AvaliacaoDTO avaliacaoDTO) {
		Produto produto = produtoRepository.findById(avaliacaoDTO.getProdutoId())
				.orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

		Cliente cliente = clienteRepository.findById(avaliacaoDTO.getClienteId())
				.orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));

		Optional<Avaliacao> avaliacaoExistente = avaliacaoRepository
				.findByProdutoIdAndClienteId(avaliacaoDTO.getProdutoId(), avaliacaoDTO.getClienteId());

		if (avaliacaoExistente.isPresent()) {
			throw new IllegalStateException("Cliente já avaliou este produto");
		}

		Avaliacao avaliacao = new Avaliacao();
		avaliacao.setProduto(produto);
		avaliacao.setCliente(cliente);
		avaliacao.setNota(avaliacaoDTO.getNota());
		avaliacao.setComentario(avaliacaoDTO.getComentario());
		avaliacao.setDataAvaliacao(LocalDateTime.now());

		Avaliacao avaliacaoSalva = avaliacaoRepository.save(avaliacao);
		return converterParaDTO(avaliacaoSalva);
	}

	public List<AvaliacaoDTO> listarAvaliacoesPorProduto(Long produtoId) {
		List<Avaliacao> avaliacoes = avaliacaoRepository.findByProdutoId(produtoId);
		List<AvaliacaoDTO> avaliacaoDTOs = new ArrayList<>();

		for (Avaliacao avaliacao : avaliacoes) {
			AvaliacaoDTO dto = converterParaDTO(avaliacao);
			avaliacaoDTOs.add(dto);
		}

		return avaliacaoDTOs;
	}

	public AvaliacaoDTO listarAvaliacaoPorId(Long id) {
		Avaliacao avaliacao = avaliacaoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Avaliação não encontrada"));
		return converterParaDTO(avaliacao);
	}

	public List<AvaliacaoDTO> listarAvaliacoes() {
		List<Avaliacao> avaliacoes = avaliacaoRepository.findAll();

		if (avaliacoes.isEmpty()) {
			throw new ResourceNotFoundException("Nenhuma avaliação encontrada");
		}

		List<AvaliacaoDTO> avaliacoesDTO = new ArrayList<>();
		for (Avaliacao avaliacao : avaliacoes) {
			avaliacoesDTO.add(converterParaDTO(avaliacao));
		}

		return avaliacoesDTO;
	}

	private AvaliacaoDTO converterParaDTO(Avaliacao avaliacao) {
		AvaliacaoDTO dto = new AvaliacaoDTO();
		dto.setId(avaliacao.getId());

		if (avaliacao.getProduto() != null) {
			dto.setProdutoId(avaliacao.getProduto().getId());
		} else {
			dto.setProdutoId(null);
		}

		if (avaliacao.getCliente() != null) {
			dto.setClienteId(avaliacao.getCliente().getId());
		} else {
			dto.setClienteId(null);
		}

		dto.setNota(avaliacao.getNota());
		dto.setComentario(avaliacao.getComentario());
		dto.setDataAvaliacao(avaliacao.getDataAvaliacao());
		return dto;
	}

	@Transactional
	public AvaliacaoDTO atualizarAvaliacao(Long id, AvaliacaoDTO avaliacaoDTO) {
		Avaliacao avaliacao = avaliacaoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Avaliação não encontrada"));

		if (!avaliacao.getCliente().getId().equals(avaliacaoDTO.getClienteId())) {
			throw new IllegalStateException("Não é permitido atualizar avaliação de outro cliente");
		}

		if (!avaliacao.getProduto().getId().equals(avaliacaoDTO.getProdutoId())) {
			throw new IllegalStateException("Não é permitido alterar o produto da avaliação");
		}

		avaliacao.setNota(avaliacaoDTO.getNota());
		avaliacao.setComentario(avaliacaoDTO.getComentario());

		avaliacao.setDataAvaliacao(LocalDateTime.now());

		Avaliacao avaliacaoAtualizada = avaliacaoRepository.save(avaliacao);
		return converterParaDTO(avaliacaoAtualizada);
	}

	@Transactional
	public void deletarAvaliacao(Long id, Long clienteId) {
		Avaliacao avaliacao = avaliacaoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Avaliação não encontrada"));

		if (!avaliacao.getCliente().getId().equals(clienteId)) {
			throw new IllegalStateException("Não é permitido deletar avaliação de outro cliente");
		}

		avaliacaoRepository.delete(avaliacao);
	}

}