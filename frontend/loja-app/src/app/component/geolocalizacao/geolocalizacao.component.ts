import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import {
  GeolocalizacaoService,
  Coordenadas,
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
  ],
})
export class GeolocalizacaoComponent implements OnInit {
  localizacaoAtual: Coordenadas | null = null;
  erroPosicao: string | null = null;
  localizacaoCarregando = false;
  enderecoAtual: string | null = null;

  enderecoBusca = '';
  resultadoGeocodificacao: ResultadoGeocodificacao | null = null;
  erroGeocodificacao: string | null = null;
  buscaCarregando = false;

  constructor(
    private geoService: GeolocalizacaoService,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.obterLocalizacaoAtual();
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
      },
      error: (erro) => {
        this.erroPosicao = `Não foi possível obter sua localização: ${erro.message}`;
        this.localizacaoCarregando = false;
        this.snackBar.open('Erro ao obter localização', 'OK', {
          duration: 5000,
          panelClass: ['snackbar-error'],
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
    this.resultadoGeocodificacao = null;

    this.geoService.obterCoordenadas(this.enderecoBusca).subscribe({
      next: (resultado) => {
        this.resultadoGeocodificacao = resultado;
        this.buscaCarregando = false;
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
}
