package com.dasad.empresa.repository;

import com.dasad.empresa.model.StatusServico;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class StatusServicoRepositoryImpl implements StatusServicoRepository {
    private final DSLContext dsl;

    @Autowired
    public StatusServicoRepositoryImpl(DSLContext dsl) {
        this.dsl = dsl;
    }

    @Override
    public Optional<List<com.dasad.empresa.model.StatusServico>> findAll() {
        List<StatusServico> statusList = new ArrayList<>();
        for (StatusServico status : StatusServico.values()) {
            StatusServico statusServico = StatusServico.valueOf(status.name());
            statusList.add(statusServico);
        }
        return Optional.ofNullable(statusList.isEmpty() ? null : statusList);
    }
}