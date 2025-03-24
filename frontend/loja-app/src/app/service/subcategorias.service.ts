import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { StatusServico } from '../enum/status-servico.enum';
import { SubcategoriaRequest } from '../interfaces/subcategoria-request.interface';
import { Categoria } from '../model/categoria.model';
import { Subcategoria } from '../model/subcategoria.model';
import { AuthService } from '../shared/service/auth.service';

@Injectable({
  providedIn: 'root',
})
export class SubcategoriasService {
  private apiUrl = environment.apiUrl + '/subcategoria';
  private http = inject(HttpClient);
  private authService = inject(AuthService);

  private categorias: Categoria[] = [];

  getSubcategorias(
    params: SubcategoriaRequest = {}
  ): Observable<Subcategoria[]> {
    const headers = this.authService.getAuthHeaders();

    // Garante que params sempre seja um objeto JSON
    const body = { ...params };

    return this.http.post<Subcategoria[]>(`${this.apiUrl}/find`, body, {
      headers: headers,
    });
  }

  // getCategoriaById(id: number): Observable<Categoria> {
  //   const categoria = this.categorias.find((cat) => cat.id === id);
  //   return of(categoria!);
  // }

  // createCategoria(categoria: Categoria): Observable<Categoria> {
  //   categoria.id = this.categorias.length + 1;
  //   this.categorias.push(categoria);
  //   return of(categoria);
  // }

  // updateCategoria(id: number, categoria: Categoria): Observable<Categoria> {
  //   const index = this.categorias.findIndex((cat) => cat.id === id);
  //   if (index !== -1) {
  //     this.categorias[index] = categoria;
  //   }
  //   return of(categoria);
  // }

  deleteSubcategoria(id: number): Observable<Subcategoria> {
    const headers = this.authService.getAuthHeaders();

    const url = `${this.apiUrl}/${id}`;

    return this.http.delete<Subcategoria>(url, { headers: headers }).pipe(
      catchError((error) => {
        let errorMessage =
          'Erro ao excluir usuário. Por favor, tente novamente mais tarde.';

        // Verificar se a resposta é JSON ou texto
        if (error.error instanceof ErrorEvent) {
          // Erro do lado do cliente
          errorMessage = `Erro: ${error.error.message}`;
        } else {
          // Erro do lado do servidor
          if (error.error && typeof error.error === 'string') {
            try {
              const parsedError = JSON.parse(error.error);
              if (parsedError.message) {
                errorMessage = parsedError.message;
              }
            } catch (e) {
              console.log('Erro ao excluir usuário:', e);
              errorMessage = error.error;
            }
          } else if (error.error && error.error.message) {
            errorMessage = error.error.message;
          }
        }

        console.error('Erro ao excluir usuário:', errorMessage);
        return throwError(() => new Error(errorMessage));
      })
    );
  }

  getStatus(): Observable<StatusServico[]> {
    const headers = this.authService.getAuthHeaders();

    return this.http.get<StatusServico[]>(`${this.apiUrl}/status`, {
      headers: headers,
    });
  }
}
