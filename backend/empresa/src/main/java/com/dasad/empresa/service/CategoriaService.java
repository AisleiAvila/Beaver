package com.dasad.empresa.service;

import aj.org.objectweb.asm.commons.Remapper;
import com.dasad.empresa.model.CategoriaModel;
import com.dasad.empresa.model.CategoriaRequest;
import com.dasad.empresa.model.StatusServico;
import com.dasad.empresa.repository.CategoriaRepository;
import com.dasad.empresa.repository.StatusServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private StatusServicoRepository statusServicoRepository;

    public CategoriaService() {
    }

    public Optional<List<CategoriaModel>> find(CategoriaRequest categoriaRequest) {
        return this.categoriaRepository.find(categoriaRequest);
    }

    public Optional<CategoriaModel> create(CategoriaModel categoriaModel) {
        return this.categoriaRepository.create(categoriaModel);
    }

    public void delete(Integer id) {
        this.categoriaRepository.delete(id);
    }

    public Optional<Object> findById(Integer id) {
        return this.categoriaRepository.findById(id);
    }

    public Optional<List<StatusServico>> getStatus() {
        return this.statusServicoRepository.findAll();
    }
}
