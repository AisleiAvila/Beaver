package com.dasad.empresa.repository;

import com.dasad.empresa.jooq.model.tables.Pais;
import com.dasad.empresa.model.PaisModel;
import org.jooq.DSLContext;
import org.jooq.Record2;
import org.jooq.SelectJoinStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PaisRepositoryImpl implements PaisRepository {
    private final DSLContext dsl;

    @Autowired
    public PaisRepositoryImpl(DSLContext dsl) {
        this.dsl = dsl;
    }

    public List<PaisModel> findAll(String nome) {
        var query = buildBaseQuery();

        Optional.ofNullable(nome)
                .filter(n -> !n.isEmpty())
                .ifPresent(n -> query.where(Pais.PAIS.NOME.likeIgnoreCase("%" + n + "%")));

        return query.fetch(PaisRepositoryImpl::getPaisModel);
    }


    public Optional<PaisModel> findById(Integer id) {
        return Optional.ofNullable(
                buildBaseQuery()
                        .where(Pais.PAIS.ID.eq(id))
                        .fetchOne(PaisRepositoryImpl::getPaisModel)
        );
    }

    private SelectJoinStep<Record2<Integer, String>> buildBaseQuery() {
        return this.dsl.select(
                        Pais.PAIS.ID,
                        Pais.PAIS.NOME
                )
                .from(Pais.PAIS);
    }

    private static PaisModel getPaisModel(Record2<Integer, String> item) {
        if (item == null) {
            return null;
        }

        PaisModel pais = new PaisModel();
        pais.setId(item.get(Pais.PAIS.ID));
        pais.setNome(item.get(Pais.PAIS.NOME));
        return pais;
    }
}