package com.dasad.empresa.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Arrays;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.lang.Nullable;
import java.util.NoSuchElementException;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * LoginRequestDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-05T12:52:25.659801500+01:00[Europe/Lisbon]", comments = "Generator version: 7.12.0")
public class LoginRequestDTO {

  private @Nullable String email;

  private @Nullable String senha;

  private JsonNullable<Integer> organizacaoId = JsonNullable.<Integer>undefined();

  public LoginRequestDTO email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
   */
  @jakarta.validation.constraints.Email 
  @Schema(name = "email", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("email")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public LoginRequestDTO senha(String senha) {
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

  public LoginRequestDTO organizacaoId(Integer organizacaoId) {
    this.organizacaoId = JsonNullable.of(organizacaoId);
    return this;
  }

  /**
   * Get organizacaoId
   * @return organizacaoId
   */
  
  @Schema(name = "organizacaoId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("organizacaoId")
  public JsonNullable<Integer> getOrganizacaoId() {
    return organizacaoId;
  }

  public void setOrganizacaoId(JsonNullable<Integer> organizacaoId) {
    this.organizacaoId = organizacaoId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LoginRequestDTO loginRequestDTO = (LoginRequestDTO) o;
    return Objects.equals(this.email, loginRequestDTO.email) &&
        Objects.equals(this.senha, loginRequestDTO.senha) &&
        equalsNullable(this.organizacaoId, loginRequestDTO.organizacaoId);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, senha, hashCodeNullable(organizacaoId));
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
    sb.append("class LoginRequestDTO {\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    senha: ").append(toIndentedString(senha)).append("\n");
    sb.append("    organizacaoId: ").append(toIndentedString(organizacaoId)).append("\n");
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

