package com.dasad.empresa.model;

import java.net.URI;
import java.util.Objects;
import com.dasad.empresa.model.StatusServico;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Arrays;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.format.annotation.DateTimeFormat;
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
 * CategoriaModel
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-04-05T17:52:15.110897700+01:00[Europe/Lisbon]", comments = "Generator version: 7.12.0")
public class CategoriaModel {

  private @Nullable Integer id;

  private @Nullable String nome;

  private @Nullable String descricao;

  private @Nullable StatusServico status;

  private @Nullable Boolean requerCertificacao;

  private @Nullable String tipoCertificacao;

  private @Nullable Integer experienciaMinimaMeses;

  private @Nullable String nivelRisco;

  private @Nullable Boolean seguroObrigatorio;

  private @Nullable BigDecimal valorBaseHora;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private @Nullable OffsetDateTime dataCriacao;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private @Nullable OffsetDateTime dataAtualizacao;

  private @Nullable String urlImagem;

  private @Nullable String palavrasChave;

  private @Nullable Integer horasMinimasAgendamento;

  private @Nullable Integer horasCancelamentoGratis;

  private JsonNullable<Float> percentualComissao = JsonNullable.<Float>undefined();

  private @Nullable String documentosNecessarios;

  public CategoriaModel id(Integer id) {
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

  public CategoriaModel nome(String nome) {
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

  public CategoriaModel descricao(String descricao) {
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

  public CategoriaModel status(StatusServico status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
   */
  @Valid 
  @Schema(name = "status", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("status")
  public StatusServico getStatus() {
    return status;
  }

  public void setStatus(StatusServico status) {
    this.status = status;
  }

  public CategoriaModel requerCertificacao(Boolean requerCertificacao) {
    this.requerCertificacao = requerCertificacao;
    return this;
  }

  /**
   * Get requerCertificacao
   * @return requerCertificacao
   */
  
  @Schema(name = "requer_certificacao", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("requer_certificacao")
  public Boolean getRequerCertificacao() {
    return requerCertificacao;
  }

  public void setRequerCertificacao(Boolean requerCertificacao) {
    this.requerCertificacao = requerCertificacao;
  }

  public CategoriaModel tipoCertificacao(String tipoCertificacao) {
    this.tipoCertificacao = tipoCertificacao;
    return this;
  }

  /**
   * Get tipoCertificacao
   * @return tipoCertificacao
   */
  
  @Schema(name = "tipo_certificacao", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("tipo_certificacao")
  public String getTipoCertificacao() {
    return tipoCertificacao;
  }

  public void setTipoCertificacao(String tipoCertificacao) {
    this.tipoCertificacao = tipoCertificacao;
  }

  public CategoriaModel experienciaMinimaMeses(Integer experienciaMinimaMeses) {
    this.experienciaMinimaMeses = experienciaMinimaMeses;
    return this;
  }

  /**
   * Get experienciaMinimaMeses
   * @return experienciaMinimaMeses
   */
  
  @Schema(name = "experiencia_minima_meses", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("experiencia_minima_meses")
  public Integer getExperienciaMinimaMeses() {
    return experienciaMinimaMeses;
  }

  public void setExperienciaMinimaMeses(Integer experienciaMinimaMeses) {
    this.experienciaMinimaMeses = experienciaMinimaMeses;
  }

  public CategoriaModel nivelRisco(String nivelRisco) {
    this.nivelRisco = nivelRisco;
    return this;
  }

  /**
   * Get nivelRisco
   * @return nivelRisco
   */
  
  @Schema(name = "nivel_risco", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("nivel_risco")
  public String getNivelRisco() {
    return nivelRisco;
  }

  public void setNivelRisco(String nivelRisco) {
    this.nivelRisco = nivelRisco;
  }

  public CategoriaModel seguroObrigatorio(Boolean seguroObrigatorio) {
    this.seguroObrigatorio = seguroObrigatorio;
    return this;
  }

  /**
   * Get seguroObrigatorio
   * @return seguroObrigatorio
   */
  
  @Schema(name = "seguro_obrigatorio", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("seguro_obrigatorio")
  public Boolean getSeguroObrigatorio() {
    return seguroObrigatorio;
  }

  public void setSeguroObrigatorio(Boolean seguroObrigatorio) {
    this.seguroObrigatorio = seguroObrigatorio;
  }

  public CategoriaModel valorBaseHora(BigDecimal valorBaseHora) {
    this.valorBaseHora = valorBaseHora;
    return this;
  }

  /**
   * Get valorBaseHora
   * @return valorBaseHora
   */
  @Valid 
  @Schema(name = "valor_base_hora", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("valor_base_hora")
  public BigDecimal getValorBaseHora() {
    return valorBaseHora;
  }

  public void setValorBaseHora(BigDecimal valorBaseHora) {
    this.valorBaseHora = valorBaseHora;
  }

  public CategoriaModel dataCriacao(OffsetDateTime dataCriacao) {
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

  public CategoriaModel dataAtualizacao(OffsetDateTime dataAtualizacao) {
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

  public CategoriaModel urlImagem(String urlImagem) {
    this.urlImagem = urlImagem;
    return this;
  }

  /**
   * Get urlImagem
   * @return urlImagem
   */
  
  @Schema(name = "url_imagem", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("url_imagem")
  public String getUrlImagem() {
    return urlImagem;
  }

  public void setUrlImagem(String urlImagem) {
    this.urlImagem = urlImagem;
  }

  public CategoriaModel palavrasChave(String palavrasChave) {
    this.palavrasChave = palavrasChave;
    return this;
  }

  /**
   * Get palavrasChave
   * @return palavrasChave
   */
  
  @Schema(name = "palavras_chave", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("palavras_chave")
  public String getPalavrasChave() {
    return palavrasChave;
  }

  public void setPalavrasChave(String palavrasChave) {
    this.palavrasChave = palavrasChave;
  }

  public CategoriaModel horasMinimasAgendamento(Integer horasMinimasAgendamento) {
    this.horasMinimasAgendamento = horasMinimasAgendamento;
    return this;
  }

  /**
   * Get horasMinimasAgendamento
   * @return horasMinimasAgendamento
   */
  
  @Schema(name = "horas_minimas_agendamento", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("horas_minimas_agendamento")
  public Integer getHorasMinimasAgendamento() {
    return horasMinimasAgendamento;
  }

  public void setHorasMinimasAgendamento(Integer horasMinimasAgendamento) {
    this.horasMinimasAgendamento = horasMinimasAgendamento;
  }

  public CategoriaModel horasCancelamentoGratis(Integer horasCancelamentoGratis) {
    this.horasCancelamentoGratis = horasCancelamentoGratis;
    return this;
  }

  /**
   * Get horasCancelamentoGratis
   * @return horasCancelamentoGratis
   */
  
  @Schema(name = "horas_cancelamento_gratis", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("horas_cancelamento_gratis")
  public Integer getHorasCancelamentoGratis() {
    return horasCancelamentoGratis;
  }

  public void setHorasCancelamentoGratis(Integer horasCancelamentoGratis) {
    this.horasCancelamentoGratis = horasCancelamentoGratis;
  }

  public CategoriaModel percentualComissao(Float percentualComissao) {
    this.percentualComissao = JsonNullable.of(percentualComissao);
    return this;
  }

  /**
   * Get percentualComissao
   * @return percentualComissao
   */
  
  @Schema(name = "percentual_comissao", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("percentual_comissao")
  public JsonNullable<Float> getPercentualComissao() {
    return percentualComissao;
  }

  public void setPercentualComissao(JsonNullable<Float> percentualComissao) {
    this.percentualComissao = percentualComissao;
  }

  public CategoriaModel documentosNecessarios(String documentosNecessarios) {
    this.documentosNecessarios = documentosNecessarios;
    return this;
  }

  /**
   * Get documentosNecessarios
   * @return documentosNecessarios
   */
  
  @Schema(name = "documentos_necessarios", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("documentos_necessarios")
  public String getDocumentosNecessarios() {
    return documentosNecessarios;
  }

  public void setDocumentosNecessarios(String documentosNecessarios) {
    this.documentosNecessarios = documentosNecessarios;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CategoriaModel categoriaModel = (CategoriaModel) o;
    return Objects.equals(this.id, categoriaModel.id) &&
        Objects.equals(this.nome, categoriaModel.nome) &&
        Objects.equals(this.descricao, categoriaModel.descricao) &&
        Objects.equals(this.status, categoriaModel.status) &&
        Objects.equals(this.requerCertificacao, categoriaModel.requerCertificacao) &&
        Objects.equals(this.tipoCertificacao, categoriaModel.tipoCertificacao) &&
        Objects.equals(this.experienciaMinimaMeses, categoriaModel.experienciaMinimaMeses) &&
        Objects.equals(this.nivelRisco, categoriaModel.nivelRisco) &&
        Objects.equals(this.seguroObrigatorio, categoriaModel.seguroObrigatorio) &&
        Objects.equals(this.valorBaseHora, categoriaModel.valorBaseHora) &&
        Objects.equals(this.dataCriacao, categoriaModel.dataCriacao) &&
        Objects.equals(this.dataAtualizacao, categoriaModel.dataAtualizacao) &&
        Objects.equals(this.urlImagem, categoriaModel.urlImagem) &&
        Objects.equals(this.palavrasChave, categoriaModel.palavrasChave) &&
        Objects.equals(this.horasMinimasAgendamento, categoriaModel.horasMinimasAgendamento) &&
        Objects.equals(this.horasCancelamentoGratis, categoriaModel.horasCancelamentoGratis) &&
        equalsNullable(this.percentualComissao, categoriaModel.percentualComissao) &&
        Objects.equals(this.documentosNecessarios, categoriaModel.documentosNecessarios);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nome, descricao, status, requerCertificacao, tipoCertificacao, experienciaMinimaMeses, nivelRisco, seguroObrigatorio, valorBaseHora, dataCriacao, dataAtualizacao, urlImagem, palavrasChave, horasMinimasAgendamento, horasCancelamentoGratis, hashCodeNullable(percentualComissao), documentosNecessarios);
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
    sb.append("class CategoriaModel {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
    sb.append("    descricao: ").append(toIndentedString(descricao)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    requerCertificacao: ").append(toIndentedString(requerCertificacao)).append("\n");
    sb.append("    tipoCertificacao: ").append(toIndentedString(tipoCertificacao)).append("\n");
    sb.append("    experienciaMinimaMeses: ").append(toIndentedString(experienciaMinimaMeses)).append("\n");
    sb.append("    nivelRisco: ").append(toIndentedString(nivelRisco)).append("\n");
    sb.append("    seguroObrigatorio: ").append(toIndentedString(seguroObrigatorio)).append("\n");
    sb.append("    valorBaseHora: ").append(toIndentedString(valorBaseHora)).append("\n");
    sb.append("    dataCriacao: ").append(toIndentedString(dataCriacao)).append("\n");
    sb.append("    dataAtualizacao: ").append(toIndentedString(dataAtualizacao)).append("\n");
    sb.append("    urlImagem: ").append(toIndentedString(urlImagem)).append("\n");
    sb.append("    palavrasChave: ").append(toIndentedString(palavrasChave)).append("\n");
    sb.append("    horasMinimasAgendamento: ").append(toIndentedString(horasMinimasAgendamento)).append("\n");
    sb.append("    horasCancelamentoGratis: ").append(toIndentedString(horasCancelamentoGratis)).append("\n");
    sb.append("    percentualComissao: ").append(toIndentedString(percentualComissao)).append("\n");
    sb.append("    documentosNecessarios: ").append(toIndentedString(documentosNecessarios)).append("\n");
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

