import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, from, of, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

export interface Coordenadas {
  latitude: number;
  longitude: number;
  precisao?: number; // Precisão em metros
}

export interface Endereco {
  logradouro: string;
  numero?: string;
  complemento?: string;
  bairro?: string;
  cidade: string;
  estado: string;
  cep?: string;
  pais?: string;
}

export interface ResultadoGeocodificacao {
  endereco: string;
  coordenadas: Coordenadas;
  enderecoCompleto?: Endereco;
}

@Injectable({
  providedIn: 'root',
})
export class GeolocalizacaoService {
  private geocodingApiUrl = 'https://maps.googleapis.com/maps/api/geocode/json';
  private googleMapsApiKey = ''; // Insira sua chave API do Google Maps

  constructor(private http: HttpClient) {}

  /**
   * Obtém a localização atual do usuário usando a API de Geolocalização do navegador
   * @returns Observable com as coordenadas (latitude e longitude)
   */
  obterLocalizacaoUsuario(): Observable<Coordenadas> {
    // Verificar se o navegador suporta geolocalização
    if (!navigator.geolocation) {
      return throwError(
        () => new Error('Navegador não suporta geolocalização')
      );
    }

    return from(
      new Promise<Coordenadas>((resolve, reject) => {
        navigator.geolocation.getCurrentPosition(
          (position) => {
            resolve({
              latitude: position.coords.latitude,
              longitude: position.coords.longitude,
              precisao: position.coords.accuracy,
            });
          },
          (error) => {
            let mensagemErro = 'Erro desconhecido ao obter localização';

            switch (error.code) {
              case error.PERMISSION_DENIED:
                mensagemErro = 'Usuário negou a solicitação de geolocalização';
                break;
              case error.POSITION_UNAVAILABLE:
                mensagemErro = 'Informação de localização indisponível';
                break;
              case error.TIMEOUT:
                mensagemErro = 'Tempo limite excedido para obter localização';
                break;
            }

            reject(new Error(mensagemErro));
          },
          {
            enableHighAccuracy: true,
            timeout: 10000,
            maximumAge: 0,
          }
        );
      })
    ).pipe(catchError((error) => throwError(() => error)));
  }

  /**
   * Converte um endereço em coordenadas geográficas (latitude e longitude)
   * @param endereco Endereço completo ou parcial para geocodificação
   * @returns Observable com o resultado da geocodificação
   */
  obterCoordenadas(endereco: string): Observable<ResultadoGeocodificacao> {
    // Se a chave API não estiver configurada, retorna erro
    if (!this.googleMapsApiKey) {
      return throwError(
        () => new Error('API Key do Google Maps não configurada')
      );
    }

    // Constrói os parâmetros da requisição
    const params = {
      address: endereco,
      key: this.googleMapsApiKey,
    };

    return this.http.get(this.geocodingApiUrl, { params }).pipe(
      map((response: any) => {
        if (
          response.status === 'OK' &&
          response.results &&
          response.results.length > 0
        ) {
          const resultado = response.results[0];
          const location = resultado.geometry.location;

          // Extraindo componentes do endereço
          const enderecoCompleto: Endereco = {
            logradouro: this.extrairComponenteEndereco(
              resultado,
              'route',
              'long_name',
              ''
            ),
            numero: this.extrairComponenteEndereco(
              resultado,
              'street_number',
              'long_name',
              ''
            ),
            bairro: this.extrairComponenteEndereco(
              resultado,
              'sublocality',
              'long_name',
              ''
            ),
            cidade: this.extrairComponenteEndereco(
              resultado,
              'administrative_area_level_2',
              'long_name',
              ''
            ),
            estado: this.extrairComponenteEndereco(
              resultado,
              'administrative_area_level_1',
              'short_name',
              ''
            ),
            cep: this.extrairComponenteEndereco(
              resultado,
              'postal_code',
              'long_name',
              ''
            ),
            pais: this.extrairComponenteEndereco(
              resultado,
              'country',
              'long_name',
              ''
            ),
          };

          return {
            endereco: resultado.formatted_address,
            coordenadas: {
              latitude: location.lat,
              longitude: location.lng,
            },
            enderecoCompleto: enderecoCompleto,
          };
        } else {
          throw new Error('Endereço não encontrado');
        }
      }),
      catchError((error) => {
        console.error('Erro na geocodificação:', error);
        return throwError(
          () => new Error('Erro ao buscar coordenadas para o endereço')
        );
      })
    );
  }

  /**
   * Converte coordenadas geográficas em um endereço (geocodificação reversa)
   * @param latitude Latitude
   * @param longitude Longitude
   * @returns Observable com o endereço correspondente
   */
  obterEnderecoPorCoordenadas(
    latitude: number,
    longitude: number
  ): Observable<ResultadoGeocodificacao> {
    // Se a chave API não estiver configurada, retorna erro
    if (!this.googleMapsApiKey) {
      return throwError(
        () => new Error('API Key do Google Maps não configurada')
      );
    }

    // Constrói os parâmetros da requisição
    const params = {
      latlng: `${latitude},${longitude}`,
      key: this.googleMapsApiKey,
    };

    return this.http.get(this.geocodingApiUrl, { params }).pipe(
      map((response: any) => {
        if (
          response.status === 'OK' &&
          response.results &&
          response.results.length > 0
        ) {
          const resultado = response.results[0];

          // Extraindo componentes do endereço
          const enderecoCompleto: Endereco = {
            logradouro: this.extrairComponenteEndereco(
              resultado,
              'route',
              'long_name',
              ''
            ),
            numero: this.extrairComponenteEndereco(
              resultado,
              'street_number',
              'long_name',
              ''
            ),
            bairro: this.extrairComponenteEndereco(
              resultado,
              'sublocality',
              'long_name',
              ''
            ),
            cidade: this.extrairComponenteEndereco(
              resultado,
              'administrative_area_level_2',
              'long_name',
              ''
            ),
            estado: this.extrairComponenteEndereco(
              resultado,
              'administrative_area_level_1',
              'short_name',
              ''
            ),
            cep: this.extrairComponenteEndereco(
              resultado,
              'postal_code',
              'long_name',
              ''
            ),
            pais: this.extrairComponenteEndereco(
              resultado,
              'country',
              'long_name',
              ''
            ),
          };

          return {
            endereco: resultado.formatted_address,
            coordenadas: {
              latitude,
              longitude,
            },
            enderecoCompleto: enderecoCompleto,
          };
        } else {
          throw new Error(
            'Endereço não encontrado para as coordenadas fornecidas'
          );
        }
      }),
      catchError((error) => {
        console.error('Erro na geocodificação reversa:', error);
        return throwError(
          () => new Error('Erro ao buscar endereço para as coordenadas')
        );
      })
    );
  }

  /**
   * Calcula a distância em km entre duas coordenadas usando a fórmula de Haversine
   * @param lat1 Latitude do ponto 1
   * @param lon1 Longitude do ponto 1
   * @param lat2 Latitude do ponto 2
   * @param lon2 Longitude do ponto 2
   * @returns Distância em quilômetros
   */
  calcularDistancia(
    lat1: number,
    lon1: number,
    lat2: number,
    lon2: number
  ): number {
    const raioTerra = 6371; // Raio da Terra em km
    const dLat = this.toRadians(lat2 - lat1);
    const dLon = this.toRadians(lon2 - lon1);

    const a =
      Math.sin(dLat / 2) * Math.sin(dLat / 2) +
      Math.cos(this.toRadians(lat1)) *
        Math.cos(this.toRadians(lat2)) *
        Math.sin(dLon / 2) *
        Math.sin(dLon / 2);

    const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    const distancia = raioTerra * c;

    return distancia;
  }

  /**
   * Converte graus para radianos
   */
  private toRadians(graus: number): number {
    return graus * (Math.PI / 180);
  }

  /**
   * Extrai componentes específicos do endereço retornado pela API de Geocodificação do Google
   */
  private extrairComponenteEndereco(
    resultadoGeocode: any,
    tipo: string,
    nomeFormato: string,
    valorPadrao: string
  ): string {
    if (!resultadoGeocode.address_components) {
      return valorPadrao;
    }

    const componente = resultadoGeocode.address_components.find(
      (component: any) => component.types.indexOf(tipo) !== -1
    );

    return componente ? componente[nomeFormato] : valorPadrao;
  }

  /**
   * Verifica se a API Key do Google Maps está configurada
   * @returns boolean indicando se a chave está configurada
   */
  temChaveGoogleMapsConfigiurada(): boolean {
    return !!this.googleMapsApiKey && this.googleMapsApiKey.length > 0;
  }
}
