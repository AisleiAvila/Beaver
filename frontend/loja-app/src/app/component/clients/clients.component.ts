import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { TranslateModule } from '@ngx-translate/core';

import { ClientService } from 'src/app/services/client.service';
import { ClientDetailsComponent } from './client-details/client-details.component';
import { AddClientComponent } from './add-client/add-client.component';
import { EditClientComponent } from './edit-client/edit-client.component';
import { DeleteClientConfirmationComponent } from './delete-client-confirmation/delete-client-confirmation.component';

@Component({
  selector: 'app-clients',
  templateUrl: './clients.component.html',
  styleUrls: ['./clients.component.scss'],
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    TranslateModule,
    ClientDetailsComponent,
    AddClientComponent,
    EditClientComponent,
    DeleteClientConfirmationComponent,
  ],
})
export class ClientsComponent implements OnInit {
  clients: any[] = [];
  isLoading = false;
  showAddForm = false;
  showEditForm = false;
  showDeleteConfirmation = false;
  selectedClient: any = null;

  constructor(private clientService: ClientService) {}

  ngOnInit(): void {
    this.loadClients();
  }

  loadClients(): void {
    this.isLoading = true;
    this.clientService.getClients().subscribe(
      (data) => {
        this.clients = data;
        this.isLoading = false;
      },
      (error) => {
        console.error('Error loading clients', error);
        this.isLoading = false;
      }
    );
  }

  openAddForm(): void {
    this.showAddForm = true;
  }

  closeAddForm(): void {
    this.showAddForm = false;
  }

  openEditForm(client: any): void {
    this.selectedClient = client;
    this.showEditForm = true;
  }

  closeEditForm(): void {
    this.showEditForm = false;
    this.selectedClient = null;
  }

  openDeleteConfirmation(client: any): void {
    this.selectedClient = client;
    this.showDeleteConfirmation = true;
  }

  closeDeleteConfirmation(): void {
    this.showDeleteConfirmation = false;
    this.selectedClient = null;
  }

  onClientAdded(): void {
    this.closeAddForm();
    this.loadClients();
  }

  onClientUpdated(): void {
    this.closeEditForm();
    this.loadClients();
  }

  onClientDeleted(): void {
    this.closeDeleteConfirmation();
    this.loadClients();
  }

  viewDetails(client: any): void {
    this.selectedClient = client;
  }
}
