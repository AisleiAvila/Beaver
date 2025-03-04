import { TestBed } from '@angular/core/testing';
import {
  HttpClientTestingModule,
  HttpTestingController,
} from '@angular/common/http/testing';

import { GeolocalizacaoService } from './geolocalizacao.service';

describe('GeolocalizacaoService', () => {
  let service: GeolocalizacaoService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [GeolocalizacaoService],
    });
    service = TestBed.inject(GeolocalizacaoService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('deve ser criado', () => {
    expect(service).toBeTruthy();
  });

  it('deve calcular corretamente a distância entre dois pontos', () => {
    // São Paulo -> Rio de Janeiro (aproximadamente 360 km em linha reta)
    const distancia = service.calcularDistancia(
      -23.5505,
      -46.6333,
      -22.9068,
      -43.1729
    );
    expect(distancia).toBeCloseTo(360, -1); // Precisão de dezenas
  });
});
