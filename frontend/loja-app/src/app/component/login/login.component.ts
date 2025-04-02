import { CommonModule } from '@angular/common';
import {
  Component,
  ElementRef,
  OnInit,
  Renderer2,
  ViewChild,
  inject,
} from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatTooltipModule } from '@angular/material/tooltip';
import { ActivatedRoute, Router } from '@angular/router';
import { TranslateModule, TranslateService } from '@ngx-translate/core';
import { firstValueFrom, map, tap } from 'rxjs';
import { Organizacao } from 'src/app/model/organizacao.model';
import { OrganizacaoStateService } from 'src/app/service/organizacao-state.service';
import { OrganizacoesService } from 'src/app/service/organizacoes.service';
import { UsuariosService } from 'src/app/service/usuarios.service';
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
  ],
})
/**
 * Componente responsável por exibir a tela de login da aplicação.
 */
export class LoginComponent implements OnInit {
  loginService = inject(LoginService);
  router = inject(Router);
  modalService = inject(ModalCommunicationService);
  route = inject(ActivatedRoute);
  renderer = inject(Renderer2);
  translate = inject(TranslateService);
  organizacoesService = inject(OrganizacoesService);
  organizacaoStateService = inject(OrganizacaoStateService);
  usuarioService = inject(UsuariosService);

  @ViewChild('loginButton') loginButton!: ElementRef;

  email: string | undefined;
  senha: string | undefined;
  lembrarSenha: boolean | undefined;
  mensagem: string;
  isProcessing = false;

  organizacaoSelecionada: OrganizacaoWrapper | undefined;
  organizacoes: OrganizacaoWrapper[] | undefined;

  ngOnInit(): void {
    // Obter a mensagem dos parâmetros da URL
    this.route.queryParams.subscribe((params) => {
      this.mensagem = params['mensagem'];
    });
  }

  async onLogin(): Promise<void> {
    this.isProcessing = true;

    // Verificar credenciais
    if (!this.validarCredenciais()) {
      this.isProcessing = false;
      return;
    }

    // Obter perfil do usuário
    const perfil = await this.obterPerfil();
    if (!perfil) {
      return; // Erro já tratado em obterPerfil()
    }

    if (perfil === 'ADMINISTRADOR') {
      try {
        // Aguarda a conclusão da busca de organizações
        this.organizacoes = await this.buscarOrganizacoes();

        console.log('Organizações carregadas, pronto para mostrar modal');

        // Agora podemos exibir o modal com segurança
        this.exibirSelecaoOrganizacao(this.organizacoes || []);
      } catch (error) {
        console.error('Falha ao buscar organizações:', error);
        this.isProcessing = false;
        this.modalService.abrirModal(
          'Não foi possível carregar as organizações. Tente novamente mais tarde.',
          'Erro'
        );
      }
    } else {
      this.getLogin(this.email, this.senha);
    }
  }

  private getLogin(email: string, senha: string, oganzacaoId?: number): void {
    this.loginService.getLogin(email, senha, oganzacaoId).subscribe({
      next: (response) => {
        console.log('Login bem-sucedido', response);
        this.router.navigate(['/dashboard']);
        this.isProcessing = false;
        //        this.getOrganizacao(response.organizacoesIds[0]);
      },
      error: (error) => {
        console.error('Erro no login', error);
        this.isProcessing = false;
        this.renderer.addClass(this.loginButton.nativeElement, 'error');
        setTimeout(() => {
          this.renderer.removeClass(this.loginButton.nativeElement, 'error');
        }, 1000);
      },
    });
  }

  /**
   * Método responsável por recuperar a senha do usuário.
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

  async getOrganizacao(organizacaoId: number): Promise<void> {
    // Aqui você pode usar o ID da organização retornado na resposta do login
    // para buscar os dados da organização

    if (!organizacaoId || organizacaoId === 0) {
      console.error('Nenhuma organização encontrada para o usuário.');
      return;
    }

    try {
      // Criar um array para armazenar a requisição (observável)
      const request =
        this.organizacoesService.getOrganizacaoById(organizacaoId);

      // Criar um array com esta única requisição
      const requests = [request];

      // Verificar se temos a requisição
      if (!request) {
        console.error('Nenhuma requisição encontrada para a organização.');
        return;
      }

      try {
        const respostasAPI = await Promise.all(
          requests.map((request) => firstValueFrom(request))
        );

        // Transformar as respostas para o formato esperado
        const organizacoes: OrganizacaoWrapper[] = respostasAPI.map(
          (resposta) => {
            // Verificar a estrutura da resposta e adaptá-la
            if (resposta && Array.isArray(resposta)) {
              // Se a resposta for um array, considere o primeiro item
              return {
                organizacoes: resposta,
                totalRecords: resposta.length,
              };
            } else if (resposta && typeof resposta === 'object') {
              // Se já for um objeto com a estrutura esperada
              if ('organizacoes' in resposta) {
                return resposta as unknown as OrganizacaoWrapper;
              }

              // Se for um objeto simples (Organizacao), transforme em wrapper
              return {
                organizacoes: [resposta as Organizacao],
                totalRecords: 1,
              };
            }

            // Caso não seja possível determinar, crie um wrapper vazio
            console.error('Estrutura de resposta não reconhecida:', resposta);
            return {
              organizacoes: [],
              totalRecords: 0,
            };
          }
        );

        if (organizacoes.length === 1) {
          // Agora temos o objeto Organizacao resolvido, não mais uma Promise
          const org = organizacoes[0].organizacoes[0];

          this.organizacaoSelecionada = {
            organizacoes: [org],
            totalRecords: 1,
          };

          // Armazenar o ID da organização para uso em todo o aplicativo
          localStorage.setItem(
            'organizacaoId',
            this.organizacaoSelecionada.organizacoes[0].id.toString()
          );

          localStorage.setItem(
            'organizacaoNome',
            this.organizacaoSelecionada.organizacoes[0].nome
          );

          this.organizacaoStateService.atualizarOrganizacao(
            this.organizacaoSelecionada.organizacoes[0].nome,
            this.organizacaoSelecionada.organizacoes[0].id
          );

          // Redirecionar para a página principal
          this.router.navigate(['/dashboard']);
        } else if (organizacoes.length > 1) {
          this.isProcessing = false;
          // Exibir modal para seleção de organização
          this.exibirSelecaoOrganizacao(organizacoes);
        }
      } catch (error) {
        console.error('Erro ao obter organizações:', error);
        this.isProcessing = false;
      }
    } catch (error) {
      console.error('Erro ao buscar organizações:', error);
      this.isProcessing = false;
    }
  }

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
          }; // Atribui a primeira organização

          // Armazenar no localStorage
          localStorage.setItem(
            'organizacaoId',
            resultado.organizacoes[0].id.toString()
          );
          localStorage.setItem(
            'organizacaoNome',
            resultado.organizacoes[0].nome
          );

          this.organizacaoStateService.atualizarOrganizacao(
            resultado.organizacoes[0].nome,
            resultado.organizacoes[0].id
          );

          this.getLogin(this.email, this.senha, resultado.organizacoes[0].id);

          // Redirecionar
          // this.router.navigate(['/dashboard']);
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
   * Validar as credenciais de login
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
   * Exibir mensagem de erro de login
   * @param chaveTraducao Chave para tradução da mensagem
   */
  private exibirErroLogin(chaveTraducao: string): void {
    this.translate.get(chaveTraducao).subscribe((texto: string) => {
      this.modalService.abrirModal(texto, 'Erro de Login');
    });
  }

  /**
   * Obter o perfil do usuário de forma assíncrona
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
