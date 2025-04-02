package com.dasad.empresa.service;

import com.dasad.empresa.model.LoginRequestDTO;
import com.dasad.empresa.model.PerfilModel;
import com.dasad.empresa.model.UsuarioFotoModel;
import com.dasad.empresa.model.UsuarioModel;
import com.dasad.empresa.model.UsuarioRequest;
import com.dasad.empresa.repository.EnderecoRepository;
import com.dasad.empresa.repository.UsuarioRecuperarSenhaRepository;
import com.dasad.empresa.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioRecuperarSenhaRepository tokenRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public UsuarioService() {
    }

    public Optional<List<UsuarioModel>> find(UsuarioRequest usuarioRequest, Integer organizacaoId) {
        return this.usuarioRepository.find(usuarioRequest, organizacaoId);
    }

    public  Optional<List<UsuarioFotoModel>> findFoto(Integer usuarioId, Boolean ativo) {
        if (usuarioId != null) {
            var isAtivo = ativo != null ? ativo : true;
            return this.usuarioRepository.findFoto(usuarioId, isAtivo);
        }

        return Optional.empty();

    }


    public Optional<Integer> countTotalRecords(UsuarioRequest usuarioRequest, Integer organizacaoId) {
        return this.usuarioRepository.countTotalRecords(usuarioRequest, organizacaoId);
    }

    public Optional<UsuarioModel> findById(Integer id) {
        return this.usuarioRepository.findById(id);
    }

    public Optional<com.dasad.empresa.model.UsuarioModel> findByEmail(String email) {
        return this.usuarioRepository.findByEmail(email);
    }

    public UsuarioModel create(UsuarioModel usuario, Integer organizacaoId) {
        return this.usuarioRepository.create(usuario, organizacaoId);
    }

    public UsuarioModel update(UsuarioModel usuario, Integer organizacaoId) {
        return this.usuarioRepository.update(usuario, organizacaoId);
    }

    public void deleteById(Integer id) {
        enderecoRepository.findAll()
                .stream()
                .filter(endereco -> endereco.getUsuarioId().equals(id)).
                forEach(endereco -> enderecoRepository.deleteById(endereco.getId()));
        this.usuarioRepository.deleteById(id);
    }

    public void updatePassword(Integer id, String password) {
        this.usuarioRepository.updatePassword(id, password);
    }

    public UsuarioFotoModel createFoto(UsuarioFotoModel usuarioFotoModel) {
        return this.usuarioRepository.createFoto(usuarioFotoModel);
    }

    public UsuarioFotoModel updateFoto(UsuarioFotoModel usuarioFotoModel) {
        return this.usuarioRepository.updateFoto(usuarioFotoModel);
    }

    public Optional<PerfilModel> findPerfilUsuario(LoginRequestDTO loginRequestDTO) {
        return this.usuarioRepository.findPerfilUsuario(loginRequestDTO);

    }
}
