package com.dasad.empresa.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * SalvarSenhaRequest
 */

@JsonTypeName("salvarSenha_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-02T10:06:15.811723800Z[Europe/Lisbon]", comments = "Generator version: 7.9.0")
public class SalvarSenhaRequest {

  private String email;

  private String senha;

  private String repeatSenha;

  private String token;

  public SalvarSenhaRequest email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
   */
  @jakarta.validation.constraints.Email 
  @Schema(name = "email", example = "usuario@exemplo.com", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("email")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public SalvarSenhaRequest senha(String senha) {
    this.senha = senha;
    return this;
  }

  /**
   * Get senha
   * @return senha
   */
  
  @Schema(name = "senha", example = "novaSenha", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("senha")
  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public SalvarSenhaRequest repeatSenha(String repeatSenha) {
    this.repeatSenha = repeatSenha;
    return this;
  }

  /**
   * Get repeatSenha
   * @return repeatSenha
   */
  
  @Schema(name = "repeatSenha", example = "novaSenha", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("repeatSenha")
  public String getRepeatSenha() {
    return repeatSenha;
  }

  public void setRepeatSenha(String repeatSenha) {
    this.repeatSenha = repeatSenha;
  }

  public SalvarSenhaRequest token(String token) {
    this.token = token;
    return this;
  }

  /**
   * Get token
   * @return token
   */
  
  @Schema(name = "token", example = "eyJhbGciOi", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("token")
  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SalvarSenhaRequest salvarSenhaRequest = (SalvarSenhaRequest) o;
    return Objects.equals(this.email, salvarSenhaRequest.email) &&
        Objects.equals(this.senha, salvarSenhaRequest.senha) &&
        Objects.equals(this.repeatSenha, salvarSenhaRequest.repeatSenha) &&
        Objects.equals(this.token, salvarSenhaRequest.token);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, senha, repeatSenha, token);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SalvarSenhaRequest {\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    senha: ").append(toIndentedString(senha)).append("\n");
    sb.append("    repeatSenha: ").append(toIndentedString(repeatSenha)).append("\n");
    sb.append("    token: ").append(toIndentedString(token)).append("\n");
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

