package com.dasad.empresa.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import java.util.Arrays;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * UsuarioFotoModel
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-29T12:01:59.756847Z[Europe/Lisbon]", comments = "Generator version: 7.12.0")
public class UsuarioFotoModel {

  private @Nullable Integer id;

  private @Nullable Integer usuarioId;

  private @Nullable byte[] foto;

  private @Nullable Boolean ativo;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private @Nullable OffsetDateTime dataCriacao;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private @Nullable OffsetDateTime dataAtualizacao;

  public UsuarioFotoModel id(Integer id) {
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

  public UsuarioFotoModel usuarioId(Integer usuarioId) {
    this.usuarioId = usuarioId;
    return this;
  }

  /**
   * Get usuarioId
   * @return usuarioId
   */
  
  @Schema(name = "usuario_id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("usuario_id")
  public Integer getUsuarioId() {
    return usuarioId;
  }

  public void setUsuarioId(Integer usuarioId) {
    this.usuarioId = usuarioId;
  }

  public UsuarioFotoModel foto(byte[] foto) {
    this.foto = foto;
    return this;
  }

  /**
   * Get foto
   * @return foto
   */
  
  @Schema(name = "foto", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("foto")
  public byte[] getFoto() {
    return foto;
  }

  public void setFoto(byte[] foto) {
    this.foto = foto;
  }

  public UsuarioFotoModel ativo(Boolean ativo) {
    this.ativo = ativo;
    return this;
  }

  /**
   * Get ativo
   * @return ativo
   */
  
  @Schema(name = "ativo", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("ativo")
  public Boolean getAtivo() {
    return ativo;
  }

  public void setAtivo(Boolean ativo) {
    this.ativo = ativo;
  }

  public UsuarioFotoModel dataCriacao(OffsetDateTime dataCriacao) {
    this.dataCriacao = dataCriacao;
    return this;
  }

  /**
   * Get dataCriacao
   * @return dataCriacao
   */
  @Valid 
  @Schema(name = "data_criacao", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("data_criacao")
  public OffsetDateTime getDataCriacao() {
    return dataCriacao;
  }

  public void setDataCriacao(OffsetDateTime dataCriacao) {
    this.dataCriacao = dataCriacao;
  }

  public UsuarioFotoModel dataAtualizacao(OffsetDateTime dataAtualizacao) {
    this.dataAtualizacao = dataAtualizacao;
    return this;
  }

  /**
   * Get dataAtualizacao
   * @return dataAtualizacao
   */
  @Valid 
  @Schema(name = "data_atualizacao", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("data_atualizacao")
  public OffsetDateTime getDataAtualizacao() {
    return dataAtualizacao;
  }

  public void setDataAtualizacao(OffsetDateTime dataAtualizacao) {
    this.dataAtualizacao = dataAtualizacao;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UsuarioFotoModel usuarioFotoModel = (UsuarioFotoModel) o;
    return Objects.equals(this.id, usuarioFotoModel.id) &&
        Objects.equals(this.usuarioId, usuarioFotoModel.usuarioId) &&
        Arrays.equals(this.foto, usuarioFotoModel.foto) &&
        Objects.equals(this.ativo, usuarioFotoModel.ativo) &&
        Objects.equals(this.dataCriacao, usuarioFotoModel.dataCriacao) &&
        Objects.equals(this.dataAtualizacao, usuarioFotoModel.dataAtualizacao);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, usuarioId, Arrays.hashCode(foto), ativo, dataCriacao, dataAtualizacao);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UsuarioFotoModel {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    usuarioId: ").append(toIndentedString(usuarioId)).append("\n");
    sb.append("    foto: ").append(toIndentedString(foto)).append("\n");
    sb.append("    ativo: ").append(toIndentedString(ativo)).append("\n");
    sb.append("    dataCriacao: ").append(toIndentedString(dataCriacao)).append("\n");
    sb.append("    dataAtualizacao: ").append(toIndentedString(dataAtualizacao)).append("\n");
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

