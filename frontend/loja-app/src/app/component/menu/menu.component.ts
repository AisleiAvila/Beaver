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
import { filter } from 'rxjs/operators';

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
      .subscribe((result) => {
        this.isHandset = result.matches;
        if (this.isHandset) {
          this.isExpanded = false;
        } else {
          this.isExpanded = true;
        }
        this.expansionChange.emit(this.isExpanded);
      });
  }

  menuItems = [
    {
      labelKey: 'Home',
      icon: 'home',
      action: () => this.home(),
      route: '/home',
    },
    {
      labelKey: 'TITLE_ORGANIZACOES',
      icon: 'business',
      action: () => this.navigateToOrganizacoes(),
      route: '/organizacao',
    },
    {
      labelKey: 'TITLE_PRODUTOS',
      icon: 'inventory_2',
      action: () => this.navigateToProdutos(),
      route: '/produtos',
    },
    {
      labelKey: 'TITLE_CHAT',
      icon: 'chat',
      action: () => this.navigateToChat(),
      route: '/chat',
    },
    {
      labelKey: 'login',
      icon: 'login',
      action: () => this.navigateToLogin(),
      route: '/login',
    },
    {
      labelKey: 'TERMS_OF_SERVICE',
      icon: 'description',
      action: () => this.navigateToTerms(),
      route: '/terms',
    },
    {
      labelKey: 'PRIVACY_POLICY',
      icon: 'security',
      action: () => this.navigateToPrivacy(),
      route: '/privacy',
    },
    {
      labelKey: 'LABLE_BACKLOG',
      icon: 'list',
      route: '/backlog',
      action: () => this.navigateToBackLog(),
    },
    {
      labelKey: 'TITLE_AGENDAMENTOS',
      icon: 'event',
      route: '/agendamentos',
      action: () => this.navigateToAgendamentos(),
    },
    {
      labelKey: 'TITLE_AGENDAMENTOS',
      icon: 'event',
      route: '/agendamento',
      action: () => this.navigateToAgendamento(),
    },
    {
      labelKey: 'TITLE_USUARIOS',
      icon: 'people',
      action: () => this.navigateToUsuarios(),
      route: '/usuarios',
    },
    {
      labelKey: 'LABLE_CATEGORIA',
      icon: 'category',
      action: () => this.navigateToCategorias(),
      route: '/categorias',
    },
    {
      labelKey: 'TITLE_GEOLOCALIZACAO',
      icon: 'map',
      action: () => this.navigateToGeolocalizacao(),
      route: '/geolocalizacao',
    },
    // {
    //   label: 'Webcam-Capture',
    //   icon: 'photo_camera',
    //   action: () => this.navigateToWebcamCapture(),
    //   route: '/webcam-capture',
    // },
  ];

  home(): void {
    const authorization = localStorage.getItem('Authorization');
    if (!authorization) {
      this.router.navigate(['/login']);
      return;
    }

    this.router.navigate(['/home']);
  }

  navigateToUsuarios(): void {
    if (this.isAuthorization()) {
      this.router.navigate(['/usuarios']);
    }
  }

  navigateToLogin(): void {
    localStorage.removeItem('Authorization');
    this.router.navigate(['/login']);
  }

  navigateToChat(): void {
    if (this.isAuthorization()) {
      this.router.navigate(['/chat']);
    }
  }

  navigateToTerms() {
    this.router.navigate(['/terms']);
  }

  navigateToPrivacy() {
    this.router.navigate(['/privacy']);
  }

  navigateToOrganizacoes() {
    if (this.isAuthorization()) {
      this.router.navigate(['/organizacao']);
    }
  }

  navigateToProdutos() {
    if (this.isAuthorization()) {
      this.router.navigate(['/produtos']);
    }
  }

  navigateToBackLog() {
    if (this.isAuthorization()) {
      this.router.navigate(['/backlog']);
    }
  }

  navigateToAgendamentos(): void {
    if (this.isAuthorization()) {
      this.router.navigate(['/agendamentos']);
    }
  }

  navigateToAgendamento(): void {
    if (this.isAuthorization()) {
      this.router.navigate(['/agendamento']);
    }
  }

  navigateToCategorias(): void {
    if (this.isAuthorization()) {
      this.router.navigate(['/categorias']);
    }
  }

  navigateToGeolocalizacao(): void {
    if (this.isAuthorization()) {
      this.router.navigate(['/geolocalizacao']);
    }
  }

  // navigateToWebcamCapture(): void {
  //   if (this.isAuthorization()) {
  //     this.router.navigate(['/webcam-capture']);
  //   }
  // }

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
