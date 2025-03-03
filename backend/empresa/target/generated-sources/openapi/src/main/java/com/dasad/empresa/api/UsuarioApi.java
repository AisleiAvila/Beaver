/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (7.9.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.dasad.empresa.api;

import com.dasad.empresa.model.RegisterRequestDTO;
import com.dasad.empresa.model.UsuarioModel;
import com.dasad.empresa.model.UsuarioRequest;
import com.dasad.empresa.model.UsuarioResponseDTO;
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

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-02T10:06:15.811723800Z[Europe/Lisbon]", comments = "Generator version: 7.9.0")
@Validated
@Tag(name = "Usuario", description = "Operações relacionadas a usuários")
public interface UsuarioApi {

    /**
     * PUT /usuario/create : Cria um usuário
     *
     * @param registerRequestDTO  (optional)
     * @return Sucesso (status code 200)
     */
    @Operation(
        operationId = "createUsuario",
        summary = "Cria um usuário",
        tags = { "Usuario" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioModel.class))
            })
        },
        security = {
            @SecurityRequirement(name = "bearerAuth")
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/usuario/create",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    
    ResponseEntity<UsuarioModel> createUsuario(
        @Parameter(name = "RegisterRequestDTO", description = "") @Valid @RequestBody(required = false) RegisterRequestDTO registerRequestDTO
    );


    /**
     * DELETE /usuario/delete/{id} : Deleta um usuário
     *
     * @param id  (required)
     * @return No Content (status code 204)
     */
    @Operation(
        operationId = "deleteUsuario",
        summary = "Deleta um usuário",
        tags = { "Usuario" },
        responses = {
            @ApiResponse(responseCode = "204", description = "No Content")
        },
        security = {
            @SecurityRequirement(name = "bearerAuth")
        }
    )
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/usuario/delete/{id}"
    )
    
    ResponseEntity<Void> deleteUsuario(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Integer id
    );


    /**
     * GET /usuario/detail/{id} : Detalha  um usuário
     *
     * @param id  (required)
     * @return Sucesso (status code 200)
     */
    @Operation(
        operationId = "detailUsuario",
        summary = "Detalha  um usuário",
        tags = { "Usuario" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDTO.class))
            })
        },
        security = {
            @SecurityRequirement(name = "bearerAuth")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/usuario/detail/{id}",
        produces = { "application/json" }
    )
    
    ResponseEntity<UsuarioResponseDTO> detailUsuario(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Integer id
    );


    /**
     * POST /usuario/find : Busca usuários
     *
     * @param usuarioRequest  (optional)
     * @return Sucesso (status code 200)
     */
    @Operation(
        operationId = "findUsuario",
        summary = "Busca usuários",
        tags = { "Usuario" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDTO.class))
            })
        },
        security = {
            @SecurityRequirement(name = "bearerAuth")
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/usuario/find",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    
    ResponseEntity<UsuarioResponseDTO> findUsuario(
        @Parameter(name = "UsuarioRequest", description = "") @Valid @RequestBody(required = false) UsuarioRequest usuarioRequest
    );


    /**
     * PATCH /usuario/update : Atualiza um usuário
     *
     * @param usuarioModel  (optional)
     * @return Sucesso (status code 200)
     */
    @Operation(
        operationId = "updateUsuario",
        summary = "Atualiza um usuário",
        tags = { "Usuario" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioModel.class))
            })
        },
        security = {
            @SecurityRequirement(name = "bearerAuth")
        }
    )
    @RequestMapping(
        method = RequestMethod.PATCH,
        value = "/usuario/update",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    
    ResponseEntity<UsuarioModel> updateUsuario(
        @Parameter(name = "UsuarioModel", description = "") @Valid @RequestBody(required = false) UsuarioModel usuarioModel
    );

}
