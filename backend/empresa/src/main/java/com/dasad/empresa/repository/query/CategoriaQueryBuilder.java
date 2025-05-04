package com.dasad.empresa.repository.query;

import com.dasad.empresa.jooq.model.tables.Categoria;
import com.dasad.empresa.jooq.model.tables.Subcategoria;
import com.dasad.empresa.model.CategoriaModel;
import com.dasad.empresa.model.StatusServico;
import com.dasad.empresa.model.SubCategoriaModel;
import jakarta.validation.constraints.NotNull;
import lombok.extern.log4j.Log4j2;
import org.jooq.DSLContext;
import org.jooq.Record12;
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

@Log4j2
public class CategoriaQueryBuilder {
    private @NotNull SelectConditionStep<Record18<Integer, String, String, com.dasad.empresa.jooq.model.enums.StatusServico, Boolean, String, Integer, Object, Boolean, BigDecimal, LocalDateTime, LocalDateTime, String, String[], Integer, Integer, BigDecimal, String[]>> query;
    private @NotNull SelectConditionStep<Record12<Integer, Integer, String, String, com.dasad.empresa.jooq.model.enums.StatusServico, Integer, Object, BigDecimal, Object, String[], LocalDateTime, LocalDateTime>> querySubcategoria;
    private static final Integer DEFAULT_LIMIT = 10;
    private final DSLContext dslContext;
    private Boolean returnSubcategorias = false;

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

        this.querySubcategoria = db.select(
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
                        Subcategoria.SUBCATEGORIA.DATA_ATUALIZACAO)
                .from(Subcategoria.SUBCATEGORIA)
                .where(DSL.trueCondition());

    }

    public CategoriaQueryBuilder withId(Integer id) {
        if (id != null && id != 0) {
            this.query = this.query.and(Categoria.CATEGORIA.ID.eq(id));
        }
        return this;
    }

    public CategoriaQueryBuilder withNome(String nome) {
        if (nome != null && !nome.isEmpty()) {
            this.query = this.query.and(DSL.lower(Categoria.CATEGORIA.NOME).like("%" + nome.toLowerCase() + "%"));
        }
        return this;
    }

    public CategoriaQueryBuilder withStatus(List<StatusServico> status) {
        if (status != null && !status.isEmpty()) {
            this.query = this.query.and(Categoria.CATEGORIA.STATUS.in(status));
        }
        return this;
    }

    public CategoriaQueryBuilder withRequerCertificacao(Boolean requerCertificacao) {
        if (requerCertificacao != null) {
            this.query = this.query.and(Categoria.CATEGORIA.REQUER_CERTIFICACAO.eq(requerCertificacao));
        }
        return this;
    }

    public CategoriaQueryBuilder withTipoCertificacao(String tipoCertificacao) {
        if (tipoCertificacao != null && !tipoCertificacao.isEmpty()) {
            this.query = this.query.and(Categoria.CATEGORIA.TIPO_CERTIFICACAO.eq(tipoCertificacao));
        }
        return this;
    }

    public CategoriaQueryBuilder withSubcategorias(Boolean subcategorias) {
        returnSubcategorias = subcategorias;
        return this;
    }

    public CategoriaQueryBuilder withExperienciaMinimaMeses(Integer experienciaMinimaMeses) {
        if (experienciaMinimaMeses != null && experienciaMinimaMeses != 0) {
            this.query = this.query.and(Categoria.CATEGORIA.EXPERIENCIA_MINIMA_MESES.eq(experienciaMinimaMeses));
        }
        return this;
    }

    public CategoriaQueryBuilder withNivelRisco(String nivelRisco) {
        if (nivelRisco != null && !nivelRisco.isEmpty()) {
            this.query = this.query.and(Categoria.CATEGORIA.NIVEL_RISCO.eq(nivelRisco));
        }
        return this;
    }

    public CategoriaQueryBuilder withLimit(Integer limit) {
        this.query.limit(limit != null && limit > 0 ? limit : DEFAULT_LIMIT);
        return this;
    }

    public CategoriaQueryBuilder withOffset(Integer offset) {
        this.query.offset(offset != null ? offset : 0);
        return this;
    }

    public CompletableFuture<List<CategoriaModel>> build() {
        return CompletableFuture.supplyAsync(() -> this.query.fetch().stream().map(registro -> {
                    CategoriaModel categoria = new CategoriaModel();
                    categoria.setId(registro.get(Categoria.CATEGORIA.ID));
                    categoria.setNome(registro.get(Categoria.CATEGORIA.NOME));
                    categoria.setDescricao(registro.get(Categoria.CATEGORIA.DESCRICAO));
                    categoria.setStatus(StatusServico.valueOf(registro.get(Categoria.CATEGORIA.STATUS).toString()));
                    categoria.setRequerCertificacao(registro.get(Categoria.CATEGORIA.REQUER_CERTIFICACAO));
                    categoria.setTipoCertificacao(registro.get(Categoria.CATEGORIA.TIPO_CERTIFICACAO));
                    categoria.setExperienciaMinimaMeses(registro.get(Categoria.CATEGORIA.EXPERIENCIA_MINIMA_MESES));
                    categoria.setNivelRisco(registro.get(Categoria.CATEGORIA.NIVEL_RISCO).toString());
                    categoria.setSeguroObrigatorio(registro.get(Categoria.CATEGORIA.SEGURO_OBRIGATORIO));
                    categoria.setValorBaseHora(registro.get(Categoria.CATEGORIA.VALOR_BASE_HORA));
                    var dataCriacao = registro.get(Categoria.CATEGORIA.DATA_CRIACAO);
                    categoria.setDataCriacao(dataCriacao != null ? OffsetDateTime.of(dataCriacao, ZoneOffset.UTC) : null);
                    var dataAtualizacao = registro.get(Categoria.CATEGORIA.DATA_ATUALIZACAO);
                    categoria.setDataAtualizacao(dataAtualizacao != null ? OffsetDateTime.of(dataAtualizacao, ZoneOffset.UTC) : null);
                    categoria.setUrlImagem(registro.get(Categoria.CATEGORIA.URL_IMAGEM));
                    String[] palavrasChave = registro.get(Categoria.CATEGORIA.PALAVRAS_CHAVE);
                    categoria.setPalavrasChave(palavrasChave != null ? String.join(",", palavrasChave) : "");
                    categoria.setHorasMinimasAgendamento(registro.get(Categoria.CATEGORIA.HORAS_MINIMAS_AGENDAMENTO));
                    categoria.setHorasCancelamentoGratis(registro.get(Categoria.CATEGORIA.HORAS_CANCELAMENTO_GRATIS));
                    categoria.setPercentualComissao(JsonNullable.of(registro.get(Categoria.CATEGORIA.PERCENTUAL_COMISSAO).floatValue()));
                    categoria.setDocumentosNecessarios(String.join(",", registro.get(Categoria.CATEGORIA.DOCUMENTOS_NECESSARIOS)));
                    return categoria;
                }).toList())
                .thenCompose(categorias -> {
                    if (Boolean.TRUE.equals(this.returnSubcategorias) && categorias != null && !categorias.isEmpty()) {
                        List<CompletableFuture<Void>> futures = categorias.stream().map(categoria ->
                                getSubcategorias(categoria.getId()).thenAccept(subs -> categoria.setSubcategorias(subs))
                        ).toList();
                        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                                .exceptionally(ex -> {
                                    log.error("Error fetching subcategories: {}", ex.getMessage());
                                    return null;
                                })
                                .thenApply(v -> categorias);
                    } else {
                        return CompletableFuture.completedFuture(categorias);
                    }
                });
    }

    private CompletableFuture<List<SubCategoriaModel>> getSubcategorias(Integer categoriaId) {
        return CompletableFuture.supplyAsync(() -> this.dslContext
                .select(
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
                .where(Subcategoria.SUBCATEGORIA.CATEGORIA_ID.eq(categoriaId))
                .fetch()
                .stream()
                .map(registro -> {
                    SubCategoriaModel subcategoria = new SubCategoriaModel();
                    subcategoria.setId(registro.get(Subcategoria.SUBCATEGORIA.ID));
                    subcategoria.setNome(registro.get(Subcategoria.SUBCATEGORIA.NOME));
                    subcategoria.setDescricao(registro.get(Subcategoria.SUBCATEGORIA.DESCRICAO));
                    subcategoria.setStatus(StatusServico.valueOf(registro.get(Subcategoria.SUBCATEGORIA.STATUS).toString()).name());
                    subcategoria.setTempoMedioMinutos(registro.get(Subcategoria.SUBCATEGORIA.TEMPO_MEDIO_MINUTOS));
                    subcategoria.setNivelComplexidade(registro.get(Subcategoria.SUBCATEGORIA.NIVEL_COMPLEXIDADE).toString());
                    subcategoria.setPrecoBase(registro.get(Subcategoria.SUBCATEGORIA.PRECO_BASE));
                    subcategoria.setUnidadeMedida(registro.get(Subcategoria.SUBCATEGORIA.UNIDADE_MEDIDA).toString());
                    var dataCriacao = registro.get(Subcategoria.SUBCATEGORIA.DATA_CRIACAO);
                    subcategoria.setDataCriacao(dataCriacao != null ? OffsetDateTime.of(dataCriacao, ZoneOffset.systemDefault().getRules().getOffset(dataCriacao)) : null);
                    var dataAtualizacao = registro.get(Subcategoria.SUBCATEGORIA.DATA_ATUALIZACAO);
                    subcategoria.setDataAtualizacao(dataAtualizacao != null ? OffsetDateTime.of(dataAtualizacao, ZoneOffset.UTC) : null);
                    return subcategoria;
                })
                .collect(Collectors.toList())
        );
    }

    public CompletableFuture<Integer> calculateTotalPages(Integer limit) {
        int effectiveLimit = (limit != null && limit > 0) ? limit : DEFAULT_LIMIT;
        return countTotalRecords().thenApply(totalRecords -> (int) Math.ceil((double) totalRecords / effectiveLimit));
    }

    public CompletableFuture<Integer> countTotalRecords() {
        return CompletableFuture.supplyAsync(() -> this.dslContext
                .selectCount()
                .from(Categoria.CATEGORIA)
                .fetchOne(0, int.class));
    }
}