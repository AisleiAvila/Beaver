import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { RecuperarSenhaResponse } from 'src/app/model/recuperarSenhaResponse.model';

@Injectable({
  providedIn: 'root',
})
export class LembrarSenhaService {
  private http = inject(HttpClient);

  private apiUrl = environment.apiUrl + '/senha/recuperar';

  lembrarSenha(email: string): Observable<RecuperarSenhaResponse> {
    return this.http.post<RecuperarSenhaResponse>(`${this.apiUrl}`, { email });
  }
}
