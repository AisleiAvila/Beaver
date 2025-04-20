import { CommonModule } from '@angular/common';
import {
  Component,
  ElementRef,
  inject,
  OnDestroy,
  OnInit,
  Renderer2,
  ViewChild,
} from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatTooltipModule } from '@angular/material/tooltip';
import { ActivatedRoute, Router } from '@angular/router';
import { TranslateModule, TranslateService } from '@ngx-translate/core';
import { firstValueFrom, map, Subscription, tap } from 'rxjs';
import { OrganizacaoStateService } from 'src/app/service/organizacao-state.service';
import { OrganizacoesService } from 'src/app/service/organizacoes.service';
import { UsuariosService } from 'src/app/service/usuarios.service';
import { DeviceService } from 'src/app/shared/service/device.service';
import { LoginService } from '../../service/login.service';
import { ModalCommunicationService } from '../../service/modal-communication.service';
import { OrganizacaoWrapper } from './../../wrapper/0rganizacao-wrapper.interface';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    TranslateModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatTooltipModule,
    MatProgressBarModule,
    MatCheckboxModule,
  ],
})
/**
 * Componente responsável por exibir a tela de login da aplicação.
 */
export class LoginComponent implements OnInit, OnDestroy {
  loginService = inject(LoginService);
  router = inject(Router);
  modalService = inject(ModalCommunicationService);
  route = inject(ActivatedRoute);
  renderer = inject(Renderer2);
  translate = inject(TranslateService);
  organizacoesService = inject(OrganizacoesService);
  organizacaoStateService = inject(OrganizacaoStateService);
  usuarioService = inject(UsuariosService);
  deviceService = inject(DeviceService);

  @ViewChild('loginButton') loginButton!: ElementRef;

  email: string | undefined;
  senha: string | undefined;
  lembrarSenha: boolean | undefined;
  mensagem: string;
  isProcessing = false;

  isMobile = false;
  private subscription = new Subscription();

  organizacaoSelecionada: OrganizacaoWrapper | undefined;
  organizacoes: OrganizacaoWrapper[] | undefined;

  ngOnInit(): void {
    // Obter a mensagem dos parâmetros da URL
    this.route.queryParams.subscribe((params) => {
      this.mensagem = params['mensagem'];
    });

    this.subscription.add(
      this.deviceService.isMobile$.subscribe((isMobile) => {
        this.isMobile = isMobile;
        console.log('Login - dispositivo móvel:', isMobile);
      })
    );
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }

  /**
   * Método responsável por realizar o login do usuário.
   * @returns {Promise<void>}
   */
  async onLogin(): Promise<void> {
    this.isProcessing = true;

    // Verificar credenciais
    if (!this.validarCredenciais()) {
      this.isProcessing = false;
      return;
    }

    try {
      // Aguarda a execução do getLogin
      await this.getLogin(this.email, this.senha);

      // Após o login, executa o getOrganizacoes
      await this.getOrganizacoes();
    } catch (error) {
      console.error('Erro durante o login ou ao buscar organizações:', error);
      this.isProcessing = false;
    }
  }

  async getOrganizacoes(): Promise<void> {
    try {
      // Busca as organizações por ID
      this.organizacoes = await this.buscarOrganizacoesPorId();

      // Verifica se há organizações disponíveis
      if (!this.organizacoes || this.organizacoes.length === 0) {
        console.error('Nenhuma organização encontrada para o usuário.');
        this.isProcessing = false;
        return;
      }

      // Caso haja apenas uma organização, seleciona automaticamente
      if (this.organizacoes.length === 1) {
        this.selecionarOrganizacao(this.organizacoes[0]);
        return;
      }

      // Caso haja mais de uma organização, exibe o modal de seleção
      this.exibirSelecaoOrganizacao(this.organizacoes);
    } catch (error) {
      console.error('Erro ao buscar organizações:', error);
      this.isProcessing = false;
    }
  }

  /**
   * Método responsável por selecionar uma organização e armazenar seus dados.
   * @param organizacaoWrapper Organização selecionada
   */
  private selecionarOrganizacao(organizacaoWrapper: OrganizacaoWrapper): void {
    this.organizacaoSelecionada = organizacaoWrapper;

    let org;

    organizacaoWrapper.organizacoes.forEach((organizacao) => {
      org = organizacao;
    });

    org.organizacoes.forEach((organizacao2) => {
      // Armazena o ID e o nome da organização no localStorage
      localStorage.setItem('organizacaoId', organizacao2.id.toString());
      localStorage.setItem('organizacaoNome', organizacao2.nome);
    });

    this.isProcessing = false;

    // Redireciona para o dashboard
    this.router.navigate(['/dashboard']);
  }

  /**
   * Método responsável por realizar o login do usuário.
   * @param {string} email - Email do usuário.
   * @param {string} senha - Senha do usuário.
   * @param {number} [oganzacaoId] - ID da organização do usuário.
   */
  private getLogin(email: string, senha: string): Promise<void> {
    return new Promise((resolve, reject) => {
      this.loginService.getLogin(email, senha).subscribe({
        next: (response) => {
          console.log('Login bem-sucedido', response);
          resolve();
        },
        error: (error) => {
          console.error('Erro no login', error);
          this.isProcessing = false;
          this.renderer.addClass(this.loginButton.nativeElement, 'error');
          setTimeout(() => {
            this.renderer.removeClass(this.loginButton.nativeElement, 'error');
          }, 1000);
          reject(error); // Rejeita a Promise em caso de erro
        },
      });
    });
  }

  /**
   * Método responsável por redirecionar para a tela de recuperação de senha do usuário.
   */
  recuperarSenha(): void {
    this.router.navigate(['/lembrar-senha']);
    // if (!this.email) {
    //   this.modalService.abrirModal(
    //     'Email é obrigatório',
    //     'Erro de Recuperação de Senha'
    //   );
    // } else {
    //   this.modalService.abrirModal(
    //     'Falta implementar método para Esqueceu a senha...',
    //     'Recuperação de Senha'
    //   );
    // }
  }

  /**
   * Método responsável por exibir o modal de seleção de organização.
   * @param {OrganizacaoWrapper[]} organizacoes - Lista de organizações.
   */
  private exibirSelecaoOrganizacao(organizacoes: OrganizacaoWrapper[]): void {
    const dialogRef =
      this.modalService.abrirModalSelecaoOrganizacao(organizacoes);

    dialogRef.afterClosed().subscribe({
      next: (resultado: OrganizacaoWrapper) => {
        if (
          resultado &&
          resultado.organizacoes &&
          resultado.organizacoes.length > 0
        ) {
          this.organizacaoSelecionada = {
            organizacoes: [organizacoes[0].organizacoes[0]], // Atribui a primeira organização
            totalRecords: 1,
          };

          let org;

          resultado.organizacoes.forEach((organizacao) => {
            org = organizacao;
          });

          org.organizacoes.forEach((organizacao2) => {
            // Armazena o ID e o nome da organização no localStorage
            localStorage.setItem('organizacaoId', organizacao2.id.toString());
            localStorage.setItem('organizacaoNome', organizacao2.nome);
          });

          this.organizacaoStateService.atualizarOrganizacao(
            localStorage.getItem('organizacaoNome'),
            Number(localStorage.getItem('organizacaoId'))
          );

          // Redirecionar
          this.router.navigate(['/dashboard']);
        } else {
          console.log('Nenhuma organização válida selecionada');
        }
      },
      error: (err) => {
        console.error('Erro na seleção:', err);
      },
    });
  }

  /**
   * Método responsável por buscar todas as organizações disponíveis.
   * Retorna uma Promise que é resolvida quando as organizações forem carregadas.
   */
  private buscarOrganizacoes(): Promise<OrganizacaoWrapper[]> {
    return new Promise((resolve, reject) => {
      this.organizacoesService
        .getOrganizacoes({})
        .pipe(
          map((response) => {
            // Transformar cada Organizacao em um OrganizacaoWrapper
            return response.organizacoes.map((org) => ({
              organizacoes: [org],
              totalRecords: 1,
            }));
          }),
          tap((wrappedOrganizacoes) => {
            // Agora o tipo está correto
            this.organizacoes = wrappedOrganizacoes;
            console.log('Organizações armazenadas:', this.organizacoes);
          })
        )
        .subscribe({
          next: (organizacoes) => {
            console.log('Busca de organizações concluída com sucesso');
            resolve(organizacoes); // Resolve a Promise com as organizações
          },
          error: (error) => {
            console.error('Erro ao buscar organizações:', error);
            reject(error); // Rejeita a Promise em caso de erro
          },
        });
    });
  }

  /**
   * Método responsável por buscar organizações por ID.
   * Retorna uma Promise que é resolvida quando as organizações forem carregadas.
   */
  private buscarOrganizacoesPorId(): Promise<OrganizacaoWrapper[]> {
    return new Promise((resolve, reject) => {
      const organizacoesIds = localStorage
        .getItem('organizacoesIds')
        ?.split(',')
        .map(Number);

      if (!organizacoesIds || organizacoesIds.length === 0) {
        console.error('Nenhum ID de organização encontrado.');
        reject('Nenhum ID de organização encontrado');
        return;
      }

      // Usar Promise.all para aguardar todas as consultas
      const consultas = organizacoesIds.map((organizacaoId) =>
        this.organizacoesService.getOrganizacaoById(organizacaoId).toPromise()
      );

      Promise.all(consultas)
        .then((responses) => {
          const organizacoesWrappers: OrganizacaoWrapper[] = responses.map(
            (response) => ({
              organizacoes: [response],
              totalRecords: 1,
            })
          );

          resolve(organizacoesWrappers);
        })
        .catch((error) => {
          console.error('Erro ao carregar organizações:', error);
          reject(error);
        });
    });
  }

  /**
   * Método responsável por validar as credenciais de login.
   * @returns Verdadeiro se as credenciais são válidas, falso caso contrário
   */
  private validarCredenciais(): boolean {
    // Verificar se email e senha foram fornecidos
    if (!this.email || !this.senha) {
      this.exibirErroLogin('ERRO_LOGIN');
      return false;
    }

    return true;
  }

  /**
   * Método responsável por exibir mensagem de erro de login.
   * @param chaveTraducao Chave para tradução da mensagem
   */
  private exibirErroLogin(chaveTraducao: string): void {
    this.translate.get(chaveTraducao).subscribe((texto: string) => {
      this.modalService.abrirModal(texto, 'Erro de Login');
    });
  }

  /**
   * Método responsável por obter o perfil do usuário de forma assíncrona.
   * @returns Promise com o perfil do usuário
   */
  private async obterPerfil(): Promise<string | null> {
    try {
      // Usar firstValueFrom para converter Observable para Promise
      const perfil = await firstValueFrom(
        this.usuarioService.getPerfilUsuario(this.email!, this.senha!)
      );

      return perfil?.nome?.toUpperCase() || null;
    } catch (error) {
      console.error('Erro ao obter perfil:', error);
      this.exibirErroLogin('ERRO_LOGIN');
      return null;
    }
  }
}
