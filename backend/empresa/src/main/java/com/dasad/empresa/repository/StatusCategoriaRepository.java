package com.dasad.empresa.repository;

import com.dasad.empresa.model.StatusCategoria;

import java.util.List;
import java.util.Optional;

public interface StatusCategoriaRepository {

    Optional<List<StatusCategoria>> findAll();

}
