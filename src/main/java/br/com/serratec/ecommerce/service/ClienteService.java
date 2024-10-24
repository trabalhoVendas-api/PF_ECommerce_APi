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
import br.com.serratec.ecommerce.dto.ClienteRequestDTO;
import br.com.serratec.ecommerce.dto.ClienteResponseDTO;
import br.com.serratec.ecommerce.entity.Cliente;
import br.com.serratec.ecommerce.entity.Endereco;
import br.com.serratec.ecommerce.exception.EmailException;
import br.com.serratec.ecommerce.exception.ResourceNotFoundException;
import br.com.serratec.ecommerce.repository.ClienteRepository;
import br.com.serratec.ecommerce.repository.EnderecoRepository;
import jakarta.transaction.Transactional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private MailConfig mailConfig;

    public List<ClienteResponseDTO> listar() {
        List<Cliente> clientes = repository.findAll();
        List<ClienteResponseDTO> dtos = new ArrayList<>();
        for (Cliente cliente : clientes) {
            dtos.add(new ClienteResponseDTO(cliente));
        }
        return dtos;
    }

    @Transactional
    public ClienteResponseDTO inserir(ClienteRequestDTO dto) {
        Optional<Cliente> c = repository.findByEmail(dto.getEmail());
        if (c.isPresent()) {
            throw new EmailException("Email existente!");
        }
        dto.setSenha(encoder.encode(dto.getSenha()));

        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        cliente.setSenha(dto.getSenha());

        Endereco endereco = enderecoRepository.findByCep(dto.getCep());
        if (endereco != null) {
            cliente.setEndereco(endereco);
        } else {
            RestTemplate rs = new RestTemplate();
            String uri = "https://viacep.com.br/ws/" + dto.getCep() + "/json/";
            Optional<Endereco> enderecoViaCep = Optional.ofNullable(rs.getForObject(uri, Endereco.class));
            if (enderecoViaCep.isPresent() && enderecoViaCep.get().getCep() != null) {
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
        cliente.setEndereco(endereco);
        repository.save(cliente);

        mailConfig.sendEmail(cliente.getEmail(), "Confirmação de cadastro",
                "Olá " + cliente.getNome() + ",\n\n" +
                "Seu cadastro foi realizado com sucesso!");

        return new ClienteResponseDTO(cliente);
    }

    public ClienteResponseDTO buscar(Long id) {
        Optional<Cliente> cliente = repository.findById(id);
        if (cliente.isPresent()) {
            return new ClienteResponseDTO(cliente.get());
        }
        throw new ResourceNotFoundException("Cliente não encontrado");
    }

    @Transactional
    public ClienteResponseDTO atualizar(Long id, ClienteRequestDTO dto) {
        Cliente cliente = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));

        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());

        if (dto.getSenha() != null && !dto.getSenha().isEmpty()) {
            cliente.setSenha(encoder.encode(dto.getSenha()));
        }

        
        Endereco endereco = enderecoRepository.findByCep(dto.getCep());
        if (endereco != null) {
            cliente.setEndereco(endereco);
        } else {
            RestTemplate rs = new RestTemplate();
            String uri = "https://viacep.com.br/ws/" + dto.getCep() + "/json/";
            Optional<Endereco> enderecoViaCep = Optional.ofNullable(rs.getForObject(uri, Endereco.class));
            if (enderecoViaCep.isPresent() && enderecoViaCep.get().getCep() != null) {
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
        cliente.setEndereco(endereco);
        repository.save(cliente);

        return new ClienteResponseDTO(cliente);
    }

    @Transactional
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Cliente não encontrado");
        }
        repository.deleteById(id);
    }
}
