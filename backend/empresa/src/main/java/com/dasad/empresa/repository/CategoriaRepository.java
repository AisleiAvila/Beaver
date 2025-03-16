package com.dasad.empresa.repository;

import com.dasad.empresa.model.CategoriaModel;
import com.dasad.empresa.model.CategoriaRequest;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository {

    Optional<List<CategoriaModel>> find(CategoriaRequest categoriarequest);

}
