/*
 * This file is generated by jOOQ.
 */
package com.dasad.empresa.jooq.tables.records;


import com.dasad.empresa.jooq.tables.Categorias;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * Tabela principal de categorias de serviços
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class CategoriasRecord extends UpdatableRecordImpl<CategoriasRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.categorias.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.categorias.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.categorias.nome</code>.
     */
    public void setNome(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.categorias.nome</code>.
     */
    public String getNome() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.categorias.descricao</code>.
     */
    public void setDescricao(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.categorias.descricao</code>.
     */
    public String getDescricao() {
        return (String) get(2);
    }

    /**
     * @deprecated Unknown data type. If this is a qualified, user-defined type,
     * it may have been excluded from code generation. If this is a built-in
     * type, you can define an explicit {@link org.jooq.Binding} to specify how
     * this type should be handled. Deprecation can be turned off using
     * {@literal <deprecationOnUnknownTypes/>} in your code generator
     * configuration.
     */
    @Deprecated
    public void setStatus(Object value) {
        set(3, value);
    }

    /**
     * @deprecated Unknown data type. If this is a qualified, user-defined type,
     * it may have been excluded from code generation. If this is a built-in
     * type, you can define an explicit {@link org.jooq.Binding} to specify how
     * this type should be handled. Deprecation can be turned off using
     * {@literal <deprecationOnUnknownTypes/>} in your code generator
     * configuration.
     */
    @Deprecated
    public Object getStatus() {
        return get(3);
    }

    /**
     * Setter for <code>public.categorias.requer_certificacao</code>.
     */
    public void setRequerCertificacao(Boolean value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.categorias.requer_certificacao</code>.
     */
    public Boolean getRequerCertificacao() {
        return (Boolean) get(4);
    }

    /**
     * Setter for <code>public.categorias.tipo_certificacao</code>.
     */
    public void setTipoCertificacao(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.categorias.tipo_certificacao</code>.
     */
    public String getTipoCertificacao() {
        return (String) get(5);
    }

    /**
     * Setter for <code>public.categorias.experiencia_minima_meses</code>.
     */
    public void setExperienciaMinimaMeses(Integer value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.categorias.experiencia_minima_meses</code>.
     */
    public Integer getExperienciaMinimaMeses() {
        return (Integer) get(6);
    }

    /**
     * @deprecated Unknown data type. If this is a qualified, user-defined type,
     * it may have been excluded from code generation. If this is a built-in
     * type, you can define an explicit {@link org.jooq.Binding} to specify how
     * this type should be handled. Deprecation can be turned off using
     * {@literal <deprecationOnUnknownTypes/>} in your code generator
     * configuration.
     */
    @Deprecated
    public void setNivelRisco(Object value) {
        set(7, value);
    }

    /**
     * @deprecated Unknown data type. If this is a qualified, user-defined type,
     * it may have been excluded from code generation. If this is a built-in
     * type, you can define an explicit {@link org.jooq.Binding} to specify how
     * this type should be handled. Deprecation can be turned off using
     * {@literal <deprecationOnUnknownTypes/>} in your code generator
     * configuration.
     */
    @Deprecated
    public Object getNivelRisco() {
        return get(7);
    }

    /**
     * Setter for <code>public.categorias.seguro_obrigatorio</code>.
     */
    public void setSeguroObrigatorio(Boolean value) {
        set(8, value);
    }

    /**
     * Getter for <code>public.categorias.seguro_obrigatorio</code>.
     */
    public Boolean getSeguroObrigatorio() {
        return (Boolean) get(8);
    }

    /**
     * Setter for <code>public.categorias.valor_base_hora</code>.
     */
    public void setValorBaseHora(BigDecimal value) {
        set(9, value);
    }

    /**
     * Getter for <code>public.categorias.valor_base_hora</code>.
     */
    public BigDecimal getValorBaseHora() {
        return (BigDecimal) get(9);
    }

    /**
     * Setter for <code>public.categorias.data_criacao</code>.
     */
    public void setDataCriacao(LocalDateTime value) {
        set(10, value);
    }

    /**
     * Getter for <code>public.categorias.data_criacao</code>.
     */
    public LocalDateTime getDataCriacao() {
        return (LocalDateTime) get(10);
    }

    /**
     * Setter for <code>public.categorias.data_atualizacao</code>.
     */
    public void setDataAtualizacao(LocalDateTime value) {
        set(11, value);
    }

    /**
     * Getter for <code>public.categorias.data_atualizacao</code>.
     */
    public LocalDateTime getDataAtualizacao() {
        return (LocalDateTime) get(11);
    }

    /**
     * Setter for <code>public.categorias.url_imagem</code>.
     */
    public void setUrlImagem(String value) {
        set(12, value);
    }

    /**
     * Getter for <code>public.categorias.url_imagem</code>.
     */
    public String getUrlImagem() {
        return (String) get(12);
    }

    /**
     * Setter for <code>public.categorias.palavras_chave</code>.
     */
    public void setPalavrasChave(String[] value) {
        set(13, value);
    }

    /**
     * Getter for <code>public.categorias.palavras_chave</code>.
     */
    public String[] getPalavrasChave() {
        return (String[]) get(13);
    }

    /**
     * Setter for <code>public.categorias.horas_minimas_agendamento</code>.
     */
    public void setHorasMinimasAgendamento(Integer value) {
        set(14, value);
    }

    /**
     * Getter for <code>public.categorias.horas_minimas_agendamento</code>.
     */
    public Integer getHorasMinimasAgendamento() {
        return (Integer) get(14);
    }

    /**
     * Setter for <code>public.categorias.horas_cancelamento_gratis</code>.
     */
    public void setHorasCancelamentoGratis(Integer value) {
        set(15, value);
    }

    /**
     * Getter for <code>public.categorias.horas_cancelamento_gratis</code>.
     */
    public Integer getHorasCancelamentoGratis() {
        return (Integer) get(15);
    }

    /**
     * Setter for <code>public.categorias.percentual_comissao</code>.
     */
    public void setPercentualComissao(BigDecimal value) {
        set(16, value);
    }

    /**
     * Getter for <code>public.categorias.percentual_comissao</code>.
     */
    public BigDecimal getPercentualComissao() {
        return (BigDecimal) get(16);
    }

    /**
     * Setter for <code>public.categorias.documentos_necessarios</code>.
     */
    public void setDocumentosNecessarios(String[] value) {
        set(17, value);
    }

    /**
     * Getter for <code>public.categorias.documentos_necessarios</code>.
     */
    public String[] getDocumentosNecessarios() {
        return (String[]) get(17);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CategoriasRecord
     */
    public CategoriasRecord() {
        super(Categorias.CATEGORIAS);
    }

    /**
     * Create a detached, initialised CategoriasRecord
     */
    public CategoriasRecord(Integer id, String nome, String descricao, Object status, Boolean requerCertificacao, String tipoCertificacao, Integer experienciaMinimaMeses, Object nivelRisco, Boolean seguroObrigatorio, BigDecimal valorBaseHora, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao, String urlImagem, String[] palavrasChave, Integer horasMinimasAgendamento, Integer horasCancelamentoGratis, BigDecimal percentualComissao, String[] documentosNecessarios) {
        super(Categorias.CATEGORIAS);

        setId(id);
        setNome(nome);
        setDescricao(descricao);
        setStatus(status);
        setRequerCertificacao(requerCertificacao);
        setTipoCertificacao(tipoCertificacao);
        setExperienciaMinimaMeses(experienciaMinimaMeses);
        setNivelRisco(nivelRisco);
        setSeguroObrigatorio(seguroObrigatorio);
        setValorBaseHora(valorBaseHora);
        setDataCriacao(dataCriacao);
        setDataAtualizacao(dataAtualizacao);
        setUrlImagem(urlImagem);
        setPalavrasChave(palavrasChave);
        setHorasMinimasAgendamento(horasMinimasAgendamento);
        setHorasCancelamentoGratis(horasCancelamentoGratis);
        setPercentualComissao(percentualComissao);
        setDocumentosNecessarios(documentosNecessarios);
        resetChangedOnNotNull();
    }
}
