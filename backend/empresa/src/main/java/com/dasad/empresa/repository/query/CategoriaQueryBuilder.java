package com.dasad.empresa.repository.query;

import com.dasad.empresa.jooq.tables.Categoria;
import com.dasad.empresa.jooq.tables.Usuario;
import com.dasad.empresa.model.CategoriaModel;
import jakarta.annotation.Nonnull;
import org.jooq.DSLContext;
import org.jooq.Record18;
import org.jooq.SelectConditionStep;
import org.jooq.impl.DSL;
import org.openapitools.jackson.nullable.JsonNullable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class CategoriaQueryBuilder {
    private  SelectConditionStep<Record18<Integer, String, String, Object, Boolean, String, Integer, Object, Boolean, BigDecimal, LocalDateTime, LocalDateTime, String, String[], Integer, Integer, BigDecimal, String[]>> query;
    private final static Integer DEFAULT_LIMIT = 10;
    private final DSLContext dslContext;

    public CategoriaQueryBuilder(DSLContext db) {
        this.dslContext = db;
        this.query = db.select(
                        Categoria.CATEGORIA.ID,
                        Categoria.CATEGORIA.NOME,
                        Categoria.CATEGORIA.DESCRICAO,
                        Categoria.CATEGORIA.STATUS,
                        Categoria.CATEGORIA.REQUER_CERTIFICACAO,
                        Categoria.CATEGORIA.TIPO_CERTIFICACAO,
                        Categoria.CATEGORIA.EXPERIENCIA_MINIMA_MESES,
                        Categoria.CATEGORIA.NIVEL_RISCO,
                        Categoria.CATEGORIA.SEGURO_OBRIGATORIO,
                        Categoria.CATEGORIA.VALOR_BASE_HORA,
                        Categoria.CATEGORIA.DATA_CRIACAO,
                        Categoria.CATEGORIA.DATA_ATUALIZACAO,
                        Categoria.CATEGORIA.URL_IMAGEM,
                        Categoria.CATEGORIA.PALAVRAS_CHAVE,
                        Categoria.CATEGORIA.HORAS_MINIMAS_AGENDAMENTO,
                        Categoria.CATEGORIA.HORAS_CANCELAMENTO_GRATIS,
                        Categoria.CATEGORIA.PERCENTUAL_COMISSAO,
                        Categoria.CATEGORIA.DOCUMENTOS_NECESSARIOS
                )
                .from(Categoria.CATEGORIA)
                .where(DSL.trueCondition());
    }

    public CategoriaQueryBuilder withId(@Nonnull Integer id) {
        if (id != null &&  id != 0) {
            this.query = this.query.and(Categoria.CATEGORIA.ID.eq(id));
        }
        return this;
    }

    public CategoriaQueryBuilder withNome(@Nonnull String nome) {
        if (nome != null &&  !nome.isEmpty()) {
            this.query = this.query.and(DSL.lower(Categoria.CATEGORIA.NOME).like("%" + nome.toLowerCase() + "%"));
        }
        return this;
    }

    public  CategoriaQueryBuilder withStatus(@Nonnull String status) {
        if (status != null &&  !status.isEmpty()) {
            this.query = this.query.and(Categoria.CATEGORIA.STATUS.eq(status));
        }
        return this;
    }

    public  CategoriaQueryBuilder withRequerCertificacao(@Nonnull Boolean requerCertificacao) {
        if (requerCertificacao != null) {
            this.query = this.query.and(Categoria.CATEGORIA.REQUER_CERTIFICACAO.eq(requerCertificacao));        }
        return this;
    }

    public  CategoriaQueryBuilder withTipoCertificacao(@Nonnull String tipoCertificacao) {
        if (tipoCertificacao != null &&  !tipoCertificacao.isEmpty()) {
            this.query = this.query.and(Categoria.CATEGORIA.TIPO_CERTIFICACAO.eq(tipoCertificacao));
        }
        return this;
    }

    public  CategoriaQueryBuilder withExperienciaMinimaMeses(@Nonnull Integer experienciaMinimaMeses) {
        if (experienciaMinimaMeses != null && experienciaMinimaMeses != 0) {
            this.query = this.query.and(Categoria.CATEGORIA.EXPERIENCIA_MINIMA_MESES.eq(experienciaMinimaMeses));
        }
        return this;
    }

    public  CategoriaQueryBuilder withNivelRisco(@Nonnull String nivelRisco) {
        if (nivelRisco != null &&  !nivelRisco.isEmpty()) {
            this.query = this.query.and(Categoria.CATEGORIA.NIVEL_RISCO.eq(nivelRisco));
        }
        return this;
    }

    public CategoriaQueryBuilder withLimit(@Nonnull Integer limit) {
        this.query.limit(limit != null &&  limit > 0 ? limit : DEFAULT_LIMIT);
        return this;
    }

    public CategoriaQueryBuilder withOffset(@Nonnull Integer offset) {
        this.query.offset(offset != null  ? offset : 0);
        return this;
    }

    public CompletableFuture<List<CategoriaModel>> build() {
        return CompletableFuture.supplyAsync(() -> {
            return this.query.fetch().stream().collect(Collectors.groupingBy(
                    record -> record.get(Usuario.USUARIO.ID),
                    Collectors.mapping(record -> record, Collectors.toList())
            )).values().stream().map(records -> {
                Record18<Integer, String, String, Object, Boolean, String, Integer, Object, Boolean, BigDecimal, LocalDateTime, LocalDateTime, String, String[], Integer, Integer, BigDecimal, String[]> record = records.getFirst();
                CategoriaModel categoria = new CategoriaModel();
                categoria.setId(record.get(Categoria.CATEGORIA.ID));
                categoria.setNome(record.get(Categoria.CATEGORIA.NOME));
                categoria.setDescricao(record.get(Categoria.CATEGORIA.DESCRICAO));
                categoria.setStatus(record.get(Categoria.CATEGORIA.STATUS).toString());
                categoria.setRequerCertificacao(record.get(Categoria.CATEGORIA.REQUER_CERTIFICACAO));
                categoria.setTipoCertificacao(record.get(Categoria.CATEGORIA.TIPO_CERTIFICACAO));
                categoria.setExperienciaMinimaMeses(record.get(Categoria.CATEGORIA.EXPERIENCIA_MINIMA_MESES));
                categoria.setNivelRisco(record.get(Categoria.CATEGORIA.NIVEL_RISCO).toString());
                categoria.setSeguroObrigatorio(record.get(Categoria.CATEGORIA.SEGURO_OBRIGATORIO));
                categoria.setValorBaseHora(record.get(Categoria.CATEGORIA.VALOR_BASE_HORA));
                var dataCriacao = record.get(Categoria.CATEGORIA.DATA_CRIACAO);
                categoria.setDataCriacao(dataCriacao != null ? OffsetDateTime.of(dataCriacao, ZoneOffset.UTC) : null);
                var dataAtualizacao = record.get(Categoria.CATEGORIA.DATA_ATUALIZACAO);
                categoria.setDataAtualizacao(dataAtualizacao != null ? OffsetDateTime.of(dataAtualizacao, ZoneOffset.UTC) : null);
                categoria.setUrlImagem(record.get(Categoria.CATEGORIA.URL_IMAGEM));
                categoria.setPalavrasChave(String.join(",", record.get(Categoria.CATEGORIA.PALAVRAS_CHAVE)));
                categoria.setHorasMinimasAgendamento(record.get(Categoria.CATEGORIA.HORAS_MINIMAS_AGENDAMENTO));
                categoria.setHorasCancelamentoGratis(record.get(Categoria.CATEGORIA.HORAS_CANCELAMENTO_GRATIS));
                categoria.setPercentualComissao(JsonNullable.of(record.get(Categoria.CATEGORIA.PERCENTUAL_COMISSAO).floatValue()));
                categoria.setDocumentosNecessarios(String.join(",", record.get(Categoria.CATEGORIA.DOCUMENTOS_NECESSARIOS)));
                return categoria;
            }).collect(Collectors.toList());
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
                    .from(Categoria.CATEGORIA)
                    .fetchOne(0, int.class);
        });
    }
}