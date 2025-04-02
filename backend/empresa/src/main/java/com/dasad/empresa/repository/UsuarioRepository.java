package com.dasad.empresa.repository;

import com.dasad.empresa.model.LoginRequestDTO;
import com.dasad.empresa.model.UsuarioFotoModel;
import com.dasad.empresa.model.UsuarioModel;
import com.dasad.empresa.model.UsuarioRequest;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository {
    Optional<List<UsuarioModel>> find(UsuarioRequest usuariorequest, Integer organizacaoId);
    Optional<Integer> countTotalRecords(UsuarioRequest usuariorequest, Integer organizacaoId);
    Optional<UsuarioModel> findById(Integer id);
    Optional<UsuarioModel> findByEmail(String email);
    Optional<UsuarioModel> findByEmailAndOrganizacaoId(String email, Integer organizacaoId);
    UsuarioModel create(UsuarioModel usuario, Integer organizacaoId);
    UsuarioModel update(UsuarioModel usuario, Integer organizacaoId);
    void deleteById(Integer id);
    void updatePassword(Integer id, String password);
    Optional<List<UsuarioFotoModel>> findFoto(Integer usuarioId, boolean isAtivo);
    UsuarioFotoModel createFoto(UsuarioFotoModel usuarioFotoModel);
    UsuarioFotoModel updateFoto(UsuarioFotoModel usuarioFotoModel);
    Optional findPerfilUsuario(LoginRequestDTO loginRequestDTO);
}
