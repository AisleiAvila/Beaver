package com.dasad.empresa.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dasad.empresa.model.OrganizacaoModel;
import com.dasad.empresa.model.PerfilModel;
import com.dasad.empresa.model.UsuarioModel;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Serviço responsável pela gestão de autorizações e tokens JWT.
 *
 * Esta classe gerencia o ciclo de vida completo dos tokens:
 * - Geração de tokens JWT para usuários autenticados
 * - Validação de tokens recebidos
 * - Revogação de tokens quando necessário
 *
 * O serviço utiliza o algoritmo HMAC512 para assinatura e verificação
 * dos tokens, garantindo a integridade e autenticidade dos mesmos.
 */
@Service
@Log4j2
public class AuthorizationService {
    /**
     * Tempo de expiração do token JWT (2 horas em milissegundos).
     */
    private static final long JWT_EXPIRATION = 2L * 60 * 60 * 1000;

    /**
     * Chave secreta usada para assinar os tokens JWT.
     * Obtida a partir de configurações externas.
     */
    @Value("${api.security.token.secret}")
    private String secret;

    /**
     * Conjunto de tokens revogados.
     * Tokens neste conjunto serão considerados inválidos mesmo antes de sua expiração.
     */
    private final Set<String> revokedTokens = new HashSet<>();

    /**
     * Construtor padrão sem parâmetros.
     * Utilizado pela injeção de dependências do Spring.
     */
    public AuthorizationService() {
        // Construtor padrão necessário para injeção de dependências
    }

    /**
     * Gera um token JWT para o usuário especificado.
     *
     * O token contém informações do usuário como email, ID, nome, perfil,
     * organização e permissões (roles).
     *
     * @param usuarioModel O modelo de usuário para o qual o token será gerado
     * @return O token JWT gerado como string
     * @throws NoSuchElementException Se o usuário não possui perfis ou organizações
     * @throws IllegalStateException Se a chave secreta não estiver configurada
     */
    public String generateToken(UsuarioModel usuarioModel) {
        hasPerfil(usuarioModel);
        hasTokenSecret();

        var rolesString = getRolesString(usuarioModel);
        var organizacoesIds = getOrganizacoesIds(usuarioModel);

        return JWT.create()
                .withSubject(usuarioModel.getEmail())
                .withClaim("userId", usuarioModel.getId())
                .withClaim("nome", usuarioModel.getNome())
                .withClaim("perfil", usuarioModel.getPerfis().getFirst().getNome())
                .withClaim("organizacaoId", organizacoesIds.getFirst())
                .withIssuer("login-auth-api")
                .withClaim("role", rolesString)
                .withExpiresAt(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
                .sign(Algorithm.HMAC512(this.secret.getBytes()));
    }

    /**
     * Obtém a lista de IDs das organizações associadas ao usuário.
     *
     * @param usuarioModel O modelo de usuário
     * @return Lista de IDs das organizações
     * @throws NoSuchElementException Se o usuário não possui organizações
     */
    private static List<Integer> getOrganizacoesIds(UsuarioModel usuarioModel) {
        // Extrair IDs das organizações
        List<Integer> organizacoesIds = usuarioModel.getOrganizacoes().stream()
                .map(OrganizacaoModel::getId)
                .toList();

        if (organizacoesIds.isEmpty()) {
            throw new NoSuchElementException("User has no organizations");
        }
        return organizacoesIds;
    }

    /**
     * Obtém uma string contendo as roles (perfis) do usuário, separadas por vírgula.
     *
     * @param usuarioModel O modelo de usuário
     * @return String com as roles separadas por vírgula
     */
    private static String getRolesString(UsuarioModel usuarioModel) {
        List<String> roles = usuarioModel.getPerfis().stream()
                .map(PerfilModel::getNome)
                .toList();
        return String.join(",", roles);
    }

    /**
     * Verifica se a chave secreta foi configurada adequadamente.
     *
     * @throws IllegalStateException Se a chave secreta não estiver configurada
     */
    private void hasTokenSecret() {
        if (!StringUtils.hasText(this.secret)) {
            log.error("Token secret is not configured properly.");
            throw new IllegalStateException("Token secret is not configured properly.");
        }
    }

    /**
     * Verifica se o usuário possui ao menos um perfil associado.
     *
     * @param usuarioModel O modelo de usuário
     * @throws NoSuchElementException Se o usuário não possui perfis
     */
    private static void hasPerfil(UsuarioModel usuarioModel) {
        if (usuarioModel.getPerfis().isEmpty()) {
            throw new NoSuchElementException("User has no profiles");
        }
    }

    /**
     * Valida um token JWT e configura a autenticação no contexto de segurança.
     *
     * Este método verifica a integridade e validade do token, extraindo informações
     * do usuário e suas permissões para configurar o SecurityContext.
     *
     * @param token O token JWT a ser validado
     * @return O email do usuário se o token for válido, ou null caso contrário
     */
    public String validateToken(String token) {
        if (!StringUtils.hasText(token)) {
            log.error("Token inexistente ");
            return null;
        }

        if (revokedTokens.contains(token)) {
            log.error("Token has been revoked");
            return null;
        }

        try {
            Algorithm algorithm = Algorithm.HMAC512(this.secret.getBytes());
            DecodedJWT jwt = JWT.require(algorithm)
                    .withIssuer("login-auth-api")
                    .build()
                    .verify(token);
            Instant expirationTime = jwt.getExpiresAt().toInstant();
            long minutesUntilExpiration = Duration.between(Instant.now(), expirationTime).toMinutes();
            log.info("Tempo restante até a expiração do token: {} minutos", minutesUntilExpiration);

            String userEmail = jwt.getSubject();
            String rolesString = jwt.getClaim("role").asString();
            if (StringUtils.hasText(userEmail) && StringUtils.hasText(rolesString)) {
                List<SimpleGrantedAuthority> authorities = Arrays.stream(rolesString.split(","))
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                        .toList();
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userEmail, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                log.info("Autenticação bem-sucedida para o usuário: {} com roles: {}", userEmail, rolesString);
                return userEmail;
            } else {
                log.error("Token inválido ou expirado");
                return null;
            }
        } catch (JWTVerificationException e) {
            log.error("Token verification failed", e);
            return null;
        }
    }

    /**
     * Revoga um token, impedindo seu uso futuro mesmo antes da expiração.
     *
     * @param token O token JWT a ser revogado
     */
    public void revokeToken(String token) {
        log.info("Revogando token");
        revokedTokens.add(token);
    }
}