package com.dasad.empresa.service;

import com.dasad.empresa.model.StatusServico;
import com.dasad.empresa.repository.StatusServicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoService {

    private final StatusServicoRepository statusServicoRepository;

    /**
     * Construtor que recebe o repositório de StatusServico.
     * Utilizado para injeção de dependência pelo Spring.
     */
    public ServicoService(StatusServicoRepository statusServicoRepository) {
        this.statusServicoRepository = statusServicoRepository;
    }

    public Optional<List<StatusServico>> getStatus() {
        return this.statusServicoRepository.findAll();
    }
}
