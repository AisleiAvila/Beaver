package com.dasad.empresa.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Component
@Log4j2
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Value("${app.security.public-endpoints}")
    private List<String> publicEndpoints;

    /*
     * Este filtro é responsável por interceptar as requisições e verificar se o token JWT
     * está presente e é válido. Se o token for válido, ele autentica o usuário e permite
     * que a requisição prossiga.
     */
    public JwtAuthenticationFilter() {
        // Construtor padrão
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        String endpoint = request.getRequestURI();

        log.info("Requisição recebida no JwtAuthenticationFilter: {}", endpoint);

        // Excluir URLs do Swagger e endpoints de autenticação da autenticação
        if (isPublicEndpoint(endpoint, publicEndpoints)) {
            filterChain.doFilter(request, response);
            return;
        }

        if (authorizationHeader == null) {
            log.error("Cabeçalho de autorização ausente ou malformado");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Cabeçalho de autorização ausente ou malformado");
            return;
        }

        String token = authorizationHeader.replace("Bearer ", "");
        DecodedJWT decodedJWT = this.validateToken(token);

        if (decodedJWT == null) {
            log.error("Token inválido ou expirado");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido ou expirado");
            return;
        }

        String userEmail = decodedJWT.getSubject();
        String role = decodedJWT.getClaim("role").asString();

        if (userEmail == null || role == null) {
            log.error("Token inválido ou expirado");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido ou expirado");
            return;
        }

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userEmail, null, List.of(new SimpleGrantedAuthority("ROLE_" + role)));
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("Autenticação bem-sucedida para o usuário: {}", userEmail);

        log.info("Antes de filterChain.doFilter no JwtAuthenticationFilter");
        filterChain.doFilter(request, response);
        log.info("Depois de filterChain.doFilter no JwtAuthenticationFilter");
    }

    private DecodedJWT validateToken(String token) {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            if (decodedJWT.getExpiresAt().before(new Date())) {
                log.error("Token expirado");
                return null;
            } else {
                return decodedJWT;
            }
        } catch (JWTDecodeException e) {
            log.error("Erro ao decodificar o token: {}", e.getMessage());
            return null;
        }
    }

    private boolean isPublicEndpoint(String endpoint, List<String> publicEndpoints) {
        if (publicEndpoints == null || publicEndpoints.isEmpty()) {
            return false;
        }

        return publicEndpoints.stream().anyMatch(pattern -> {
            if (pattern.endsWith("/**")) {
                String basePath = pattern.substring(0, pattern.length() - 3);
                return endpoint.startsWith(basePath);
            } else {
                return endpoint.equals(pattern);
            }
        });
    }
}