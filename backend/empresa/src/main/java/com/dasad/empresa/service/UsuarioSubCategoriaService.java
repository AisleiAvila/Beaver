package com.dasad.empresa.service;

import com.dasad.empresa.model.SubCategoriaModel;
import com.dasad.empresa.model.UsuarioModel;
import com.dasad.empresa.model.UsuarioSubcategoriaModel;
import com.dasad.empresa.model.UsuarioSubcategoriaRequest;
import com.dasad.empresa.repository.SubCategoriaRepository;
import com.dasad.empresa.repository.UsuarioRepository;
import com.dasad.empresa.repository.UsuarioSubCategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioSubCategoriaService {
    private final UsuarioSubCategoriaRepository usuarioSubCategoriaRepository;
    private final UsuarioRepository usuarioRepository;
    private final SubCategoriaRepository subCategoriaRepository;

    public UsuarioSubCategoriaService(UsuarioSubCategoriaRepository usuarioSubCategoriaRepository1, UsuarioRepository usuarioRepository, SubCategoriaRepository subCategoriaRepository) {
        this.usuarioSubCategoriaRepository = usuarioSubCategoriaRepository1;
        this.usuarioRepository = usuarioRepository;
        this.subCategoriaRepository = subCategoriaRepository;
    }

    public Optional<List<UsuarioSubcategoriaModel>> find(Integer usuarioId, Boolean ativo) {
        return this.usuarioSubCategoriaRepository.find(usuarioId, ativo);
    }

    public void associarUsuarioSubcategoria(UsuarioSubcategoriaRequest usuarioSubcategoriaRequest) {
        validarRequest(usuarioSubcategoriaRequest);
        validarUsuario(usuarioSubcategoriaRequest);
        validarSubcategoria(usuarioSubcategoriaRequest);

        // Verificar se o usuário já está associado à subcategoria
        Optional<UsuarioSubcategoriaModel> existingAssociations = this.usuarioSubCategoriaRepository.findByUsuarioIdAndSubcategoriaId(usuarioSubcategoriaRequest.getUsuarioId(), usuarioSubcategoriaRequest.getSubcategoriaId(), null);
        if (existingAssociations.isEmpty()) {
            usuarioSubCategoriaRepository.save(usuarioSubcategoriaRequest.getUsuarioId(), usuarioSubcategoriaRequest.getSubcategoriaId());
            return;
        }

        if (existingAssociations.get().getDataExclusao() == null) {
            throw new IllegalArgumentException("Usuário já está associado à subcategoria.");
        }

        usuarioSubCategoriaRepository.update(usuarioSubcategoriaRequest.getUsuarioId(), usuarioSubcategoriaRequest.getSubcategoriaId(), null);
    }

    public void desassociarUsuarioSubcategoria(UsuarioSubcategoriaRequest usuarioSubcategoriaRequest) {
        validarRequest(usuarioSubcategoriaRequest);
        validarAssocicaoUsuarioSubcategoria(usuarioSubcategoriaRequest);

        usuarioSubCategoriaRepository.update(usuarioSubcategoriaRequest.getUsuarioId(), usuarioSubcategoriaRequest.getSubcategoriaId(), true);
    }

    private void validarSubcategoria(UsuarioSubcategoriaRequest usuarioSubcategoriaRequest) {
        // Verificar se a subcategoria existe
        Optional<SubCategoriaModel> subcategoria = subCategoriaRepository.findById(usuarioSubcategoriaRequest.getSubcategoriaId());
        if (subcategoria.isEmpty()) {
            throw new IllegalArgumentException("Subcategoria não encontrada.");
        }
    }

    private void validarUsuario(UsuarioSubcategoriaRequest usuarioSubcategoriaRequest) {
        // Verificar se o usuário existe
        Optional<UsuarioModel> usuario = usuarioRepository.findById(usuarioSubcategoriaRequest.getUsuarioId());
        if (usuario.isEmpty()) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }
    }

    private static void validarRequest(UsuarioSubcategoriaRequest usuarioSubcategoriaRequest) {
        // Validar os dados do request
        if (usuarioSubcategoriaRequest.getUsuarioId() == null || usuarioSubcategoriaRequest.getSubcategoriaId() == null) {
            throw new IllegalArgumentException("Usuário e subcategoria devem ser informados.");
        }
    }

    private void validarAssocicaoUsuarioSubcategoria(UsuarioSubcategoriaRequest usuarioSubcategoriaRequest) {
        // Verificar se o usuário existe
        Optional<UsuarioSubcategoriaModel> usuarioSubcategoria = usuarioSubCategoriaRepository.findByUsuarioIdAndSubcategoriaId(usuarioSubcategoriaRequest.getUsuarioId(), usuarioSubcategoriaRequest.getSubcategoriaId(), null);
        if (usuarioSubcategoria.isEmpty()) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }

        if (usuarioSubcategoria.get().getDataExclusao() != null) {
            throw new IllegalArgumentException("Associação Usuário e Subcategoria já está desativada.");
        }
    }

}
