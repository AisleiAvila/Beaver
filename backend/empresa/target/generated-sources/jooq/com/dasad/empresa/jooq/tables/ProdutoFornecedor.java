/*
 * This file is generated by jOOQ.
 */
package com.dasad.empresa.jooq.tables;


import com.dasad.empresa.jooq.Keys;
import com.dasad.empresa.jooq.Public;
import com.dasad.empresa.jooq.tables.Fornecedor.FornecedorPath;
import com.dasad.empresa.jooq.tables.Produto.ProdutoPath;
import com.dasad.empresa.jooq.tables.records.ProdutoFornecedorRecord;

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
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class ProdutoFornecedor extends TableImpl<ProdutoFornecedorRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.produto_fornecedor</code>
     */
    public static final ProdutoFornecedor PRODUTO_FORNECEDOR = new ProdutoFornecedor();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ProdutoFornecedorRecord> getRecordType() {
        return ProdutoFornecedorRecord.class;
    }

    /**
     * The column <code>public.produto_fornecedor.id</code>.
     */
    public final TableField<ProdutoFornecedorRecord, Integer> ID = createField(DSL.name("id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.produto_fornecedor.produto_id</code>.
     */
    public final TableField<ProdutoFornecedorRecord, Integer> PRODUTO_ID = createField(DSL.name("produto_id"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>public.produto_fornecedor.fornecedor_id</code>.
     */
    public final TableField<ProdutoFornecedorRecord, Integer> FORNECEDOR_ID = createField(DSL.name("fornecedor_id"), SQLDataType.INTEGER.nullable(false), this, "");

    private ProdutoFornecedor(Name alias, Table<ProdutoFornecedorRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private ProdutoFornecedor(Name alias, Table<ProdutoFornecedorRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>public.produto_fornecedor</code> table reference
     */
    public ProdutoFornecedor(String alias) {
        this(DSL.name(alias), PRODUTO_FORNECEDOR);
    }

    /**
     * Create an aliased <code>public.produto_fornecedor</code> table reference
     */
    public ProdutoFornecedor(Name alias) {
        this(alias, PRODUTO_FORNECEDOR);
    }

    /**
     * Create a <code>public.produto_fornecedor</code> table reference
     */
    public ProdutoFornecedor() {
        this(DSL.name("produto_fornecedor"), null);
    }

    public <O extends Record> ProdutoFornecedor(Table<O> path, ForeignKey<O, ProdutoFornecedorRecord> childPath, InverseForeignKey<O, ProdutoFornecedorRecord> parentPath) {
        super(path, childPath, parentPath, PRODUTO_FORNECEDOR);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class ProdutoFornecedorPath extends ProdutoFornecedor implements Path<ProdutoFornecedorRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> ProdutoFornecedorPath(Table<O> path, ForeignKey<O, ProdutoFornecedorRecord> childPath, InverseForeignKey<O, ProdutoFornecedorRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private ProdutoFornecedorPath(Name alias, Table<ProdutoFornecedorRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public ProdutoFornecedorPath as(String alias) {
            return new ProdutoFornecedorPath(DSL.name(alias), this);
        }

        @Override
        public ProdutoFornecedorPath as(Name alias) {
            return new ProdutoFornecedorPath(alias, this);
        }

        @Override
        public ProdutoFornecedorPath as(Table<?> alias) {
            return new ProdutoFornecedorPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public Identity<ProdutoFornecedorRecord, Integer> getIdentity() {
        return (Identity<ProdutoFornecedorRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<ProdutoFornecedorRecord> getPrimaryKey() {
        return Keys.PRODUTO_FORNECEDOR_PKEY;
    }

    @Override
    public List<ForeignKey<ProdutoFornecedorRecord, ?>> getReferences() {
        return Arrays.asList(Keys.PRODUTO_FORNECEDOR__PRODUTO_FORNECEDOR_FORNECEDOR_ID_FKEY, Keys.PRODUTO_FORNECEDOR__PRODUTO_FORNECEDOR_PRODUTO_ID_FKEY);
    }

    private transient FornecedorPath _fornecedor;

    /**
     * Get the implicit join path to the <code>public.fornecedor</code> table.
     */
    public FornecedorPath fornecedor() {
        if (_fornecedor == null)
            _fornecedor = new FornecedorPath(this, Keys.PRODUTO_FORNECEDOR__PRODUTO_FORNECEDOR_FORNECEDOR_ID_FKEY, null);

        return _fornecedor;
    }

    private transient ProdutoPath _produto;

    /**
     * Get the implicit join path to the <code>public.produto</code> table.
     */
    public ProdutoPath produto() {
        if (_produto == null)
            _produto = new ProdutoPath(this, Keys.PRODUTO_FORNECEDOR__PRODUTO_FORNECEDOR_PRODUTO_ID_FKEY, null);

        return _produto;
    }

    @Override
    public ProdutoFornecedor as(String alias) {
        return new ProdutoFornecedor(DSL.name(alias), this);
    }

    @Override
    public ProdutoFornecedor as(Name alias) {
        return new ProdutoFornecedor(alias, this);
    }

    @Override
    public ProdutoFornecedor as(Table<?> alias) {
        return new ProdutoFornecedor(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public ProdutoFornecedor rename(String name) {
        return new ProdutoFornecedor(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public ProdutoFornecedor rename(Name name) {
        return new ProdutoFornecedor(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public ProdutoFornecedor rename(Table<?> name) {
        return new ProdutoFornecedor(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public ProdutoFornecedor where(Condition condition) {
        return new ProdutoFornecedor(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public ProdutoFornecedor where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public ProdutoFornecedor where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public ProdutoFornecedor where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public ProdutoFornecedor where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public ProdutoFornecedor where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public ProdutoFornecedor where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public ProdutoFornecedor where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public ProdutoFornecedor whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public ProdutoFornecedor whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
