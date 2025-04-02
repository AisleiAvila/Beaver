package com.dasad.empresa.controller;

import com.dasad.empresa.api.PerfilApi;
import com.dasad.empresa.infra.security.AuthorizationService;
import com.dasad.empresa.model.PerfilModel;
import com.dasad.empresa.service.PerfilService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({"/perfis"})
@CrossOrigin(
        origins = {"http://localhost:4200", "http://localhost:8080", "http://localhost:8100"}
)
public class PerfilController implements PerfilApi {
    private final PerfilService perfilService;
    private final AuthorizationService authorizationService;

    public PerfilController(
            PerfilService perfilService,
            AuthorizationService authorizationService
    ) {
        this.perfilService = perfilService;
        this.authorizationService = authorizationService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<PerfilModel>> findPerfil() {
        var perfis =  this.perfilService.findAll();
        return ResponseEntity.ok(perfis);
    }

}
