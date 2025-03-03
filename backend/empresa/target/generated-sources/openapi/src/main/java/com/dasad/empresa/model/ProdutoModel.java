package com.dasad.empresa.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.math.BigDecimal;
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
 * ProdutoModel
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-02T10:06:15.811723800Z[Europe/Lisbon]", comments = "Generator version: 7.9.0")
public class ProdutoModel {

  private Integer id;

  private String nome;

  private String descricao;

  private BigDecimal preco;

  private String categoria;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private JsonNullable<LocalDate> dataCadastro = JsonNullable.<LocalDate>undefined();

  public ProdutoModel id(Integer id) {
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

  public ProdutoModel nome(String nome) {
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

  public ProdutoModel descricao(String descricao) {
    this.descricao = descricao;
    return this;
  }

  /**
   * Get descricao
   * @return descricao
   */
  
  @Schema(name = "descricao", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("descricao")
  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public ProdutoModel preco(BigDecimal preco) {
    this.preco = preco;
    return this;
  }

  /**
   * Get preco
   * @return preco
   */
  @Valid 
  @Schema(name = "preco", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("preco")
  public BigDecimal getPreco() {
    return preco;
  }

  public void setPreco(BigDecimal preco) {
    this.preco = preco;
  }

  public ProdutoModel categoria(String categoria) {
    this.categoria = categoria;
    return this;
  }

  /**
   * Get categoria
   * @return categoria
   */
  
  @Schema(name = "categoria", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("categoria")
  public String getCategoria() {
    return categoria;
  }

  public void setCategoria(String categoria) {
    this.categoria = categoria;
  }

  public ProdutoModel dataCadastro(LocalDate dataCadastro) {
    this.dataCadastro = JsonNullable.of(dataCadastro);
    return this;
  }

  /**
   * Get dataCadastro
   * @return dataCadastro
   */
  @Valid 
  @Schema(name = "dataCadastro", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("dataCadastro")
  public JsonNullable<LocalDate> getDataCadastro() {
    return dataCadastro;
  }

  public void setDataCadastro(JsonNullable<LocalDate> dataCadastro) {
    this.dataCadastro = dataCadastro;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProdutoModel produtoModel = (ProdutoModel) o;
    return Objects.equals(this.id, produtoModel.id) &&
        Objects.equals(this.nome, produtoModel.nome) &&
        Objects.equals(this.descricao, produtoModel.descricao) &&
        Objects.equals(this.preco, produtoModel.preco) &&
        Objects.equals(this.categoria, produtoModel.categoria) &&
        equalsNullable(this.dataCadastro, produtoModel.dataCadastro);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nome, descricao, preco, categoria, hashCodeNullable(dataCadastro));
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
    sb.append("class ProdutoModel {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
    sb.append("    descricao: ").append(toIndentedString(descricao)).append("\n");
    sb.append("    preco: ").append(toIndentedString(preco)).append("\n");
    sb.append("    categoria: ").append(toIndentedString(categoria)).append("\n");
    sb.append("    dataCadastro: ").append(toIndentedString(dataCadastro)).append("\n");
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

