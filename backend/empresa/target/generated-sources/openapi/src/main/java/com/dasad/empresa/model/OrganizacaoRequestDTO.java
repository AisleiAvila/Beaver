package com.dasad.empresa.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.LocalDate;
import java.util.Arrays;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.NoSuchElementException;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * OrganizacaoRequestDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-02T10:06:15.811723800Z[Europe/Lisbon]", comments = "Generator version: 7.9.0")
public class OrganizacaoRequestDTO {

  private Integer id;

  private String nome;

  private String nif;

  private String email;

  private String website;

  private String setorAtividade;

  private String missao;

  private String representanteLegal;

  private String cargo;

  private String numeroRegistoComercial;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private JsonNullable<LocalDate> dataRegisto = JsonNullable.<LocalDate>undefined();

  public OrganizacaoRequestDTO id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Identificador da organização
   * @return id
   */
  
  @Schema(name = "id", example = "1", description = "Identificador da organização", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public OrganizacaoRequestDTO nome(String nome) {
    this.nome = nome;
    return this;
  }

  /**
   * Nome da organização
   * @return nome
   */
  
  @Schema(name = "nome", example = "XPTO Lda", description = "Nome da organização", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("nome")
  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public OrganizacaoRequestDTO nif(String nif) {
    this.nif = nif;
    return this;
  }

  /**
   * Número de Identificação Fiscal
   * @return nif
   */
  
  @Schema(name = "nif", example = "123456789", description = "Número de Identificação Fiscal", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("nif")
  public String getNif() {
    return nif;
  }

  public void setNif(String nif) {
    this.nif = nif;
  }

  public OrganizacaoRequestDTO email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Endereço de e-mail
   * @return email
   */
  
  @Schema(name = "email", example = "Baker Street 221B", description = "Endereço de e-mail", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("email")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public OrganizacaoRequestDTO website(String website) {
    this.website = website;
    return this;
  }

  /**
   * Endereço do website
   * @return website
   */
  
  @Schema(name = "website", example = "https://www.exemplo.com", description = "Endereço do website", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("website")
  public String getWebsite() {
    return website;
  }

  public void setWebsite(String website) {
    this.website = website;
  }

  public OrganizacaoRequestDTO setorAtividade(String setorAtividade) {
    this.setorAtividade = setorAtividade;
    return this;
  }

  /**
   * Setor de atividade
   * @return setorAtividade
   */
  
  @Schema(name = "setorAtividade", example = "Educação", description = "Setor de atividade", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("setorAtividade")
  public String getSetorAtividade() {
    return setorAtividade;
  }

  public void setSetorAtividade(String setorAtividade) {
    this.setorAtividade = setorAtividade;
  }

  public OrganizacaoRequestDTO missao(String missao) {
    this.missao = missao;
    return this;
  }

  /**
   * Missão da organização
   * @return missao
   */
  
  @Schema(name = "missao", example = "Promover a educação", description = "Missão da organização", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("missao")
  public String getMissao() {
    return missao;
  }

  public void setMissao(String missao) {
    this.missao = missao;
  }

  public OrganizacaoRequestDTO representanteLegal(String representanteLegal) {
    this.representanteLegal = representanteLegal;
    return this;
  }

  /**
   * Representante legal
   * @return representanteLegal
   */
  
  @Schema(name = "representanteLegal", example = "João Silva", description = "Representante legal", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("representanteLegal")
  public String getRepresentanteLegal() {
    return representanteLegal;
  }

  public void setRepresentanteLegal(String representanteLegal) {
    this.representanteLegal = representanteLegal;
  }

  public OrganizacaoRequestDTO cargo(String cargo) {
    this.cargo = cargo;
    return this;
  }

  /**
   * Cargo do representante legal
   * @return cargo
   */
  
  @Schema(name = "cargo", example = "CEO", description = "Cargo do representante legal", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("cargo")
  public String getCargo() {
    return cargo;
  }

  public void setCargo(String cargo) {
    this.cargo = cargo;
  }

  public OrganizacaoRequestDTO numeroRegistoComercial(String numeroRegistoComercial) {
    this.numeroRegistoComercial = numeroRegistoComercial;
    return this;
  }

  /**
   * Número de registo comercial
   * @return numeroRegistoComercial
   */
  
  @Schema(name = "numeroRegistoComercial", example = "123456789", description = "Número de registo comercial", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("numeroRegistoComercial")
  public String getNumeroRegistoComercial() {
    return numeroRegistoComercial;
  }

  public void setNumeroRegistoComercial(String numeroRegistoComercial) {
    this.numeroRegistoComercial = numeroRegistoComercial;
  }

  public OrganizacaoRequestDTO dataRegisto(LocalDate dataRegisto) {
    this.dataRegisto = JsonNullable.of(dataRegisto);
    return this;
  }

  /**
   * Data de registo comercial
   * @return dataRegisto
   */
  @Valid 
  @Schema(name = "dataRegisto", example = "Wed Sep 01 01:00:00 WEST 2021", description = "Data de registo comercial", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("dataRegisto")
  public JsonNullable<LocalDate> getDataRegisto() {
    return dataRegisto;
  }

  public void setDataRegisto(JsonNullable<LocalDate> dataRegisto) {
    this.dataRegisto = dataRegisto;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrganizacaoRequestDTO organizacaoRequestDTO = (OrganizacaoRequestDTO) o;
    return Objects.equals(this.id, organizacaoRequestDTO.id) &&
        Objects.equals(this.nome, organizacaoRequestDTO.nome) &&
        Objects.equals(this.nif, organizacaoRequestDTO.nif) &&
        Objects.equals(this.email, organizacaoRequestDTO.email) &&
        Objects.equals(this.website, organizacaoRequestDTO.website) &&
        Objects.equals(this.setorAtividade, organizacaoRequestDTO.setorAtividade) &&
        Objects.equals(this.missao, organizacaoRequestDTO.missao) &&
        Objects.equals(this.representanteLegal, organizacaoRequestDTO.representanteLegal) &&
        Objects.equals(this.cargo, organizacaoRequestDTO.cargo) &&
        Objects.equals(this.numeroRegistoComercial, organizacaoRequestDTO.numeroRegistoComercial) &&
        equalsNullable(this.dataRegisto, organizacaoRequestDTO.dataRegisto);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nome, nif, email, website, setorAtividade, missao, representanteLegal, cargo, numeroRegistoComercial, hashCodeNullable(dataRegisto));
  }

  private static <T> int hashCodeNullable(JsonNullable<T> a) {
    if (a == null) {
      return 1;
    }
    return a.isPresent() ? Arrays.deepHashCode(new Object[]{a.get()}) : 31;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrganizacaoRequestDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
    sb.append("    nif: ").append(toIndentedString(nif)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    website: ").append(toIndentedString(website)).append("\n");
    sb.append("    setorAtividade: ").append(toIndentedString(setorAtividade)).append("\n");
    sb.append("    missao: ").append(toIndentedString(missao)).append("\n");
    sb.append("    representanteLegal: ").append(toIndentedString(representanteLegal)).append("\n");
    sb.append("    cargo: ").append(toIndentedString(cargo)).append("\n");
    sb.append("    numeroRegistoComercial: ").append(toIndentedString(numeroRegistoComercial)).append("\n");
    sb.append("    dataRegisto: ").append(toIndentedString(dataRegisto)).append("\n");
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

