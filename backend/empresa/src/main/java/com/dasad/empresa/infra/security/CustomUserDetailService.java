package com.dasad.empresa.infra.security;

import com.dasad.empresa.model.UsuarioModel;
import com.dasad.empresa.repository.UsuarioRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Serviço responsável por carregar os detalhes do usuário para autenticação.
 *
 * Esta classe implementa a interface UserDetailsService do Spring Security,
 * que é fundamental para o processo de autenticação. Ela busca os detalhes
 * do usuário a partir do email (username) fornecido durante o processo de login.
 *
 * O serviço consulta o banco de dados através do repositório de usuários e
 * converte o modelo de domínio (UsuarioModel) para o modelo de segurança do
 * Spring (UserDetails).
 */
@Component
@Log4j2
public class CustomUserDetailService implements UserDetailsService {

    /**
     * Repositório de usuários utilizado para buscar informações no banco de dados.
     */
    private final UsuarioRepository usuarioRepository;

    /**
     * Construtor com injeção de dependência.
     *
     * A anotação @Lazy é utilizada para evitar dependências circulares durante
     * a inicialização dos componentes.
     *
     * @param usuarioRepository repositório para acesso aos dados de usuários
     */
    public CustomUserDetailService(@Lazy UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Carrega os detalhes do usuário pelo nome de usuário (email).
     *
     * Este método é chamado pelo Spring Security durante o processo de autenticação.
     * Ele busca o usuário pelo email no repositório e, se encontrado, cria um
     * objeto UserDetails com as credenciais necessárias.
     *
     * @param username o email do usuário a ser carregado
     * @return objeto UserDetails contendo as credenciais do usuário
     * @throws UsernameNotFoundException se o usuário não for encontrado no banco de dados
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Carregando usuário por username");
        UsuarioModel usuario = this.usuarioRepository.findByEmail(username).orElseThrow(() -> {
            log.error("Usuário não encontrado");
            return new UsernameNotFoundException("Usuário não encontrado");
        });
        log.info("Usuário encontrado");
        return new User(usuario.getEmail(), usuario.getSenha(), new ArrayList<>());
    }
}