package com.dasad.empresa.repository.query;

import com.dasad.empresa.jooq.enums.StatusServico;
import com.dasad.empresa.jooq.tables.Categoria;
import com.dasad.empresa.jooq.tables.Usuario;
import com.dasad.empresa.model.CategoriaModel;
import jakarta.validation.constraints.NotNull;
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
    private @NotNull SelectConditionStep<Record18<Integer, String, String, StatusServico, Boolean, String, Integer, Object, Boolean, BigDecimal, LocalDateTime, LocalDateTime, String, String[], Integer, Integer, BigDecimal, String[]>> query;
    private static final Integer DEFAULT_LIMIT = 10;
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

    public CategoriaQueryBuilder withId(Integer id) {
        if (id != null &&  id != 0) {
            this.query = this.query.and(Categoria.CATEGORIA.ID.eq(id));
        }
        return this;
    }

    public CategoriaQueryBuilder withNome(String nome) {
        if (nome != null &&  !nome.isEmpty()) {
            this.query = this.query.and(DSL.lower(Categoria.CATEGORIA.NOME).like("%" + nome.toLowerCase() + "%"));
        }
        return this;
    }

    public  CategoriaQueryBuilder withStatus(List<StatusServico> status) {
        if (status != null && !status.isEmpty()) {
            this.query = this.query.and(Categoria.CATEGORIA.STATUS.in(status));
        }
        return this;
    }

    public  CategoriaQueryBuilder withRequerCertificacao(Boolean requerCertificacao) {
        if (requerCertificacao != null) {
            this.query = this.query.and(Categoria.CATEGORIA.REQUER_CERTIFICACAO.eq(requerCertificacao));        }
        return this;
    }

    public  CategoriaQueryBuilder withTipoCertificacao(String tipoCertificacao) {
        if (tipoCertificacao != null &&  !tipoCertificacao.isEmpty()) {
            this.query = this.query.and(Categoria.CATEGORIA.TIPO_CERTIFICACAO.eq(tipoCertificacao));
        }
        return this;
    }

    public  CategoriaQueryBuilder withExperienciaMinimaMeses(Integer experienciaMinimaMeses) {
        if (experienciaMinimaMeses != null && experienciaMinimaMeses != 0) {
            this.query = this.query.and(Categoria.CATEGORIA.EXPERIENCIA_MINIMA_MESES.eq(experienciaMinimaMeses));
        }
        return this;
    }

    public  CategoriaQueryBuilder withNivelRisco( String nivelRisco) {
        if (nivelRisco != null &&  !nivelRisco.isEmpty()) {
            this.query = this.query.and(Categoria.CATEGORIA.NIVEL_RISCO.eq(nivelRisco));
        }
        return this;
    }

    public CategoriaQueryBuilder withLimit(Integer limit) {
        this.query.limit(limit != null &&  limit > 0 ? limit : DEFAULT_LIMIT);
        return this;
    }

    public CategoriaQueryBuilder withOffset(Integer offset) {
        this.query.offset(offset != null  ? offset : 0);
        return this;
    }

    public CompletableFuture<List<CategoriaModel>> build() {
        return CompletableFuture.supplyAsync(() -> this.query.fetch().stream().collect(Collectors.groupingBy(
                registro -> registro.get(Usuario.USUARIO.ID),
                Collectors.mapping(registro -> registro, Collectors.toList())
        )).values().stream().map(records -> {
            Record18<Integer, String, String, StatusServico, Boolean, String, Integer, Object, Boolean, BigDecimal, LocalDateTime, LocalDateTime, String, String[], Integer, Integer, BigDecimal, String[]> registro = records.getFirst();
            CategoriaModel categoria = new CategoriaModel();
            categoria.setId(registro.get(Categoria.CATEGORIA.ID));
            categoria.setNome(registro.get(Categoria.CATEGORIA.NOME));
            categoria.setDescricao(registro.get(Categoria.CATEGORIA.DESCRICAO));
            categoria.setStatus(com.dasad.empresa.model.StatusServico.valueOf(registro.get(Categoria.CATEGORIA.STATUS).toString()));
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
            categoria.setPalavrasChave(String.join(",", registro.get(Categoria.CATEGORIA.PALAVRAS_CHAVE)));
            categoria.setHorasMinimasAgendamento(registro.get(Categoria.CATEGORIA.HORAS_MINIMAS_AGENDAMENTO));
            categoria.setHorasCancelamentoGratis(registro.get(Categoria.CATEGORIA.HORAS_CANCELAMENTO_GRATIS));
            categoria.setPercentualComissao(JsonNullable.of(registro.get(Categoria.CATEGORIA.PERCENTUAL_COMISSAO).floatValue()));
            categoria.setDocumentosNecessarios(String.join(",", registro.get(Categoria.CATEGORIA.DOCUMENTOS_NECESSARIOS)));
            return categoria;
        }).toList());
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