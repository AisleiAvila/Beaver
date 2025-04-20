package com.dasad.empresa.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets StatusServico
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-04-17T13:03:56.201480700+01:00[Europe/Lisbon]", comments = "Generator version: 7.12.0")
public enum StatusServico {
  
  ATIVO("ATIVO"),
  
  INATIVO("INATIVO"),
  
  SUSPENSO("SUSPENSO");

  private String value;

  StatusServico(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static StatusServico fromValue(String value) {
    for (StatusServico b : StatusServico.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

