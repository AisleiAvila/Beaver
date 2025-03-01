package com.dasad.empresa.model;

import java.net.URI;
import java.util.Objects;
import com.dasad.empresa.model.UsuarioModel;
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
 * UsuarioResponseDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-02-27T20:37:40.910467600Z[Europe/Lisbon]", comments = "Generator version: 7.9.0")
public class UsuarioResponseDTO {

  @Valid
  private List<@Valid UsuarioModel> usuarios = new ArrayList<>();

  private Integer totalRecords;

  public UsuarioResponseDTO usuarios(List<@Valid UsuarioModel> usuarios) {
    this.usuarios = usuarios;
    return this;
  }

  public UsuarioResponseDTO addUsuariosItem(UsuarioModel usuariosItem) {
    if (this.usuarios == null) {
      this.usuarios = new ArrayList<>();
    }
    this.usuarios.add(usuariosItem);
    return this;
  }

  /**
   * Get usuarios
   * @return usuarios
   */
  @Valid 
  @Schema(name = "usuarios", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("usuarios")
  public List<@Valid UsuarioModel> getUsuarios() {
    return usuarios;
  }

  public void setUsuarios(List<@Valid UsuarioModel> usuarios) {
    this.usuarios = usuarios;
  }

  public UsuarioResponseDTO totalRecords(Integer totalRecords) {
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
    UsuarioResponseDTO usuarioResponseDTO = (UsuarioResponseDTO) o;
    return Objects.equals(this.usuarios, usuarioResponseDTO.usuarios) &&
        Objects.equals(this.totalRecords, usuarioResponseDTO.totalRecords);
  }

  @Override
  public int hashCode() {
    return Objects.hash(usuarios, totalRecords);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UsuarioResponseDTO {\n");
    sb.append("    usuarios: ").append(toIndentedString(usuarios)).append("\n");
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

