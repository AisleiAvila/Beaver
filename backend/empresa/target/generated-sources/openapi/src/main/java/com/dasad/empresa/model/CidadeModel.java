package com.dasad.empresa.model;

import java.net.URI;
import java.util.Objects;
import com.dasad.empresa.model.EstadoModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * CidadeModel
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-02T10:06:15.811723800Z[Europe/Lisbon]", comments = "Generator version: 7.9.0")
public class CidadeModel {

  private Integer id;

  private String nome;

  private EstadoModel estadoId;

  public CidadeModel id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */
  
  @Schema(name = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public CidadeModel nome(String nome) {
    this.nome = nome;
    return this;
  }

  /**
   * Get nome
   * @return nome
   */
  
  @Schema(name = "nome", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("nome")
  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public CidadeModel estadoId(EstadoModel estadoId) {
    this.estadoId = estadoId;
    return this;
  }

  /**
   * Get estadoId
   * @return estadoId
   */
  @Valid 
  @Schema(name = "estado_id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("estado_id")
  public EstadoModel getEstadoId() {
    return estadoId;
  }

  public void setEstadoId(EstadoModel estadoId) {
    this.estadoId = estadoId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CidadeModel cidadeModel = (CidadeModel) o;
    return Objects.equals(this.id, cidadeModel.id) &&
        Objects.equals(this.nome, cidadeModel.nome) &&
        Objects.equals(this.estadoId, cidadeModel.estadoId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nome, estadoId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CidadeModel {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
    sb.append("    estadoId: ").append(toIndentedString(estadoId)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

