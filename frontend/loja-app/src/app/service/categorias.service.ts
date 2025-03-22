import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CategoriaRequest } from '../interfaces/categoria-request.interface';
import { Categoria } from '../model/categoria.model';
import { AuthService } from './../shared/service/auth.service';

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

  // getCategorias(): Observable<Categoria[]> {
  //   return of(this.categorias);
  // }

  getCategorias(params: CategoriaRequest = {}): Observable<CategoriaRequest[]> {
    alert('getCategorias --> params: ' + JSON.stringify(params));
    const headers = this.authService.getAuthHeaders();

    // Garante que params sempre seja um objeto JSON
    const body = { ...params };

    return this.http.post<Categoria[]>(`${this.apiUrl}/find`, body, {
      headers: headers,
    });
  }

  getCategoriaById(id: number): Observable<Categoria> {
    const categoria = this.categorias.find((cat) => cat.id === id);
    return of(categoria!);
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
}
