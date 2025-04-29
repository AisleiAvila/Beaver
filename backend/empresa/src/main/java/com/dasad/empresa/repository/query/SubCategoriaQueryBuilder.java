package com.dasad.empresa.repository.query;

import com.dasad.empresa.jooq.model.enums.StatusServico;
import com.dasad.empresa.jooq.model.tables.Subcategoria;
import com.dasad.empresa.jooq.model.tables.Usuario;
import com.dasad.empresa.model.SubCategoriaModel;
import jakarta.validation.constraints.NotNull;
import org.jooq.DSLContext;
import org.jooq.Record12;
import org.jooq.SelectConditionStep;
import org.jooq.impl.DSL;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class SubCategoriaQueryBuilder {
    private @NotNull SelectConditionStep<Record12<Integer, Integer, String, String, StatusServico, Integer, Object, BigDecimal, Object, String[], LocalDateTime, LocalDateTime>> query;
    private static final Integer DEFAULT_LIMIT = 10;
    private final DSLContext dslContext;

    public SubCategoriaQueryBuilder(DSLContext db) {
        this.dslContext = db;
        this.query = db.select(
                        Subcategoria.SUBCATEGORIA.ID,
                        Subcategoria.SUBCATEGORIA.CATEGORIA_ID,
                        Subcategoria.SUBCATEGORIA.NOME,
                        Subcategoria.SUBCATEGORIA.DESCRICAO,
                        Subcategoria.SUBCATEGORIA.STATUS,
                        Subcategoria.SUBCATEGORIA.TEMPO_MEDIO_MINUTOS,
                        Subcategoria.SUBCATEGORIA.NIVEL_COMPLEXIDADE,
                        Subcategoria.SUBCATEGORIA.PRECO_BASE,
                        Subcategoria.SUBCATEGORIA.UNIDADE_MEDIDA,
                        Subcategoria.SUBCATEGORIA.MATERIAIS_TIPICOS,
                        Subcategoria.SUBCATEGORIA.DATA_CRIACAO,
                        Subcategoria.SUBCATEGORIA.DATA_ATUALIZACAO
                )
                .from(Subcategoria.SUBCATEGORIA)
                .where(DSL.trueCondition());
    }

    public SubCategoriaQueryBuilder withId(Integer id) {
        if (id != null && id != 0) {
            this.query = this.query.and(Subcategoria.SUBCATEGORIA.ID.eq(id));
        }
        return this;
    }

    public SubCategoriaQueryBuilder withCategoriaId(Integer categoriaId) {
        if (categoriaId != null && categoriaId != 0) {
            this.query = this.query.and(Subcategoria.SUBCATEGORIA.CATEGORIA_ID.eq(categoriaId));
        }
        return this;
    }

    public SubCategoriaQueryBuilder withNome(String nome) {
        if (nome != null && !nome.isEmpty()) {
            this.query = this.query.and(DSL.lower(Subcategoria.SUBCATEGORIA.NOME).like("%" + nome.toLowerCase() + "%"));
        }
        return this;
    }

    public SubCategoriaQueryBuilder withStatus(String status) {
        if (status != null && !status.isEmpty()) {
            this.query = this.query.and(Subcategoria.SUBCATEGORIA.STATUS.eq(StatusServico.valueOf(status)));
        }
        return this;
    }

    public SubCategoriaQueryBuilder withNivelComlexidade(String nivelComlexidade) {
        if (nivelComlexidade != null && !nivelComlexidade.isEmpty()) {
            this.query = this.query.and(Subcategoria.SUBCATEGORIA.NIVEL_COMPLEXIDADE.eq(nivelComlexidade));
        }
        return this;
    }


    public SubCategoriaQueryBuilder withLimit(Integer limit) {
        this.query.limit(limit != null && limit > 0 ? limit : DEFAULT_LIMIT);
        return this;
    }

    public SubCategoriaQueryBuilder withOffset(Integer offset) {
        this.query.offset(offset != null ? offset : 0);
        return this;
    }

    public CompletableFuture<List<SubCategoriaModel>> build() {
        return CompletableFuture.supplyAsync(() -> {
            return this.query.fetch().stream().collect(Collectors.groupingBy(
                    record -> record.get(Usuario.USUARIO.ID),
                    Collectors.mapping(record -> record, Collectors.toList())
            )).values().stream().map(records -> {
                Record12<Integer, Integer, String, String, StatusServico, Integer, Object, BigDecimal, Object, String[], LocalDateTime, LocalDateTime> record = records.getFirst();
                SubCategoriaModel subCategoria = new SubCategoriaModel();
                subCategoria.setId(record.get(Subcategoria.SUBCATEGORIA.ID));
                subCategoria.setCategoriaId(record.get(Subcategoria.SUBCATEGORIA.CATEGORIA_ID));
                subCategoria.setNome(record.get(Subcategoria.SUBCATEGORIA.NOME));
                subCategoria.setDescricao(record.get(Subcategoria.SUBCATEGORIA.DESCRICAO));
                subCategoria.setStatus(record.get(Subcategoria.SUBCATEGORIA.STATUS).toString());
                subCategoria.setTempoMedioMinutos(record.get(Subcategoria.SUBCATEGORIA.TEMPO_MEDIO_MINUTOS));
                subCategoria.setNivelComplexidade(record.get(Subcategoria.SUBCATEGORIA.NIVEL_COMPLEXIDADE).toString());
                subCategoria.setPrecoBase(record.get(Subcategoria.SUBCATEGORIA.PRECO_BASE));
                subCategoria.setUnidadeMedida(record.get(Subcategoria.SUBCATEGORIA.UNIDADE_MEDIDA).toString());
                subCategoria.setMateriaisTipicos(String.join(",", record.get(Subcategoria.SUBCATEGORIA.MATERIAIS_TIPICOS)));
                var dataCriacao = record.get(Subcategoria.SUBCATEGORIA.DATA_CRIACAO);
                subCategoria.setDataCriacao(dataCriacao != null ? OffsetDateTime.of(dataCriacao, ZoneOffset.UTC) : null);
                var dataAtualizacao = record.get(Subcategoria.SUBCATEGORIA.DATA_ATUALIZACAO);
                subCategoria.setDataAtualizacao(dataAtualizacao != null ? OffsetDateTime.of(dataAtualizacao, ZoneOffset.UTC) : null);
                return subCategoria;
            }).toList();
        });
    }

    public CompletableFuture<Integer> calculateTotalPages(Integer limit) {
        int effectiveLimit = (limit != null && limit > 0) ? limit : DEFAULT_LIMIT;
        return countTotalRecords().thenApply(totalRecords -> (int) Math.ceil((double) totalRecords / effectiveLimit));
    }

    public CompletableFuture<Integer> countTotalRecords() {
        return CompletableFuture.supplyAsync(() -> {
            return this.dslContext
                    .selectCount()
                    .from(Subcategoria.SUBCATEGORIA)
                    .fetchOne(0, int.class);
        });
    }
}