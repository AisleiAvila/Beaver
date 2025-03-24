package com.dasad.empresa.controller;

import com.dasad.empresa.api.SubcategoriaApi;
import com.dasad.empresa.model.SubCategoriaModel;
import com.dasad.empresa.model.SubCategoriaRequest;
import com.dasad.empresa.service.SubCategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({"/subcategoria"})
public class SubCategoriaController implements SubcategoriaApi {
    @Autowired
    private SubCategoriaService subCategoriaService;

    public SubCategoriaController() {
    }

    @Override
    @PostMapping("/find")
    public ResponseEntity<List<SubCategoriaModel>> findSubCategoria(SubCategoriaRequest subCategoriaRequest) {
        return this.subCategoriaService.find(subCategoriaRequest).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
