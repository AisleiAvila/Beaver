import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { firstValueFrom, map, Observable, of } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CategoriaRequest } from '../interfaces/categoria-request.interface';
import { Categoria } from '../model/categoria.model';
import { AuthService } from './../shared/service/auth.service';
import { StatusServico } from '../enum/status-servico.enum';

@Injectable({
  providedIn: 'root',
})
export class CategoriasService {
  private apiUrl = environment.apiUrl + '/categoria';
  private http = inject(HttpClient);
  private authService = inject(AuthService);

  private categorias: Categoria[] = [
    { id: 1, nome: 'Categoria 1' },
    { id: 2, nome: 'Categoria 2' },
  ];

  getCategorias(params: CategoriaRequest = {}): Observable<Categoria[]> {
    const headers = this.authService.getAuthHeaders();

    // Garante que params sempre seja um objeto JSON
    const body = { ...params };

    // Garante que withSubcategorias seja enviado ao backend, mesmo se for false
    if (params.withSubcategorias === undefined) {
      body.withSubcategorias = false;
    } else {
      body.withSubcategorias = params.withSubcategorias;
    }

    return this.http.post<Categoria[]>(`${this.apiUrl}/find`, body, {
      headers: headers,
    });
  }

  async getCategoriaById(id: number): Promise<Categoria> {
    const headers = this.authService.getAuthHeaders();

    const params: CategoriaRequest = {
      id: id,
      offset: 0,
      limit: 1,
    };

    // Usando firstValueFrom para converter Observable em Promise
    return firstValueFrom(
      this.http
        .post<Categoria[]>(`${this.apiUrl}/find`, params, {
          headers: headers,
        })
        .pipe(
          map((categorias) => {
            if (categorias && categorias.length > 0) {
              return categorias[0];
            }
            return {
              id: 0,
              nome: '',
              status: StatusServico.ATIVO,
              documentos_necessarios: [],
              requer_certificacao: false,
            } as Categoria;
          })
        )
    );
  }

  createCategoria(categoria: Categoria): Observable<Categoria> {
    categoria.id = this.categorias.length + 1;
    this.categorias.push(categoria);
    return of(categoria);
  }

  updateCategoria(id: number, categoria: Categoria): Observable<Categoria> {
    const index = this.categorias.findIndex((cat) => cat.id === id);
    if (index !== -1) {
      this.categorias[index] = categoria;
    }
    return of(categoria);
  }

  deleteCategoria(id: number): Observable<void> {
    this.categorias = this.categorias.filter((cat) => cat.id !== id);
    return of();
  }

  getStatus(): Observable<StatusServico[]> {
    const headers = this.authService.getAuthHeaders();

    return this.http.get<StatusServico[]>(`${this.apiUrl}/status`, {
      headers: headers,
    });
  }
}
