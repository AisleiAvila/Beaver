package com.dasad.empresa.model;

import java.net.URI;
import java.util.Objects;
import com.dasad.empresa.model.OrganizacaoModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * OrganizacaoResponseDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-02-27T20:37:40.910467600Z[Europe/Lisbon]", comments = "Generator version: 7.9.0")
public class OrganizacaoResponseDTO {

  @Valid
  private List<@Valid OrganizacaoModel> organizacoes = new ArrayList<>();

  private Integer totalRecords;

  public OrganizacaoResponseDTO organizacoes(List<@Valid OrganizacaoModel> organizacoes) {
    this.organizacoes = organizacoes;
    return this;
  }

  public OrganizacaoResponseDTO addOrganizacoesItem(OrganizacaoModel organizacoesItem) {
    if (this.organizacoes == null) {
      this.organizacoes = new ArrayList<>();
    }
    this.organizacoes.add(organizacoesItem);
    return this;
  }

  /**
   * Get organizacoes
   * @return organizacoes
   */
  @Valid 
  @Schema(name = "organizacoes", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("organizacoes")
  public List<@Valid OrganizacaoModel> getOrganizacoes() {
    return organizacoes;
  }

  public void setOrganizacoes(List<@Valid OrganizacaoModel> organizacoes) {
    this.organizacoes = organizacoes;
  }

  public OrganizacaoResponseDTO totalRecords(Integer totalRecords) {
    this.totalRecords = totalRecords;
    return this;
  }

  /**
   * Get totalRecords
   * @return totalRecords
   */
  
  @Schema(name = "totalRecords", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("totalRecords")
  public Integer getTotalRecords() {
    return totalRecords;
  }

  public void setTotalRecords(Integer totalRecords) {
    this.totalRecords = totalRecords;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrganizacaoResponseDTO organizacaoResponseDTO = (OrganizacaoResponseDTO) o;
    return Objects.equals(this.organizacoes, organizacaoResponseDTO.organizacoes) &&
        Objects.equals(this.totalRecords, organizacaoResponseDTO.totalRecords);
  }

  @Override
  public int hashCode() {
    return Objects.hash(organizacoes, totalRecords);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrganizacaoResponseDTO {\n");
    sb.append("    organizacoes: ").append(toIndentedString(organizacoes)).append("\n");
    sb.append("    totalRecords: ").append(toIndentedString(totalRecords)).append("\n");
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

