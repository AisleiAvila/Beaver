/*
 * This file is generated by jOOQ.
 */
package com.dasad.empresa.jooq.tables.records;


import com.dasad.empresa.jooq.tables.Subcategorias;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * Tabela de subcategorias de serviços vinculadas às categorias
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class SubcategoriasRecord extends UpdatableRecordImpl<SubcategoriasRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.subcategorias.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.subcategorias.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.subcategorias.categoria_id</code>.
     */
    public void setCategoriaId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.subcategorias.categoria_id</code>.
     */
    public Integer getCategoriaId() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>public.subcategorias.nome</code>.
     */
    public void setNome(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.subcategorias.nome</code>.
     */
    public String getNome() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.subcategorias.descricao</code>.
     */
    public void setDescricao(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.subcategorias.descricao</code>.
     */
    public String getDescricao() {
        return (String) get(3);
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
        set(4, value);
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
        return get(4);
    }

    /**
     * Setter for <code>public.subcategorias.tempo_medio_minutos</code>.
     */
    public void setTempoMedioMinutos(Integer value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.subcategorias.tempo_medio_minutos</code>.
     */
    public Integer getTempoMedioMinutos() {
        return (Integer) get(5);
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
    public void setNivelComplexidade(Object value) {
        set(6, value);
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
    public Object getNivelComplexidade() {
        return get(6);
    }

    /**
     * Setter for <code>public.subcategorias.preco_base</code>.
     */
    public void setPrecoBase(BigDecimal value) {
        set(7, value);
    }

    /**
     * Getter for <code>public.subcategorias.preco_base</code>.
     */
    public BigDecimal getPrecoBase() {
        return (BigDecimal) get(7);
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
    public void setUnidadeMedida(Object value) {
        set(8, value);
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
    public Object getUnidadeMedida() {
        return get(8);
    }

    /**
     * Setter for <code>public.subcategorias.materiais_tipicos</code>.
     */
    public void setMateriaisTipicos(String[] value) {
        set(9, value);
    }

    /**
     * Getter for <code>public.subcategorias.materiais_tipicos</code>.
     */
    public String[] getMateriaisTipicos() {
        return (String[]) get(9);
    }

    /**
     * Setter for <code>public.subcategorias.data_criacao</code>.
     */
    public void setDataCriacao(LocalDateTime value) {
        set(10, value);
    }

    /**
     * Getter for <code>public.subcategorias.data_criacao</code>.
     */
    public LocalDateTime getDataCriacao() {
        return (LocalDateTime) get(10);
    }

    /**
     * Setter for <code>public.subcategorias.data_atualizacao</code>.
     */
    public void setDataAtualizacao(LocalDateTime value) {
        set(11, value);
    }

    /**
     * Getter for <code>public.subcategorias.data_atualizacao</code>.
     */
    public LocalDateTime getDataAtualizacao() {
        return (LocalDateTime) get(11);
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
     * Create a detached SubcategoriasRecord
     */
    public SubcategoriasRecord() {
        super(Subcategorias.SUBCATEGORIAS);
    }

    /**
     * Create a detached, initialised SubcategoriasRecord
     */
    public SubcategoriasRecord(Integer id, Integer categoriaId, String nome, String descricao, Object status, Integer tempoMedioMinutos, Object nivelComplexidade, BigDecimal precoBase, Object unidadeMedida, String[] materiaisTipicos, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {
        super(Subcategorias.SUBCATEGORIAS);

        setId(id);
        setCategoriaId(categoriaId);
        setNome(nome);
        setDescricao(descricao);
        setStatus(status);
        setTempoMedioMinutos(tempoMedioMinutos);
        setNivelComplexidade(nivelComplexidade);
        setPrecoBase(precoBase);
        setUnidadeMedida(unidadeMedida);
        setMateriaisTipicos(materiaisTipicos);
        setDataCriacao(dataCriacao);
        setDataAtualizacao(dataAtualizacao);
        resetChangedOnNotNull();
    }
}
