// agendamento.component.ts
import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import {
  DateAdapter,
  MAT_DATE_FORMATS,
  MAT_DATE_LOCALE,
  MatDateFormats,
  MatNativeDateModule,
} from '@angular/material/core';
import { MatDialogModule } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';
import { TranslateModule } from '@ngx-translate/core';
import { MatButtonToggleModule } from '@angular/material/button-toggle';

interface Agendamento {
  id: number;
  cliente: string;
  prestador: string;
  titulo: string;
  dataInicio: Date;
  dataFim: Date;
  descricao: string;
  cor: string;
}

const CUSTOM_DATE_FORMATS: MatDateFormats = {
  parse: {
    dateInput: 'DD/MM/YYYY',
  },
  display: {
    dateInput: 'DD/MM/YYYY',
    monthYearLabel: 'MMM YYYY',
    dateA11yLabel: 'DD/MM/YYYY',
    monthYearA11yLabel: 'MMMM YYYY',
  },
};

@Component({
  selector: 'app-agendamento',
  templateUrl: './agendamento.component.html',
  styleUrls: ['./agendamento.component.scss'],
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatButtonModule,
    MatIconModule,
    TranslateModule,
    MatCardModule,
    MatButtonToggleModule,
    MatDialogModule,
    MatNativeDateModule,
  ],
  providers: [
    { provide: MAT_DATE_LOCALE, useValue: 'pt-BR' },
    { provide: MAT_DATE_FORMATS, useValue: CUSTOM_DATE_FORMATS },
  ],
})
export class AgendamentoComponent implements OnInit {
  // Modo de visualização: dia, semana, mês
  visualizacao: 'dia' | 'semana' | 'mes' = 'dia';

  // Data atual selecionada
  dataAtual: Date = new Date();

  // Dias da semana
  diasSemana: string[] = ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb'];

  // Array para armazenar os agendamentos exibidos na visualização atual
  agendamentosExibidos: Agendamento[] = [];

  // Lista de agendamentos de exemplo
  agendamentos: Agendamento[] = [
    {
      id: 1,
      cliente: 'Jose da Silva',
      prestador: 'João Amaro',
      titulo: 'Conserto de Ar-condicionado',
      dataInicio: new Date(2025, 2, 2, 10, 0), // 2 de março de 2025, 10:00
      dataFim: new Date(2025, 2, 2, 11, 30), // 2 de março de 2025, 11:30
      descricao: 'Discussão sobre novo projeto',
      cor: '#7986CB',
    },
    {
      id: 2,
      cliente: 'Jose da Silva',
      prestador: 'João Amaro',
      titulo: 'Almoço de equipe',
      dataInicio: new Date(2025, 2, 2, 12, 30), // 2 de março de 2025, 12:30
      dataFim: new Date(2025, 2, 2, 14, 0), // 2 de março de 2025, 14:00
      descricao: 'Comemoração de aniversário',
      cor: '#4DB6AC',
    },
    {
      id: 3,
      cliente: 'Jose da Silva',
      prestador: 'João Amaro',
      titulo: 'Planejamento semanal',
      dataInicio: new Date(2025, 2, 3, 9, 0), // 3 de março de 2025, 09:00
      dataFim: new Date(2025, 2, 3, 10, 30), // 3 de março de 2025, 10:30
      descricao: 'Definição de metas da semana',
      cor: '#FFB74D',
    },
    {
      id: 4,
      cliente: 'Jose da Silva',
      prestador: 'João Amaro',
      titulo: 'Entrevista candidato',
      dataInicio: new Date(2025, 2, 4, 14, 0), // 4 de março de 2025, 14:00
      dataFim: new Date(2025, 2, 4, 15, 0), // 4 de março de 2025, 15:00
      descricao: 'Vaga de desenvolvedor frontend',
      cor: '#AED581',
    },
    {
      id: 5,
      cliente: 'Jose da Silva',
      prestador: 'João Amaro',
      titulo: 'Treinamento',
      dataInicio: new Date(2025, 2, 6, 13, 0), // 6 de março de 2025, 13:00
      dataFim: new Date(2025, 2, 6, 17, 0), // 6 de março de 2025, 17:00
      descricao: 'Curso de Angular avançado',
      cor: '#FF8A65',
    },
  ];

  // Horários para visualização diária
  horarios: string[] = Array.from(Array(24).keys()).map((hora) => `${hora}:00`);

  // Dias para visualização mensal
  diasMes: number[] = [];
  diasAnterior: number[] = [];
  diasProximo: number[] = [];

  constructor(private dateAdapter: DateAdapter<Date>) {
    this.dateAdapter.setLocale('pt-BR');
  }

  ngOnInit(): void {
    this.atualizarAgendamentosExibidos();
  }

  mudarVisualizacao(modo: 'dia' | 'semana' | 'mes'): void {
    this.visualizacao = modo;
    this.atualizarAgendamentosExibidos();
  }

  navegarData(direcao: number): void {
    if (this.visualizacao === 'dia') {
      this.dataAtual = new Date(
        this.dataAtual.setDate(this.dataAtual.getDate() + direcao)
      );
    } else if (this.visualizacao === 'semana') {
      this.dataAtual = new Date(
        this.dataAtual.setDate(this.dataAtual.getDate() + direcao * 7)
      );
    } else if (this.visualizacao === 'mes') {
      this.dataAtual = new Date(
        this.dataAtual.setMonth(this.dataAtual.getMonth() + direcao)
      );
    }
    this.atualizarAgendamentosExibidos();
  }

  irParaHoje(): void {
    this.dataAtual = new Date();
    this.atualizarAgendamentosExibidos();
  }

  atualizarAgendamentosExibidos(): void {
    if (this.visualizacao === 'dia') {
      this.filtrarAgendamentosDia();
    } else if (this.visualizacao === 'semana') {
      this.filtrarAgendamentosSemana();
    } else if (this.visualizacao === 'mes') {
      this.atualizarCalendarioMes();
      this.filtrarAgendamentosMes();
    }
  }

  filtrarAgendamentosDia(): void {
    const inicio = new Date(this.dataAtual);
    inicio.setHours(0, 0, 0, 0);

    const fim = new Date(this.dataAtual);
    fim.setHours(23, 59, 59, 999);

    this.agendamentosExibidos = this.agendamentos.filter(
      (agendamento) =>
        agendamento.dataInicio >= inicio && agendamento.dataInicio <= fim
    );
  }

  filtrarAgendamentosSemana(): void {
    // Calcular o início da semana (domingo)
    const inicioSemana = new Date(this.dataAtual);
    inicioSemana.setDate(this.dataAtual.getDate() - this.dataAtual.getDay());
    inicioSemana.setHours(0, 0, 0, 0);

    // Calcular o fim da semana (sábado)
    const fimSemana = new Date(inicioSemana);
    fimSemana.setDate(inicioSemana.getDate() + 6);
    fimSemana.setHours(23, 59, 59, 999);

    this.agendamentosExibidos = this.agendamentos.filter(
      (agendamento) =>
        agendamento.dataInicio >= inicioSemana &&
        agendamento.dataInicio <= fimSemana
    );
  }

  atualizarCalendarioMes(): void {
    // Resetar arrays
    this.diasMes = [];
    this.diasAnterior = [];
    this.diasProximo = [];

    // Obter o primeiro dia do mês atual
    const primeiroDia = new Date(
      this.dataAtual.getFullYear(),
      this.dataAtual.getMonth(),
      1
    );
    // Obter o número de dias no mês atual
    const ultimoDia = new Date(
      this.dataAtual.getFullYear(),
      this.dataAtual.getMonth() + 1,
      0
    );
    const numDias = ultimoDia.getDate();

    // Preencher os dias do mês atual
    for (let i = 1; i <= numDias; i++) {
      this.diasMes.push(i);
    }

    // Preencher os dias do mês anterior que aparecem no calendário
    const primeiroDiaSemana = primeiroDia.getDay();
    if (primeiroDiaSemana > 0) {
      const ultimoDiaMesAnterior = new Date(
        this.dataAtual.getFullYear(),
        this.dataAtual.getMonth(),
        0
      ).getDate();
      for (let i = 0; i < primeiroDiaSemana; i++) {
        this.diasAnterior.unshift(ultimoDiaMesAnterior - i);
      }
    }

    // Preencher os dias do próximo mês que aparecem no calendário
    const diasRestantes = (7 - ((primeiroDiaSemana + numDias) % 7)) % 7;
    for (let i = 1; i <= diasRestantes; i++) {
      this.diasProximo.push(i);
    }
  }

  filtrarAgendamentosMes(): void {
    const inicioMes = new Date(
      this.dataAtual.getFullYear(),
      this.dataAtual.getMonth(),
      1
    );
    const fimMes = new Date(
      this.dataAtual.getFullYear(),
      this.dataAtual.getMonth() + 1,
      0,
      23,
      59,
      59,
      999
    );

    this.agendamentosExibidos = this.agendamentos.filter(
      (agendamento) =>
        agendamento.dataInicio >= inicioMes && agendamento.dataInicio <= fimMes
    );
  }

  formataDataHeader(): string {
    if (this.visualizacao === 'dia') {
      return this.dataAtual.toLocaleDateString('pt-BR', {
        weekday: 'long',
        day: 'numeric',
        month: 'long',
        year: 'numeric',
      });
    } else if (this.visualizacao === 'semana') {
      const inicioSemana = new Date(this.dataAtual);
      inicioSemana.setDate(this.dataAtual.getDate() - this.dataAtual.getDay());

      const fimSemana = new Date(inicioSemana);
      fimSemana.setDate(inicioSemana.getDate() + 6);

      return `${inicioSemana.toLocaleDateString('pt-BR', {
        day: 'numeric',
        month: 'short',
      })} - ${fimSemana.toLocaleDateString('pt-BR', {
        day: 'numeric',
        month: 'short',
        year: 'numeric',
      })}`;
    } else {
      return this.dataAtual.toLocaleDateString('pt-BR', {
        month: 'long',
        year: 'numeric',
      });
    }
  }

  getAgendamentosDia(dia: number): Agendamento[] {
    // const data = new Date(this.dataAtual.getFullYear(), this.dataAtual.getMonth(), dia);
    return this.agendamentos.filter(
      (agendamento) =>
        agendamento.dataInicio.getDate() === dia &&
        agendamento.dataInicio.getMonth() === this.dataAtual.getMonth() &&
        agendamento.dataInicio.getFullYear() === this.dataAtual.getFullYear()
    );
  }

  // Método para obter o número do dia da semana pelo índice
  getDiaSemana(indice: number): number {
    const dataInicio = new Date(this.dataAtual);
    dataInicio.setDate(this.dataAtual.getDate() - this.dataAtual.getDay());
    const dataDia = new Date(dataInicio);
    dataDia.setDate(dataInicio.getDate() + indice);
    return dataDia.getDate();
  }

  isAgendamentoNoDia(
    agendamento: Agendamento,
    indiceDiaSemana: number
  ): boolean {
    const dataInicio = new Date(this.dataAtual);
    dataInicio.setDate(this.dataAtual.getDate() - this.dataAtual.getDay());
    const dataDia = new Date(dataInicio);
    dataDia.setDate(dataInicio.getDate() + indiceDiaSemana);

    return (
      agendamento.dataInicio.getDate() === dataDia.getDate() &&
      agendamento.dataInicio.getMonth() === dataDia.getMonth() &&
      agendamento.dataInicio.getFullYear() === dataDia.getFullYear()
    );
  }

  formatarHorario(data: Date): string {
    return data.toLocaleTimeString('pt-BR', {
      hour: '2-digit',
      minute: '2-digit',
    });
  }

  ehDataAtual(dia: number): boolean {
    const hoje = new Date();
    return (
      dia === hoje.getDate() &&
      this.dataAtual.getMonth() === hoje.getMonth() &&
      this.dataAtual.getFullYear() === hoje.getFullYear()
    );
  }
}
