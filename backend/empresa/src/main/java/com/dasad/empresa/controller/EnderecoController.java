package com.dasad.empresa.controller;

import com.dasad.empresa.api.EnderecoApi;
import com.dasad.empresa.model.EnderecoModel;
import com.dasad.empresa.service.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({"/endereco"})
@Tag(name = "Endereço", description = "API para gerenciamento de endereços")
@SecurityRequirement(name = "bearerAuth")
public class EnderecoController implements EnderecoApi {
    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @Override
    @PutMapping({"/{id}"})
    @Operation(summary = "Atualiza um endereço existente", description = "Atualiza os dados de um endereço a partir do ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso",
                    content = @Content(schema = @Schema(implementation = EnderecoModel.class))),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado", content = @Content)
    })
    public ResponseEntity<EnderecoModel> updateendereco(@PathVariable Integer id, @RequestBody EnderecoModel enderecoDetails) {
        return this.enderecoService.findById(id).map(endereco -> {
            endereco.setLogradouro(enderecoDetails.getLogradouro());
            endereco.setCidadeId(enderecoDetails.getCidadeId());
            endereco.setCep(enderecoDetails.getCep());
            endereco.setNumero(enderecoDetails.getNumero());
            endereco.setComplemento(enderecoDetails.getComplemento());
            endereco.setBairro(enderecoDetails.getBairro());
            EnderecoModel enderecoSave = this.enderecoService.save(endereco);

            return ResponseEntity.ok(enderecoSave);
        }).orElse(ResponseEntity.notFound().build());
    }

    @Override
    @DeleteMapping({"/{id}"})
    @Operation(summary = "Remove um endereço", description = "Exclui um endereço a partir do ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Endereço removido com sucesso"),
            @ApiResponse(responseCode = "400", description = "ID inválido"),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado")
    })
    public ResponseEntity<Void> deleteendereco(@PathVariable Integer id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }

        if (this.enderecoService.findById(id).isPresent()) {
            this.enderecoService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.notFound().build();
    }

    @Override
    @PostMapping
    @Operation(summary = "Cria um novo endereço", description = "Registra um novo endereço no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço criado com sucesso",
                    content = @Content(schema = @Schema(implementation = EnderecoModel.class))),
            @ApiResponse(responseCode = "400", description = "Dados de endereço inválidos", content = @Content)
    })
    public ResponseEntity<EnderecoModel> createendereco(@RequestBody EnderecoModel enderecoModel) {
        if (enderecoModel == null) {
            return ResponseEntity.badRequest().build();
        }

        var endereco = this.enderecoService.save(enderecoModel);
        return ResponseEntity.ok(endereco);
    }

    @Override
    @GetMapping({"/{id}"})
    @Operation(summary = "Obtém detalhes de um endereço", description = "Retorna os dados de um endereço específico pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dados do endereço recuperados com sucesso",
                    content = @Content(schema = @Schema(implementation = EnderecoModel.class))),
            @ApiResponse(responseCode = "400", description = "ID inválido"),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado")
    })
    public ResponseEntity<EnderecoModel> detailendereco(@PathVariable Integer id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }

        return this.enderecoService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Override
    @GetMapping
    @Operation(summary = "Lista todos os endereços", description = "Retorna a lista completa de endereços cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista de endereços recuperada com sucesso",
            content = @Content(schema = @Schema(implementation = EnderecoModel.class)))
    public ResponseEntity<List<EnderecoModel>> findendereco() {
        var enderecos = this.enderecoService.findAll();
        return ResponseEntity.ok(enderecos);
    }
}
