import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { TranslateModule } from '@ngx-translate/core';

interface Functionality {
  name: string;
  route: string;
  image: string;
}

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
  standalone: true,
  imports: [CommonModule, RouterModule, TranslateModule],
})
export class DashboardComponent implements OnInit {
  functionalities: Functionality[] = [];

  ngOnInit(): void {
    this.functionalities = [
      {
        name: 'Backlog',
        route: '/backlog',
        image: 'assets/icons/list.svg',
      },
      {
        name: 'Agenda',
        route: '/agendamentos',
        image: 'assets/icons/calendar_month.svg',
      },
      {
        name: 'Agenda',
        route: '/agendamento',
        image: 'assets/icons/calendar_month.svg',
      },
      {
        name: 'Produtos',
        route: '/produtos',
        image: 'assets/icons/inventory_2.svg',
      },
      {
        name: 'Unidades Federativas',
        route: '/unidades-federativas',
        image: 'assets/icons/location_city.svg',
      },
      {
        name: 'Usuários',
        route: '/usuarios',
        image: 'assets/icons/person.svg',
      },
      { name: 'Chat', route: '/chat', image: 'assets/icons/chat.svg' },
      {
        name: 'Organização',
        route: '/organizacao',
        image: 'assets/icons/business.svg',
      },
      {
        name: 'Categorias',
        route: '/categorias',
        image: 'assets/icons/category.svg',
      },
      {
        name: 'Serviços',
        route: '/servicos',
        image: 'assets/icons/service_toolbox.svg',
      },
      {
        name: 'Clientes',
        image: 'assets/icons/person.svg',
        route: '/clients',
      },
    ];
  }
}
