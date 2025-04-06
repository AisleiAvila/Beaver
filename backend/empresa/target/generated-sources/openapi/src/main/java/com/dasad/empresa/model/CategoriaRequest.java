package com.dasad.empresa.model;

import java.net.URI;
import java.util.Objects;
import com.dasad.empresa.model.StatusServico;
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
 * CategoriaRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-04-05T17:52:15.110897700+01:00[Europe/Lisbon]", comments = "Generator version: 7.12.0")
public class CategoriaRequest {

  private @Nullable Integer id;

  private @Nullable String nome;

  @Valid
  private List<StatusServico> status = new ArrayList<>();

  private @Nullable Boolean requerCertificacao;

  private @Nullable String tipoCertificacao;

  private @Nullable Integer experienciaMinimaMeses;

  private @Nullable String nivelRisco;

  private @Nullable Integer limit;

  private @Nullable Integer offset;

  public CategoriaRequest id(Integer id) {
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

  public CategoriaRequest nome(String nome) {
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

  public CategoriaRequest status(List<StatusServico> status) {
    this.status = status;
    return this;
  }

  public CategoriaRequest addStatusItem(StatusServico statusItem) {
    if (this.status == null) {
      this.status = new ArrayList<>();
    }
    this.status.add(statusItem);
    return this;
  }

  /**
   * Get status
   * @return status
   */
  @Valid 
  @Schema(name = "status", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("status")
  public List<StatusServico> getStatus() {
    return status;
  }

  public void setStatus(List<StatusServico> status) {
    this.status = status;
  }

  public CategoriaRequest requerCertificacao(Boolean requerCertificacao) {
    this.requerCertificacao = requerCertificacao;
    return this;
  }

  /**
   * Get requerCertificacao
   * @return requerCertificacao
   */
  
  @Schema(name = "requer_certificacao", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("requer_certificacao")
  public Boolean getRequerCertificacao() {
    return requerCertificacao;
  }

  public void setRequerCertificacao(Boolean requerCertificacao) {
    this.requerCertificacao = requerCertificacao;
  }

  public CategoriaRequest tipoCertificacao(String tipoCertificacao) {
    this.tipoCertificacao = tipoCertificacao;
    return this;
  }

  /**
   * Get tipoCertificacao
   * @return tipoCertificacao
   */
  
  @Schema(name = "tipo_certificacao", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("tipo_certificacao")
  public String getTipoCertificacao() {
    return tipoCertificacao;
  }

  public void setTipoCertificacao(String tipoCertificacao) {
    this.tipoCertificacao = tipoCertificacao;
  }

  public CategoriaRequest experienciaMinimaMeses(Integer experienciaMinimaMeses) {
    this.experienciaMinimaMeses = experienciaMinimaMeses;
    return this;
  }

  /**
   * Get experienciaMinimaMeses
   * @return experienciaMinimaMeses
   */
  
  @Schema(name = "experiencia_minima_meses", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("experiencia_minima_meses")
  public Integer getExperienciaMinimaMeses() {
    return experienciaMinimaMeses;
  }

  public void setExperienciaMinimaMeses(Integer experienciaMinimaMeses) {
    this.experienciaMinimaMeses = experienciaMinimaMeses;
  }

  public CategoriaRequest nivelRisco(String nivelRisco) {
    this.nivelRisco = nivelRisco;
    return this;
  }

  /**
   * Get nivelRisco
   * @return nivelRisco
   */
  
  @Schema(name = "nivel_risco", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("nivel_risco")
  public String getNivelRisco() {
    return nivelRisco;
  }

  public void setNivelRisco(String nivelRisco) {
    this.nivelRisco = nivelRisco;
  }

  public CategoriaRequest limit(Integer limit) {
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

  public CategoriaRequest offset(Integer offset) {
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
    CategoriaRequest categoriaRequest = (CategoriaRequest) o;
    return Objects.equals(this.id, categoriaRequest.id) &&
        Objects.equals(this.nome, categoriaRequest.nome) &&
        Objects.equals(this.status, categoriaRequest.status) &&
        Objects.equals(this.requerCertificacao, categoriaRequest.requerCertificacao) &&
        Objects.equals(this.tipoCertificacao, categoriaRequest.tipoCertificacao) &&
        Objects.equals(this.experienciaMinimaMeses, categoriaRequest.experienciaMinimaMeses) &&
        Objects.equals(this.nivelRisco, categoriaRequest.nivelRisco) &&
        Objects.equals(this.limit, categoriaRequest.limit) &&
        Objects.equals(this.offset, categoriaRequest.offset);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nome, status, requerCertificacao, tipoCertificacao, experienciaMinimaMeses, nivelRisco, limit, offset);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CategoriaRequest {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    requerCertificacao: ").append(toIndentedString(requerCertificacao)).append("\n");
    sb.append("    tipoCertificacao: ").append(toIndentedString(tipoCertificacao)).append("\n");
    sb.append("    experienciaMinimaMeses: ").append(toIndentedString(experienciaMinimaMeses)).append("\n");
    sb.append("    nivelRisco: ").append(toIndentedString(nivelRisco)).append("\n");
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

