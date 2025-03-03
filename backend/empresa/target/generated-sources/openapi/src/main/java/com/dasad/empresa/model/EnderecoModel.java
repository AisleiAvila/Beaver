package com.dasad.empresa.model;

import java.net.URI;
import java.util.Objects;
import com.dasad.empresa.model.CidadeModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.math.BigDecimal;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * EnderecoModel
 */

@JsonTypeName("enderecoModel")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-02T10:06:15.811723800Z[Europe/Lisbon]", comments = "Generator version: 7.9.0")
public class EnderecoModel {

  private Integer id;

  private CidadeModel cidadeId;

  private String cep;

  private String logradouro;

  private String numero;

  private String complemento;

  private String bairro;

  private BigDecimal latitude;

  private BigDecimal longitude;

  private Integer usuarioId;

  public EnderecoModel id(Integer id) {
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

  public EnderecoModel cidadeId(CidadeModel cidadeId) {
    this.cidadeId = cidadeId;
    return this;
  }

  /**
   * Get cidadeId
   * @return cidadeId
   */
  @Valid 
  @Schema(name = "cidade_id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("cidade_id")
  public CidadeModel getCidadeId() {
    return cidadeId;
  }

  public void setCidadeId(CidadeModel cidadeId) {
    this.cidadeId = cidadeId;
  }

  public EnderecoModel cep(String cep) {
    this.cep = cep;
    return this;
  }

  /**
   * Get cep
   * @return cep
   */
  
  @Schema(name = "cep", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("cep")
  public String getCep() {
    return cep;
  }

  public void setCep(String cep) {
    this.cep = cep;
  }

  public EnderecoModel logradouro(String logradouro) {
    this.logradouro = logradouro;
    return this;
  }

  /**
   * Get logradouro
   * @return logradouro
   */
  
  @Schema(name = "logradouro", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("logradouro")
  public String getLogradouro() {
    return logradouro;
  }

  public void setLogradouro(String logradouro) {
    this.logradouro = logradouro;
  }

  public EnderecoModel numero(String numero) {
    this.numero = numero;
    return this;
  }

  /**
   * Get numero
   * @return numero
   */
  
  @Schema(name = "numero", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("numero")
  public String getNumero() {
    return numero;
  }

  public void setNumero(String numero) {
    this.numero = numero;
  }

  public EnderecoModel complemento(String complemento) {
    this.complemento = complemento;
    return this;
  }

  /**
   * Get complemento
   * @return complemento
   */
  
  @Schema(name = "complemento", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("complemento")
  public String getComplemento() {
    return complemento;
  }

  public void setComplemento(String complemento) {
    this.complemento = complemento;
  }

  public EnderecoModel bairro(String bairro) {
    this.bairro = bairro;
    return this;
  }

  /**
   * Get bairro
   * @return bairro
   */
  
  @Schema(name = "bairro", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("bairro")
  public String getBairro() {
    return bairro;
  }

  public void setBairro(String bairro) {
    this.bairro = bairro;
  }

  public EnderecoModel latitude(BigDecimal latitude) {
    this.latitude = latitude;
    return this;
  }

  /**
   * Get latitude
   * @return latitude
   */
  @Valid 
  @Schema(name = "latitude", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("latitude")
  public BigDecimal getLatitude() {
    return latitude;
  }

  public void setLatitude(BigDecimal latitude) {
    this.latitude = latitude;
  }

  public EnderecoModel longitude(BigDecimal longitude) {
    this.longitude = longitude;
    return this;
  }

  /**
   * Get longitude
   * @return longitude
   */
  @Valid 
  @Schema(name = "longitude", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("longitude")
  public BigDecimal getLongitude() {
    return longitude;
  }

  public void setLongitude(BigDecimal longitude) {
    this.longitude = longitude;
  }

  public EnderecoModel usuarioId(Integer usuarioId) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EnderecoModel enderecoModel = (EnderecoModel) o;
    return Objects.equals(this.id, enderecoModel.id) &&
        Objects.equals(this.cidadeId, enderecoModel.cidadeId) &&
        Objects.equals(this.cep, enderecoModel.cep) &&
        Objects.equals(this.logradouro, enderecoModel.logradouro) &&
        Objects.equals(this.numero, enderecoModel.numero) &&
        Objects.equals(this.complemento, enderecoModel.complemento) &&
        Objects.equals(this.bairro, enderecoModel.bairro) &&
        Objects.equals(this.latitude, enderecoModel.latitude) &&
        Objects.equals(this.longitude, enderecoModel.longitude) &&
        Objects.equals(this.usuarioId, enderecoModel.usuarioId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, cidadeId, cep, logradouro, numero, complemento, bairro, latitude, longitude, usuarioId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EnderecoModel {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    cidadeId: ").append(toIndentedString(cidadeId)).append("\n");
    sb.append("    cep: ").append(toIndentedString(cep)).append("\n");
    sb.append("    logradouro: ").append(toIndentedString(logradouro)).append("\n");
    sb.append("    numero: ").append(toIndentedString(numero)).append("\n");
    sb.append("    complemento: ").append(toIndentedString(complemento)).append("\n");
    sb.append("    bairro: ").append(toIndentedString(bairro)).append("\n");
    sb.append("    latitude: ").append(toIndentedString(latitude)).append("\n");
    sb.append("    longitude: ").append(toIndentedString(longitude)).append("\n");
    sb.append("    usuarioId: ").append(toIndentedString(usuarioId)).append("\n");
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

