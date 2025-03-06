package com.dasad.empresa.service;

import com.dasad.empresa.model.EnderecoModel;
import com.dasad.empresa.repository.EnderecoRepository;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
@Log4j2
public class GeolocalizacaoService {
    @Autowired
    private EnderecoRepository enderecoRepository;

    private final RestTemplate restTemplate;
    private static final String NOMINATIM_API_URL = "https://nominatim.openstreetmap.org/search";

    public GeolocalizacaoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public EnderecoModel getCoordinates(EnderecoModel enderecoModel) {
        if (enderecoModel == null) {
            return new EnderecoModel();
        }

        try {
            // Formata o endereço completo
            String fullAddress = formatFullAddress(enderecoModel);
            log.info("Buscando coordenadas para o endereço: {}", fullAddress);

            // Construir URL da API Nominatim
            String url = UriComponentsBuilder.fromUriString(NOMINATIM_API_URL)
                    .queryParam("q", fullAddress)
                    .queryParam("format", "json")
                    .queryParam("limit", "1")
                    .build()
                    .toUriString();

            // Adicionar User-Agent para respeitar as regras de uso da API
            HttpHeaders headers = new HttpHeaders();
            headers.set("User-Agent", "DasadEmpresaApp/1.0");
            HttpEntity<String> entity = new HttpEntity<>(headers);

            // Realizar a requisição usando exchange com ResponseEntity<List<Map<String, Object>>>
            ResponseEntity<List<Map<String, Object>>> responseEntity = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<List<Map<String, Object>>>() {}
            );

            List<Map<String, Object>> response = responseEntity.getBody();

            if (response != null && !response.isEmpty()) {
                Map<String, Object> result = response.get(0);

                double latitude = Double.parseDouble(result.get("lat").toString());
                double longitude = Double.parseDouble(result.get("lon").toString());
                JSONArray jsonArray = new JSONArray(response);

                log.info("Coordenadas encontradas: Latitude: {}, Longitude: {}", latitude, longitude);

                    enderecoModel.setLatitude(BigDecimal.valueOf(latitude));
                    enderecoModel.setLongitude(BigDecimal.valueOf(longitude));
            } else {
                log.warn("Nenhum resultado encontrado para o endereço: {}", fullAddress);
            }

            // Adicionar um delay para respeitar o limite de requisições do Nominatim
            Thread.sleep(1000);

        } catch (Exception e) {
            log.error("Erro ao obter coordenadas para o endereço", e);
        }

        // Remover o ID se for um endereço novo para evitar problemas de chave duplicada
        if (enderecoRepository.findById(enderecoModel.getId()).isEmpty()) {
            enderecoModel.setId(null); // Deixe o banco gerar o ID
        }

        return this.enderecoRepository.update(enderecoModel);
    }

    private String formatFullAddress(EnderecoModel enderecoModel) {
        StringBuilder sb = new StringBuilder();

        if (enderecoModel.getLogradouro() != null) {
            sb.append(enderecoModel.getLogradouro());
        }

        if (enderecoModel.getNumero() != null) {
            sb.append(", ").append(enderecoModel.getNumero());
        }

        if (enderecoModel.getBairro() != null) {
            sb.append(", ").append(enderecoModel.getBairro());
        }

        if (enderecoModel.getCidadeId().getNome() != null) {
            sb.append(", ").append(enderecoModel.getCidadeId().getNome());
        }

        if (enderecoModel.getCidadeId().getEstadoId().getNome() != null) {
            sb.append(", ").append(enderecoModel.getCidadeId().getEstadoId().getNome());
        }

        if (enderecoModel.getCidadeId().getEstadoId().getPaisId().getNome() != null) {
            sb.append(", ").append(enderecoModel.getCidadeId().getEstadoId().getPaisId().getNome());
        }

        return sb.toString();
    }
}