import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';

export interface Client {
  id: string;
  name: string;
  email: string;
  phone: string;
  address?: string;
}

@Injectable({
  providedIn: 'root',
})
export class ClientService {
  // Mock data
  private mockClients: Client[] = [
    {
      id: '1',
      name: 'João Silva',
      email: 'joao.silva@example.com',
      phone: '(11) 98765-4321',
      address: 'Rua das Flores, 123 - São Paulo, SP',
    },
    {
      id: '2',
      name: 'Maria Santos',
      email: 'maria.santos@example.com',
      phone: '(21) 91234-5678',
      address: 'Av. Atlântica, 456 - Rio de Janeiro, RJ',
    },
    {
      id: '3',
      name: 'Pedro Oliveira',
      email: 'pedro.oliveira@example.com',
      phone: '(31) 99876-5432',
      address: 'Rua das Palmeiras, 789 - Belo Horizonte, MG',
    },
  ];

  constructor() {}

  // Get all clients
  getClients(): Observable<Client[]> {
    return of(this.mockClients);
  }

  // Get client by ID
  getClientById(id: string): Observable<Client | undefined> {
    const client = this.mockClients.find((c) => c.id === id);
    return of(client);
  }

  // Create new client
  createClient(client: Omit<Client, 'id'>): Observable<Client> {
    const newClient = {
      ...client,
      id: Date.now().toString(), // Generate a simple ID
    };
    this.mockClients.push(newClient);
    return of(newClient);
  }

  // Update existing client
  updateClient(
    id: string,
    client: Partial<Client>
  ): Observable<Client | undefined> {
    const index = this.mockClients.findIndex((c) => c.id === id);
    if (index !== -1) {
      this.mockClients[index] = { ...this.mockClients[index], ...client };
      return of(this.mockClients[index]);
    }
    return of(undefined);
  }

  // Delete client
  deleteClient(id: string): Observable<boolean> {
    const index = this.mockClients.findIndex((c) => c.id === id);
    if (index !== -1) {
      this.mockClients.splice(index, 1);
      return of(true);
    }
    return of(false);
  }
}
