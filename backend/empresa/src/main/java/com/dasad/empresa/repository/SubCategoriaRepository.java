package com.dasad.empresa.repository;

import com.dasad.empresa.model.SubCategoriaModel;
import com.dasad.empresa.model.SubCategoriaRequest;

import java.util.List;
import java.util.Optional;

public interface SubCategoriaRepository {

    Optional<List<SubCategoriaModel>> find(SubCategoriaRequest categoriarequest);

}
