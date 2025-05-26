import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { CommonModule, Location } from '@angular/common';
import {
  Component,
  ElementRef,
  EventEmitter,
  Input,
  OnInit,
  Output,
  ViewChild,
} from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatMenuModule } from '@angular/material/menu';
import { MatToolbarModule } from '@angular/material/toolbar';
import { NavigationEnd, Router, RouterModule } from '@angular/router';
import { TranslateModule, TranslateService } from '@ngx-translate/core';
import { filter, map, tap } from 'rxjs/operators';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss'],
  standalone: true,
  imports: [
    CommonModule,
    MatToolbarModule,
    MatIconModule,
    MatListModule,
    MatButtonModule,
    RouterModule,
    MatMenuModule,
    TranslateModule,
  ],
})
export class MenuComponent implements OnInit {
  @ViewChild('menu') menu!: ElementRef;
  @Output() expansionChange = new EventEmitter<boolean>();
  @Input() isExpanded = false;
  activeRoute = '';
  isHandset = false;

  constructor(
    private router: Router,
    private location: Location,
    private breakpointObserver: BreakpointObserver,
    private translate: TranslateService
  ) {
    this.router.events
      .pipe(filter((event) => event instanceof NavigationEnd))
      .subscribe(() => {
        this.activeRoute = this.location.path();
      });
  }

  ngOnInit(): void {
    this.breakpointObserver
      .observe([Breakpoints.Handset])
      .pipe(
        map((result) => result.matches),
        tap((matches) => (this.isHandset = matches)),
        tap((matches) => {
          this.isExpanded = !matches;
          this.expansionChange.emit(this.isExpanded);
        })
      )
      .subscribe();
  }

  menuItems = [
    {
      labelKey: 'TITLE_DASHBOARD',
      icon: 'dashboard',
      action: () => this.navigateTo('/dashboard'),
      route: '/dashboard',
    },

    {
      labelKey: 'TITLE_ORGANIZACOES',
      icon: 'business',
      action: () => this.navigateTo('/organizacao'),
      route: '/organizacao',
    },
    {
      labelKey: 'TITLE_PRODUTOS',
      icon: 'inventory_2',
      action: () => this.navigateTo('/produtos'),
      route: '/produtos',
    },
    {
      labelKey: 'TITLE_CHAT',
      icon: 'chat',
      action: () => this.navigateTo('/chat'),
      route: '/chat',
    },
    {
      labelKey: 'login',
      icon: 'login',
      action: () => this.navigateTo('/login'),
      route: '/login',
    },
    {
      labelKey: 'TERMS_OF_SERVICE',
      icon: 'description',
      action: () => this.navigateTo('/terms'),
      route: '/terms',
    },
    {
      labelKey: 'PRIVACY_POLICY',
      icon: 'security',
      action: () => this.navigateTo('/privacy'),
      route: '/privacy',
    },
    {
      labelKey: 'LABLE_BACKLOG',
      icon: 'list',
      route: '/backlog',
      action: () => this.navigateTo('/backlog'),
    },
    {
      labelKey: 'TITLE_AGENDAMENTOS',
      icon: 'event',
      action: () => this.navigateTo('/agendamentos'),
      route: '/agendamentos',
    },
    {
      labelKey: 'TITLE_AGENDAMENTOS',
      icon: 'event',
      route: '/agendamento',
      action: () => this.navigateTo('/agendamento'),
    },
    {
      labelKey: 'LABLE_CLIENTES',
      icon: 'people',
      action: () => this.navigateTo('/clients'),
      route: '/clients',
    },
    {
      labelKey: 'TITLE_USUARIOS',
      icon: 'people',
      action: () => this.navigateTo('/usuarios'),
      route: '/usuarios',
    },
    {
      labelKey: 'LABLE_CATEGORIA',
      icon: 'category',
      action: () => this.navigateTo('/categorias'),
      route: '/categorias',
    },
    {
      labelKey: 'TITLE_GEOLOCALIZACAO',
      icon: 'map',
      action: () => this.navigateTo('/geolocalizacao'),
      route: '/geolocalizacao',
    },
    {
      labelKey: 'TITLE_SERVICO',
      icon: 'service_toolbox',
      action: () => this.navigateTo('/servico'),
      route: '/servico',
    },
    {
      labelKey: 'TITLE_SERVICO',
      icon: 'build',
      action: () => this.navigateTo('/servicos'),
      route: '/servicos',
    },
  ];

  home(): void {
    const authorization = localStorage.getItem('Authorization');
    if (authorization) {
      this.router.navigate(['/home']);
    } else {
      this.router.navigate(['/landingpage']);
    }
  }

  private navigateTo(route: string): void {
    if (this.isAuthorization()) {
      this.router.navigate([route]);
    }
  }

  toggleExpansion(): void {
    if (!this.isHandset) {
      this.isExpanded = !this.isExpanded;
      this.expansionChange.emit(this.isExpanded);
    }
  }

  private isAuthorization(): boolean {
    const authorization = localStorage.getItem('Authorization');
    console.log('Token de autorização:', authorization);
    return authorization != null;
  }

  isActive(route: string): boolean {
    return this.activeRoute.startsWith(route);
  }
}
