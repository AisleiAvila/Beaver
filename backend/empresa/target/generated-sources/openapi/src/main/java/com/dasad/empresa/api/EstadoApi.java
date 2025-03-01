/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (7.9.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.dasad.empresa.api;

import com.dasad.empresa.model.EstadoModel;
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
@Tag(name = "Estado", description = "Operações relacionadas a estados")
public interface EstadoApi {

    /**
     * GET /estado/{id} : Detalha um Estado
     *
     * @param id  (required)
     * @return Sucesso (status code 200)
     */
    @Operation(
        operationId = "detailEstado",
        summary = "Detalha um Estado",
        tags = { "Estado" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = EstadoModel.class))
            })
        },
        security = {
            @SecurityRequirement(name = "bearerAuth")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/estado/{id}",
        produces = { "application/json" }
    )
    
    ResponseEntity<EstadoModel> detailEstado(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Integer id
    );


    /**
     * GET /estado : Busca Estados
     *
     * @param nome  (optional)
     * @param paisId  (optional)
     * @return Sucesso (status code 200)
     */
    @Operation(
        operationId = "findEstado",
        summary = "Busca Estados",
        tags = { "Estado" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = EstadoModel.class)))
            })
        },
        security = {
            @SecurityRequirement(name = "bearerAuth")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/estado",
        produces = { "application/json" }
    )
    
    ResponseEntity<List<EstadoModel>> findEstado(
        @Parameter(name = "nome", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "nome", required = false) String nome,
        @Parameter(name = "pais_id", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "pais_id", required = false) Integer paisId
    );

}
