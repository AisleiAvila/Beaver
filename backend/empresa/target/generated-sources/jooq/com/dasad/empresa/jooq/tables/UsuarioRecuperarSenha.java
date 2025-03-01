/*
 * This file is generated by jOOQ.
 */
package com.dasad.empresa.jooq.tables;


import com.dasad.empresa.jooq.Keys;
import com.dasad.empresa.jooq.Public;
import com.dasad.empresa.jooq.tables.Usuario.UsuarioPath;
import com.dasad.empresa.jooq.tables.records.UsuarioRecuperarSenhaRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.InverseForeignKey;
import org.jooq.Name;
import org.jooq.Path;
import org.jooq.PlainSQL;
import org.jooq.QueryPart;
import org.jooq.Record;
import org.jooq.SQL;
import org.jooq.Schema;
import org.jooq.Select;
import org.jooq.Stringly;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class UsuarioRecuperarSenha extends TableImpl<UsuarioRecuperarSenhaRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.usuario_recuperar_senha</code>
     */
    public static final UsuarioRecuperarSenha USUARIO_RECUPERAR_SENHA = new UsuarioRecuperarSenha();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<UsuarioRecuperarSenhaRecord> getRecordType() {
        return UsuarioRecuperarSenhaRecord.class;
    }

    /**
     * The column <code>public.usuario_recuperar_senha.id</code>.
     */
    public final TableField<UsuarioRecuperarSenhaRecord, Integer> ID = createField(DSL.name("id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.usuario_recuperar_senha.usuario_id</code>.
     */
    public final TableField<UsuarioRecuperarSenhaRecord, Integer> USUARIO_ID = createField(DSL.name("usuario_id"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>public.usuario_recuperar_senha.token</code>.
     */
    public final TableField<UsuarioRecuperarSenhaRecord, String> TOKEN = createField(DSL.name("token"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>public.usuario_recuperar_senha.data_expiracao</code>.
     */
    public final TableField<UsuarioRecuperarSenhaRecord, LocalDateTime> DATA_EXPIRACAO = createField(DSL.name("data_expiracao"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "");

    /**
     * The column <code>public.usuario_recuperar_senha.usado</code>.
     */
    public final TableField<UsuarioRecuperarSenhaRecord, Boolean> USADO = createField(DSL.name("usado"), SQLDataType.BOOLEAN.defaultValue(DSL.field(DSL.raw("false"), SQLDataType.BOOLEAN)), this, "");

    private UsuarioRecuperarSenha(Name alias, Table<UsuarioRecuperarSenhaRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private UsuarioRecuperarSenha(Name alias, Table<UsuarioRecuperarSenhaRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>public.usuario_recuperar_senha</code> table
     * reference
     */
    public UsuarioRecuperarSenha(String alias) {
        this(DSL.name(alias), USUARIO_RECUPERAR_SENHA);
    }

    /**
     * Create an aliased <code>public.usuario_recuperar_senha</code> table
     * reference
     */
    public UsuarioRecuperarSenha(Name alias) {
        this(alias, USUARIO_RECUPERAR_SENHA);
    }

    /**
     * Create a <code>public.usuario_recuperar_senha</code> table reference
     */
    public UsuarioRecuperarSenha() {
        this(DSL.name("usuario_recuperar_senha"), null);
    }

    public <O extends Record> UsuarioRecuperarSenha(Table<O> path, ForeignKey<O, UsuarioRecuperarSenhaRecord> childPath, InverseForeignKey<O, UsuarioRecuperarSenhaRecord> parentPath) {
        super(path, childPath, parentPath, USUARIO_RECUPERAR_SENHA);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class UsuarioRecuperarSenhaPath extends UsuarioRecuperarSenha implements Path<UsuarioRecuperarSenhaRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> UsuarioRecuperarSenhaPath(Table<O> path, ForeignKey<O, UsuarioRecuperarSenhaRecord> childPath, InverseForeignKey<O, UsuarioRecuperarSenhaRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private UsuarioRecuperarSenhaPath(Name alias, Table<UsuarioRecuperarSenhaRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public UsuarioRecuperarSenhaPath as(String alias) {
            return new UsuarioRecuperarSenhaPath(DSL.name(alias), this);
        }

        @Override
        public UsuarioRecuperarSenhaPath as(Name alias) {
            return new UsuarioRecuperarSenhaPath(alias, this);
        }

        @Override
        public UsuarioRecuperarSenhaPath as(Table<?> alias) {
            return new UsuarioRecuperarSenhaPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public Identity<UsuarioRecuperarSenhaRecord, Integer> getIdentity() {
        return (Identity<UsuarioRecuperarSenhaRecord, Integer>) super.getIdentity();
    }

    @Override
    public List<ForeignKey<UsuarioRecuperarSenhaRecord, ?>> getReferences() {
        return Arrays.asList(Keys.USUARIO_RECUPERAR_SENHA__USUARIO_ID_FKEY);
    }

    private transient UsuarioPath _usuario;

    /**
     * Get the implicit join path to the <code>public.usuario</code> table.
     */
    public UsuarioPath usuario() {
        if (_usuario == null)
            _usuario = new UsuarioPath(this, Keys.USUARIO_RECUPERAR_SENHA__USUARIO_ID_FKEY, null);

        return _usuario;
    }

    @Override
    public UsuarioRecuperarSenha as(String alias) {
        return new UsuarioRecuperarSenha(DSL.name(alias), this);
    }

    @Override
    public UsuarioRecuperarSenha as(Name alias) {
        return new UsuarioRecuperarSenha(alias, this);
    }

    @Override
    public UsuarioRecuperarSenha as(Table<?> alias) {
        return new UsuarioRecuperarSenha(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public UsuarioRecuperarSenha rename(String name) {
        return new UsuarioRecuperarSenha(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public UsuarioRecuperarSenha rename(Name name) {
        return new UsuarioRecuperarSenha(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public UsuarioRecuperarSenha rename(Table<?> name) {
        return new UsuarioRecuperarSenha(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public UsuarioRecuperarSenha where(Condition condition) {
        return new UsuarioRecuperarSenha(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public UsuarioRecuperarSenha where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public UsuarioRecuperarSenha where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public UsuarioRecuperarSenha where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public UsuarioRecuperarSenha where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public UsuarioRecuperarSenha where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public UsuarioRecuperarSenha where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public UsuarioRecuperarSenha where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public UsuarioRecuperarSenha whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public UsuarioRecuperarSenha whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
