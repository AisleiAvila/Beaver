import { CommonModule } from '@angular/common';
import { Component, inject, OnDestroy, OnInit } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatToolbarModule } from '@angular/material/toolbar';
import { NavigationEnd, Router } from '@angular/router';
import { TranslateModule, TranslateService } from '@ngx-translate/core';
import { Subscription } from 'rxjs';
import { OrganizacaoStateService } from 'src/app/service/organizacao-state.service';
import { AuthService } from 'src/app/shared/service/auth.service';
import { LoginService } from '../../service/login.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
  standalone: true,
  imports: [
    CommonModule,
    MatToolbarModule,
    MatIconModule,
    MatListModule,
    TranslateModule,
  ],
})
export class HeaderComponent implements OnInit, OnDestroy {
  router = inject(Router);
  loginService = inject(LoginService);
  authService = inject(AuthService);
  translate = inject(TranslateService);
  organizacaoStateService = inject(OrganizacaoStateService);

  isLoginScreen = false;
  title = 'Loja XPTO';

  nomeOrganizacao = '';
  private subscription: Subscription = new Subscription();

  nomeUsuario: string | null = localStorage.getItem('nomeUsuario');
  //nomeOrganizacao: string | null = localStorage.getItem('organizacaoNome');

  ngOnInit(): void {
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.isLoginScreen = this.router.url === '/login';
      }
    });

    // Inscrever-se para receber atualizações do nome da organização
    this.subscription.add(
      this.organizacaoStateService.nomeOrganizacao$.subscribe((nome) => {
        this.nomeOrganizacao = nome;
      })
    );

    this.checkAuthorization();
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  private limparDadosELogout(): void {
    localStorage.removeItem('Authorization');
    localStorage.removeItem('nomeUsuario');
    this.organizacaoStateService.limparOrganizacao();
    this.router.navigate(['/login']);
  }

  logout(): void {
    const token = localStorage.getItem('Authorization');
    if (token) {
      this.authService.revogarToken(token).subscribe(
        () => {
          this.limparDadosELogout();
        },
        (error) => {
          console.error('Erro ao revogar token:', error);
          this.limparDadosELogout();
        }
      );
    } else {
      this.limparDadosELogout();
    }
  }

  getNomeUsuario(): string {
    this.nomeUsuario = localStorage.getItem('nomeUsuario');
    return this.nomeUsuario || '';
  }

  checkAuthorization(): void {
    const authorization = localStorage.getItem('Authorization');
    // Se não tiver autorização e não estiver na tela de login ou na tela Lembrar Senha, redireciona para a tela de login
    if (this.router.url === '/lembrar-senha') {
      this.isLoginScreen = true;
    } else if (!authorization && this.router.url !== '/lembrar-senha') {
      this.navigateToLandingpage();
    }
  }

  navigateToLandingpage(): void {
    localStorage.removeItem('navigateToLogin');
    this.router.navigate(['/landingpage']);
  }

  changeLanguage(lang: string) {
    this.translate.use(lang);
  }
}
