import { CommonModule } from '@angular/common';
import { Component, inject, OnInit, ViewChild } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatNativeDateModule, MatOptionModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatMenuModule } from '@angular/material/menu';
import {
  MatPaginator,
  MatPaginatorIntl,
  MatPaginatorModule,
} from '@angular/material/paginator';
import { MatSelectModule } from '@angular/material/select';
import {
  MatSnackBar,
  MatSnackBarConfig,
  MatSnackBarModule,
} from '@angular/material/snack-bar';
import { MatSort, MatSortModule } from '@angular/material/sort';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatTooltipModule } from '@angular/material/tooltip';
import { Router } from '@angular/router';
import { NgbModal, NgbModalModule } from '@ng-bootstrap/ng-bootstrap';
import { TranslateModule } from '@ngx-translate/core';
import { Subscription } from 'rxjs';
import { Servico } from 'src/app/model/servico.model';
import { ServicoService } from 'src/app/service/servico.service';
import { MessageModalComponent } from 'src/app/shared/components/modal/message-modal/message-modal.component';
import { CustomPaginatorIntl } from 'src/app/shared/service/custom-paginator-intl';
import { DeviceService } from 'src/app/shared/service/device.service';

// interface Servico {
//   id: number;
//   nome: string;
//   tecnico: string;
//   cliente: string;
//   dataAgendada: Date;
//   valorCobrado: number;
//   status: 'Agendado' | 'Em Andamento' | 'Concluído' | 'Cancelado';
// }

@Component({
  selector: 'app-servicos',
  templateUrl: './servicos.component.html',
  styleUrls: ['./servicos.component.scss'],
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    TranslateModule,
    MatTooltipModule,
    MatIconModule,
    MatFormFieldModule,
    MatSelectModule,
    MatButtonModule,
    ReactiveFormsModule,
    MatTableModule,
    MatInputModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatPaginatorModule,
    MatSortModule,
    MatOptionModule,
    MatSnackBarModule,
    MatMenuModule,
    NgbModalModule,
    MatCheckboxModule,
  ],
})
export class ServicosComponent implements OnInit {
  servicos = new MatTableDataSource<Servico>([]);
  filteredServicos: Servico[] = [];
  filterNome = '';
  filterDataAgendada: Date | null = null;
  filterValorCobrado: number | null = null;
  filterStatus: string | null = null; // Exemplo de filtro para status
  filterCliente: string | null = null; // Exemplo de filtro para cliente

  tecnicos: { id: number; nome: string }[] = []; // Array de técnicos (preencher via serviço ou mock)
  clientes: { id: number; nome: string }[] = []; // Array de clientes (preencher via serviço ou mock)
  statusOptions: string[] = [];

  selectedTecnico: number | null = null;
  selectedCliente: number | null = null;
  selectedStatus: string | null = null; // Exemplo de filtro para status
  selectedStatuses: string[] = []; // Para seleção múltipla de status

  isMobile = false;
  showFilters = false;
  private subscription = new Subscription();

  totalServicos = 0;
  pageSize = 5;
  pageIndex = 0;
  pageSizeOptions: number[] = [5, 10, 20];

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  deviceService = inject(DeviceService);
  servicoService = inject(ServicoService);
  modalService = inject(NgbModal);
  router = inject(Router);
  snackBar = inject(MatSnackBar);
  paginatorIntl = inject(MatPaginatorIntl);

  displayedColumns: string[] = [
    'nome',
    'tecnico',
    'cliente',
    'dataAgendada',
    'valorCobrado',
    'status',
    'acoes',
  ];

  availableColumns = [
    { name: 'LABLE_NOME', key: 'nome', visible: true },
    { name: 'LABLE_TECNICO', key: 'tecnico', visible: true },
    { name: 'LABLE_CLIENTE', key: 'cliente', visible: true },
    { name: 'LABLE_DATA_AGENDADA', key: 'dataAgendada', visible: true },
    { name: 'LABLE_VALOR_COBRADO', key: 'valorCobrado', visible: true },
    { name: 'LABLE_STATUS', key: 'status', visible: true },
    { name: 'LABLE_ACOES', key: 'acoes', visible: true },
  ];

  ngOnInit(): void {
    // Mock data for services
    this.servicos = new MatTableDataSource<Servico>([
      {
        id: 1,
        nome: 'Instalação de Ar Condicionado',
        tecnico: 'João Silva',
        cliente: 'Empresa ABC',
        dataAgendada: new Date('2024-07-20T10:00:00'),
        valorCobrado: 350.0,
        status: 'Agendado',
      },
      {
        id: 2,
        nome: 'Manutenção Preventiva',
        tecnico: 'Maria Oliveira',
        cliente: 'Condomínio Sol Nascente',
        dataAgendada: new Date('2024-07-22T14:30:00'),
        valorCobrado: 200.0,
        status: 'Agendado',
      },
      {
        id: 3,
        nome: 'Reparo Elétrico Urgente',
        tecnico: 'Carlos Pereira',
        cliente: 'Residência Souza',
        dataAgendada: new Date('2024-07-18T09:00:00'),
        valorCobrado: 150.0,
        status: 'Concluído',
      },
      {
        id: 4,
        nome: 'Consultoria Técnica',
        tecnico: 'Ana Costa',
        cliente: 'Startup Inovadora',
        dataAgendada: new Date('2024-07-25T11:00:00'),
        valorCobrado: 500.0,
        status: 'Em Andamento',
      },
      {
        id: 5,
        nome: 'Limpeza de Fachada',
        tecnico: 'João Silva',
        cliente: 'Loja Varejo Central',
        dataAgendada: new Date('2024-07-15T08:00:00'),
        valorCobrado: 800.0,
        status: 'Cancelado',
      },
    ]);

    this.totalServicos = this.servicos.data.length;
    this.servicos.paginator = this.paginator;

    this.tecnicos = [
      { id: 1, nome: 'João Silva' },
      { id: 2, nome: 'Maria Oliveira' },
      { id: 3, nome: 'Carlos Pereira' },
      { id: 4, nome: 'Ana Costa' },
    ]; // Mock data for tecnicos

    this.clientes = [
      { id: 1, nome: 'Empresa ABC' },
      { id: 2, nome: 'Condomínio Sol Nascente' },
      { id: 3, nome: 'Residência Souza' },
      { id: 4, nome: 'Startup Inovadora' },
      { id: 5, nome: 'Loja Varejo Central' },
    ]; // Mock data for clientes

    this.servicoService.getStatus().subscribe((statuses: string[]) => {
      this.statusOptions = statuses;
    });

    this.onFilterChange(); // Initialize filtered list

    this.subscription.add(
      this.deviceService.isMobile$.subscribe((isMobile) => {
        this.isMobile = isMobile;
        console.log('ServicosComponent - dispositivo móvel:', isMobile);
      })
    );
  }

  onFilterChange(): void {
    if (!this.filterNome && this.selectedTecnico === null) {
      this.filteredServicos = [...this.servicos.data];
    } else {
      const filter = this.filterNome.toLowerCase();
      this.filteredServicos = this.servicos.data.filter((servico) => {
        const matchesNome = servico.nome.toLowerCase().includes(filter);
        const matchesTecnico =
          this.selectedTecnico === null ||
          servico.tecnico.toLowerCase() ===
            this.tecnicos
              .find((t) => t.id === this.selectedTecnico)
              ?.nome.toLowerCase();
        const matchesCliente = servico.cliente.toLowerCase().includes(filter);
        const matchesStatus = servico.status.toLowerCase().includes(filter);

        return matchesNome && matchesTecnico && matchesCliente && matchesStatus;
      });
    }
  }

  detalharServico(servico: Servico): void {
    // Placeholder for detail action
    console.log('Detalhar:', servico);
    alert(`Detalhes do Serviço: ${servico.nome}`);
  }

  alterarServico(servico: Servico): void {
    // Placeholder for edit action
    console.log('Alterar:', servico);
    alert(`Alterar Serviço: ${servico.nome}`);
  }

  onPesquisar(): void {
    // Implemente a lógica de pesquisa, se necessário.
    this.onFilterChange();
  }

  onLimparFiltros(): void {
    this.filterNome = '';
    this.selectedTecnico = null;
    this.onFilterChange();
  }

  onCadastrar(): void {
    // Implemente a navegação ou lógica para cadastrar um novo serviço.
    // Exemplo: this.router.navigate(['/servicos/cadastrar']);
  }

  onTecnicoChange(tecnicoId: number | null): void {
    this.selectedTecnico = tecnicoId;
    this.onFilterChange();
  }

  onClienteChange(clienteId: number | null): void {
    this.selectedCliente = clienteId;
    this.onFilterChange();
  }

  onStatusChange(status: string | null): void {
    this.selectedStatus = status;
    this.onFilterChange();
  }

  toggleFiltersVisibility() {
    this.showFilters = !this.showFilters;
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

  async cadastroServico(id: number, acao: string): Promise<void> {
    try {
      const response = await this.servicoService.getServico(id);
      if (
        response
        //&& response.servico
      ) {
        const servico = response;
        // .servico;
        this.router.navigate(['/cadastro-usuario', id], {
          state: { servico, acao: acao },
        });
      } else {
        this.abrirModal('Serviço não encontrado', 'error');
      }
    } catch (error) {
      console.error('Erro ao carregar serviço:', error);
      this.abrirModal('Erro ao carregar serviço', 'error');
    }
  }

  async excluirServico(id: number): Promise<void> {
    try {
      await this.servicoService.excluirServico(id);
      this.snackBar.open('Usuário excluído com sucesso!', 'Fechar', {
        duration: 3000,
        horizontalPosition: 'center',
        verticalPosition: 'top',
      } as MatSnackBarConfig);
      //   this.loadUsuarios();
    } catch (error) {
      console.error('Erro ao excluir usuário:', error);
      this.abrirModal('Erro ao excluir usuário' + error, 'error');
    }
  }

  abrirModal(message: string, type: string): void {
    const modalRef = this.modalService.open(MessageModalComponent, {
      size: 'md',
    });
    modalRef.componentInstance.message = message;
    modalRef.componentInstance.type = type;
  }

  async loadServicos(
    params: {
      nome?: string;
      id?: number;
      tecnicoId?: string;
      clienteId?: string;
      dataAgendada?: string;
      valorCobrado?: number;
      status?: string;
      limit?: number;
      offset?: number;
    } = {}
  ): Promise<void> {
    const offset = this.pageIndex * this.pageSize;
    const requestParams = {
      ...params,
      limit: this.pageSize,
      offset: offset,
    };

    try {
      const response = await this.servicoService.getServicos(requestParams);

      if (response && Array.isArray(response.servicos)) {
        this.servicos.data = response.servicos; // Supondo que a resposta tenha um campo 'servicos'
        this.totalServicos = response.totalRecords; // Supondo que a resposta tenha um campo 'total'

        if (this.paginator) {
          // Atualizar o paginator
          this.paginator.length = this.totalServicos;
          this.paginator.pageSize = this.pageSize;

          // Importante: Atualizar o pageIndex por último
          setTimeout(() => {
            this.paginator.pageIndex = this.pageIndex;
          });

          const start = offset + 1;
          const end = Math.min(start + this.pageSize - 1, this.totalServicos);

          if (this.paginatorIntl instanceof CustomPaginatorIntl) {
            this.paginatorIntl.setValues(start, end, this.totalServicos);
            this.paginatorIntl.emitChanges();
          }

          // Atualizar estado da paginação
          this.updatePaginationState();
        }
      } else {
        console.error('Formato de resposta inválido:', response);
        this.snackBar.open('Erro ao carregar dados', 'Fechar', {
          duration: 3000,
        });
      }
    } catch (error) {
      console.error('Erro ao carregar usuários:', error);
      if (error.status === 401) {
        this.router.navigate(['/login']);
      }
      this.snackBar.open(
        error.message || 'Erro ao carregar usuários',
        'Fechar',
        { duration: 3000 }
      );
    }
  }

  private updatePaginationState(): void {
    const hasNextPage =
      (this.pageIndex + 1) * this.pageSize < this.totalServicos;
    const hasPreviousPage = this.pageIndex > 0;

    Object.assign(this.paginator, {
      hasNextPage: () => hasNextPage,
      hasPreviousPage: () => hasPreviousPage,
    });
  }

  capitalizeStatus(status: string): string {
    if (!status) return '';
    return status.charAt(0).toUpperCase() + status.slice(1).toLowerCase();
  }
}
