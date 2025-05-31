package com.dasad.empresa.service;

import com.dasad.empresa.model.CidadeModel;
import com.dasad.empresa.repository.CidadeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CidadeService {
    private final CidadeRepository cidadeRepository;

    public CidadeService(CidadeRepository cidadeRepository) {
        this.cidadeRepository = cidadeRepository;
    }

    public List<CidadeModel> findAll(String nome, Integer estadoId) {
        return this.cidadeRepository.findAll(nome, estadoId);
    }

    public Optional<CidadeModel> findById(Integer id) {
        return this.cidadeRepository.findById(id);
    }

}
