import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';
import { MatTooltipModule } from '@angular/material/tooltip';
import { TranslateModule } from '@ngx-translate/core';

interface Servico {
  id: number;
  nome: string;
  tecnico: string;
  cliente: string;
  dataAgendada: Date;
  valorCobrado: number;
  status: 'Agendado' | 'Em Andamento' | 'Concluído' | 'Cancelado';
}

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
  ],
})
export class ServicosComponent implements OnInit {
  servicos: Servico[] = [];
  filteredServicos: Servico[] = [];
  filterText = '';

  ngOnInit(): void {
    // Mock data for services
    this.servicos = [
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
    ];
    this.onFilterChange(); // Initialize filtered list
  }

  onFilterChange(): void {
    if (!this.filterText) {
      this.filteredServicos = [...this.servicos];
    } else {
      const filter = this.filterText.toLowerCase();
      this.filteredServicos = this.servicos.filter(
        (servico) =>
          servico.nome.toLowerCase().includes(filter) ||
          servico.tecnico.toLowerCase().includes(filter) ||
          servico.cliente.toLowerCase().includes(filter) ||
          servico.status.toLowerCase().includes(filter)
      );
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
}
