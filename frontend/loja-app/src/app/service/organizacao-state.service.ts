// organizacao-state.service.ts
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class OrganizacaoStateService {
  private nomeOrganizacaoSubject = new BehaviorSubject<string>('');
  private idOrganizacaoSubject = new BehaviorSubject<number>(0);
  public nomeOrganizacao$: Observable<string> =
    this.nomeOrganizacaoSubject.asObservable();
  public idOrganizacao$: Observable<number> =
    this.idOrganizacaoSubject.asObservable();

  constructor() {
    // Inicializar com o valor do localStorage, se existir
    const nomeOrganizacao = localStorage.getItem('organizacaoName');
    if (nomeOrganizacao) {
      this.nomeOrganizacaoSubject.next(nomeOrganizacao);
    }

    const idOrganizacao = localStorage.getItem('organizacaoId');
    if (idOrganizacao) {
      this.idOrganizacaoSubject.next(Number(idOrganizacao));
    }
  }

  atualizarOrganizacao(nome: string, id: number): void {
    localStorage.setItem('organizacaoName', nome);
    this.nomeOrganizacaoSubject.next(nome);
    localStorage.setItem('organizacaoId', id.toString());
    this.idOrganizacaoSubject.next(id);
  }

  limparOrganizacao(): void {
    localStorage.removeItem('organizacaoName');
    this.nomeOrganizacaoSubject.next('');
    localStorage.removeItem('organizacaoId');
    this.idOrganizacaoSubject.next(0);
  }
}
