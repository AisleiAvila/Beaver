package com.dasad.empresa.controller;

import com.dasad.empresa.api.UsuarioApi;
import com.dasad.empresa.model.LoginRequestDTO;
import com.dasad.empresa.model.PerfilModel;
import com.dasad.empresa.model.RegisterRequestDTO;
import com.dasad.empresa.model.UsuarioFotoModel;
import com.dasad.empresa.model.UsuarioModel;
import com.dasad.empresa.model.UsuarioRequest;
import com.dasad.empresa.model.UsuarioResponseDTO;
import com.dasad.empresa.service.UsuarioService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController implements UsuarioApi {
    @Autowired
    private UsuarioService usuarioService;

    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<UsuarioModel> createUsuario(
            @RequestParam(value = "organizaoId") Integer organizacaoId,
            @RequestBody RegisterRequestDTO registerRequestDTO) {

        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setId(registerRequestDTO.getId());
        usuarioModel.setNome(registerRequestDTO.getNome());
        usuarioModel.setEmail(registerRequestDTO.getEmail());
        usuarioModel.setSenha(registerRequestDTO.getSenha());
        usuarioModel.setDataNascimento(registerRequestDTO.getDataNascimento());
        usuarioModel.setEnderecos(registerRequestDTO.getEnderecos());
        usuarioModel.setPerfis(registerRequestDTO.getPerfis());

        return ResponseEntity.ok(this.usuarioService.create(usuarioModel, organizacaoId));
    }

    @Override
    @PostMapping("/foto")
    @PreAuthorize("hasAnyRole('Administrador', 'Moderador', 'Usuário')")
    public ResponseEntity<UsuarioFotoModel> createFotoUsuario(UsuarioFotoModel usuarioFotoModel) {
        return ResponseEntity.ok(this.usuarioService.createFoto(usuarioFotoModel));
    }

    @Override
    @GetMapping("/detail/{id}")
    @PreAuthorize("hasAnyRole('Administrador', 'Moderador', 'Usuário')")
    public ResponseEntity<UsuarioResponseDTO> detailUsuario(@PathVariable Integer id) {
        Optional<UsuarioModel> usuario = this.usuarioService.findById(id);
        return usuario.map(u -> {
            UsuarioResponseDTO responseDTO = new UsuarioResponseDTO();
            responseDTO.setUsuarios(Collections.singletonList(usuario.orElse(null)));
            return ResponseEntity.ok(responseDTO);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping("/perfil")
    public ResponseEntity<PerfilModel> findPerfilUsuario(
            @Parameter(description = "Credenciais do usuário",
                    required = true,
                    schema = @Schema(implementation = LoginRequestDTO.class))
            @RequestBody LoginRequestDTO loginRequestDTO
    ) {
        return this.usuarioService.findPerfilUsuario(loginRequestDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<Void> deleteUsuario(
            @RequestParam(value = "organizaoId") Integer organizacaoId,
            @PathVariable Integer id) {
        if (this.usuarioService.findById(id).isPresent()) {
            this.usuarioService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @DeleteMapping("/foto/{usuarioId}")
    @PreAuthorize("hasAnyRole('Administrador', 'Moderador', 'Usuário')")
    public ResponseEntity<Void> deleteFotoUsuario(@PathVariable Integer usuarioId) {
        return null;
    }

    @Override
    @PostMapping("/find")
    @PreAuthorize("hasAnyRole('Administrador', 'Moderador', 'Usuário')")
    public ResponseEntity<UsuarioResponseDTO> findUsuario(
            @RequestParam(value = "organizaoId") Integer organizacaoId,
            @RequestBody UsuarioRequest usuarioRequest) {
        try {
            Optional<List<UsuarioModel>> usuarios = this.usuarioService.find(usuarioRequest, organizacaoId);
            var totalRecords = 0;
            if (usuarioRequest.getOffset() == null || usuarioRequest.getOffset() == 0) {
                var retorno = this.usuarioService.countTotalRecords(usuarioRequest, organizacaoId);
                totalRecords = retorno.orElse(0);
            }
            UsuarioResponseDTO responseDTO = new UsuarioResponseDTO();
            responseDTO.setUsuarios(usuarios.orElse(Collections.emptyList()));
            responseDTO.setTotalRecords(totalRecords);
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            throw new RuntimeException("Erro interno do servidor", e);
        }
    }

    @Override
    @PatchMapping
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<UsuarioModel> updateUsuario(
            @RequestParam(value = "organizaoId") Integer organizacaoId,
            @RequestBody UsuarioModel usuarioModel) {
        var usuario = this.usuarioService.update(usuarioModel, organizacaoId);
        return ResponseEntity.ok(usuario);
    }

    @Override
    @GetMapping("/foto")
    @PreAuthorize("hasAnyRole('Administrador', 'Moderador', 'Usuário')")
    public ResponseEntity<List<UsuarioFotoModel>> getFotoUsuario(Integer usuarioId, Boolean ativo) {
        try {
            Optional<List<UsuarioFotoModel>> fotos = this.usuarioService.findFoto(usuarioId, ativo);
            return ResponseEntity.ok(fotos.orElse(Collections.emptyList()));
        } catch (Exception e) {
            throw new RuntimeException("Erro interno do servidor", e);
        }

    }

    @Override
    @PatchMapping("/foto")
    @PreAuthorize("hasAnyRole('Administrador', 'Moderador', 'Usuário')")
    public ResponseEntity<UsuarioFotoModel> updateFotoUsuario(UsuarioFotoModel usuarioFotoModel) {
        var usuarioFoto = this.usuarioService.updateFoto(usuarioFotoModel);
        return ResponseEntity.ok(usuarioFoto);
    }

}