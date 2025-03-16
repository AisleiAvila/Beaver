package com.dasad.empresa.repository;

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
                .withStatus(categoriarequest.getStatus())
                .withRequerCertificacao(categoriarequest.getRequerCertificacao())
                .withNivelRisco(categoriarequest.getNivelRisco())
                .withTipoCertificacao(categoriarequest.getTipoCertificacao())
                .withLimit(categoriarequest.getLimit())
                .withOffset(categoriarequest.getOffset());
        List<CategoriaModel> result = queryBuilder.build().join();
        return Optional.ofNullable(result.isEmpty() ? null : result);
    }
}