package com.dasad.empresa.model;

import java.net.URI;
import java.util.Objects;
import com.dasad.empresa.model.EnderecoModel;
import com.dasad.empresa.model.PerfilModel;
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
 * UsuarioModel
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-02T10:06:15.811723800Z[Europe/Lisbon]", comments = "Generator version: 7.9.0")
public class UsuarioModel {

  private Integer id;

  private String nome;

  private String senha;

  private String email;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate dataNascimento;

  @Valid
  private List<@Valid EnderecoModel> enderecos = new ArrayList<>();

  @Valid
  private List<@Valid PerfilModel> perfis = new ArrayList<>();

  public UsuarioModel id(Integer id) {
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

  public UsuarioModel nome(String nome) {
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

  public UsuarioModel senha(String senha) {
    this.senha = senha;
    return this;
  }

  /**
   * Get senha
   * @return senha
   */
  
  @Schema(name = "senha", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("senha")
  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public UsuarioModel email(String email) {
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

  public UsuarioModel dataNascimento(LocalDate dataNascimento) {
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

  public UsuarioModel enderecos(List<@Valid EnderecoModel> enderecos) {
    this.enderecos = enderecos;
    return this;
  }

  public UsuarioModel addEnderecosItem(EnderecoModel enderecosItem) {
    if (this.enderecos == null) {
      this.enderecos = new ArrayList<>();
    }
    this.enderecos.add(enderecosItem);
    return this;
  }

  /**
   * Get enderecos
   * @return enderecos
   */
  @Valid 
  @Schema(name = "enderecos", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("enderecos")
  public List<@Valid EnderecoModel> getEnderecos() {
    return enderecos;
  }

  public void setEnderecos(List<@Valid EnderecoModel> enderecos) {
    this.enderecos = enderecos;
  }

  public UsuarioModel perfis(List<@Valid PerfilModel> perfis) {
    this.perfis = perfis;
    return this;
  }

  public UsuarioModel addPerfisItem(PerfilModel perfisItem) {
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
  @Valid 
  @Schema(name = "perfis", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("perfis")
  public List<@Valid PerfilModel> getPerfis() {
    return perfis;
  }

  public void setPerfis(List<@Valid PerfilModel> perfis) {
    this.perfis = perfis;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UsuarioModel usuarioModel = (UsuarioModel) o;
    return Objects.equals(this.id, usuarioModel.id) &&
        Objects.equals(this.nome, usuarioModel.nome) &&
        Objects.equals(this.senha, usuarioModel.senha) &&
        Objects.equals(this.email, usuarioModel.email) &&
        Objects.equals(this.dataNascimento, usuarioModel.dataNascimento) &&
        Objects.equals(this.enderecos, usuarioModel.enderecos) &&
        Objects.equals(this.perfis, usuarioModel.perfis);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nome, senha, email, dataNascimento, enderecos, perfis);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UsuarioModel {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
    sb.append("    senha: ").append(toIndentedString(senha)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    dataNascimento: ").append(toIndentedString(dataNascimento)).append("\n");
    sb.append("    enderecos: ").append(toIndentedString(enderecos)).append("\n");
    sb.append("    perfis: ").append(toIndentedString(perfis)).append("\n");
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

