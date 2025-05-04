package com.dasad.empresa.controller;

import com.dasad.empresa.api.UsuariosubcategoriaApi;
import com.dasad.empresa.model.UsuarioSubcategoriaModel;
import com.dasad.empresa.model.UsuarioSubcategoriaRequest;
import com.dasad.empresa.service.UsuarioSubCategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/usuario-subcategoria")
public class UsuarioSubCategoriaController implements UsuariosubcategoriaApi {

    private final UsuarioSubCategoriaService usuarioSubCategoriaService;

    public UsuarioSubCategoriaController(UsuarioSubCategoriaService usuarioSubCategoriaService) {
        this.usuarioSubCategoriaService = usuarioSubCategoriaService;
    }

    @Override
    @Operation(
            summary = "Lista subcategorias associadas ao usuário",
            description = "Retorna as subcategorias vinculadas a um usuário, podendo filtrar por ativo/inativo.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de subcategorias do usuário",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = UsuarioSubcategoriaModel.class)
                            )
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<UsuarioSubcategoriaModel>> getUsuarioSubcategoria(
            @Parameter(description = "ID do usuário", required = true)
            @RequestParam(value = "usuario_id") Integer usuarioId,
            @Parameter(description = "Status de associação ativa", required = true)
            @RequestParam(value = "ativo") Boolean ativo) {
        var usuarioSubcategorias = this.usuarioSubCategoriaService.find(usuarioId, ativo);
        log.info(usuarioSubcategorias);
        return ResponseEntity.ok(usuarioSubcategorias.orElse(List.of()));
    }

    @Override
    @PostMapping
    public ResponseEntity<List<UsuarioSubcategoriaModel>> associarUsuarioSubcategoria(UsuarioSubcategoriaRequest usuarioSubcategoriaRequest) {
        // Chama o método do serviço (que não retorna nada)
        this.usuarioSubCategoriaService.associarUsuarioSubcategoria(usuarioSubcategoriaRequest);

        // Após associar, busca a lista atualizada para o usuário
        var usuarioSubcategorias = this.usuarioSubCategoriaService.find(usuarioSubcategoriaRequest.getUsuarioId(), true);
        return ResponseEntity.ok(usuarioSubcategorias.orElse(List.of()));
    }

    @Override
    @PutMapping
    public ResponseEntity<List<UsuarioSubcategoriaModel>> desassociarUsuarioSubcategoria(UsuarioSubcategoriaRequest usuarioSubcategoriaRequest) {
        this.usuarioSubCategoriaService.desassociarUsuarioSubcategoria(usuarioSubcategoriaRequest);

        // Após desassociar, busca a lista atualizada para o usuário
        var usuarioSubcategorias = this.usuarioSubCategoriaService.find(usuarioSubcategoriaRequest.getUsuarioId(), true);
        return ResponseEntity.ok(usuarioSubcategorias.orElse(List.of()));
    }
}
