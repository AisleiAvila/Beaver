package com.dasad.empresa.repository;

import com.dasad.empresa.mapper.UsuarioSubcategoriaMapper;
import com.dasad.empresa.model.UsuarioSubcategoriaModel;
import lombok.extern.log4j.Log4j2;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

import static com.dasad.empresa.jooq.model.tables.Categoria.CATEGORIA;
import static com.dasad.empresa.jooq.model.tables.Subcategoria.SUBCATEGORIA;
import static com.dasad.empresa.jooq.model.tables.UsuarioSubcategoria.USUARIO_SUBCATEGORIA;

@Repository
@Log4j2
public class UsuarioSubCategoriaRepositoryImpl implements UsuarioSubCategoriaRepository {
    private final DSLContext dsl;

    @Autowired
    public UsuarioSubCategoriaRepositoryImpl(DSLContext dsl) {
        this.dsl = dsl;
    }

    @Override
    public Optional<List<UsuarioSubcategoriaModel>> find(Integer usuarioId, Boolean ativo) {
        log.info("find subcategoria usuario: {}", usuarioId);

        List<UsuarioSubcategoriaModel> result = dsl.select(
                        USUARIO_SUBCATEGORIA.USUARIO_ID,
                        USUARIO_SUBCATEGORIA.SUBCATEGORIA_ID,
                        USUARIO_SUBCATEGORIA.DATA_CRIACAO,
                        USUARIO_SUBCATEGORIA.DATA_EXCLUSAO,
                        SUBCATEGORIA.NOME,
                        CATEGORIA.ID,
                        CATEGORIA.NOME
                )
                .from(USUARIO_SUBCATEGORIA)
                .join(SUBCATEGORIA).on(USUARIO_SUBCATEGORIA.SUBCATEGORIA_ID.eq(SUBCATEGORIA.ID))
                .join(CATEGORIA).on(CATEGORIA.ID.eq(SUBCATEGORIA.CATEGORIA_ID))
                .where(USUARIO_SUBCATEGORIA.USUARIO_ID.eq(usuarioId))
                .and(ativo != null ? USUARIO_SUBCATEGORIA.DATA_EXCLUSAO.isNull() : DSL.noCondition())
                .fetch()
                .map(UsuarioSubcategoriaMapper::map); // Usando o mapper

        return Optional.ofNullable(result.isEmpty() ? null : result);
    }

    @Override
    public Optional<UsuarioSubcategoriaModel> findByUsuarioIdAndSubcategoriaId(
            Integer usuarioId, Integer subcategoriaId, Boolean ativo) {
        log.info("findByUsuarioIdAndSubcategoriaId usuario: {}, subcategoria: {}", usuarioId, subcategoriaId);

        List<UsuarioSubcategoriaModel> result = dsl.select(
                        USUARIO_SUBCATEGORIA.USUARIO_ID,
                        USUARIO_SUBCATEGORIA.SUBCATEGORIA_ID,
                        USUARIO_SUBCATEGORIA.DATA_CRIACAO,
                        USUARIO_SUBCATEGORIA.DATA_EXCLUSAO,
                        SUBCATEGORIA.NOME,
                        CATEGORIA.ID,
                        CATEGORIA.NOME
                )
                .from(USUARIO_SUBCATEGORIA)
                .join(SUBCATEGORIA).on(USUARIO_SUBCATEGORIA.SUBCATEGORIA_ID.eq(SUBCATEGORIA.ID))
                .join(CATEGORIA).on(CATEGORIA.ID.eq(SUBCATEGORIA.CATEGORIA_ID))
                .where(USUARIO_SUBCATEGORIA.USUARIO_ID.eq(usuarioId))
                .and(USUARIO_SUBCATEGORIA.SUBCATEGORIA_ID.eq(subcategoriaId))
                .and(ativo != null ? USUARIO_SUBCATEGORIA.DATA_EXCLUSAO.isNull() : DSL.noCondition())
                .fetch()
                .map(UsuarioSubcategoriaMapper::map); // Usando o mapper

        return Optional.ofNullable(result.isEmpty() ? null : result.getFirst());
    }

    @Override
    public void update(Integer usuarioId, Integer subcategoriaId, Boolean excluir) {
        dsl.update(USUARIO_SUBCATEGORIA)
                .set(USUARIO_SUBCATEGORIA.DATA_EXCLUSAO, excluir ?
                        OffsetDateTime.now(ZoneOffset.UTC).toLocalDateTime() : null)
                .where(USUARIO_SUBCATEGORIA.USUARIO_ID.eq(usuarioId))
                .and(USUARIO_SUBCATEGORIA.SUBCATEGORIA_ID.eq(subcategoriaId))
                .execute();
    }

    @Override
    public void save(Integer usuarioId, Integer subcategoriaId) {
        dsl.insertInto(USUARIO_SUBCATEGORIA)
                .set(USUARIO_SUBCATEGORIA.USUARIO_ID, usuarioId)
                .set(USUARIO_SUBCATEGORIA.SUBCATEGORIA_ID, subcategoriaId)
                .set(USUARIO_SUBCATEGORIA.DATA_CRIACAO, OffsetDateTime.now(ZoneOffset.UTC).toLocalDateTime())
                .execute();
    }

}