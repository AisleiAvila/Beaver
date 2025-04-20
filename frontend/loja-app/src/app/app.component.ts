import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import {
  NavigationEnd,
  Router,
  RouterModule,
  RouterOutlet,
} from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { filter } from 'rxjs/operators';
import { BodyComponent } from './component/body/body.component';
import { HeaderComponent } from './component/header/header.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    HeaderComponent,
    BodyComponent,
    RouterOutlet,
  ],
})
export class AppComponent {
  private router = inject(Router);
  private translate = inject(TranslateService);

  isLoginScreen = false;
  isLandingPage = false;
  isExpanded = false;

  // Inicializador que substitui o constructor
  private initialize = (() => {
    // Definir idioma padrão
    this.translate.setDefaultLang('pt');

    // Usar idioma padrão
    this.translate.use('pt');

    // Redirecionar para a landing page ao iniciar
    if (this.router.url === '/') {
      this.router.navigate(['/landingpage']);
    }

    // Monitorar eventos de navegação do Angular Router
    this.router.events
      .pipe(filter((event) => event instanceof NavigationEnd)) // Filtrar apenas eventos de navegação concluída
      .subscribe((event) => {
        if (event instanceof NavigationEnd) {
          this.isLoginScreen = this.router.url === '/login';
          this.isLandingPage = this.router.url === '/landingpage';
        }
      });
  })();

  /**
   * Método responsável por atualizar o estado de expansão do menu.
   */
  onExpansionChange(isExpanded: boolean): void {
    this.isExpanded = isExpanded;
  }
}
