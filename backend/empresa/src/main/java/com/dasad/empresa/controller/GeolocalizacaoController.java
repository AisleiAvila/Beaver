package com.dasad.empresa.controller;

import com.dasad.empresa.api.GeolocalizacaoApi;
import com.dasad.empresa.model.EnderecoModel;
import com.dasad.empresa.service.GeolocalizacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/geolocalizacao"})
public class GeolocalizacaoController implements GeolocalizacaoApi {

    @Autowired
    private GeolocalizacaoService geolocalizacaoService;

    public GeolocalizacaoController() {
    }

    @Override
    @PostMapping
    public ResponseEntity<EnderecoModel> getCoordenadas(EnderecoModel enderecoModel) {
        if (enderecoModel == null) {
            return ResponseEntity.badRequest().build();
        }

        var endereco = this.geolocalizacaoService.getCoordinates(enderecoModel);
        return ResponseEntity.ok(endereco);
    }
}
