package com.dasad.empresa.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * LoginResponseDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-05T12:52:25.659801500+01:00[Europe/Lisbon]", comments = "Generator version: 7.12.0")
public class LoginResponseDTO {

  private @Nullable String nome;

  private @Nullable String authorization;

  private @Nullable String perfil;

  @Valid
  private List<Integer> organizacoesIds = new ArrayList<>();

  public LoginResponseDTO nome(String nome) {
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

  public LoginResponseDTO authorization(String authorization) {
    this.authorization = authorization;
    return this;
  }

  /**
   * Get authorization
   * @return authorization
   */
  
  @Schema(name = "authorization", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("authorization")
  public String getAuthorization() {
    return authorization;
  }

  public void setAuthorization(String authorization) {
    this.authorization = authorization;
  }

  public LoginResponseDTO perfil(String perfil) {
    this.perfil = perfil;
    return this;
  }

  /**
   * Get perfil
   * @return perfil
   */
  
  @Schema(name = "perfil", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("perfil")
  public String getPerfil() {
    return perfil;
  }

  public void setPerfil(String perfil) {
    this.perfil = perfil;
  }

  public LoginResponseDTO organizacoesIds(List<Integer> organizacoesIds) {
    this.organizacoesIds = organizacoesIds;
    return this;
  }

  public LoginResponseDTO addOrganizacoesIdsItem(Integer organizacoesIdsItem) {
    if (this.organizacoesIds == null) {
      this.organizacoesIds = new ArrayList<>();
    }
    this.organizacoesIds.add(organizacoesIdsItem);
    return this;
  }

  /**
   * Get organizacoesIds
   * @return organizacoesIds
   */
  
  @Schema(name = "organizacoesIds", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("organizacoesIds")
  public List<Integer> getOrganizacoesIds() {
    return organizacoesIds;
  }

  public void setOrganizacoesIds(List<Integer> organizacoesIds) {
    this.organizacoesIds = organizacoesIds;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LoginResponseDTO loginResponseDTO = (LoginResponseDTO) o;
    return Objects.equals(this.nome, loginResponseDTO.nome) &&
        Objects.equals(this.authorization, loginResponseDTO.authorization) &&
        Objects.equals(this.perfil, loginResponseDTO.perfil) &&
        Objects.equals(this.organizacoesIds, loginResponseDTO.organizacoesIds);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nome, authorization, perfil, organizacoesIds);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LoginResponseDTO {\n");
    sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
    sb.append("    authorization: ").append(toIndentedString(authorization)).append("\n");
    sb.append("    perfil: ").append(toIndentedString(perfil)).append("\n");
    sb.append("    organizacoesIds: ").append(toIndentedString(organizacoesIds)).append("\n");
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

