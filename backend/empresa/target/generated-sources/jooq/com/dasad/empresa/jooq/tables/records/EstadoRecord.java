/*
 * This file is generated by jOOQ.
 */
package com.dasad.empresa.jooq.tables.records;


import com.dasad.empresa.jooq.tables.Estado;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class EstadoRecord extends UpdatableRecordImpl<EstadoRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.estado.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.estado.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.estado.nome</code>.
     */
    public void setNome(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.estado.nome</code>.
     */
    public String getNome() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.estado.pais_id</code>.
     */
    public void setPaisId(Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.estado.pais_id</code>.
     */
    public Integer getPaisId() {
        return (Integer) get(2);
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
     * Create a detached EstadoRecord
     */
    public EstadoRecord() {
        super(Estado.ESTADO);
    }

    /**
     * Create a detached, initialised EstadoRecord
     */
    public EstadoRecord(Integer id, String nome, Integer paisId) {
        super(Estado.ESTADO);

        setId(id);
        setNome(nome);
        setPaisId(paisId);
        resetChangedOnNotNull();
    }
}
