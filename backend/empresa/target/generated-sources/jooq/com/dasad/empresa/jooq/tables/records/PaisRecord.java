/*
 * This file is generated by jOOQ.
 */
package com.dasad.empresa.jooq.tables.records;


import com.dasad.empresa.jooq.tables.Pais;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class PaisRecord extends UpdatableRecordImpl<PaisRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.pais.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.pais.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.pais.nome</code>.
     */
    public void setNome(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.pais.nome</code>.
     */
    public String getNome() {
        return (String) get(1);
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
     * Create a detached PaisRecord
     */
    public PaisRecord() {
        super(Pais.PAIS);
    }

    /**
     * Create a detached, initialised PaisRecord
     */
    public PaisRecord(Integer id, String nome) {
        super(Pais.PAIS);

        setId(id);
        setNome(nome);
        resetChangedOnNotNull();
    }
}
