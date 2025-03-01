package com.dasad.empresa.model;

import java.net.URI;
import java.util.Objects;
import com.dasad.empresa.model.ProdutoModel;
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
 * ProdutoResponseDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-02-27T20:37:40.910467600Z[Europe/Lisbon]", comments = "Generator version: 7.9.0")
public class ProdutoResponseDTO {

  @Valid
  private List<@Valid ProdutoModel> produtos = new ArrayList<>();

  private Integer totalRecords;

  public ProdutoResponseDTO produtos(List<@Valid ProdutoModel> produtos) {
    this.produtos = produtos;
    return this;
  }

  public ProdutoResponseDTO addProdutosItem(ProdutoModel produtosItem) {
    if (this.produtos == null) {
      this.produtos = new ArrayList<>();
    }
    this.produtos.add(produtosItem);
    return this;
  }

  /**
   * Get produtos
   * @return produtos
   */
  @Valid 
  @Schema(name = "produtos", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("produtos")
  public List<@Valid ProdutoModel> getProdutos() {
    return produtos;
  }

  public void setProdutos(List<@Valid ProdutoModel> produtos) {
    this.produtos = produtos;
  }

  public ProdutoResponseDTO totalRecords(Integer totalRecords) {
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
    ProdutoResponseDTO produtoResponseDTO = (ProdutoResponseDTO) o;
    return Objects.equals(this.produtos, produtoResponseDTO.produtos) &&
        Objects.equals(this.totalRecords, produtoResponseDTO.totalRecords);
  }

  @Override
  public int hashCode() {
    return Objects.hash(produtos, totalRecords);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProdutoResponseDTO {\n");
    sb.append("    produtos: ").append(toIndentedString(produtos)).append("\n");
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

