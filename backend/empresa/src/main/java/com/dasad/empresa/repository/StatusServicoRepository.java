package com.dasad.empresa.repository;

import com.dasad.empresa.model.StatusServico;

import java.util.List;
import java.util.Optional;

public interface StatusServicoRepository {

    Optional<List<StatusServico>> findAll();

}
