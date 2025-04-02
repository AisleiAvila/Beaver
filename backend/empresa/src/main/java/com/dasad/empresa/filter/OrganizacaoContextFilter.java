package com.dasad.empresa.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Log4j2
public class OrganizacaoContextFilter extends OncePerRequestFilter {
    private static final ThreadLocal<OrganizacaoContext> CONTEXTO = new ThreadLocal<>();

    @Getter
    @Setter
    public static class OrganizacaoContext {
        private Long organizacaoAtual;
        private List<Long> organizacoesPermitidas;
        private boolean isAdmin;


    }

    public static OrganizacaoContext getContext() {
        return CONTEXTO.get();
    }

    public static void clear() {
        CONTEXTO.remove();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = extractToken(request);
        if (token != null) {
            try {
                DecodedJWT jwt = JWT.decode(token);

                OrganizacaoContext context = new OrganizacaoContext();
                context.setOrganizacaoAtual(jwt.getClaim("organizacaoId").asLong());
                context.setAdmin(jwt.getClaim("isAdmin").asBoolean());

                // Extrair lista de organizações permitidas
                List<Long> organizacoes = new ArrayList<>();
                jwt.getClaim("organizacoesIds").asList(Long.class).forEach(organizacoes::add);
                context.setOrganizacoesPermitidas(organizacoes);

                CONTEXTO.set(context);
            } catch (Exception e) {
                 log.error("Erro ao decodificar o token JWT: {}", e.getMessage());
            }
        }

        try {
            filterChain.doFilter(request, response);
        } finally {
            clear();
        }
    }

    private String extractToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }
}