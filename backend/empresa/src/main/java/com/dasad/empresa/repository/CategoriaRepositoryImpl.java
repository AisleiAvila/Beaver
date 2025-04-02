package com.dasad.empresa.repository;

import com.dasad.empresa.jooq.enums.StatusServico;
import com.dasad.empresa.jooq.tables.Categoria;
import com.dasad.empresa.model.CategoriaModel;
import com.dasad.empresa.model.CategoriaRequest;
import com.dasad.empresa.repository.query.CategoriaQueryBuilder;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoriaRepositoryImpl implements CategoriaRepository {
    private final DSLContext dsl;

    @Autowired
    public CategoriaRepositoryImpl(DSLContext dsl) {
        this.dsl = dsl;
    }

    @Override
    public Optional<List<CategoriaModel>> find(CategoriaRequest categoriarequest) {
        CategoriaQueryBuilder queryBuilder = new CategoriaQueryBuilder(this.dsl)
                .withId(categoriarequest.getId())
                .withNome(categoriarequest.getNome())
                .withExperienciaMinimaMeses(categoriarequest.getExperienciaMinimaMeses())
                .withStatus(categoriarequest.getStatus() != null ?
                        categoriarequest.getStatus().stream()
                                .map(status -> StatusServico.valueOf(status.name()))
                                .toList()
                        : null)
                .withRequerCertificacao(categoriarequest.getRequerCertificacao())
                .withNivelRisco(categoriarequest.getNivelRisco())
                .withTipoCertificacao(categoriarequest.getTipoCertificacao())
                .withLimit(categoriarequest.getLimit())
                .withOffset(categoriarequest.getOffset());
        List<CategoriaModel> result = queryBuilder.build().join();
        return Optional.ofNullable(result.isEmpty() ? null : result);
    }

    @Override
    public Optional<CategoriaModel> create(CategoriaModel categoriaModel) {
        return dsl.transactionResult(
                configuration -> {
                    CategoriaModel result = dsl.insertInto(Categoria.CATEGORIA)
                            .set(Categoria.CATEGORIA.NOME, categoriaModel.getNome())
                            .set(Categoria.CATEGORIA.EXPERIENCIA_MINIMA_MESES, categoriaModel.getExperienciaMinimaMeses())
                            .set(Categoria.CATEGORIA.STATUS, StatusServico.valueOf(categoriaModel.getStatus().name()))
                            .set(Categoria.CATEGORIA.REQUER_CERTIFICACAO, categoriaModel.getRequerCertificacao())
                            .set(Categoria.CATEGORIA.NIVEL_RISCO, categoriaModel.getNivelRisco())
                            .set(Categoria.CATEGORIA.TIPO_CERTIFICACAO, categoriaModel.getTipoCertificacao())
                            .returning()
                            .fetchOne()
                            .into(CategoriaModel.class);
                    return Optional.of(result);
                }
        );
    }

    @Override
    public void delete(Integer id) {
        dsl.deleteFrom(Categoria.CATEGORIA).where(Categoria.CATEGORIA.ID.eq(id)).execute();
    }

    @Override
    public Optional<Object> findById(Integer id) {
        CategoriaQueryBuilder queryBuilder = new CategoriaQueryBuilder(this.dsl)
                .withId(id)
                .withLimit(1)
                .withOffset(0);
        List<CategoriaModel> result = queryBuilder.build().join();
        return Optional.ofNullable(result.isEmpty() ? null : result.getFirst());
    }
}