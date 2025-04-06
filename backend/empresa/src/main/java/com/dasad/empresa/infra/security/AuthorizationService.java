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

@Service
@Log4j2
public class AuthorizationService {
    private static final long JWT_EXPIRATION = 2L * 60 * 60 * 1000; // 2 hours in milliseconds // 2 hours in milliseconds

    @Value("${api.security.token.secret}")
    private String secret;

    private final Set<String> revokedTokens = new HashSet<>();

    /**
     * Construtor padrão sem parâmetros.
     * Utilizado pela injeção de dependências do Spring.
     */
    public AuthorizationService() {
        // Construtor padrão necessário para injeção de dependências
    }

    public String generateToken(UsuarioModel usuarioModel) {
        if (usuarioModel.getPerfis().isEmpty()) {
            throw new NoSuchElementException("User has no profiles");
        }

        if (!StringUtils.hasText(this.secret)) {
            log.error("Token secret is not configured properly.");
            throw new IllegalStateException("Token secret is not configured properly.");
        }

        List<String> roles = usuarioModel.getPerfis().stream()
                .map(PerfilModel::getNome)
                .toList();
        String rolesString = String.join(",", roles);

        // Extrair IDs das organizações
        List<Integer> organizacoesIds = usuarioModel.getOrganizacoes().stream()
                .map(OrganizacaoModel::getId)
                .toList();

        if (organizacoesIds.isEmpty()) {
            throw new NoSuchElementException("User has no organizations");
        }

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

    public void revokeToken(String token) {
        log.info("Revogando token");
        revokedTokens.add(token);
    }

}