package com.dasad.empresa.service;

import com.dasad.empresa.model.SubCategoriaModel;
import com.dasad.empresa.model.SubCategoriaRequest;
import com.dasad.empresa.repository.SubCategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubCategoriaService {
    @Autowired
    private SubCategoriaRepository subCategoriaRepository;

    public SubCategoriaService() {
    }

    public Optional<List<SubCategoriaModel>> find(SubCategoriaRequest subCategoriaRequest) {
        return this.subCategoriaRepository.find(subCategoriaRequest);
    }


}
