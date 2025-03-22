package com.dasad.empresa.model;

import java.net.URI;
import java.util.Objects;
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
 * SubCategoriaRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-22T15:44:36.725596400Z[Europe/Lisbon]", comments = "Generator version: 7.9.0")
public class SubCategoriaRequest {

  private Integer id;

  private Integer categoriaId;

  private String nome;

  private String status;

  private String nivelMedioComplexidade;

  private Integer limit;

  private Integer offset;

  public SubCategoriaRequest id(Integer id) {
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

  public SubCategoriaRequest categoriaId(Integer categoriaId) {
    this.categoriaId = categoriaId;
    return this;
  }

  /**
   * Get categoriaId
   * @return categoriaId
   */
  
  @Schema(name = "categoria_id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("categoria_id")
  public Integer getCategoriaId() {
    return categoriaId;
  }

  public void setCategoriaId(Integer categoriaId) {
    this.categoriaId = categoriaId;
  }

  public SubCategoriaRequest nome(String nome) {
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

  public SubCategoriaRequest status(String status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
   */
  
  @Schema(name = "status", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("status")
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public SubCategoriaRequest nivelMedioComplexidade(String nivelMedioComplexidade) {
    this.nivelMedioComplexidade = nivelMedioComplexidade;
    return this;
  }

  /**
   * Get nivelMedioComplexidade
   * @return nivelMedioComplexidade
   */
  
  @Schema(name = "nivel_medio_complexidade", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("nivel_medio_complexidade")
  public String getNivelMedioComplexidade() {
    return nivelMedioComplexidade;
  }

  public void setNivelMedioComplexidade(String nivelMedioComplexidade) {
    this.nivelMedioComplexidade = nivelMedioComplexidade;
  }

  public SubCategoriaRequest limit(Integer limit) {
    this.limit = limit;
    return this;
  }

  /**
   * Get limit
   * @return limit
   */
  
  @Schema(name = "limit", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("limit")
  public Integer getLimit() {
    return limit;
  }

  public void setLimit(Integer limit) {
    this.limit = limit;
  }

  public SubCategoriaRequest offset(Integer offset) {
    this.offset = offset;
    return this;
  }

  /**
   * Get offset
   * @return offset
   */
  
  @Schema(name = "offset", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("offset")
  public Integer getOffset() {
    return offset;
  }

  public void setOffset(Integer offset) {
    this.offset = offset;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SubCategoriaRequest subCategoriaRequest = (SubCategoriaRequest) o;
    return Objects.equals(this.id, subCategoriaRequest.id) &&
        Objects.equals(this.categoriaId, subCategoriaRequest.categoriaId) &&
        Objects.equals(this.nome, subCategoriaRequest.nome) &&
        Objects.equals(this.status, subCategoriaRequest.status) &&
        Objects.equals(this.nivelMedioComplexidade, subCategoriaRequest.nivelMedioComplexidade) &&
        Objects.equals(this.limit, subCategoriaRequest.limit) &&
        Objects.equals(this.offset, subCategoriaRequest.offset);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, categoriaId, nome, status, nivelMedioComplexidade, limit, offset);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubCategoriaRequest {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    categoriaId: ").append(toIndentedString(categoriaId)).append("\n");
    sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    nivelMedioComplexidade: ").append(toIndentedString(nivelMedioComplexidade)).append("\n");
    sb.append("    limit: ").append(toIndentedString(limit)).append("\n");
    sb.append("    offset: ").append(toIndentedString(offset)).append("\n");
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

