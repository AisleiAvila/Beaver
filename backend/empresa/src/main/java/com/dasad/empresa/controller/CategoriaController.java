package com.dasad.empresa.controller;

import com.dasad.empresa.api.CategoriaApi;
import com.dasad.empresa.model.CategoriaModel;
import com.dasad.empresa.model.CategoriaRequest;
import com.dasad.empresa.model.StatusServico;
import com.dasad.empresa.service.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({"/categoria"})
public class CategoriaController implements CategoriaApi {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @Override
    @PostMapping("/create")
    public ResponseEntity<CategoriaModel> createCategoria(CategoriaModel categoriaModel) {
        return this.categoriaService.create(categoriaModel).map(ResponseEntity::ok).orElse(ResponseEntity.noContent().build());
    }

    @Override
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteCategoria(Integer id) {
        if (this.categoriaService.findById(id).isPresent()) {
            // TODO: Verificar se há subcategorias associadas a esta categoria
            this.categoriaService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @Override
    @PostMapping("/find")
    public ResponseEntity<List<CategoriaModel>> findCategoria(CategoriaRequest categoriaRequest) {
        return this.categoriaService.find(categoriaRequest).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Override
    @GetMapping("/status")
    public ResponseEntity<List<StatusServico>> findStatusServico() {
        return this.categoriaService.getStatus().map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PutMapping("/update")
    public ResponseEntity<CategoriaModel> updateCategoria(CategoriaModel categoriaModel) {
        return null;
    }
}
