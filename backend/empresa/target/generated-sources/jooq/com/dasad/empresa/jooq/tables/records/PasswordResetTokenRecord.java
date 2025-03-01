/*
 * This file is generated by jOOQ.
 */
package com.dasad.empresa.jooq.tables.records;


import com.dasad.empresa.jooq.tables.PasswordResetToken;

import java.time.LocalDateTime;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class PasswordResetTokenRecord extends UpdatableRecordImpl<PasswordResetTokenRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.password_reset_token.id</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.password_reset_token.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.password_reset_token.token</code>.
     */
    public void setToken(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.password_reset_token.token</code>.
     */
    public String getToken() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.password_reset_token.usuario_id</code>.
     */
    public void setUsuarioId(Long value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.password_reset_token.usuario_id</code>.
     */
    public Long getUsuarioId() {
        return (Long) get(2);
    }

    /**
     * Setter for <code>public.password_reset_token.expiry_date</code>.
     */
    public void setExpiryDate(LocalDateTime value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.password_reset_token.expiry_date</code>.
     */
    public LocalDateTime getExpiryDate() {
        return (LocalDateTime) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PasswordResetTokenRecord
     */
    public PasswordResetTokenRecord() {
        super(PasswordResetToken.PASSWORD_RESET_TOKEN);
    }

    /**
     * Create a detached, initialised PasswordResetTokenRecord
     */
    public PasswordResetTokenRecord(Long id, String token, Long usuarioId, LocalDateTime expiryDate) {
        super(PasswordResetToken.PASSWORD_RESET_TOKEN);

        setId(id);
        setToken(token);
        setUsuarioId(usuarioId);
        setExpiryDate(expiryDate);
        resetChangedOnNotNull();
    }
}
