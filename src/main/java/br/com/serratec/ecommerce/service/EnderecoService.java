package br.com.serratec.ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.serratec.ecommerce.dto.EnderecoResponseDTO;
import br.com.serratec.ecommerce.entity.Endereco;
import br.com.serratec.ecommerce.repository.EnderecoRepository;
import io.swagger.v3.oas.annotations.servers.Server;

import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repository;
	
	public EnderecoResponseDTO buscar(String cep) {
        var endereco = Optional.ofNullable(repository.findByCep(cep));
        if (endereco.isPresent()) {
            return new EnderecoResponseDTO(endereco.get());
		}else {
			RestTemplate rs = new RestTemplate();
			
			String uri = "https://viacep.com.br/ws/"+ cep + "json/";
			Optional<Endereco> enderecoViaCep = Optional.ofNullable(rs.getForObject(uri, Endereco.class));
			if(enderecoViaCep.get().getCep() !=null) {
				String cepSemTraco = enderecoViaCep.get().getCep().replaceAll("-", "");
				enderecoViaCep.get().setCep(cepSemTraco);
				return inserir(enderecoViaCep.get());
			}else {
				throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
			}
		}
		
	}
	private EnderecoResponseDTO inserir(Endereco endereco) {
		endereco = repository.save(endereco);
		return new EnderecoResponseDTO(endereco);
		
	}
}
