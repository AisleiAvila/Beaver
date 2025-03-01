/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (7.9.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.dasad.empresa.api;

import com.dasad.empresa.model.RecuperarSenha200Response;
import com.dasad.empresa.model.RecuperarSenha404Response;
import com.dasad.empresa.model.RecuperarSenhaRequest;
import com.dasad.empresa.model.SalvarSenha200Response;
import com.dasad.empresa.model.SalvarSenha404Response;
import com.dasad.empresa.model.SalvarSenhaRequest;
import com.dasad.empresa.model.ValidarResetToken200Response;
import com.dasad.empresa.model.ValidarResetToken404Response;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-02-27T20:37:40.910467600Z[Europe/Lisbon]", comments = "Generator version: 7.9.0")
@Validated
@Tag(name = "Senha", description = "Operações relacionadas a senha de acesso do usuário")
public interface SenhaApi {

    /**
     * POST /senha/recuperar : Gera link de recuperação de senha
     * Recebe o e-mail do usuário e envia um link de recuperação com token válido por 10 minutos.
     *
     * @param recuperarSenhaRequest  (required)
     * @return Link de recuperação enviado ao e-mail. (status code 200)
     *         or Usuário não encontrado. (status code 404)
     */
    @Operation(
        operationId = "recuperarSenha",
        summary = "Gera link de recuperação de senha",
        description = "Recebe o e-mail do usuário e envia um link de recuperação com token válido por 10 minutos.",
        tags = { "Senha" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Link de recuperação enviado ao e-mail.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = RecuperarSenha200Response.class))
            }),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = RecuperarSenha404Response.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/senha/recuperar",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    
    ResponseEntity<RecuperarSenha200Response> recuperarSenha(
        @Parameter(name = "RecuperarSenhaRequest", description = "", required = true) @Valid @RequestBody RecuperarSenhaRequest recuperarSenhaRequest
    );


    /**
     * POST /senha/salvar : Salvar senha do usuário
     * Recebe o e-mail do usuário e a nova senha.
     *
     * @param salvarSenhaRequest  (required)
     * @return Senha alterada com sucesso. (status code 200)
     *         or Senha não salva. (status code 404)
     */
    @Operation(
        operationId = "salvarSenha",
        summary = "Salvar senha do usuário",
        description = "Recebe o e-mail do usuário e a nova senha.",
        tags = { "Senha" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Senha alterada com sucesso.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = SalvarSenha200Response.class))
            }),
            @ApiResponse(responseCode = "404", description = "Senha não salva.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = SalvarSenha404Response.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/senha/salvar",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    
    ResponseEntity<SalvarSenha200Response> salvarSenha(
        @Parameter(name = "SalvarSenhaRequest", description = "", required = true) @Valid @RequestBody SalvarSenhaRequest salvarSenhaRequest
    );


    /**
     * GET /senha/validar-reset-token : Valida token de recuperação de senha
     * Valida o token de recuperação de senha e retorna o e-mail do usuário.
     *
     * @param token  (required)
     * @return Token válido (status code 200)
     *         or Token inválido (status code 404)
     */
    @Operation(
        operationId = "validarResetToken",
        summary = "Valida token de recuperação de senha",
        description = "Valida o token de recuperação de senha e retorna o e-mail do usuário.",
        tags = { "Senha" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Token válido", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ValidarResetToken200Response.class))
            }),
            @ApiResponse(responseCode = "404", description = "Token inválido", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ValidarResetToken404Response.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/senha/validar-reset-token",
        produces = { "application/json" }
    )
    
    ResponseEntity<ValidarResetToken200Response> validarResetToken(
        @NotNull @Parameter(name = "token", description = "", required = true, in = ParameterIn.QUERY) @Valid @RequestParam(value = "token", required = true) String token
    );

}
