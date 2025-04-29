package com.dasad.empresa.repository;

import com.dasad.empresa.jooq.model.enums.StatusServico;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class StatusServicoRepositoryImpl implements StatusServicoRepository {
    private final DSLContext dsl;

    @Autowired
    public StatusServicoRepositoryImpl(DSLContext dsl) {
        this.dsl = dsl;
    }

    @Override
    public Optional<List<com.dasad.empresa.model.StatusServico>> findAll() {
        List<com.dasad.empresa.model.StatusServico> statusList = Arrays.stream(StatusServico.values())
                .map(status -> com.dasad.empresa.model.StatusServico.valueOf(status.name()))
                .collect(Collectors.toList());
        return Optional.ofNullable(statusList.isEmpty() ? null : statusList);
    }
}