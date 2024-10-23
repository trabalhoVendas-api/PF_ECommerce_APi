package br.com.serratec.ecommerce.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.ecommerce.dto.ClienteRequestDTO;
import br.com.serratec.ecommerce.dto.ClienteResponseDTO;
import br.com.serratec.ecommerce.entity.Cliente;
import br.com.serratec.ecommerce.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/Clientes")
public class ClienteController {
	
		@Autowired
		private ClienteService service;
		
		@Operation(summary = "Lista todos os usuários", description = "A resposta lista os dados dos usuários id, nome, cpf e email.")
		@ApiResponses(value = { 
				@ApiResponse(responseCode = "200", 
				content = {@Content(schema = @Schema(implementation = Cliente.class), mediaType = "application/json")},
				description = "Retorna todos os usuários"),
				@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
				@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
				@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
				@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
		@GetMapping
		public ResponseEntity<List<ClienteResponseDTO>> listar() {
			return ResponseEntity.ok(service.listar());
		}

		
		@Operation(summary = "Insere um novo usuário", description = "A resposta retorna o nome e email.")
		@ApiResponses(value = { 
				@ApiResponse(responseCode = "201", 
				content = {@Content(schema = @Schema(implementation = Cliente.class), mediaType = "application/json")},
				description = "Usuário cadastrado com sucesso"),
				@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
				@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
				@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
				@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })

		@PostMapping
		public ResponseEntity<Object> inserir(@RequestBody ClienteRequestDTO dto)throws IOException {
			ClienteResponseDTO dtoResponse = service.inserir(dto);
			return ResponseEntity.created(null).body(dtoResponse);
		}
		
		@GetMapping("{id}")
		public  ResponseEntity<ClienteResponseDTO> buscar(@PathVariable Long id){
			return ResponseEntity.ok(service.buscar(id));
		}
}
