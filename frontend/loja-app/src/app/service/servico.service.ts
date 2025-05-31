import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Servico } from '../model/servico.model';
import { ServicoResponseDTO } from '../model/servicoResponseDTO.model';
import { AuthService } from '../shared/service/auth.service';

@Injectable({
  providedIn: 'root',
})
export class ServicoService {
  private apiUrl = environment.apiUrl + '/servico';
  // private usuarios: any[] = [];

  constructor(private http: HttpClient, private authService: AuthService) {}

  async getServicos(params: {
    nome?: string;
    id?: number;
    tecnicoId?: string;
    clienteId?: string;
    dataAgendada?: string;
    valorCobrado?: number;
    status?: string;
    limit?: number;
    offset?: number;
  }): Promise<ServicoResponseDTO> {
    const headers = this.authService.getAuthHeaders();

    // Adicionar ao body o idOrganizacao
    const organizacaoId = localStorage.getItem('organizacaoId');
    if (!organizacaoId) {
      throw new Error('ID da organização não encontrado no localStorage');
    }

    // Garantir que params sempre seja um objeto JSON
    const body = { ...params };

    const options = {
      headers: headers,
      params: {
        organizaoId: Number(organizacaoId),
      },
    };

    try {
      return await firstValueFrom(
        this.http.post<ServicoResponseDTO>(`${this.apiUrl}/find`, body, options)
      );
    } catch (error) {
      console.error('Erro ao buscar usuários:', error);
      throw error;
    }
  }

  getServico(id: number): Observable<Servico> {
    return this.http.get<Servico>(`${this.apiUrl}/${id}`);
  }

  salvarServico(servico: Servico): Observable<Servico> {
    if (servico.id) {
      return this.http.put<Servico>(`${this.apiUrl}/${servico.id}`, servico);
    } else {
      return this.http.post<Servico>(this.apiUrl, servico);
    }
  }

  excluirServico(id: number): Observable<Servico> {
    return this.http.delete<Servico>(`${this.apiUrl}/${id}`);
  }

  getStatus(): Observable<string[]> {
    const headers = this.authService.getAuthHeaders();

    return this.http.get<string[]>(`${this.apiUrl}/status`, {
      headers: headers,
    });
  }
}
