package com.dasad.empresa.model;

import java.net.URI;
import java.util.Objects;
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
 * ChatResponseDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-02T10:06:15.811723800Z[Europe/Lisbon]", comments = "Generator version: 7.9.0")
public class ChatResponseDTO {

  private String model;

  private String createdAt;

  private String response;

  private Boolean done;

  public ChatResponseDTO model(String model) {
    this.model = model;
    return this;
  }

  /**
   * Get model
   * @return model
   */
  
  @Schema(name = "model", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("model")
  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public ChatResponseDTO createdAt(String createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  /**
   * Get createdAt
   * @return createdAt
   */
  
  @Schema(name = "createdAt", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("createdAt")
  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public ChatResponseDTO response(String response) {
    this.response = response;
    return this;
  }

  /**
   * Get response
   * @return response
   */
  
  @Schema(name = "response", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("response")
  public String getResponse() {
    return response;
  }

  public void setResponse(String response) {
    this.response = response;
  }

  public ChatResponseDTO done(Boolean done) {
    this.done = done;
    return this;
  }

  /**
   * Get done
   * @return done
   */
  
  @Schema(name = "done", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("done")
  public Boolean getDone() {
    return done;
  }

  public void setDone(Boolean done) {
    this.done = done;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChatResponseDTO chatResponseDTO = (ChatResponseDTO) o;
    return Objects.equals(this.model, chatResponseDTO.model) &&
        Objects.equals(this.createdAt, chatResponseDTO.createdAt) &&
        Objects.equals(this.response, chatResponseDTO.response) &&
        Objects.equals(this.done, chatResponseDTO.done);
  }

  @Override
  public int hashCode() {
    return Objects.hash(model, createdAt, response, done);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ChatResponseDTO {\n");
    sb.append("    model: ").append(toIndentedString(model)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    response: ").append(toIndentedString(response)).append("\n");
    sb.append("    done: ").append(toIndentedString(done)).append("\n");
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

