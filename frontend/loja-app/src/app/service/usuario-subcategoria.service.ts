import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { Observable, forkJoin, of } from 'rxjs';
import { environment } from '../../environments/environment';
import { UsuarioSubcategoria } from '../model/usuarioSubcategoria.model';
import { AuthService } from '../shared/service/auth.service';
import { ModalCommunicationService } from './modal-communication.service';
import { UsuarioSubcategoriaRequest } from '../model/usuarioSubcategoriaRequest.model';

@Injectable({
  providedIn: 'root',
})
/**
 * Serviço responsável por realizar a comunicação com a API de usuarios.
 */
export class UsuarioSubcategoriaService {
  translate = inject(TranslateService);
  modalService = inject(ModalCommunicationService);

  private apiUrl = environment.apiUrl + '/usuario-subcategoria';

  constructor(private http: HttpClient, private authService: AuthService) {}

  getUsuarioSubcategoriaByUsuarioId(
    id: number,
    ativo: boolean
  ): Observable<UsuarioSubcategoria[]> {
    const headers = this.authService.getAuthHeaders();

    return this.http.get<UsuarioSubcategoria[]>(
      `${this.apiUrl}?usuario_id=${id}&ativo=${ativo}`,
      {
        headers: headers,
      }
    );
  }

  salvarAssociacoes(
    selecionadas: number[],
    associadas: number[],
    usuarioId: number
  ): Observable<any> {
    const subcategoriasParaSalvar = Array.isArray(selecionadas)
      ? selecionadas.filter(
          (subcategoria) => !associadas?.includes(subcategoria)
        )
      : [];

    const subcategoriasParaRemover = associadas.filter(
      (subcategoria) => !selecionadas.includes(subcategoria)
    );

    const observables: Observable<any>[] = [];

    if (usuarioId && subcategoriasParaSalvar.length > 0) {
      observables.push(
        this.associarUsuarioSubcategoria(usuarioId, subcategoriasParaSalvar)
      );
    }

    if (usuarioId && subcategoriasParaRemover.length > 0) {
      observables.push(
        this.deassociarUsuarioSubcategoria(usuarioId, subcategoriasParaRemover)
      );
    }

    return observables.length > 0 ? forkJoin(observables) : of([]);
  }

  private associarUsuarioSubcategoria(
    usuarioId: number,
    subcategorias: number[]
  ): Observable<void[]> {
    const headers = this.authService.getAuthHeaders();
    // Retorna um array de observables para cada requisição
    const requests = subcategorias.map((subcategoriaId) => {
      const usuarioSubcategoria: UsuarioSubcategoriaRequest = {
        usuarioId: usuarioId,
        subcategoriaId: subcategoriaId,
      };
      // Adiciona subscribe para debug
      const obs = this.http.post<void>(this.apiUrl, usuarioSubcategoria, {
        headers,
      });
      obs.subscribe({
        next: () => console.log('POST sucesso', usuarioSubcategoria),
        error: (err) => console.error('POST erro', usuarioSubcategoria, err),
      });
      return obs;
    });
    // Retorna um único observable que emite quando todas as requisições terminarem
    return requests.length ? forkJoin(requests) : of([]);
  }

  private deassociarUsuarioSubcategoria(
    usuarioId: number,
    subcategorias: number[]
  ): Observable<void[]> {
    const headers = this.authService.getAuthHeaders();
    // Retorna um array de observables para cada requisição
    const requests = subcategorias.map((subcategoriaId) => {
      return this.http.put<void>(
        `${this.apiUrl}/${usuarioId}/${subcategoriaId}`,
        {
          headers: headers,
        }
      );
    });
    // Retorna um único observable que emite quando todas as requisições terminarem
    return requests.length ? forkJoin(requests) : of([]);
  }

  // async getUsuarios(params: {
  //   nome?: string;
  //   id?: number;
  //   email?: string;
  //   dataNascimento?: string;
  //   perfis?: number[];
  //   limit?: number;
  //   offset?: number;
  // }): Promise<UsuarioResponseDTO> {
  //   const headers = this.authService.getAuthHeaders();

  //   // Adicionar ao body o idOrganizacao
  //   const organizacaoId = localStorage.getItem('organizacaoId');
  //   if (!organizacaoId) {
  //     throw new Error('ID da organização não encontrado no localStorage');
  //   }

  //   // Garantir que params sempre seja um objeto JSON
  //   const body = { ...params };

  //   const options = {
  //     headers: headers,
  //     params: {
  //       organizaoId: Number(organizacaoId),
  //     },
  //   };

  //   try {
  //     return await firstValueFrom(
  //       this.http.post<UsuarioResponseDTO>(`${this.apiUrl}/find`, body, options)
  //     );
  //   } catch (error) {
  //     console.error('Erro ao buscar usuários:', error);
  //     throw error;
  //   }
  // }

  // saveUsuario(usuario: Usuario): Observable<Usuario> {
  //   if (!usuario) {
  //     return of(usuario);
  //   }

  //   if (usuario.id !== undefined && usuario.id !== null && usuario.id > 0) {
  //     return this.updateUsuario(usuario);
  //   } else {
  //     return this.insertUsuario(usuario);
  //   }
  // }

  // insertUsuario(usuario: Usuario): Observable<Usuario> {
  //   const headers = this.authService.getAuthHeaders();

  //   return this.http
  //     .post<Usuario>(`${this.apiUrl}`, usuario, { headers: headers })
  //     .pipe(
  //       catchError((error) => {
  //         let errorMessage =
  //           'Erro ao salvar usuário. Por favor, tente novamente mais tarde.';

  //         // Verificar se a resposta é JSON ou texto
  //         if (error.error instanceof ErrorEvent) {
  //           // Erro do lado do cliente
  //           errorMessage = `Erro: ${error.error.message}`;
  //         } else {
  //           // Erro do lado do servidor
  //           if (error.error && typeof error.error === 'string') {
  //             try {
  //               const parsedError = JSON.parse(error.error);
  //               if (parsedError.message) {
  //                 errorMessage = parsedError.message;
  //               }
  //             } catch (e) {
  //               console.log('Erro ao salvar usuário:', e);
  //               errorMessage = error.error;
  //             }
  //           } else if (error.error && error.error.message) {
  //             errorMessage = error.error.message;
  //           }
  //         }

  //         console.error('Erro ao salvar usuário:', errorMessage);
  //         return throwError(() => new Error(errorMessage));
  //       })
  //     );
  // }

  // updateUsuario(usuario: Usuario): Observable<Usuario> {
  //   const headers = this.authService.getAuthHeaders();

  //   return this.http
  //     .patch<Usuario>(this.apiUrl, usuario, { headers: headers })
  //     .pipe(
  //       catchError((error) => {
  //         let errorMessage =
  //           'Erro ao atualizar usuário. Por favor, tente novamente mais tarde.';

  //         if (error.error instanceof ErrorEvent) {
  //           errorMessage = `Erro: ${error.error.message}`;
  //         } else {
  //           if (error.error && typeof error.error === 'string') {
  //             try {
  //               const parsedError = JSON.parse(error.error);
  //               if (parsedError.message) {
  //                 errorMessage = parsedError.message;
  //               }
  //             } catch (e) {
  //               console.log('Erro ao atualizar usuário:', e);
  //               errorMessage = error.error;
  //             }
  //           } else if (error.error && error.error.message) {
  //             errorMessage = error.error.message;
  //           }
  //         }

  //         return throwError(() => new Error(errorMessage));
  //       })
  //     );
  // }

  // deleteUsuario(params: { id: number }): Observable<Usuario> {
  //   const headers = this.authService.getAuthHeaders();

  //   // Incluindo o ID na URL
  //   const url = `${this.apiUrl}/${params.id}`;
  //   console.log('url', url);

  //   return this.http.delete<Usuario>(url, { headers: headers }).pipe(
  //     catchError((error) => {
  //       let errorMessage =
  //         'Erro ao excluir usuário. Por favor, tente novamente mais tarde.';

  //       // Verificar se a resposta é JSON ou texto
  //       if (error.error instanceof ErrorEvent) {
  //         // Erro do lado do cliente
  //         errorMessage = `Erro: ${error.error.message}`;
  //       } else {
  //         // Erro do lado do servidor
  //         if (error.error && typeof error.error === 'string') {
  //           try {
  //             const parsedError = JSON.parse(error.error);
  //             if (parsedError.message) {
  //               errorMessage = parsedError.message;
  //             }
  //           } catch (e) {
  //             console.log('Erro ao excluir usuário:', e);
  //             errorMessage = error.error;
  //           }
  //         } else if (error.error && error.error.message) {
  //           errorMessage = error.error.message;
  //         }
  //       }

  //       console.error('Erro ao excluir usuário:', errorMessage);
  //       return throwError(() => new Error(errorMessage));
  //     })
  //   );
  // }

  // saveUsuarioFoto(foto: Foto): Observable<Foto> {
  //   if (!foto) {
  //     return of(foto);
  //   }

  //   if (!foto.usuario_id) {
  //     return throwError(() => new Error('ID do usuário não informado.'));
  //   }

  //   if (!foto.foto) {
  //     return throwError(() => new Error('Foto não informada.'));
  //   }

  //   if (foto.id !== undefined && foto.id !== null && foto.id > 0) {
  //     return this.updateUsuarioFoto(foto);
  //   } else {
  //     return this.insertUsuarioFoto(foto);
  //   }
  // }

  // insertUsuarioFoto(foto: Foto): Observable<Foto> {
  //   const headers = this.authService.getAuthHeaders();

  //   return this.http
  //     .post<Foto>(`${this.apiUrlFoto}`, foto, { headers: headers })
  //     .pipe(
  //       catchError((error) => {
  //         let errorMessage =
  //           'Erro ao salvar foto do usuário. Por favor, tente novamente mais tarde.';

  //         // Verificar se a resposta é JSON ou texto
  //         if (error.error instanceof ErrorEvent) {
  //           // Erro do lado do cliente
  //           errorMessage = `Erro: ${error.error.message}`;
  //         } else {
  //           // Erro do lado do servidor
  //           if (error.error && typeof error.error === 'string') {
  //             try {
  //               const parsedError = JSON.parse(error.error);
  //               if (parsedError.message) {
  //                 errorMessage = parsedError.message;
  //               }
  //             } catch (e) {
  //               console.log('Erro ao salvar foto do usuário:', e);
  //               errorMessage = error.error;
  //             }
  //           } else if (error.error && error.error.message) {
  //             errorMessage = error.error.message;
  //           }
  //         }

  //         console.error('Erro ao salvar foto do usuário:', errorMessage);
  //         return throwError(() => new Error(errorMessage));
  //       })
  //     );
  // }

  // updateUsuarioFoto(foto: Foto): Observable<Foto> {
  //   const headers = this.authService.getAuthHeaders();

  //   return this.http
  //     .patch<Foto>(`${this.apiUrlFoto}`, foto, { headers: headers })
  //     .pipe(
  //       catchError((error) => {
  //         let errorMessage =
  //           'Erro ao salvar foto do usuário. Por favor, tente novamente mais tarde.';

  //         // Verificar se a resposta é JSON ou texto
  //         if (error.error instanceof ErrorEvent) {
  //           // Erro do lado do cliente
  //           errorMessage = `Erro: ${error.error.message}`;
  //         } else {
  //           // Erro do lado do servidor
  //           if (error.error && typeof error.error === 'string') {
  //             try {
  //               const parsedError = JSON.parse(error.error);
  //               if (parsedError.message) {
  //                 errorMessage = parsedError.message;
  //               }
  //             } catch (e) {
  //               console.log('Erro ao salvar foto do usuário:', e);
  //               errorMessage = error.error;
  //             }
  //           } else if (error.error && error.error.message) {
  //             errorMessage = error.error.message;
  //           }
  //         }

  //         console.error('Erro ao salvar foto do usuário:', errorMessage);
  //         return throwError(() => new Error(errorMessage));
  //       })
  //     );
  // }

  // deleteUsuarioFoto(foto: Foto): Observable<Foto> {
  //   if (!foto) {
  //     return of(foto);
  //   }

  //   if (!foto.usuario_id) {
  //     return throwError(() => new Error('ID do usuário não informado.'));
  //   }

  //   if (foto.id === undefined || foto.id === null || foto.id == 0) {
  //     return of(foto);
  //   }

  //   const headers = this.authService.getAuthHeaders();

  //   return this.http.delete<Foto>(this.apiUrlFoto, { headers: headers }).pipe(
  //     catchError((error) => {
  //       let errorMessage =
  //         'Erro ao excluir foto do usuário. Por favor, tente novamente mais tarde.';

  //       // Verificar se a resposta é JSON ou texto
  //       if (error.error instanceof ErrorEvent) {
  //         // Erro do lado do cliente
  //         errorMessage = `Erro: ${error.error.message}`;
  //       } else {
  //         // Erro do lado do servidor
  //         if (error.error && typeof error.error === 'string') {
  //           try {
  //             const parsedError = JSON.parse(error.error);
  //             if (parsedError.message) {
  //               errorMessage = parsedError.message;
  //             }
  //           } catch (e) {
  //             console.log('Erro ao excluir foto do usuário:', e);
  //             errorMessage = error.error;
  //           }
  //         } else if (error.error && error.error.message) {
  //           errorMessage = error.error.message;
  //         }
  //       }

  //       console.error('Erro ao excluir foto do usuário:', errorMessage);
  //       return throwError(() => new Error(errorMessage));
  //     })
  //   );
  // }

  // getPerfilUsuario(email: string, senha: string): Observable<Perfil> {
  //   const url = `${this.apiUrl}/perfil`;

  //   const headers = new HttpHeaders({
  //     'Content-Type': 'application/json',
  //   });

  //   return this.http.post<Perfil>(url, { email, senha }, { headers }).pipe(
  //     tap((response: Perfil) => {
  //       // Atualiza o localStorage com o authorization
  //       if (response.id) {
  //         localStorage.setItem('perfilId', response.id.toString());
  //         localStorage.setItem('perfilNome', response.nome.toUpperCase() || '');
  //         // Removido o redirecionamento direto para '/home'
  //         console.log(
  //           'Perfil do usuário encontrado. Dados armazenados no localStorage.'
  //         );
  //       }
  //     }),
  //     catchError((error: HttpErrorResponse) => {
  //       // Limpa o localStorage e exibe uma mensagem de erro
  //       localStorage.removeItem('perfilId');
  //       localStorage.removeItem('perfilNome');
  //       this.translate.get('ERRO_LOGIN').subscribe((texto: string) => {
  //         this.modalService.abrirModal(texto, 'Erro');
  //       });
  //       let errorMessage = 'Erro desconhecido ao fazer login';
  //       if (error.error instanceof ErrorEvent) {
  //         errorMessage = `Erro do lado do cliente: ${error.error.message}`;
  //       } else {
  //         errorMessage = `Erro do servidor: ${error.status}, mensagem: ${error.message}`;
  //       }
  //       console.error('Erro:', errorMessage, 'Detalhes:', error);
  //       return throwError(() => new Error(errorMessage));
  //     })
  //   );
  // }
}
