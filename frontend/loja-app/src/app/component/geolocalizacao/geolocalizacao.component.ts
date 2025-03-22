import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import {
  AfterViewInit,
  Component,
  inject,
  OnInit,
  ViewChild,
} from '@angular/core';
import { FormsModule } from '@angular/forms';
import { GoogleMap, MapMarker } from '@angular/google-maps';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { catchError, map, Observable, of } from 'rxjs';

import {
  Coordenadas,
  GeolocalizacaoService,
  ResultadoGeocodificacao,
} from '../../services/geolocalizacao.service';

@Component({
  selector: 'app-geolocalizacao',
  templateUrl: './geolocalizacao.component.html',
  styleUrls: ['./geolocalizacao.component.scss'],
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatButtonModule,
    MatCardModule,
    MatDividerModule,
    MatFormFieldModule,
    MatIconModule,
    MatInputModule,
    MatProgressSpinnerModule,
    MatSnackBarModule,
    GoogleMap,
    MapMarker,
  ],
})
export class GeolocalizacaoComponent implements OnInit, AfterViewInit {
  httpClient = inject(HttpClient);
  geoService = inject(GeolocalizacaoService);
  snackBar = inject(MatSnackBar);

  @ViewChild(GoogleMap) map!: GoogleMap;

  // Propriedades existentes
  localizacaoAtual: Coordenadas | null = null;
  erroPosicao: string | null = null;
  localizacaoCarregando = false;
  enderecoAtual: string | null = null;

  enderecoBusca = '';
  resultadoGeocodificacao: ResultadoGeocodificacao | null = null;
  erroGeocodificacao: string | null = null;
  buscaCarregando = false;

  // Propriedades do Google Maps
  apiCarregada = false;
  center: google.maps.LatLngLiteral = { lat: -15.7801, lng: -47.9292 }; // Centro padrão (Brasília)
  zoom = 12;
  mapOptions: google.maps.MapOptions = {
    mapTypeId: 'roadmap',
    zoomControl: true,
    scrollwheel: true,
    disableDoubleClickZoom: true,
    maxZoom: 18,
    minZoom: 2,
  };
  markers: any[] = [];
  mapCarregado = false;

  ngOnInit(): void {
    // Verificar se a API do Google Maps está disponível
    this.verificarDisponibilidadeAPI().subscribe((disponivel) => {
      if (disponivel) {
        this.apiCarregada = true;
        this.mapCarregado = true;
        // Inicializa a localização do usuário assim que o componente é carregado
        this.obterLocalizacaoAtual();
      } else {
        this.snackBar.open(
          'Não foi possível carregar a API do Google Maps',
          'OK',
          {
            duration: 5000,
          }
        );
        this.erroPosicao = 'API do Google Maps não disponível';
      }
    });
  }

  ngAfterViewInit(): void {
    // Código que depende do mapa estar inicializado
    if (this.map && this.apiCarregada) {
      // Configurações adicionais do mapa, se necessário
    }
  }

  verificarDisponibilidadeAPI(): Observable<boolean> {
    // Verifica se a API está disponível usando JSONP
    return this.httpClient
      .jsonp(
        'https://maps.googleapis.com/maps/api/js?key=' +
          this.geoService.getApiKey(),
        'callback'
      )
      .pipe(
        map(() => true),
        catchError(() => of(false))
      );
  }

  obterLocalizacaoAtual(): void {
    this.localizacaoCarregando = true;
    this.erroPosicao = null;
    this.enderecoAtual = null;

    this.geoService.obterLocalizacaoUsuario().subscribe({
      next: (coordenadas) => {
        this.localizacaoAtual = coordenadas;
        this.obterEnderecoAtual(coordenadas.latitude, coordenadas.longitude);
        this.localizacaoCarregando = false;

        // Atualiza o mapa com a localização do usuário
        this.center = {
          lat: coordenadas.latitude,
          lng: coordenadas.longitude,
        };

        // Limpa marcadores anteriores e adiciona um para a localização atual
        this.markers = [];
        this.adicionarMarcador(
          coordenadas.latitude,
          coordenadas.longitude,
          'Sua localização',
          'blue'
        );
      },
      error: (erro) => {
        this.erroPosicao = `Não foi possível obter sua localização: ${erro.message}`;
        this.localizacaoCarregando = false;
        this.snackBar.open('Erro ao obter localização', 'OK', {
          duration: 5000,
        });
      },
    });
  }

  obterEnderecoAtual(latitude: number, longitude: number): void {
    if (!this.geoService.temChaveGoogleMapsConfigiurada()) {
      return;
    }

    this.geoService.obterEnderecoPorCoordenadas(latitude, longitude).subscribe({
      next: (resultado) => {
        this.enderecoAtual = resultado.endereco;
      },
      error: () => {
        this.enderecoAtual = null;
      },
    });
  }

  buscarCoordenadas(): void {
    if (!this.enderecoBusca.trim()) {
      this.snackBar.open('Digite um endereço para buscar', 'OK', {
        duration: 3000,
      });
      return;
    }

    if (!this.geoService.temChaveGoogleMapsConfigiurada()) {
      this.erroGeocodificacao =
        'Busca de endereços não está disponível. API Key do Google Maps não configurada.';
      return;
    }

    this.buscaCarregando = true;
    this.erroGeocodificacao = null;

    this.geoService.obterCoordenadas(this.enderecoBusca).subscribe({
      next: (resultado) => {
        this.resultadoGeocodificacao = resultado;
        this.buscaCarregando = false;

        // Adiciona marcador para o resultado da busca
        this.adicionarMarcador(
          resultado.coordenadas.latitude,
          resultado.coordenadas.longitude,
          resultado.endereco,
          'red'
        );

        // Ajusta o mapa para exibir ambos os marcadores se houver localização atual
        if (this.localizacaoAtual) {
          this.ajustarZoomParaVerTodosOsMarcadores();
        } else {
          // Se não houver localização atual, centraliza no endereço buscado
          this.center = {
            lat: resultado.coordenadas.latitude,
            lng: resultado.coordenadas.longitude,
          };
          this.zoom = 15;
        }
      },
      error: (erro) => {
        this.erroGeocodificacao = `Erro na busca: ${erro.message}`;
        this.buscaCarregando = false;
      },
    });
  }

  limparBusca(): void {
    this.enderecoBusca = '';
    this.resultadoGeocodificacao = null;
    this.erroGeocodificacao = null;

    // Remove o marcador do endereço buscado
    this.markers = this.markers.filter((marker) => marker.icone !== 'red');

    // Centraliza no usuário se disponível
    if (this.localizacaoAtual) {
      this.center = {
        lat: this.localizacaoAtual.latitude,
        lng: this.localizacaoAtual.longitude,
      };
      this.zoom = 15;
    }
  }

  calcularDistanciaEntrePontos(): number {
    if (!this.localizacaoAtual || !this.resultadoGeocodificacao) {
      return 0;
    }

    return this.geoService.calcularDistancia(
      this.localizacaoAtual.latitude,
      this.localizacaoAtual.longitude,
      this.resultadoGeocodificacao.coordenadas.latitude,
      this.resultadoGeocodificacao.coordenadas.longitude
    );
  }

  // Métodos adaptados para usar o AdvancedMarkerElement ou contornar o aviso de depreciação
  adicionarMarcador(
    lat: number,
    lng: number,
    titulo: string,
    icone: string
  ): void {
    const novoMarcador = {
      position: {
        lat: lat,
        lng: lng,
      },
      titulo: titulo,
      icone: icone,
      options: {
        title: titulo,
        animation: google.maps.Animation.DROP,
        icon: this.obterIconeMarcador(icone),
      },
    };

    // Remove marcadores com a mesma cor (substitui em vez de adicionar)
    this.markers = this.markers.filter((marker) => marker.icone !== icone);
    this.markers.push(novoMarcador);
  }

  obterIconeMarcador(cor: string): google.maps.Icon {
    return {
      url: `https://maps.google.com/mapfiles/ms/icons/${cor}-dot.png`,
      scaledSize: new google.maps.Size(32, 32),
    };
  }

  ajustarZoomParaVerTodosOsMarcadores(): void {
    if (this.markers.length <= 1 || !this.map) {
      return;
    }

    const bounds = new google.maps.LatLngBounds();
    this.markers.forEach((marker) => {
      bounds.extend(marker.position);
    });

    this.map.fitBounds(bounds);

    // Centraliza o mapa com base nos limites calculados
    this.center = {
      lat: bounds.getCenter().lat(),
      lng: bounds.getCenter().lng(),
    };
  }
}
