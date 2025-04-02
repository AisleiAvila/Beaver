package com.dasad.empresa.repository.query;

import com.dasad.empresa.jooq.tables.Produto;
import com.dasad.empresa.jooq.tables.Usuario;
import com.dasad.empresa.model.ProdutoModel;
import jakarta.annotation.Nonnull;
import org.jooq.DSLContext;
import org.jooq.Record6;
import org.jooq.SelectJoinStep;
import org.jooq.impl.DSL;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class ProdutoQueryBuilder {
    private final @Nonnull SelectJoinStep<Record6<Integer, String, String, String, BigDecimal, LocalDateTime>> query;
    private static final Integer DEFAULT_LIMIT = 10;
    private final DSLContext dslContext;

    public ProdutoQueryBuilder(DSLContext db) {
        this.dslContext = db;
        this.query = db.select(
                        Produto.PRODUTO.ID,
                        Produto.PRODUTO.NOME,
                        Produto.PRODUTO.DESCRICAO,
                        Produto.PRODUTO.CATEGORIA,
                        Produto.PRODUTO.PRECO,
                        Produto.PRODUTO.DATA_CADASTRO
                )
                .from(Produto.PRODUTO);
    }

    public ProdutoQueryBuilder withId(Integer id) {
        if(id != null) {
            this.query.where(Produto.PRODUTO.ID.eq(id));
        }
        return this;
    }

    public ProdutoQueryBuilder withNome(String nome) {
        if (nome != null) {
            this.query.where(DSL.lower(Produto.PRODUTO.NOME).like("%" + nome.toLowerCase() + "%"));
        }
        return this;
    }

    public ProdutoQueryBuilder withLimit(Integer limit) {
        this.query.limit(limit != null && limit > 0 ? limit : DEFAULT_LIMIT);
        return this;
    }

    public ProdutoQueryBuilder withOffset(Integer offset) {
        this.query.offset(offset != null  ? offset : 0);
        return this;
    }

    public CompletableFuture<List<ProdutoModel>> build() {
        return CompletableFuture.supplyAsync(() -> this.query.fetch().stream().collect(Collectors.groupingBy(
                item -> item.get(Usuario.USUARIO.ID),
                Collectors.mapping(item -> item, Collectors.toList())
        )).values().stream().map(records -> {
            Record6<Integer, String, String, String, BigDecimal, LocalDateTime> item = records.getFirst();
            ProdutoModel produto = new ProdutoModel();
            produto.setId(item.get(Produto.PRODUTO.ID));
            produto.setNome(item.get(Produto.PRODUTO.NOME));
            produto.setDescricao(item.get(Produto.PRODUTO.DESCRICAO));
            produto.setCategoria(item.get(Produto.PRODUTO.CATEGORIA));
            produto.setPreco(item.get(Produto.PRODUTO.PRECO));
            LocalDateTime dataCadastro = item.get(Produto.PRODUTO.DATA_CADASTRO);
            return produto;
        }).toList());
    }

    public CompletableFuture<Integer> calculateTotalPages(Integer limit) {
        int effectiveLimit = (limit != null && limit > 0) ? limit : DEFAULT_LIMIT;
        return countTotalRecords().thenApply(totalRecords -> (int) Math.ceil((double) totalRecords / effectiveLimit));
    }

    public CompletableFuture<Integer> countTotalRecords() {
        return CompletableFuture.supplyAsync(() -> this.dslContext
                .selectCount()
                .from(Produto.PRODUTO)
                .fetchOne(0, int.class));
    }
}