package br.com.serratec.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.serratec.ecommerce.config.MailConfig;
import br.com.serratec.ecommerce.dto.FornecedorRequestDTO;
import br.com.serratec.ecommerce.dto.FornecedorResponseDTO;
import br.com.serratec.ecommerce.entity.Endereco;
import br.com.serratec.ecommerce.entity.Fornecedor;
import br.com.serratec.ecommerce.exception.EmailException;
import br.com.serratec.ecommerce.exception.ResourceNotFoundException;
import br.com.serratec.ecommerce.repository.EnderecoRepository;
import br.com.serratec.ecommerce.repository.FornecedorRepository;
import jakarta.transaction.Transactional;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository repository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private EnderecoRepository enderecoRepository;
    
    @Autowired
    private MailConfig mailConfig;
    
    public List<FornecedorResponseDTO> listar() {
        List<Fornecedor> fornecedores = repository.findAll();
        List<FornecedorResponseDTO> dtos = new ArrayList<>();
        for (Fornecedor fornecedor : fornecedores) {
            dtos.add(new FornecedorResponseDTO(fornecedor));
        }
        return dtos;
    }

    @Transactional
    public FornecedorResponseDTO inserir(FornecedorRequestDTO dto) {
        Optional<Fornecedor> f = repository.findByEmail(dto.getEmail());
        if (f.isPresent()) {
            throw new EmailException("Email existente!");
        }
        dto.setSenha(encoder.encode(dto.getSenha()));

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(dto.getNome());
        fornecedor.setEmail(dto.getEmail());
        fornecedor.setSenha(dto.getSenha());

        Endereco endereco = enderecoRepository.findByCep(dto.getCep());
        if (endereco != null) {
            fornecedor.setEndereco(endereco);
        } else {
            RestTemplate rs = new RestTemplate();
            String uri = "https://viacep.com.br/ws/" + dto.getCep() + "/json/";
            Optional<Endereco> enderecoViaCep = Optional.ofNullable(rs.getForObject(uri, Endereco.class));
            if (enderecoViaCep.get().getCep() != null) {
                String cepSemTraco = enderecoViaCep.get().getCep().replaceAll("-", "");
                enderecoViaCep.get().setCep(cepSemTraco);
                endereco = new Endereco();
                endereco.setCep(enderecoViaCep.get().getCep());
                endereco.setBairro(enderecoViaCep.get().getBairro());
                endereco.setLocalidade(enderecoViaCep.get().getLocalidade());
                endereco.setLogradouro(enderecoViaCep.get().getLogradouro());
                endereco.setUf(enderecoViaCep.get().getUf());
                enderecoRepository.save(endereco);
            } else {
                throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
            }
        }
        fornecedor.setEndereco(endereco);
        repository.save(fornecedor); 
        
        mailConfig.sendEmail(fornecedor.getEmail(), "Confirmação de cadastro",
                "Olá " + fornecedor.getNome() + ",\n\n" +
                "Seu cadastro foi realizado com sucesso!");

        return new FornecedorResponseDTO(fornecedor);
    }
    
    public FornecedorResponseDTO buscar(Long id) {
        Optional<Fornecedor> fornecedor = repository.findById(id);
        if (fornecedor.isPresent()) {
            return new FornecedorResponseDTO(fornecedor.get());
        }
        throw new ResourceNotFoundException("Fornecedor não encontrado");
    }

    @Transactional
    public FornecedorResponseDTO atualizar(Long id, FornecedorRequestDTO dto) {
        Fornecedor fornecedor = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Fornecedor não encontrado"));
        
        fornecedor.setNome(dto.getNome());
        fornecedor.setEmail(dto.getEmail());
        
        if (dto.getSenha() != null && !dto.getSenha().isEmpty()) {
            fornecedor.setSenha(encoder.encode(dto.getSenha()));
        }

        Endereco endereco = enderecoRepository.findByCep(dto.getCep());
        if (endereco != null) {
            fornecedor.setEndereco(endereco);
        } else {
            RestTemplate rs = new RestTemplate();
            String uri = "https://viacep.com.br/ws/" + dto.getCep() + "/json/";
            Optional<Endereco> enderecoViaCep = Optional.ofNullable(rs.getForObject(uri, Endereco.class));
            if (enderecoViaCep.get().getCep() != null) {
                String cepSemTraco = enderecoViaCep.get().getCep().replaceAll("-", "");
                enderecoViaCep.get().setCep(cepSemTraco);
                endereco = new Endereco();
                endereco.setCep(enderecoViaCep.get().getCep());
                endereco.setBairro(enderecoViaCep.get().getBairro());
                endereco.setLocalidade(enderecoViaCep.get().getLocalidade());
                endereco.setLogradouro(enderecoViaCep.get().getLogradouro());
                endereco.setUf(enderecoViaCep.get().getUf());
                enderecoRepository.save(endereco);
            } else {
                throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
            }
        }
        fornecedor.setEndereco(endereco);
        repository.save(fornecedor);
        
        return new FornecedorResponseDTO(fornecedor);
    }

    @Transactional
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Fornecedor não encontrado");
        }
        repository.deleteById(id);
    }
}


