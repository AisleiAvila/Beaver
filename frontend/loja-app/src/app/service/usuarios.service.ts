import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, firstValueFrom, Observable, of, throwError } from 'rxjs';
import { environment } from '../../environments/environment';
import { AuthService } from '../shared/service/auth.service';
import { UsuarioResponseDTO } from '../model/usuarioResponseDTO.model';
import { Usuario } from '../model/usuario.model';
import { Foto } from '../model/foto.model';

@Injectable({
  providedIn: 'root',
})
/**
 * Serviço responsável por realizar a comunicação com a API de usuarios.
 */
export class UsuariosService {
  private apiUrl = environment.apiUrl + '/usuario';
  private apiUrlFoto = environment.apiUrl + '/usuario/foto';
  // private usuarios: any[] = [];

  constructor(private http: HttpClient, private authService: AuthService) {}

  getUsuarioById(id: number): Observable<UsuarioResponseDTO> {
    const headers = this.authService.getAuthHeaders();

    return this.http.get<UsuarioResponseDTO>(`${this.apiUrl}/detail/${id}`, {
      headers: headers,
    });
  }

  // Método para obter usuários com parâmetros opcionais
  // getUsuarios(params: {
  //   nome?: string;
  //   id?: number;
  //   email?: string;
  //   dataNascimento?: string;
  //   perfis?: number[];
  //   limit?: number;
  //   offset?: number;
  // }): Observable<UsuarioResponseDTO> {
  //   const headers = this.authService.getAuthHeaders();

  //   // Garantir que params sempre seja um objeto JSON
  //   const body = { ...params };

  //   return this.http.post<UsuarioResponseDTO>(`${this.apiUrl}/find`, body, {
  //     headers: headers,
  //   });
  // }

  async getUsuarios(params: {
    nome?: string;
    id?: number;
    email?: string;
    dataNascimento?: string;
    perfis?: number[];
    limit?: number;
    offset?: number;
  }): Promise<UsuarioResponseDTO> {
    const headers = this.authService.getAuthHeaders();

    // Garantir que params sempre seja um objeto JSON
    const body = { ...params };

    try {
      return await firstValueFrom(
        this.http.post<UsuarioResponseDTO>(`${this.apiUrl}/find`, body, {
          headers: headers,
        })
      );
    } catch (error) {
      console.error('Erro ao buscar usuários:', error);
      throw error;
    }
  }

  saveUsuario(usuario: Usuario): Observable<Usuario> {
    if (!usuario) {
      return of(usuario);
    }

    if (usuario.id !== undefined && usuario.id !== null && usuario.id > 0) {
      return this.updateUsuario(usuario);
    } else {
      return this.insertUsuario(usuario);
    }
  }

  insertUsuario(usuario: Usuario): Observable<Usuario> {
    const headers = this.authService.getAuthHeaders();

    return this.http
      .post<Usuario>(`${this.apiUrl}`, usuario, { headers: headers })
      .pipe(
        catchError((error) => {
          let errorMessage =
            'Erro ao salvar usuário. Por favor, tente novamente mais tarde.';

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
                console.log('Erro ao salvar usuário:', e);
                errorMessage = error.error;
              }
            } else if (error.error && error.error.message) {
              errorMessage = error.error.message;
            }
          }

          console.error('Erro ao salvar usuário:', errorMessage);
          return throwError(() => new Error(errorMessage));
        })
      );
  }

  updateUsuario(usuario: Usuario): Observable<Usuario> {
    const headers = this.authService.getAuthHeaders();

    return this.http
      .patch<Usuario>(this.apiUrl, usuario, { headers: headers })
      .pipe(
        catchError((error) => {
          let errorMessage =
            'Erro ao atualizar usuário. Por favor, tente novamente mais tarde.';

          if (error.error instanceof ErrorEvent) {
            errorMessage = `Erro: ${error.error.message}`;
          } else {
            if (error.error && typeof error.error === 'string') {
              try {
                const parsedError = JSON.parse(error.error);
                if (parsedError.message) {
                  errorMessage = parsedError.message;
                }
              } catch (e) {
                console.log('Erro ao atualizar usuário:', e);
                errorMessage = error.error;
              }
            } else if (error.error && error.error.message) {
              errorMessage = error.error.message;
            }
          }

          return throwError(() => new Error(errorMessage));
        })
      );
  }

  deleteUsuario(params: { id: number }): Observable<Usuario> {
    const headers = this.authService.getAuthHeaders();

    // Incluindo o ID na URL
    const url = `${this.apiUrl}/${params.id}`;
    console.log('url', url);

    return this.http.delete<Usuario>(url, { headers: headers }).pipe(
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

  saveUsuarioFoto(foto: Foto): Observable<Foto> {
    if (!foto) {
      return of(foto);
    }

    if (!foto.usuario_id) {
      return throwError(() => new Error('ID do usuário não informado.'));
    }

    if (!foto.foto) {
      return throwError(() => new Error('Foto não informada.'));
    }

    if (foto.id !== undefined && foto.id !== null && foto.id > 0) {
      return this.updateUsuarioFoto(foto);
    } else {
      return this.insertUsuarioFoto(foto);
    }
  }

  insertUsuarioFoto(foto: Foto): Observable<Foto> {
    const headers = this.authService.getAuthHeaders();

    return this.http
      .post<Foto>(`${this.apiUrlFoto}`, foto, { headers: headers })
      .pipe(
        catchError((error) => {
          let errorMessage =
            'Erro ao salvar foto do usuário. Por favor, tente novamente mais tarde.';

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
                console.log('Erro ao salvar foto do usuário:', e);
                errorMessage = error.error;
              }
            } else if (error.error && error.error.message) {
              errorMessage = error.error.message;
            }
          }

          console.error('Erro ao salvar foto do usuário:', errorMessage);
          return throwError(() => new Error(errorMessage));
        })
      );
  }

  updateUsuarioFoto(foto: Foto): Observable<Foto> {
    const headers = this.authService.getAuthHeaders();

    return this.http
      .patch<Foto>(`${this.apiUrlFoto}`, foto, { headers: headers })
      .pipe(
        catchError((error) => {
          let errorMessage =
            'Erro ao salvar foto do usuário. Por favor, tente novamente mais tarde.';

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
                console.log('Erro ao salvar foto do usuário:', e);
                errorMessage = error.error;
              }
            } else if (error.error && error.error.message) {
              errorMessage = error.error.message;
            }
          }

          console.error('Erro ao salvar foto do usuário:', errorMessage);
          return throwError(() => new Error(errorMessage));
        })
      );
  }

  deleteUsuarioFoto(foto: Foto): Observable<Foto> {
    if (!foto) {
      return of(foto);
    }

    if (!foto.usuario_id) {
      return throwError(() => new Error('ID do usuário não informado.'));
    }

    if (foto.id === undefined || foto.id === null || foto.id == 0) {
      return of(foto);
    }

    const headers = this.authService.getAuthHeaders();

    return this.http.delete<Foto>(this.apiUrlFoto, { headers: headers }).pipe(
      catchError((error) => {
        let errorMessage =
          'Erro ao excluir foto do usuário. Por favor, tente novamente mais tarde.';

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
              console.log('Erro ao excluir foto do usuário:', e);
              errorMessage = error.error;
            }
          } else if (error.error && error.error.message) {
            errorMessage = error.error.message;
          }
        }

        console.error('Erro ao excluir foto do usuário:', errorMessage);
        return throwError(() => new Error(errorMessage));
      })
    );
  }
}
