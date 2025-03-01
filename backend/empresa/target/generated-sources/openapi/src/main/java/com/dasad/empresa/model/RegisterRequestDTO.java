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
 * RegisterRequestDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-02-27T20:37:40.910467600Z[Europe/Lisbon]", comments = "Generator version: 7.9.0")
public class RegisterRequestDTO {

  private Integer id;

  private String nome;

  private String email;

  private String senha;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate dataNascimento;

  @Valid
  private List<@Valid EnderecoModel> enderecos = new ArrayList<>();

  @Valid
  private List<@Valid PerfilModel> perfis = new ArrayList<>();

  private byte[] fotoPerfil;

  public RegisterRequestDTO id(Integer id) {
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

  public RegisterRequestDTO nome(String nome) {
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

  public RegisterRequestDTO email(String email) {
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

  public RegisterRequestDTO senha(String senha) {
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

  public RegisterRequestDTO dataNascimento(LocalDate dataNascimento) {
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

  public RegisterRequestDTO enderecos(List<@Valid EnderecoModel> enderecos) {
    this.enderecos = enderecos;
    return this;
  }

  public RegisterRequestDTO addEnderecosItem(EnderecoModel enderecosItem) {
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

  public RegisterRequestDTO perfis(List<@Valid PerfilModel> perfis) {
    this.perfis = perfis;
    return this;
  }

  public RegisterRequestDTO addPerfisItem(PerfilModel perfisItem) {
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

  public RegisterRequestDTO fotoPerfil(byte[] fotoPerfil) {
    this.fotoPerfil = fotoPerfil;
    return this;
  }

  /**
   * Get fotoPerfil
   * @return fotoPerfil
   */
  
  @Schema(name = "foto_perfil", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("foto_perfil")
  public byte[] getFotoPerfil() {
    return fotoPerfil;
  }

  public void setFotoPerfil(byte[] fotoPerfil) {
    this.fotoPerfil = fotoPerfil;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RegisterRequestDTO registerRequestDTO = (RegisterRequestDTO) o;
    return Objects.equals(this.id, registerRequestDTO.id) &&
        Objects.equals(this.nome, registerRequestDTO.nome) &&
        Objects.equals(this.email, registerRequestDTO.email) &&
        Objects.equals(this.senha, registerRequestDTO.senha) &&
        Objects.equals(this.dataNascimento, registerRequestDTO.dataNascimento) &&
        Objects.equals(this.enderecos, registerRequestDTO.enderecos) &&
        Objects.equals(this.perfis, registerRequestDTO.perfis) &&
        Arrays.equals(this.fotoPerfil, registerRequestDTO.fotoPerfil);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nome, email, senha, dataNascimento, enderecos, perfis, Arrays.hashCode(fotoPerfil));
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RegisterRequestDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    senha: ").append(toIndentedString(senha)).append("\n");
    sb.append("    dataNascimento: ").append(toIndentedString(dataNascimento)).append("\n");
    sb.append("    enderecos: ").append(toIndentedString(enderecos)).append("\n");
    sb.append("    perfis: ").append(toIndentedString(perfis)).append("\n");
    sb.append("    fotoPerfil: ").append(toIndentedString(fotoPerfil)).append("\n");
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

