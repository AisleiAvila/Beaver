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
  isExpanded = false;

  // constructor() {
  //   // Definir idioma padrão
  //   this.translate.setDefaultLang('pt');

  //   // Usar idioma padrão
  //   this.translate.use('pt');

  //   this.router.events.subscribe((event) => {
  //     if (event instanceof NavigationEnd) {
  //       this.isLoginScreen = this.router.url === '/login';
  //     }
  //   });
  // }

  // Inicializador que substitui o constructor
  private initialize = (() => {
    // Definir idioma padrão
    this.translate.setDefaultLang('pt');

    // Usar idioma padrão
    this.translate.use('pt');

    this.router.events
      .pipe(filter((event) => event instanceof NavigationEnd))
      .subscribe((event) => {
        if (event instanceof NavigationEnd) {
          this.isLoginScreen = this.router.url === '/login';
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
