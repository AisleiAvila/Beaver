import { Client } from './../../services/client.service';
import { CommonModule } from '@angular/common';
import {
  AfterViewInit,
  Component,
  ElementRef,
  inject,
  OnInit,
  ViewChild,
} from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatSort, MatSortModule } from '@angular/material/sort';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatTooltipModule } from '@angular/material/tooltip';
import { Router, RouterModule } from '@angular/router';
import { TranslateModule, TranslateService } from '@ngx-translate/core';

import { ClientService } from '../../services/client.service';
import { AddClientComponent } from './add-client/add-client.component';
import { ClientDetailsComponent } from './client-details/client-details.component';
import { DeleteClientConfirmationComponent } from './delete-client-confirmation/delete-client-confirmation.component';
import { EditClientComponent } from './edit-client/edit-client.component';
import { Subscription } from 'rxjs';
import { DeviceService } from 'src/app/shared/service/device.service';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatMenuModule } from '@angular/material/menu';
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'app-clients',
  templateUrl: './clients.component.html',
  styleUrls: ['./clients.component.scss'],
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    TranslateModule,
    FormsModule,
    MatTableModule,
    MatSortModule,
    MatPaginatorModule,
    MatButtonModule,
    MatIconModule,
    MatTooltipModule,
    ClientDetailsComponent,
    AddClientComponent,
    EditClientComponent,
    DeleteClientConfirmationComponent,
    MatFormFieldModule,
    MatMenuModule,
    MatInputModule,
  ],
})
export class ClientsComponent implements OnInit, AfterViewInit {
  clients = new MatTableDataSource<Client>([]);
  // dataSource = new MatTableDataSource<any>([]);
  displayedColumns: string[] = ['name', 'email', 'phone', 'actions'];
  isLoading = false;
  showAddForm = false;
  showEditForm = false;
  showDeleteConfirmation = false;
  selectedClient: any = null;

  translate = inject(TranslateService);
  deviceService = inject(DeviceService);
  router = inject(Router);

  // Pagination settings
  pageSize = 10;
  pageSizeOptions = [5, 10, 25, 50, 100];
  pageIndex = 0;
  totalItems = 0;

  // Filter properties
  searchText = '';
  statusFilter = '';
  showAdvancedFilters = false;
  dateRangeStart: string | null = null;
  dateRangeEnd: string | null = null;
  sortOption = 'name_asc';
  sortField = 'name';
  sortDirection: 'asc' | 'desc' = 'asc';

  isMobile = false;
  showFilters = false;
  private subscription = new Subscription();

  availableColumns = [
    { name: 'LABLE_NOME', key: 'name', visible: true },
    { name: 'LABLE_EMAIL', key: 'email', visible: true },
    { name: 'LABLE_TELEFONE', key: 'phone', visible: true },
    { name: 'LABLE_ACOES', key: 'actions', visible: true },
  ];

  @ViewChild(MatSort) sort!: MatSort;
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  @ViewChild('nomeInput') nomeInput!: ElementRef;
  @ViewChild('emailInput') emailInput!: ElementRef;
  @ViewChild('phoneInput') dataNascimentoInput!: ElementRef;

  constructor(private clientService: ClientService) {}

  ngOnInit(): void {
    this.loadClients();

    this.subscription.add(
      this.deviceService.isMobile$.subscribe((isMobile) => {
        this.isMobile = isMobile;
        console.log('UsuariosComponent - dispositivo móvel:', isMobile);
      })
    );

    this.checkIfMobile();

    window.addEventListener('resize', () => {
      this.checkIfMobile();
    });

    this.updateDisplayedColumns();
  }

  ngAfterViewInit(): void {
    this.clients.sort = this.sort;
    this.clients.paginator = this.paginator;
  }

  loadClients(): void {
    this.isLoading = true;
    this.clientService.getClients().subscribe(
      (data) => {
        this.clients.data = data;
        // this.dataSource.data = this.clients;
        // this.totalItems = this.clients.length;
        this.applyFilters();
        this.isLoading = false;
      },
      (error) => {
        console.error('Error loading clients', error);
        this.isLoading = false;
      }
    );
  }

  // Apply filters to the data source
  applyFilters(): void {
    this.clients.filterPredicate = (data: any, filter: string) => {
      const searchTerms = JSON.parse(filter);

      // Text search
      if (searchTerms.text) {
        const text = searchTerms.text.toLowerCase();
        const nameMatch = data.name.toLowerCase().includes(text);
        const emailMatch = data.email.toLowerCase().includes(text);
        const phoneMatch = data.phone.toLowerCase().includes(text);

        if (!(nameMatch || emailMatch || phoneMatch)) {
          return false;
        }
      }

      // Status filter
      if (searchTerms.status && data.status !== searchTerms.status) {
        return false;
      }

      // Date range
      if (searchTerms.startDate) {
        const startDate = new Date(searchTerms.startDate);
        const clientDate = new Date(data.createdAt);
        if (clientDate < startDate) {
          return false;
        }
      }

      if (searchTerms.endDate) {
        const endDate = new Date(searchTerms.endDate);
        endDate.setHours(23, 59, 59);
        const clientDate = new Date(data.createdAt);
        if (clientDate > endDate) {
          return false;
        }
      }

      return true;
    };

    const filterValue = JSON.stringify({
      text: this.searchText,
      status: this.statusFilter,
      startDate: this.dateRangeStart,
      endDate: this.dateRangeEnd,
    });

    this.clients.filter = filterValue;

    if (this.clients.paginator) {
      this.clients.paginator.firstPage();
    }
  }

  clearSearch(): void {
    this.searchText = '';
    this.applyFilters();
  }

  clearAllFilters(): void {
    this.searchText = '';
    this.statusFilter = '';
    this.dateRangeStart = null;
    this.dateRangeEnd = null;
    this.sortOption = 'name_asc';
    this.sortField = 'name';
    this.sortDirection = 'asc';
    this.applyFilters();
  }

  hasActiveFilters(): boolean {
    return !!(
      this.searchText ||
      this.statusFilter ||
      this.dateRangeStart ||
      this.dateRangeEnd ||
      this.sortOption !== 'name_asc'
    );
  }

  toggleSort(field: string): void {
    if (this.sortField === field) {
      this.sortDirection = this.sortDirection === 'asc' ? 'desc' : 'asc';
    } else {
      this.sortField = field;
      this.sortDirection = 'asc';
    }

    this.sortOption = `${this.sortField}_${this.sortDirection}`;
  }

  // Form handling methods
  openAddForm(): void {
    this.showAddForm = true;
    this.selectedClient = null;
    this.showEditForm = false;
    this.showDeleteConfirmation = false;
  }

  closeAddForm(): void {
    this.showAddForm = false;
  }

  openEditForm(client: any): void {
    this.selectedClient = client;
    this.showEditForm = true;
    this.showAddForm = false;
    this.showDeleteConfirmation = false;
  }

  closeEditForm(): void {
    this.showEditForm = false;
    this.selectedClient = null;
  }

  openDeleteConfirmation(client: any): void {
    this.selectedClient = client;
    this.showDeleteConfirmation = true;
    this.showAddForm = false;
    this.showEditForm = false;
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
    this.showEditForm = false;
    this.showDeleteConfirmation = false;
  }

  checkIfMobile() {
    this.isMobile = window.innerWidth < 768;
    if (this.isMobile) {
      this.availableColumns[1].visible = false;
      this.availableColumns[2].visible = false;
    } else {
      this.availableColumns.forEach((col) => (col.visible = true));
    }
    this.updateDisplayedColumns();
  }

  updateDisplayedColumns() {
    this.displayedColumns = this.availableColumns
      .filter((col) => col.visible)
      .map((col) => col.key);
  }

  toggleFiltersVisibility() {
    this.showFilters = !this.showFilters;
  }

  limparFiltros(
    nomeInput: HTMLInputElement,
    emailInput: HTMLInputElement,
    phoneInput: HTMLInputElement
  ): void {
    nomeInput.value = '';
    emailInput.value = '';
    phoneInput.value = '';
    this.pageIndex = 0;
    this.loadClients();
  }

  navigateToCadastroCliente() {
    this.router.navigate(['/cadastro-usuario'], {
      state: { acao: 'Cadastrar' },
    });
  }
}
