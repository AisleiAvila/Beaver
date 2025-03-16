package com.dasad.empresa.service;

import com.dasad.empresa.model.CategoriaModel;
import com.dasad.empresa.model.CategoriaRequest;
import com.dasad.empresa.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public CategoriaService() {
    }

    public Optional<List<CategoriaModel>> find(CategoriaRequest categoriaRequest) {
        return this.categoriaRepository.find(categoriaRequest);
    }


}
