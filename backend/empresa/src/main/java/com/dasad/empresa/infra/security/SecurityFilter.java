package com.dasad.empresa.infra.security;

import com.dasad.empresa.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
@Log4j2
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    AuthorizationService authorizationService;

    @Autowired
    @Lazy
    UsuarioRepository usuarioRepository;

    @Value("${security.excludedUrls}")
    private String excludedUrls;

    public SecurityFilter() {
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException {
        log.info("Requisição recebida no JwtAuthenticationFilter: {}", request.getRequestURI());
        try {
            log.info("Requisição recebida: {}", request.getRequestURI());
            String requestUrl = request.getRequestURI();
            if (requestUrl.endsWith("/")) {
                log.info("Removendo última barra da URL");
                requestUrl = requestUrl.substring(0, requestUrl.length() - 1);
            }

            // Verifica se a URL está na lista de exclusão
            if (this.excludedUrls != null && (
                    Arrays.asList(this.excludedUrls.split(",")).contains(requestUrl) ||
                            requestUrl.startsWith("/swagger-ui") ||
                            requestUrl.startsWith("/v3/api-docs") ||
                            requestUrl.startsWith("/favicon.ico") ||
                            requestUrl.startsWith("/usuario/perfil") ||
                            requestUrl.startsWith("/organizacao/find") ||
                            requestUrl.startsWith("/senha/validar-reset-token")
            )) {
                log.info("URL excluída: {}", requestUrl);
                filterChain.doFilter(request, response);
                return;
            }

            // Tenta recuperar e validar o token
            String token = this.recoverToken(request);
            if (token == null) {
                log.info("Token não fornecido para URL protegida: {}", requestUrl);
                response.setStatus(401);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("{\"error\": \"Token de autenticação não fornecido\"}");
                return; // Interrompe o processamento aqui
            }

            String login = this.authorizationService.validateToken(token);
            if (login != null) {
                log.info("Token válido");
                com.dasad.empresa.model.UsuarioModel user = this.usuarioRepository.findByEmail(login)
                        .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
                List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(user, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request, response);
            } else {
                log.info("Token inválido");
                response.setStatus(401);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("{\"error\": \"Token de autenticação inválido\"}");
            }
        } catch (Exception ex) {
            log.error("Erro interno do servidor", ex);
            response.setStatus(500);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"error\": \"Erro interno do servidor\"}");
        }
    }

    private String recoverToken(HttpServletRequest request) {
        log.info("Recuperando token");
        String authHeader = request.getHeader("authorization");
        return authHeader == null ? null : authHeader.replace("Bearer ", "");
    }
}