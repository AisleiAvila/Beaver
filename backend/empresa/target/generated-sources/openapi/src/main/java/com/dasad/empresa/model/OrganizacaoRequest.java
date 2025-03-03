package com.dasad.empresa.model;

import java.net.URI;
import java.util.Objects;
import com.dasad.empresa.model.EventPagination;
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
 * OrganizacaoRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-02T10:06:15.811723800Z[Europe/Lisbon]", comments = "Generator version: 7.9.0")
public class OrganizacaoRequest {

  private Integer id;

  private String nome;

  private String nif;

  private String email;

  private String website;

  private String setorAtividade;

  private Integer limit;

  private Integer offset;

  private EventPagination event;

  public OrganizacaoRequest id(Integer id) {
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

  public OrganizacaoRequest nome(String nome) {
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

  public OrganizacaoRequest nif(String nif) {
    this.nif = nif;
    return this;
  }

  /**
   * Get nif
   * @return nif
   */
  
  @Schema(name = "nif", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("nif")
  public String getNif() {
    return nif;
  }

  public void setNif(String nif) {
    this.nif = nif;
  }

  public OrganizacaoRequest email(String email) {
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

  public OrganizacaoRequest website(String website) {
    this.website = website;
    return this;
  }

  /**
   * Get website
   * @return website
   */
  
  @Schema(name = "website", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("website")
  public String getWebsite() {
    return website;
  }

  public void setWebsite(String website) {
    this.website = website;
  }

  public OrganizacaoRequest setorAtividade(String setorAtividade) {
    this.setorAtividade = setorAtividade;
    return this;
  }

  /**
   * Get setorAtividade
   * @return setorAtividade
   */
  
  @Schema(name = "setorAtividade", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("setorAtividade")
  public String getSetorAtividade() {
    return setorAtividade;
  }

  public void setSetorAtividade(String setorAtividade) {
    this.setorAtividade = setorAtividade;
  }

  public OrganizacaoRequest limit(Integer limit) {
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

  public OrganizacaoRequest offset(Integer offset) {
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

  public OrganizacaoRequest event(EventPagination event) {
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
    OrganizacaoRequest organizacaoRequest = (OrganizacaoRequest) o;
    return Objects.equals(this.id, organizacaoRequest.id) &&
        Objects.equals(this.nome, organizacaoRequest.nome) &&
        Objects.equals(this.nif, organizacaoRequest.nif) &&
        Objects.equals(this.email, organizacaoRequest.email) &&
        Objects.equals(this.website, organizacaoRequest.website) &&
        Objects.equals(this.setorAtividade, organizacaoRequest.setorAtividade) &&
        Objects.equals(this.limit, organizacaoRequest.limit) &&
        Objects.equals(this.offset, organizacaoRequest.offset) &&
        Objects.equals(this.event, organizacaoRequest.event);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nome, nif, email, website, setorAtividade, limit, offset, event);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrganizacaoRequest {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
    sb.append("    nif: ").append(toIndentedString(nif)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    website: ").append(toIndentedString(website)).append("\n");
    sb.append("    setorAtividade: ").append(toIndentedString(setorAtividade)).append("\n");
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

