package com.dasad.empresa.controller;

import com.dasad.empresa.api.ServicoApi;
import com.dasad.empresa.model.StatusServico;
import com.dasad.empresa.service.ServicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({"/servico"})
public class ServicoController implements ServicoApi {

    private final ServicoService servicoService;

    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @Override
    @GetMapping("/status")
    public ResponseEntity<List<StatusServico>> findStatusServico() {
        return this.servicoService.getStatus().map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

}
