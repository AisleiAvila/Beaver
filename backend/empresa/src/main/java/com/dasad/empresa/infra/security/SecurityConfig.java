package com.dasad.empresa.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * Configuração de segurança da aplicação.
 *
 * Esta classe configura todos os aspectos de segurança da aplicação, incluindo:
 * - Autenticação e autorização
 * - Configuração de CORS (Cross-Origin Resource Sharing)
 * - Gestão de sessão (stateless)
 * - Filtros de segurança para processamento de JWT
 * - Tratamento de exceções de acesso negado
 * - Endpoints públicos e protegidos
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    private final CustomUserDetailService customUserDetailService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final SecurityFilter securityFilter;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final String[] excludedUrls;
    private final String[] corsOrigins;

    /**
     * Construtor para injeção de dependências dos componentes de segurança.
     *
     * @param customUserDetailService Serviço para carregar detalhes do usuário
     * @param jwtAuthenticationFilter Filtro para autenticação com JWT
     * @param securityFilter Filtro para validação de segurança
     * @param customAccessDeniedHandler Manipulador de exceção para acesso negado
     * @param excludedUrls URLs que não requerem autenticação
     * @param corsOrigins Origens permitidas para CORS
     */
    @Autowired
    public SecurityConfig(
            @Lazy CustomUserDetailService customUserDetailService,
            JwtAuthenticationFilter jwtAuthenticationFilter,
            SecurityFilter securityFilter,
            CustomAccessDeniedHandler customAccessDeniedHandler,
            @Value("${security.excludedUrls}") String[] excludedUrls,
            @Value("${cors.allowed-origins:http://localhost:4200}") String[] corsOrigins) {

        this.customUserDetailService = customUserDetailService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.securityFilter = securityFilter;
        this.customAccessDeniedHandler = customAccessDeniedHandler;
        this.excludedUrls = excludedUrls;
        this.corsOrigins = corsOrigins;
    }

    /**
     * Configura a cadeia de filtros de segurança HTTP.
     *
     * @param http Objeto HttpSecurity para configuração
     * @return SecurityFilterChain configurada
     * @throws Exception Caso ocorra erro na configuração
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(this::configureCsrf)
                .cors(this::configureCors)
                .sessionManagement(this::configureSessionManagement)
                .authorizeHttpRequests(this::configureAuthorization)
                .exceptionHandling(this::configureExceptionHandling)
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * Desabilita a proteção CSRF por usar autenticação baseada em token.
     */
    private void configureCsrf(CsrfConfigurer<HttpSecurity> csrf) {
        csrf.disable();
    }

    /**
     * Configura as políticas de CORS.
     */
    private void configureCors(CorsConfigurer<HttpSecurity> cors) {
        cors.configurationSource(corsConfigurationSource());
    }

    /**
     * Configura a aplicação para ser stateless (não manter sessão).
     */
    private void configureSessionManagement(SessionManagementConfigurer<HttpSecurity> session) {
        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    /**
     * Configura quais endpoints são públicos e quais requerem autenticação.
     */
    private void configureAuthorization(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authorize) {
        for (String url : excludedUrls) {
            authorize.requestMatchers(url).permitAll();
        }
        authorize.anyRequest().authenticated();
    }

    /**
     * Configura o tratamento de exceções de segurança.
     */
    private void configureExceptionHandling(ExceptionHandlingConfigurer<HttpSecurity> exceptionHandling) {
        exceptionHandling.accessDeniedHandler(customAccessDeniedHandler);
    }

    /**
     * Define o codificador de senha usado para autenticar usuários.
     *
     * @return Instância de BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configura a fonte de configuração CORS baseada em URL.
     *
     * @return Fonte de configuração CORS
     */
    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration());
        return source;
    }

    /**
     * Define a configuração CORS detalhada com origens, cabeçalhos e métodos permitidos.
     *
     * @return Configuração CORS
     */
    @Bean
    public CorsConfiguration corsConfiguration() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Arrays.asList(corsOrigins));
        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "Accept", "X-Organizacao-Id"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        config.setMaxAge(3600L);
        return config;
    }

    /**
     * Configura o gerenciador de autenticação com o serviço de detalhes do usuário.
     *
     * @param http Objeto HttpSecurity para obter o gerenciador compartilhado
     * @return Gerenciador de autenticação configurado
     * @throws Exception Caso ocorra erro na configuração
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
        auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
        return auth.build();
    }
}