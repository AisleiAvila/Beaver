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
 * OrganizacaoModel
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-02-27T20:37:40.910467600Z[Europe/Lisbon]", comments = "Generator version: 7.9.0")
public class OrganizacaoModel {

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

  public OrganizacaoModel id(Integer id) {
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

  public OrganizacaoModel nome(String nome) {
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

  public OrganizacaoModel nif(String nif) {
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

  public OrganizacaoModel email(String email) {
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

  public OrganizacaoModel website(String website) {
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

  public OrganizacaoModel setorAtividade(String setorAtividade) {
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

  public OrganizacaoModel missao(String missao) {
    this.missao = missao;
    return this;
  }

  /**
   * Get missao
   * @return missao
   */
  
  @Schema(name = "missao", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("missao")
  public String getMissao() {
    return missao;
  }

  public void setMissao(String missao) {
    this.missao = missao;
  }

  public OrganizacaoModel representanteLegal(String representanteLegal) {
    this.representanteLegal = representanteLegal;
    return this;
  }

  /**
   * Get representanteLegal
   * @return representanteLegal
   */
  
  @Schema(name = "representante_legal", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("representante_legal")
  public String getRepresentanteLegal() {
    return representanteLegal;
  }

  public void setRepresentanteLegal(String representanteLegal) {
    this.representanteLegal = representanteLegal;
  }

  public OrganizacaoModel cargo(String cargo) {
    this.cargo = cargo;
    return this;
  }

  /**
   * Get cargo
   * @return cargo
   */
  
  @Schema(name = "cargo", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("cargo")
  public String getCargo() {
    return cargo;
  }

  public void setCargo(String cargo) {
    this.cargo = cargo;
  }

  public OrganizacaoModel numeroRegistoComercial(String numeroRegistoComercial) {
    this.numeroRegistoComercial = numeroRegistoComercial;
    return this;
  }

  /**
   * Get numeroRegistoComercial
   * @return numeroRegistoComercial
   */
  
  @Schema(name = "numeroRegistoComercial", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("numeroRegistoComercial")
  public String getNumeroRegistoComercial() {
    return numeroRegistoComercial;
  }

  public void setNumeroRegistoComercial(String numeroRegistoComercial) {
    this.numeroRegistoComercial = numeroRegistoComercial;
  }

  public OrganizacaoModel dataRegisto(LocalDate dataRegisto) {
    this.dataRegisto = JsonNullable.of(dataRegisto);
    return this;
  }

  /**
   * Get dataRegisto
   * @return dataRegisto
   */
  @Valid 
  @Schema(name = "dataRegisto", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
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
    OrganizacaoModel organizacaoModel = (OrganizacaoModel) o;
    return Objects.equals(this.id, organizacaoModel.id) &&
        Objects.equals(this.nome, organizacaoModel.nome) &&
        Objects.equals(this.nif, organizacaoModel.nif) &&
        Objects.equals(this.email, organizacaoModel.email) &&
        Objects.equals(this.website, organizacaoModel.website) &&
        Objects.equals(this.setorAtividade, organizacaoModel.setorAtividade) &&
        Objects.equals(this.missao, organizacaoModel.missao) &&
        Objects.equals(this.representanteLegal, organizacaoModel.representanteLegal) &&
        Objects.equals(this.cargo, organizacaoModel.cargo) &&
        Objects.equals(this.numeroRegistoComercial, organizacaoModel.numeroRegistoComercial) &&
        equalsNullable(this.dataRegisto, organizacaoModel.dataRegisto);
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
    sb.append("class OrganizacaoModel {\n");
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

