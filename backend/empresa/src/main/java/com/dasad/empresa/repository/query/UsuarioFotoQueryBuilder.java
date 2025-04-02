package com.dasad.empresa.repository.query;

import com.dasad.empresa.jooq.tables.UsuarioFoto;
import com.dasad.empresa.model.UsuarioFotoModel;
import jakarta.annotation.Nonnull;
import org.jooq.DSLContext;
import org.jooq.Record6;
import org.jooq.SelectConditionStep;
import org.jooq.impl.DSL;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class UsuarioFotoQueryBuilder {
    private SelectConditionStep<Record6<Integer, Integer, byte[], LocalDateTime, LocalDateTime, Boolean>> query;
    private final static Integer DEFAULT_LIMIT = 10;
    private Integer limit = DEFAULT_LIMIT;

    public UsuarioFotoQueryBuilder(DSLContext db) {

        this.query = db.select(
                        UsuarioFoto.USUARIO_FOTO.ID,
                        UsuarioFoto.USUARIO_FOTO.USUARIO_ID,
                        UsuarioFoto.USUARIO_FOTO.FOTO,
                        UsuarioFoto.USUARIO_FOTO.DATA_CRIACAO,
                        UsuarioFoto.USUARIO_FOTO.DATA_ATUALIZACAO,
                        UsuarioFoto.USUARIO_FOTO.ATIVO
                )
                .from(UsuarioFoto.USUARIO_FOTO)
                .where(DSL.trueCondition());
    }

    public UsuarioFotoQueryBuilder withUsuarioId(Integer usuarioId) {
        if(usuarioId != null) {
            this.query = this.query.and(UsuarioFoto.USUARIO_FOTO.USUARIO_ID.eq(usuarioId));
        }
        return this;
    }

    public UsuarioFotoQueryBuilder withAtivo(Boolean ativo) {
        if(ativo != null && ativo) {
            this.query = this.query.and(UsuarioFoto.USUARIO_FOTO.ATIVO.eq(ativo));
        }
        return this;
    }

    public UsuarioFotoQueryBuilder withLimit(Integer limit) {
        this.limit = limit != null && limit > 0 ? limit : DEFAULT_LIMIT;
        return this;
    }

    public CompletableFuture<List<UsuarioFotoModel>> build() {
        return CompletableFuture.supplyAsync(() -> {
            return this.query
                    .orderBy(UsuarioFoto.USUARIO_FOTO.DATA_CRIACAO.desc())
                    .limit(limit)
                    .fetch()
                    .stream()
                    .collect(Collectors.groupingBy(
                            record -> record.get(UsuarioFoto.USUARIO_FOTO.USUARIO_ID),
                            Collectors.mapping(record -> record, Collectors.toList())
                    ))
                    .values()
                    .stream()
                    .map(records -> {
                        Record6<Integer, Integer, byte[], LocalDateTime, LocalDateTime, Boolean> record = records.get(0);
                        UsuarioFotoModel usuarioFoto = new UsuarioFotoModel();
                        usuarioFoto.setId(record.get(UsuarioFoto.USUARIO_FOTO.ID));
                        usuarioFoto.setUsuarioId(record.get(UsuarioFoto.USUARIO_FOTO.USUARIO_ID));
                        usuarioFoto.setFoto(record.get(UsuarioFoto.USUARIO_FOTO.FOTO));
                        usuarioFoto.setDataCriacao(OffsetDateTime.from(record.get(UsuarioFoto.USUARIO_FOTO.DATA_CRIACAO).toLocalDate()));
                        usuarioFoto.setDataAtualizacao(OffsetDateTime.from(record.get(UsuarioFoto.USUARIO_FOTO.DATA_ATUALIZACAO).toLocalDate()));
                        usuarioFoto.setAtivo(record.get(UsuarioFoto.USUARIO_FOTO.ATIVO));
                        return usuarioFoto;
                    })
                    .toList();
        });
    }
}