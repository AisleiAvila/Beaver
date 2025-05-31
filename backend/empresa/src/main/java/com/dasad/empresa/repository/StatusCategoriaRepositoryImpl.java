package com.dasad.empresa.repository;

import com.dasad.empresa.model.StatusCategoria;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class StatusCategoriaRepositoryImpl implements StatusCategoriaRepository {
    private final DSLContext dsl;

    @Autowired
    public StatusCategoriaRepositoryImpl(DSLContext dsl) {
        this.dsl = dsl;
    }

    @Override
    public Optional<List<StatusCategoria>> findAll() {
        List<StatusCategoria> statusList = new ArrayList<>();
        for (StatusCategoria status : StatusCategoria.values()) {
            StatusCategoria statusCategoria = StatusCategoria.valueOf(status.name());
            statusList.add(statusCategoria);
        }
        return Optional.ofNullable(statusList.isEmpty() ? null : statusList);
    }
}