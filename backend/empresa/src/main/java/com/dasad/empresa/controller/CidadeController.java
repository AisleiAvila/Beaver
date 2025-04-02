package com.dasad.empresa.controller;

import com.dasad.empresa.api.CidadeApi;
import com.dasad.empresa.model.CidadeModel;
import com.dasad.empresa.service.CidadeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({"/cidade"})
public class CidadeController implements CidadeApi {

    private final CidadeService cidadeService;

    public CidadeController(CidadeService cidadeService) {
        this.cidadeService = cidadeService;
    }

    @Override
    @GetMapping({"/{id}"})
    public ResponseEntity<CidadeModel> detailCidade(@PathVariable Integer id) {
        return this.cidadeService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Override
    @GetMapping
    public ResponseEntity<List<CidadeModel>> findCidade(@RequestParam(required = false) String nome, @RequestParam(required = false) Integer estadoId) {
        var cidades = this.cidadeService.findAll(nome, estadoId);
        return ResponseEntity.ok(cidades);
    }

}
