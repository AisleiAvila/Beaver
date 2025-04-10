package com.dasad.empresa.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * SubCategoriaModel
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-04-09T15:28:55.250776700+01:00[Europe/Lisbon]", comments = "Generator version: 7.12.0")
public class SubCategoriaModel {

  private @Nullable Integer id;

  private @Nullable Integer categoriaId;

  private @Nullable String nome;

  private @Nullable String descricao;

  private @Nullable String status;

  private @Nullable Integer tempoMedioMinutos;

  private @Nullable String nivelComplexidade;

  private @Nullable BigDecimal precoBase;

  private @Nullable String unidadeMedida;

  private @Nullable String materiaisTipicos;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private @Nullable OffsetDateTime dataCriacao;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private @Nullable OffsetDateTime dataAtualizacao;

  public SubCategoriaModel id(Integer id) {
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

  public SubCategoriaModel categoriaId(Integer categoriaId) {
    this.categoriaId = categoriaId;
    return this;
  }

  /**
   * Get categoriaId
   * @return categoriaId
   */
  
  @Schema(name = "categoria_id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("categoria_id")
  public Integer getCategoriaId() {
    return categoriaId;
  }

  public void setCategoriaId(Integer categoriaId) {
    this.categoriaId = categoriaId;
  }

  public SubCategoriaModel nome(String nome) {
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

  public SubCategoriaModel descricao(String descricao) {
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

  public SubCategoriaModel status(String status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
   */
  
  @Schema(name = "status", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("status")
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public SubCategoriaModel tempoMedioMinutos(Integer tempoMedioMinutos) {
    this.tempoMedioMinutos = tempoMedioMinutos;
    return this;
  }

  /**
   * Get tempoMedioMinutos
   * @return tempoMedioMinutos
   */
  
  @Schema(name = "tempo_medio_minutos", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("tempo_medio_minutos")
  public Integer getTempoMedioMinutos() {
    return tempoMedioMinutos;
  }

  public void setTempoMedioMinutos(Integer tempoMedioMinutos) {
    this.tempoMedioMinutos = tempoMedioMinutos;
  }

  public SubCategoriaModel nivelComplexidade(String nivelComplexidade) {
    this.nivelComplexidade = nivelComplexidade;
    return this;
  }

  /**
   * Get nivelComplexidade
   * @return nivelComplexidade
   */
  
  @Schema(name = "nivel_complexidade", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("nivel_complexidade")
  public String getNivelComplexidade() {
    return nivelComplexidade;
  }

  public void setNivelComplexidade(String nivelComplexidade) {
    this.nivelComplexidade = nivelComplexidade;
  }

  public SubCategoriaModel precoBase(BigDecimal precoBase) {
    this.precoBase = precoBase;
    return this;
  }

  /**
   * Get precoBase
   * @return precoBase
   */
  @Valid 
  @Schema(name = "preco_base", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("preco_base")
  public BigDecimal getPrecoBase() {
    return precoBase;
  }

  public void setPrecoBase(BigDecimal precoBase) {
    this.precoBase = precoBase;
  }

  public SubCategoriaModel unidadeMedida(String unidadeMedida) {
    this.unidadeMedida = unidadeMedida;
    return this;
  }

  /**
   * Get unidadeMedida
   * @return unidadeMedida
   */
  
  @Schema(name = "unidade_medida", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("unidade_medida")
  public String getUnidadeMedida() {
    return unidadeMedida;
  }

  public void setUnidadeMedida(String unidadeMedida) {
    this.unidadeMedida = unidadeMedida;
  }

  public SubCategoriaModel materiaisTipicos(String materiaisTipicos) {
    this.materiaisTipicos = materiaisTipicos;
    return this;
  }

  /**
   * Get materiaisTipicos
   * @return materiaisTipicos
   */
  
  @Schema(name = "materiais_tipicos", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("materiais_tipicos")
  public String getMateriaisTipicos() {
    return materiaisTipicos;
  }

  public void setMateriaisTipicos(String materiaisTipicos) {
    this.materiaisTipicos = materiaisTipicos;
  }

  public SubCategoriaModel dataCriacao(OffsetDateTime dataCriacao) {
    this.dataCriacao = dataCriacao;
    return this;
  }

  /**
   * Get dataCriacao
   * @return dataCriacao
   */
  @Valid 
  @Schema(name = "data_criacao", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("data_criacao")
  public OffsetDateTime getDataCriacao() {
    return dataCriacao;
  }

  public void setDataCriacao(OffsetDateTime dataCriacao) {
    this.dataCriacao = dataCriacao;
  }

  public SubCategoriaModel dataAtualizacao(OffsetDateTime dataAtualizacao) {
    this.dataAtualizacao = dataAtualizacao;
    return this;
  }

  /**
   * Get dataAtualizacao
   * @return dataAtualizacao
   */
  @Valid 
  @Schema(name = "data_atualizacao", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("data_atualizacao")
  public OffsetDateTime getDataAtualizacao() {
    return dataAtualizacao;
  }

  public void setDataAtualizacao(OffsetDateTime dataAtualizacao) {
    this.dataAtualizacao = dataAtualizacao;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SubCategoriaModel subCategoriaModel = (SubCategoriaModel) o;
    return Objects.equals(this.id, subCategoriaModel.id) &&
        Objects.equals(this.categoriaId, subCategoriaModel.categoriaId) &&
        Objects.equals(this.nome, subCategoriaModel.nome) &&
        Objects.equals(this.descricao, subCategoriaModel.descricao) &&
        Objects.equals(this.status, subCategoriaModel.status) &&
        Objects.equals(this.tempoMedioMinutos, subCategoriaModel.tempoMedioMinutos) &&
        Objects.equals(this.nivelComplexidade, subCategoriaModel.nivelComplexidade) &&
        Objects.equals(this.precoBase, subCategoriaModel.precoBase) &&
        Objects.equals(this.unidadeMedida, subCategoriaModel.unidadeMedida) &&
        Objects.equals(this.materiaisTipicos, subCategoriaModel.materiaisTipicos) &&
        Objects.equals(this.dataCriacao, subCategoriaModel.dataCriacao) &&
        Objects.equals(this.dataAtualizacao, subCategoriaModel.dataAtualizacao);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, categoriaId, nome, descricao, status, tempoMedioMinutos, nivelComplexidade, precoBase, unidadeMedida, materiaisTipicos, dataCriacao, dataAtualizacao);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubCategoriaModel {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    categoriaId: ").append(toIndentedString(categoriaId)).append("\n");
    sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
    sb.append("    descricao: ").append(toIndentedString(descricao)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    tempoMedioMinutos: ").append(toIndentedString(tempoMedioMinutos)).append("\n");
    sb.append("    nivelComplexidade: ").append(toIndentedString(nivelComplexidade)).append("\n");
    sb.append("    precoBase: ").append(toIndentedString(precoBase)).append("\n");
    sb.append("    unidadeMedida: ").append(toIndentedString(unidadeMedida)).append("\n");
    sb.append("    materiaisTipicos: ").append(toIndentedString(materiaisTipicos)).append("\n");
    sb.append("    dataCriacao: ").append(toIndentedString(dataCriacao)).append("\n");
    sb.append("    dataAtualizacao: ").append(toIndentedString(dataAtualizacao)).append("\n");
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

