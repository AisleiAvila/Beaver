package com.dasad.empresa.controller;

import com.dasad.empresa.api.UsuariosubcategoriaApi;
import com.dasad.empresa.model.UsuarioSubcategoriaModel;
import com.dasad.empresa.model.UsuarioSubcategoriaRequest;
import com.dasad.empresa.service.UsuarioSubCategoriaService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/usuario-subcategoria")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080", "http://localhost:8100"})
public class UsuarioSubCategoriaController implements UsuariosubcategoriaApi {

    private final UsuarioSubCategoriaService usuarioSubCategoriaService;

    public UsuarioSubCategoriaController(UsuarioSubCategoriaService usuarioSubCategoriaService) {
        this.usuarioSubCategoriaService = usuarioSubCategoriaService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<UsuarioSubcategoriaModel>> getUsuarioSubcategoria(Integer usuarioId, Boolean ativo) {
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
