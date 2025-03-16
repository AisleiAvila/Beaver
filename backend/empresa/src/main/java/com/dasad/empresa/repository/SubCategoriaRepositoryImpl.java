package com.dasad.empresa.repository;

import com.dasad.empresa.model.SubCategoriaModel;
import com.dasad.empresa.model.SubCategoriaRequest;
import com.dasad.empresa.repository.query.SubCategoriaQueryBuilder;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SubCategoriaRepositoryImpl implements SubCategoriaRepository {
    private final DSLContext dsl;

    @Autowired
    public SubCategoriaRepositoryImpl(DSLContext dsl) {
        this.dsl = dsl;
    }

    @Override
    public Optional<List<SubCategoriaModel>> find(SubCategoriaRequest subCategoriarequest) {
        SubCategoriaQueryBuilder queryBuilder = new SubCategoriaQueryBuilder(this.dsl)
                .withId(subCategoriarequest.getId())
                .withCategoriaId(subCategoriarequest.getCategoriaId())
                .withNome(subCategoriarequest.getNome())
                .withStatus(subCategoriarequest.getStatus())
                .withNivelComlexidade(subCategoriarequest.getNivelMedioComplexidade())
                .withLimit(subCategoriarequest.getLimit())
                .withOffset(subCategoriarequest.getOffset());
        List<SubCategoriaModel> result = queryBuilder.build().join();
        return Optional.ofNullable(result.isEmpty() ? null : result);
    }


}