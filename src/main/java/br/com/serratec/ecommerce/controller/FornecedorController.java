package br.com.serratec.ecommerce.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.ecommerce.dto.FornecedorRequestDTO;
import br.com.serratec.ecommerce.dto.FornecedorResponseDTO;
import br.com.serratec.ecommerce.entity.Fornecedor;
import br.com.serratec.ecommerce.service.FornecedorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {
	
	@Autowired
	private FornecedorService service;
	
	@Operation(summary = "Lista todos os fornecedores", description = "A resposta lista os dados dos fornecedores id, nome, cnpj e email.")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", 
			content = {@Content(schema = @Schema(implementation = Fornecedor.class), mediaType = "application/json")},
			description = "Retorna todos os fornecedores"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
	@GetMapping
	public ResponseEntity<List<FornecedorResponseDTO>> listar() {
		return ResponseEntity.ok(service.listar());
	}

	@Operation(summary = "Insere um novo fornecedor", description = "A resposta retorna o nome e email.")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "201", 
			content = {@Content(schema = @Schema(implementation = Fornecedor.class), mediaType = "application/json")},
			description = "Fornecedor cadastrado com sucesso"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })

	@PostMapping
	public ResponseEntity<Object> inserir(@RequestBody FornecedorRequestDTO dto) throws IOException {
		FornecedorResponseDTO dtoResponse = service.inserir(dto);
		return ResponseEntity.created(null).body(dtoResponse);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<FornecedorResponseDTO> buscar(@PathVariable Long id) {
		return ResponseEntity.ok(service.buscar(id));
	
	}
	
	@Operation(summary = "Atualiza um fornecedor existente", description = "Atualiza os dados do fornecedor especificado pelo ID.")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Fornecedor atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Fornecedor não encontrado"),
        @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
        @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
        @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") 
    })
    @PutMapping("{id}")
    public ResponseEntity<FornecedorResponseDTO> atualizar(@PathVariable Long id, @RequestBody FornecedorRequestDTO dto) {
        FornecedorResponseDTO dtoResponse = service.atualizar(id, dto);
        return ResponseEntity.ok(dtoResponse);
    }

    @Operation(summary = "Remove um fornecedor por ID", description = "Remove o fornecedor especificado pelo ID.")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "204", description = "Fornecedor removido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Fornecedor não encontrado"),
        @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
        @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
        @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") 
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

