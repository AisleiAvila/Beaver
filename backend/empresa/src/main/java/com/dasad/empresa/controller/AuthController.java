package com.dasad.empresa.controller;

import com.dasad.empresa.api.AuthApi;
import com.dasad.empresa.infra.security.AuthorizationService;
import com.dasad.empresa.model.LoginRequestDTO;
import com.dasad.empresa.model.LoginResponseDTO;
import com.dasad.empresa.model.OrganizacaoModel;
import com.dasad.empresa.model.RegisterRequestDTO;
import com.dasad.empresa.model.RevokeToken200Response;
import com.dasad.empresa.model.RevokeTokenRequest;
import com.dasad.empresa.model.UsuarioModel;
import com.dasad.empresa.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@Log4j2
@RequestMapping({"/auth"})
@Tag(name = "Autenticação", description = "API para autenticação e gerenciamento de tokens")
public class AuthController implements AuthApi {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorizationService authorizationService;

    public AuthController(
            final UsuarioRepository usuarioRepository,
            final PasswordEncoder passwordEncoder,
            final AuthorizationService authorizationService) {
        log.info("AuthController constructor");
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorizationService = authorizationService;
    }

    @Operation(
            summary = "Verifica a validade do token",
            description = "Valida o token de autorização enviado no cabeçalho"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token válido", content = @Content(schema = @Schema(implementation = Boolean.class))),
            @ApiResponse(responseCode = "400", description = "Token inválido", content = @Content)
    })
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping({"/verify-authorization"})
    public ResponseEntity<Boolean> verifyAuthorization(@RequestHeader("Authorization") String authorization) {
        log.info("Verify authorization endpoint");
        String authorized = this.authorizationService.validateToken(authorization);
        return authorized != null ? ResponseEntity.ok(true) : ResponseEntity.badRequest().build();

    }

    @Override
    @Operation(
            summary = "Autentica usuário",
            description = "Realiza login do usuário e retorna um token de acesso"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso",
                    content = @Content(schema = @Schema(implementation = LoginResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Credenciais inválidas", content = @Content)
    })
    @PostMapping({"/login"})
    public ResponseEntity<LoginResponseDTO> login(
            @Parameter(description = "Credenciais do usuário",
                    required = true,
                    schema = @Schema(implementation = LoginRequestDTO.class))
            @RequestBody LoginRequestDTO loginRequestDTO) {
        log.info("Login endpoint");
        Optional<UsuarioModel> optionalUsuario = this.usuarioRepository.findByEmailAndOrganizacaoId(
                loginRequestDTO.getEmail(),
                loginRequestDTO.getOrganizacaoId().orElse(null)
        );
        if (optionalUsuario.isPresent()) {
            var usuario = optionalUsuario.get();
            if (this.passwordEncoder.matches(loginRequestDTO.getSenha(), usuario.getSenha())) {
                String authorization = this.authorizationService.generateToken(usuario);
                var loginResponseDTO = new LoginResponseDTO();
                loginResponseDTO.setNome(usuario.getNome());
                loginResponseDTO.setAuthorization(authorization);
                loginResponseDTO.setPerfil(usuario.getPerfis().getFirst().getNome());

                // Extrair apenas os IDs das organizações
                List<Integer> organizacoesIds = usuario.getOrganizacoes().stream()
                        .map(OrganizacaoModel::getId)
                        .toList();

                loginResponseDTO.setOrganizacoesIds(organizacoesIds);


                return ResponseEntity.ok(loginResponseDTO);
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @Override
    @Operation(summary = "Register endpoint", description = "Cria um novo usuário no sistema e retorna um token de autorização")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário registrado com sucesso",
                    content = @Content(schema = @Schema(implementation = LoginResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Email já cadastrado ou dados inválidos", content = @Content)
    })
    @PostMapping({"/register"})
    public ResponseEntity<LoginResponseDTO> register(
            @RequestParam(value = "organizaoId") Integer organizacaoId,
            @RequestBody RegisterRequestDTO registerRequestDTO) {
        log.info("Register endpoint");
        Optional<UsuarioModel> usuarioCadastrado = this.usuarioRepository.findByEmail(registerRequestDTO.getEmail());
        if (usuarioCadastrado.isEmpty()) {
            log.info("Usuário não cadastrado");
            var usuario = new UsuarioModel();
            usuario.setNome(registerRequestDTO.getNome());
            usuario.setEmail(registerRequestDTO.getEmail());
            usuario.setSenha(this.passwordEncoder.encode(registerRequestDTO.getSenha()));
            usuario.setDataNascimento(registerRequestDTO.getDataNascimento());
            usuario.setEnderecos(registerRequestDTO.getEnderecos());
            usuario.setPerfis(registerRequestDTO.getPerfis());

            log.info("Criando usuário");
            this.usuarioRepository.create(usuario, organizacaoId);
            log.info("Usuário criado");
            String authorization = this.authorizationService.generateToken(usuario);
            var loginResponseDTO = new LoginResponseDTO();
            loginResponseDTO.setNome(usuario.getNome());
            loginResponseDTO.setAuthorization(authorization);
            loginResponseDTO.setPerfil(usuario.getPerfis().getFirst().getNome());
            return ResponseEntity.ok(loginResponseDTO);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @Override
    @Operation(summary = "Revoke token", description = "Invalida um token de acesso previamente emitido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token revogado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Token inválido ou não fornecido")
    })
    @PostMapping({"/revoke"})
    public ResponseEntity<RevokeToken200Response> revokeToken(RevokeTokenRequest revokeTokenRequest) {
        log.info("Revoke token");

        String authorization = revokeTokenRequest.getToken();
        if (authorization == null || authorization.isEmpty()) {
            throw new IllegalArgumentException("O token não pode ser nulo ou vazio");
        }
        authorizationService.revokeToken(authorization);
        return ResponseEntity.ok().build();
    }

    @PostMapping("authenticate")
    public String authenticate(Authentication authentication) {
        log.info("Authenticate endpoint");
        return this.authorizationService.authenticate(authentication);
    }

}
