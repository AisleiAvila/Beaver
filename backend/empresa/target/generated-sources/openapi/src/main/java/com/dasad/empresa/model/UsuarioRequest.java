package com.dasad.empresa.model;

import java.net.URI;
import java.util.Objects;
import com.dasad.empresa.model.EventPagination;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * UsuarioRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-02T10:06:15.811723800Z[Europe/Lisbon]", comments = "Generator version: 7.9.0")
public class UsuarioRequest {

  private Integer id;

  private String nome;

  private String email;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate dataNascimento;

  @Valid
  private List<Integer> perfis = new ArrayList<>();

  private Integer limit;

  private Integer offset;

  private EventPagination event;

  public UsuarioRequest id(Integer id) {
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

  public UsuarioRequest nome(String nome) {
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

  public UsuarioRequest email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
   */
  
  @Schema(name = "email", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("email")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public UsuarioRequest dataNascimento(LocalDate dataNascimento) {
    this.dataNascimento = dataNascimento;
    return this;
  }

  /**
   * Get dataNascimento
   * @return dataNascimento
   */
  @Valid 
  @Schema(name = "dataNascimento", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("dataNascimento")
  public LocalDate getDataNascimento() {
    return dataNascimento;
  }

  public void setDataNascimento(LocalDate dataNascimento) {
    this.dataNascimento = dataNascimento;
  }

  public UsuarioRequest perfis(List<Integer> perfis) {
    this.perfis = perfis;
    return this;
  }

  public UsuarioRequest addPerfisItem(Integer perfisItem) {
    if (this.perfis == null) {
      this.perfis = new ArrayList<>();
    }
    this.perfis.add(perfisItem);
    return this;
  }

  /**
   * Get perfis
   * @return perfis
   */
  
  @Schema(name = "perfis", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("perfis")
  public List<Integer> getPerfis() {
    return perfis;
  }

  public void setPerfis(List<Integer> perfis) {
    this.perfis = perfis;
  }

  public UsuarioRequest limit(Integer limit) {
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

  public UsuarioRequest offset(Integer offset) {
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

  public UsuarioRequest event(EventPagination event) {
    this.event = event;
    return this;
  }

  /**
   * Get event
   * @return event
   */
  @Valid 
  @Schema(name = "event", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("event")
  public EventPagination getEvent() {
    return event;
  }

  public void setEvent(EventPagination event) {
    this.event = event;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UsuarioRequest usuarioRequest = (UsuarioRequest) o;
    return Objects.equals(this.id, usuarioRequest.id) &&
        Objects.equals(this.nome, usuarioRequest.nome) &&
        Objects.equals(this.email, usuarioRequest.email) &&
        Objects.equals(this.dataNascimento, usuarioRequest.dataNascimento) &&
        Objects.equals(this.perfis, usuarioRequest.perfis) &&
        Objects.equals(this.limit, usuarioRequest.limit) &&
        Objects.equals(this.offset, usuarioRequest.offset) &&
        Objects.equals(this.event, usuarioRequest.event);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nome, email, dataNascimento, perfis, limit, offset, event);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UsuarioRequest {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    dataNascimento: ").append(toIndentedString(dataNascimento)).append("\n");
    sb.append("    perfis: ").append(toIndentedString(perfis)).append("\n");
    sb.append("    limit: ").append(toIndentedString(limit)).append("\n");
    sb.append("    offset: ").append(toIndentedString(offset)).append("\n");
    sb.append("    event: ").append(toIndentedString(event)).append("\n");
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

