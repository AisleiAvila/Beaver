import { CommonModule, formatDate, Location } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatSnackBar, MatSnackBarConfig } from '@angular/material/snack-bar';
import { MatTooltipModule } from '@angular/material/tooltip';
import { ActivatedRoute, Router } from '@angular/router';
import { TranslateModule, TranslateService } from '@ngx-translate/core';
import { forkJoin, tap } from 'rxjs';
import { ApiError } from 'src/app/model/apiError.model';
import { Cidade } from 'src/app/model/cidade.model';
import { Estado } from 'src/app/model/estado.model';
import { Foto } from 'src/app/model/foto.model';
import { Pais } from 'src/app/model/pais.model';
import { Perfil } from 'src/app/model/perfil.model';
import { Usuario } from 'src/app/model/usuario.model';
import { UsuarioResponseDTO } from 'src/app/model/usuarioResponseDTO.model';
import { CidadeService } from 'src/app/service/cidade.service';
import { EstadoService } from 'src/app/service/estado.service';
import { ModalCommunicationService } from 'src/app/service/modal-communication.service';
import { PaisService } from 'src/app/service/pais.service';
import { PerfisService } from 'src/app/service/perfis.service';
import { CustomSnackbarComponent } from 'src/app/shared/components/custom-snackbar/custom-snackbar.component';
import { UtilService } from 'src/app/shared/service/util.service';
import { UsuariosService } from '../../../service/usuarios.service';
import { WebcamModalComponent } from '../../../shared/components/webcam-modal/webcam-modal.component';
import { CharCountService } from '../../../shared/service/char-count.service';
import { Endereco } from './../../../model/endereco.model';

@Component({
  selector: 'app-cadastro-usuario',
  templateUrl: './cadastro-usuario.component.html',
  styleUrls: ['./cadastro-usuario.component.scss'],
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatDatepickerModule,
    MatSelectModule,
    MatButtonModule,
    MatIconModule,
    MatTooltipModule,
    MatNativeDateModule,
    MatDialogModule, // Import the module, not the service
    TranslateModule,
  ],
})
export class CadastroUsuarioComponent implements OnInit {
  route = inject(ActivatedRoute);
  usuariosService = inject(UsuariosService);
  perfisService = inject(PerfisService);
  paisService = inject(PaisService);
  estadoService = inject(EstadoService);
  cidadeService = inject(CidadeService);
  modalService = inject(ModalCommunicationService);
  location = inject(Location);
  modalCommunicationService = inject(ModalCommunicationService);
  snackBar = inject(MatSnackBar);
  router = inject(Router);
  charCountService = inject(CharCountService);
  utilService = inject(UtilService);
  translate = inject(TranslateService);
  dialog = inject(MatDialog);

  isEditMode = false;
  isCreateMode = false;
  titulo = '';
  acao = '';
  nomePais = '';
  lstPaises: Pais[] = [];
  paisId: number;
  lstEstados: Estado[] = [];
  estadoId: number;
  nomeEstado = '';
  lstCidades: Cidade[] = [];
  cidadeId: number;
  nomeCidade = '';

  lstPerfis: Perfil[] = []; // Certifique-se de que lstPerfis é um array
  id = 0;
  nome = '';
  senha = '';
  reSenha = '';
  dataNascimento: Date;
  emailUsuarioInput = '';
  perfil: Perfil = { id: 0, nome: '' };
  perfilSelecionadoId: number;
  perfilSelecionado: number;
  paisSelecionadoId: number;
  estadoSelecionadoId: number;
  cidadeSelecionadoId: number;
  confirmarSenha = '';
  endereco: Endereco = {
    id: 0,
    logradouro: '',
    numero: '',
    complemento: '',
    bairro: '',
    cidade_id: {
      id: 0,
      nome: '',
      estado_id: {
        id: 0,
        nome: '',
        pais_id: {
          id: 0,
          nome: '',
        },
      },
    },
    cep: '',
  };

  foto: Foto = {
    id: 0,
    usuario_id: 0,
    foto: '',
    ativo: true,
    data_criacao: '',
    data_atualizacao: '',
  };

  // Variáveis de estado para armazenar mensagens de erro
  nomeErro = '';
  dataNascimentoErro = '';
  emailErro = '';
  perfilErro = '';
  senhaErro = '';
  confirmarSenhaErro = '';
  logradouroErro = '';
  numeroErro = '';
  bairroErro = '';
  cidadeErro = '';
  cepErro = '';
  paisErro = '';
  estadoErro = '';

  // Add these properties to your component class
  profileImageUrl: string | null = null;

  ngOnInit(): void {
    forkJoin([this.getPerfis(), this.getPaises()]).subscribe(() => {
      this.initializeComponent();
    });
  }

  onInputChange(inputId: string, charCountId: string, maxLength: number): void {
    this.charCountService.updateCharCount(inputId, charCountId, maxLength);
  }

  getPerfis() {
    return this.perfisService.getPerfis().pipe(
      tap((perfis) => {
        this.lstPerfis = Array.isArray(perfis) ? perfis : [];
      })
    );
  }

  getPaises() {
    return this.paisService.getPais({ nome: this.nomePais }).pipe(
      tap((paises) => {
        this.lstPaises = Array.isArray(paises) ? paises : [];
      })
    );
  }

  getEstados() {
    this.endereco.cidade_id.estado_id.pais_id.id = this.paisId;
    this.estadoService.findEstados(null, this.paisId).subscribe({
      next: (estados) => {
        console.log('Estados recebidos:', estados);
        this.lstEstados = Array.isArray(estados) ? estados : [];
      },
      error: (erro) => {
        console.error('Erro ao buscar estados:', erro);
      },
    });
  }

  getCidades() {
    this.endereco.cidade_id.estado_id.id = this.estadoId;
    this.cidadeService.getCidade(this.nomeCidade, this.estadoId).subscribe({
      next: (cidades) => {
        this.lstCidades = Array.isArray(cidades) ? cidades : [];
      },
      error: (erro) => {
        console.error('Erro ao buscar cidades:', erro);
      },
    });
  }

  selecionarCidade() {
    this.endereco.cidade_id.id = this.cidadeId;
  }

  validarCampos(): boolean {
    this.resetarErros();

    let isValid = true;

    if (!this.nome?.trim()) {
      this.nomeErro = this.translate.instant('LABLE_NOME_OBRIGATORIO');
      isValid = false;
    }

    if (!this.emailUsuarioInput?.trim()) {
      this.emailErro = this.translate.instant('LABLE_EMAIL_OBRIGATORIO');
      isValid = false;
    } else if (!this.utilService.validarEmail(this.emailUsuarioInput)) {
      this.emailErro = this.translate.instant('LABLE_EMAIL_INVALIDO');
      isValid = false;
    }

    if (!this.dataNascimento) {
      this.dataNascimentoErro = this.translate.instant(
        'LABLE_DATA_NASCIMENTO_OBRIGATORIA'
      );
      isValid = false;
    }

    if (!this.perfilSelecionadoId) {
      this.perfilErro = this.translate.instant('LABLE_PERFIL_OBRIGATORIO');
      isValid = false;
    }

    if (this.isCreateMode) {
      if (!this.senha?.trim()) {
        this.senhaErro = this.translate.instant('LABLE_SENHA_OBRIGATORIA');
        isValid = false;
      }
      if (!this.confirmarSenha?.trim()) {
        this.confirmarSenhaErro = this.translate.instant(
          'LABLE_CONFIRMAR_SENHA_OBRIGATORIA'
        );
        isValid = false;
      }
      if (this.senha !== this.confirmarSenha) {
        this.confirmarSenhaErro = this.translate.instant(
          'LABLE_SENHAS_DIFERENTES'
        );
        isValid = false;
      }
    }

    if (!this.endereco.logradouro?.trim()) {
      this.logradouroErro = this.translate.instant(
        'LABLE_LOGRADOURO_OBRIGATORIO'
      );
      isValid = false;
    }

    if (this.endereco.numero == null || this.endereco.numero == '') {
      this.numeroErro = this.translate.instant('LABLE_NUMERO_OBRIGATORIO');
      isValid = false;
    }

    if (this.endereco.bairro == null || this.endereco.bairro == '') {
      this.bairroErro = this.translate.instant('LABLE_BAIRRO_OBRIGATORIO');
      isValid = false;
    }

    if (this.endereco.cidade_id.id == null || this.endereco.cidade_id.id == 0) {
      this.cidadeErro = this.translate.instant('LABLE_CIDADE_OBRIGATORIA');
      isValid = false;
    }

    if (
      this.endereco.cidade_id.estado_id.pais_id.id == null ||
      this.endereco.cidade_id.estado_id.pais_id.id == 0
    ) {
      this.paisErro = this.translate.instant('LABLE_PAIS_OBRIGATORIA');
      isValid = false;
    }

    if (
      this.endereco.cidade_id.estado_id.id == null ||
      this.endereco.cidade_id.estado_id.id == 0
    ) {
      this.estadoErro = this.translate.instant('LABLE_ESTADO_OBRIGATORIA');
      isValid = false;
    }

    if (!this.endereco.cep?.trim()) {
      this.cepErro = this.translate.instant('LABLE_CEP_OBRIGATORIO');
      isValid = false;
    }

    // Se houver campos inválidos, exibir snackbar com mensagem
    if (!isValid) {
      this.translate
        .get('LABLE_CAMPOS_OBRIGATORIOS')
        .subscribe((texto: string) => {
          this.modalService.abrirModal(texto, 'Erro');
        });
    }

    return isValid;
  }

  private resetarErros(): void {
    this.nomeErro = '';
    this.emailErro = '';
    this.dataNascimentoErro = '';
    this.perfilErro = '';
    this.senhaErro = '';
    this.confirmarSenhaErro = '';
    this.logradouroErro = '';
    this.numeroErro = '';
    this.bairroErro = '';
    this.cidadeErro = '';
    this.cepErro = '';
  }

  salvarUsuario() {
    // Validar todos os campos antes de salvar
    if (!this.validarCampos()) {
      // Rolar a página até o primeiro campo com erro
      const firstErrorField = document.querySelector('.mat-form-field-invalid');
      if (firstErrorField) {
        firstErrorField.scrollIntoView({ behavior: 'smooth', block: 'center' });
      }
      return;
    }

    const usuario = this.criarUsuario();

    let mensagemSucesso = '';
    let mensagemErro = '';

    if (this.acao === 'Alterar') {
      mensagemSucesso = 'Usuário atualizado com sucesso';
      mensagemErro = 'Erro ao atualizar usuário';
    } else {
      mensagemSucesso = 'Usuário criado com sucesso';
      mensagemErro = 'Erro ao criar usuário';
    }

    this.usuariosService.saveUsuario(usuario).subscribe(
      () => {
        this.usuariosService.saveUsuarioFoto(usuario.foto).subscribe(() => {
          this.snackBar.open(mensagemSucesso, 'Fechar', {
            duration: 3000,
            horizontalPosition: 'center',
            verticalPosition: 'top',
          } as MatSnackBarConfig);
          this.router.navigate(['/usuarios']);
        });
      },
      (error) => {
        console.error(mensagemErro, error);
        this.snackBar.openFromComponent(CustomSnackbarComponent, {
          data: {
            message: mensagemErro,
          },
          horizontalPosition: 'center',
          verticalPosition: 'top',
          panelClass: ['snackbar-multiline'],
        });
      }
    );
  }

  excluirFoto() {
    const mensagemSucesso = 'Foto do usuário excluída com sucesso';
    const mensagemErro = 'Erro ao excluir foto do usuário';
    this.profileImageUrl = null;

    if (this.foto.id) {
      this.foto.ativo = false;
      this.usuariosService.deleteUsuarioFoto(this.foto).subscribe(
        () => {
          this.snackBar.open(mensagemSucesso, 'Fechar', {
            duration: 3000,
            horizontalPosition: 'center',
            verticalPosition: 'top',
          } as MatSnackBarConfig);
        },
        (error) => {
          console.error(mensagemErro, error);
          this.snackBar.openFromComponent(CustomSnackbarComponent, {
            data: {
              message: mensagemErro,
            },
            horizontalPosition: 'center',
            verticalPosition: 'top',
            panelClass: ['snackbar-multiline'],
          });
        }
      );
    }
  }

  cancelar() {
    this.location.back();
  }

  private formatarErro(error: ApiError): string {
    // Formate a mensagem de erro conforme necessário
    if (error.error && error.error.message) {
      return error.error.message;
    } else if (error.message) {
      return error.message;
    } else {
      return 'Ocorreu um erro desconhecido';
    }
  }

  private definirTitulo(acao: string | undefined): void {
    this.isEditMode = true;
    let titleKey = '';

    if (acao === 'Alterar') {
      titleKey = 'TITLE_ALTERAR_USUARIO';
    } else if (acao === 'Cadastrar') {
      titleKey = 'TITLE_CADASTRAR_USUARIO';
    } else {
      titleKey = 'TITLE_DETALHAR_USUARIO';
      this.isEditMode = false;
    }

    // Inscrever-se nas mudanças de idioma
    this.translate.onLangChange.subscribe(() => {
      this.translate.get(titleKey).subscribe((traducao: string) => {
        this.titulo = traducao;
      });
    });

    // Definir título inicial
    this.translate.get(titleKey).subscribe((traducao: string) => {
      this.titulo = traducao;
    });
  }

  private preencherFormulario(jsonData: UsuarioResponseDTO): void {
    let data;
    if (typeof jsonData === 'string') {
      try {
        data = JSON.parse(jsonData);
      } catch (e) {
        console.error('Erro ao parsear JSON:', e);
        return;
      }
    } else {
      data = jsonData;
    }

    const usuario = data.usuarios[0];

    this.id = usuario.id;
    this.nome = usuario.nome;
    this.dataNascimento = new Date(usuario.dataNascimento);
    this.emailUsuarioInput = usuario.email;
    this.perfilSelecionadoId =
      usuario.perfis.length > 0 ? usuario.perfis[0].id : null;
    if (usuario.enderecos && usuario.enderecos.length > 0) {
      this.endereco = usuario.enderecos[0];
      this.cidadeSelecionadoId = this.endereco.cidade_id.id;
      this.estadoSelecionadoId = this.endereco.cidade_id.estado_id.id;
      this.paisSelecionadoId = this.endereco.cidade_id.estado_id.pais_id.id;
      this.cidadeId = this.endereco.cidade_id.id;
      this.estadoId = this.endereco.cidade_id.estado_id.id;
      this.paisId = this.endereco.cidade_id.estado_id.pais_id.id;
      forkJoin([this.getEstados(), this.getCidades()]).subscribe(() => {
        this.initializeComponent();
      });
    }

    // Processamento da foto/imagem
    if (usuario.foto && usuario.foto.foto) {
      // Verifica se a imagem já tem o prefixo data:image
      if (usuario.foto.foto.startsWith('data:image')) {
        this.profileImageUrl = usuario.foto.foto;
        this.foto.foto =
          usuario.foto.foto.split('base64,')[1] || usuario.foto.foto;
      } else {
        // Se for apenas base64 sem prefixo, adiciona o prefixo
        this.profileImageUrl = `data:image/jpeg;base64,${usuario.foto.foto}`;
        this.foto.foto = usuario.foto.foto;
      }

      // Atualiza outros campos da foto
      this.foto.id = usuario.foto.id || 0;
      this.foto.usuario_id = usuario.id;
      this.foto.ativo = usuario.foto.ativo || true;
      this.foto.data_criacao =
        usuario.foto.data_criacao || new Date().toISOString();
      this.foto.data_atualizacao =
        usuario.foto.data_atualizacao || new Date().toISOString();

      console.log(
        'Imagem carregada com sucesso:',
        this.profileImageUrl.substring(0, 50) + '...'
      );
    } else {
      console.log('Usuário não possui foto cadastrada');
      this.profileImageUrl = null;
    }

    this.validarCampos();
  }

  private initializeComponent() {
    this.route.paramMap.subscribe((params) => {
      this.acao = history.state.acao || '';
      const id = params.get('id');

      if (this.acao) {
        this.definirTitulo(this.acao);
      } else {
        this.definirTitulo(undefined);
      }

      if (id) {
        this.isCreateMode = false;
        this.usuariosService.getUsuarioById(+id).subscribe((usuario) => {
          this.preencherFormulario(usuario);
        });
      } else {
        this.isCreateMode = true;
        this.emailUsuarioInput = '';
      }
    });
  }

  private criarUsuario(): Usuario {
    // Encontrar o perfil selecionado
    const perfilSelecionado = this.lstPerfis.find((perfil) => {
      return Number(perfil.id) === Number(this.perfilSelecionadoId);
    });

    // Montar objeto de foto do usuário
    if (this.profileImageUrl) {
      const base64Image = this.profileImageUrl.includes('base64,')
        ? this.profileImageUrl.split('base64,')[1]
        : this.profileImageUrl;

      this.foto.foto = base64Image;
      this.foto.usuario_id = this.id;
      this.foto.ativo = true;
      this.foto.data_criacao = new Date().toISOString();
      this.foto.data_atualizacao = new Date().toISOString();
    }

    const perfis = perfilSelecionado
      ? [
          {
            id: this.perfilSelecionadoId,
            nome: perfilSelecionado.nome,
          },
        ]
      : [];

    const usuario = {
      id: this.id,
      nome: this.nome,
      dataNascimento: this.dataNascimento
        ? formatDate(this.dataNascimento, 'yyyy-MM-dd', 'en-US')
        : null,
      email: this.emailUsuarioInput,
      senha: this.senha,
      perfis: perfis,
      enderecos: [
        {
          id: this.endereco.id,
          logradouro: this.endereco.logradouro,
          numero: this.endereco.numero,
          complemento: this.endereco.complemento,
          bairro: this.endereco.bairro,
          cidade_id: {
            id: this.endereco.cidade_id.id,
            nome: this.nomeCidade,
            estado_id: {
              id: this.endereco.cidade_id.estado_id.id,
              nome: this.nomeEstado,
              pais_id: {
                id: this.endereco.cidade_id.estado_id.pais_id.id,
                nome: this.nomePais,
              },
            },
          },
          cep: this.endereco.cep,
        },
      ],
      foto: {
        id: this.foto.id,
        usuario_id: this.foto.usuario_id,
        foto: this.foto.foto,
        ativo: this.foto.ativo,
        data_criacao: this.foto.data_criacao,
        data_atualizacao: this.foto.data_atualizacao,
      },
    };

    return usuario;
  }

  // Add these methods to your component class
  tirarFoto(): void {
    const dialogRef = this.dialog.open(WebcamModalComponent, {
      width: '500px',
      disableClose: true,
      data: {},
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        console.log('Imagem capturada da webcam:', result);

        // Criar elemento de imagem para garantir que carregue corretamente
        const img = new Image();
        img.onload = () => {
          // A imagem foi carregada com sucesso
          this.profileImageUrl = result;

          // Se necessário, você pode redimensionar a imagem aqui
          // this.resizeImage(img, 300, 300);
        };
        img.onerror = (error) => {
          this.profileImageUrl = null;
          console.error('Erro ao carregar imagem da webcam:', error);
          this.snackBar.open(
            'Não foi possível processar a imagem da webcam',
            'OK',
            {
              duration: 3000,
            }
          );
        };

        // Iniciar carregamento da imagem
        img.src = result;
      }
    });
  }

  // Método auxiliar para redimensionar imagem se necessário
  private resizeImage(
    img: HTMLImageElement,
    maxWidth: number,
    maxHeight: number
  ): string {
    const canvas = document.createElement('canvas');
    let width = img.width;
    let height = img.height;

    // Redimensionar mantendo proporção
    if (width > height) {
      if (width > maxWidth) {
        height = Math.round((height * maxWidth) / width);
        width = maxWidth;
      }
    } else {
      if (height > maxHeight) {
        width = Math.round((width * maxHeight) / height);
        height = maxHeight;
      }
    }

    canvas.width = width;
    canvas.height = height;

    const ctx = canvas.getContext('2d');
    ctx.drawImage(img, 0, 0, width, height);

    return canvas.toDataURL('image/jpeg');
  }

  importarImagem(): void {
    // Criar um elemento de input de arquivo oculto e acioná-lo
    const fileInput = document.createElement('input');
    fileInput.type = 'file';
    fileInput.accept = 'image/*';
    fileInput.style.display = 'none';

    fileInput.onchange = (event: any) => {
      const file = event.target.files[0];
      if (file) {
        const reader = new FileReader();
        reader.onload = (e: any) => {
          this.profileImageUrl = e.target.result;
        };
        reader.readAsDataURL(file);
      }
    };

    document.body.appendChild(fileInput);
    fileInput.click();
    document.body.removeChild(fileInput);
  }
}
