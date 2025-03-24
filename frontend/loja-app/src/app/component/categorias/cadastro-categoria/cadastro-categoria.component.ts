import { CommonModule, NgFor } from '@angular/common';
import { Component, NO_ERRORS_SCHEMA, OnInit, inject } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatChipInputEvent, MatChipsModule } from '@angular/material/chips';
import { MatOptionModule } from '@angular/material/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { TranslateModule, TranslateService } from '@ngx-translate/core';
import { StatusServico } from 'src/app/enum/status-servico.enum';
import { SubcategoriaRequest } from 'src/app/interfaces/subcategoria-request.interface';
import { Categoria } from 'src/app/model/categoria.model';
import { Subcategoria } from 'src/app/model/subcategoria.model';
import { CategoriasService } from 'src/app/service/categorias.service';
import { SubcategoriasService } from 'src/app/service/subcategorias.service';

@Component({
  selector: 'app-cadastro-categoria',
  templateUrl: './cadastro-categoria.component.html',
  styleUrls: ['./cadastro-categoria.component.scss'],
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    MatIconModule,
    MatChipsModule,
    TranslateModule,
    MatOptionModule,
    NgFor,
  ],
  schemas: [NO_ERRORS_SCHEMA],
})
export class CadastroCategoriaComponent implements OnInit {
  route = inject(ActivatedRoute);
  fb = inject(FormBuilder);
  router = inject(Router);
  snackBar = inject(MatSnackBar);
  translate = inject(TranslateService);
  categoriasService = inject(CategoriasService);
  subcategoriasService = inject(SubcategoriasService);
  subcategorias: Subcategoria[] = [];

  categoriaForm: FormGroup;
  isEditMode = false;
  isCreateMode = false;
  titulo = '';
  tituloInformacoesBasicas = '';
  acao = '';

  statusOptions: StatusServico[] = [];
  statusSelecionado: StatusServico;
  allStatusSelected = false;

  ngOnInit(): void {
    this.initializeCategoriaForm();
    this.initializeComponent();
    this.loadStatusOptions();
  }

  loadSubcategorias(categoria_id: number): void {
    const params: SubcategoriaRequest = {
      limit: 50,
      offset: 0,
      categoria_id: categoria_id,
    };
    this.subcategoriasService
      .getSubcategorias(params)
      .subscribe((subcategorias) => {
        this.subcategorias = subcategorias;
      });
  }

  onSubmit(): void {
    if (this.categoriaForm.valid) {
      const categoria: Categoria = this.categoriaForm.value;

      // Se for edição, inclui o ID
      if (!this.isCreateMode && this.route.snapshot.paramMap.get('id')) {
        categoria.id = +this.route.snapshot.paramMap.get('id');
      }

      // Adicionar subcategorias existentes que não foram removidas
      // categoria.subcategorias = [
      //   ...this.subcategorias,
      //   ...this.categoriaForm.get('subcategorias').value,
      // ];

      const metodo = this.isCreateMode
        ? this.categoriasService.createCategoria(categoria)
        : this.categoriasService.updateCategoria(categoria.id, categoria);

      metodo.subscribe({
        next: () => {
          const mensagem = this.isCreateMode
            ? 'Categoria criada com sucesso!'
            : 'Categoria atualizada com sucesso!';

          this.snackBar.open(mensagem, 'Fechar', {
            duration: 3000,
            horizontalPosition: 'end',
            verticalPosition: 'bottom',
          });

          this.router.navigate(['/categorias']);
        },
        error: (error) => {
          console.error('Erro:', error);
          this.snackBar.open('Erro ao salvar categoria', 'Fechar', {
            duration: 3000,
            horizontalPosition: 'end',
            verticalPosition: 'bottom',
            panelClass: ['error-snackbar'],
          });
        },
      });
    }
  }

  addKeyword(event: MatChipInputEvent): void {
    const input = event.input;
    const value = event.value;

    if ((value || '').trim()) {
      this.categoriaForm.get('palavras_chave').value.push(value.trim());
      this.categoriaForm.get('palavras_chave').updateValueAndValidity();
    }

    if (input) {
      input.value = '';
    }
  }

  removeKeyword(keyword: string): void {
    const index = this.categoriaForm
      .get('palavras_chave')
      .value.indexOf(keyword);

    if (index >= 0) {
      this.categoriaForm.get('palavras_chave').value.splice(index, 1);
      this.categoriaForm.get('palavras_chave').updateValueAndValidity();
    }
  }

  addDocument(event: MatChipInputEvent): void {
    const input = event.input;
    const value = event.value;

    if ((value || '').trim()) {
      this.categoriaForm.get('documentos_necessarios').value.push(value.trim());
      this.categoriaForm.get('documentos_necessarios').updateValueAndValidity();
    }

    if (input) {
      input.value = '';
    }
  }

  removeDocument(doc: string): void {
    const index = this.categoriaForm
      .get('documentos_necessarios')
      .value.indexOf(doc);

    if (index >= 0) {
      this.categoriaForm.get('documentos_necessarios').value.splice(index, 1);
      this.categoriaForm.get('documentos_necessarios').updateValueAndValidity();
    }
  }

  cancelar(): void {
    this.router.navigate(['/categorias']);
  }

  private async initializeComponent() {
    this.route.paramMap.subscribe(async (params) => {
      this.acao = history.state.acao || '';
      const id = params.get('id');

      if (this.acao) {
        this.definirTitulo(this.acao);
      } else {
        this.definirTitulo(undefined);
      }

      if (id) {
        this.isCreateMode = false;
        try {
          const categoria = await this.categoriasService.getCategoriaById(
            Number(id)
          );
          this.loadSubcategorias(Number(id));
          this.preencherFormulario(categoria);
        } catch (error) {
          console.error('Erro ao carregar categoria:', error);
          this.snackBar.open('Erro ao carregar categoria', 'Fechar', {
            duration: 3000,
            horizontalPosition: 'end',
            verticalPosition: 'bottom',
            panelClass: ['error-snackbar'],
          });
        }
      } else {
        this.isCreateMode = true;
      }
    });
  }

  private definirTitulo(acao: string | undefined): void {
    this.isEditMode = true;
    let titleKey = '';

    if (acao === 'Alterar') {
      titleKey = 'TITLE_ALTERAR_CATEGORIA';
    } else if (acao === 'Cadastrar') {
      titleKey = 'TITLE_CADASTRAR_CATEGORIA';
    } else {
      titleKey = 'TITLE_DETALHAR_CATEGORIA';
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

  private preencherFormulario(jsonData: Categoria): void {
    let categoria;
    if (typeof jsonData === 'string') {
      try {
        categoria = JSON.parse(jsonData);
      } catch (e) {
        console.error('Erro ao parsear JSON:', e);
        return;
      }
    } else {
      categoria = jsonData;
    }

    // this.categoriaForm.get('id').setValue(usuario.id);
    // this.categoriaForm.get('categoria_id').setValue(usuario.categoria_id);
    this.categoriaForm.get('nome').setValue(categoria.nome);
    this.categoriaForm.get('descricao').setValue(categoria.descricao);
    this.categoriaForm.get('status').setValue(categoria.status);
    this.statusSelecionado = categoria.status;

    this.sincronizarSubcategorias();
    // this.id = usuario.id;
    // this.nome = usuario.nome;
    // this.dataNascimento = new Date(usuario.dataNascimento);
    // this.emailUsuarioInput = usuario.email;
    // this.perfilSelecionadoId =
    //   usuario.perfis.length > 0 ? usuario.perfis[0].id : null;
    // if (usuario.enderecos && usuario.enderecos.length > 0) {
    //   this.endereco = usuario.enderecos[0];
    //   this.cidadeSelecionadoId = this.endereco.cidade_id.id;
    //   this.estadoSelecionadoId = this.endereco.cidade_id.estado_id.id;
    //   this.paisSelecionadoId = this.endereco.cidade_id.estado_id.pais_id.id;
    //   this.cidadeId = this.endereco.cidade_id.id;
    //   this.estadoId = this.endereco.cidade_id.estado_id.id;
    //   this.paisId = this.endereco.cidade_id.estado_id.pais_id.id;
    //   forkJoin([this.getEstados(), this.getCidades()]).subscribe(() => {
    //     this.initializeComponent();
    //   });
    // }

    // // Processamento da foto/imagem
    // if (usuario.foto && usuario.foto.foto) {
    //   // Verifica se a imagem já tem o prefixo data:image
    //   if (usuario.foto.foto.startsWith('data:image')) {
    //     this.profileImageUrl = usuario.foto.foto;
    //     this.foto.foto =
    //       usuario.foto.foto.split('base64,')[1] || usuario.foto.foto;
    //   } else {
    //     // Se for apenas base64 sem prefixo, adiciona o prefixo
    //     this.profileImageUrl = `data:image/jpeg;base64,${usuario.foto.foto}`;
    //     this.foto.foto = usuario.foto.foto;
    //   }

    //   // Atualiza outros campos da foto
    //   this.foto.id = usuario.foto.id || 0;
    //   this.foto.usuario_id = usuario.id;
    //   this.foto.ativo = usuario.foto.ativo || true;
    //   this.foto.data_criacao =
    //     usuario.foto.data_criacao || new Date().toISOString();
    //   this.foto.data_atualizacao =
    //     usuario.foto.data_atualizacao || new Date().toISOString();

    //   console.log(
    //     'Imagem carregada com sucesso:',
    //     this.profileImageUrl.substring(0, 50) + '...'
    //   );
    // } else {
    //   console.log('Usuário não possui foto cadastrada');
    //   this.profileImageUrl = null;
    // }

    // this.validarCampos();
  }

  private initializeCategoriaForm() {
    this.categoriaForm = this.fb.group({
      nome: ['', [Validators.required, Validators.maxLength(100)]],
      descricao: ['', Validators.maxLength(255)],
      status: ['ATIVO', Validators.required],
      requer_certificacao: [false, Validators.required],
      tipo_certificacao: ['', Validators.maxLength(50)],
      experiencia_minima_meses: [0, [Validators.min(0)]],
      nivel_risco: ['BAIXO', Validators.required],
      seguro_obrigatorio: [false, Validators.required],
      valor_base_hora: [0, [Validators.min(0)]],
      horas_minimas_agendamento: [24, [Validators.required, Validators.min(1)]],
      horas_cancelamento_gratis: [24, [Validators.required, Validators.min(0)]],
      percentual_comissao: [
        0,
        [Validators.required, Validators.min(0), Validators.max(100)],
      ],
      url_imagem: ['', Validators.maxLength(255)],
      palavras_chave: [[]],
      documentos_necessarios: [[]],
      subcategorias: this.fb.array([]),
    });
  }

  loadStatusOptions() {
    this.categoriasService.getStatus().subscribe({
      next: (statusList) => {
        this.statusOptions = statusList;
      },
      error: (error) => {
        console.error('Erro ao carregar status:', error);
      },
    });
  }

  addSubcategoria(event: MatChipInputEvent): void {
    const value = (event.value || '').trim();
    const input = event.input;

    if (value) {
      // Verificar se já existe uma subcategoria com o mesmo nome
      const jaExiste =
        this.subcategorias.some(
          (s) => s.nome.toLowerCase() === value.toLowerCase()
        ) ||
        this.categoriaForm
          .get('subcategorias')
          .value.some((s) => s.nome.toLowerCase() === value.toLowerCase());

      if (jaExiste) {
        this.snackBar.open(
          'Uma subcategoria com este nome já existe',
          'Fechar',
          {
            duration: 3000,
            horizontalPosition: 'end',
            verticalPosition: 'bottom',
          }
        );
        if (input) input.value = '';
        return;
      }

      // Adiciona a nova subcategoria ao FormArray - CORREÇÃO AQUI
      const subcategorias = this.categoriaForm.get('subcategorias')
        .value as Subcategoria[];

      // Crie uma subcategoria completa conforme a interface
      const novaSubcategoria: Subcategoria = {
        id: undefined, // Será definido pelo backend após salvar
        nome: value,
        categoriaId: this.route.snapshot.paramMap.get('id')
          ? Number(this.route.snapshot.paramMap.get('id'))
          : undefined,
      };

      subcategorias.push(novaSubcategoria);
      this.categoriaForm.get('subcategorias').setValue(subcategorias);
      this.categoriaForm.get('subcategorias').updateValueAndValidity();
    }

    if (input) {
      input.value = '';
    }
  }
  removeSubcategoria(subcategoria: Subcategoria): void {
    const subcategorias = this.categoriaForm.get('subcategorias')
      .value as Subcategoria[];
    const index = subcategorias.findIndex((s) => s.nome === subcategoria.nome);

    if (index >= 0) {
      subcategorias.splice(index, 1);
      this.categoriaForm.get('subcategorias').setValue(subcategorias);
      this.categoriaForm.get('subcategorias').updateValueAndValidity();
    }
  }

  removerSubcategoriaExistente(subcategoria: Subcategoria): void {
    // Confirmar com o usuário se deseja realmente excluir
    const confirma = confirm(
      `Deseja realmente excluir a subcategoria "${subcategoria.nome}"?`
    );

    if (confirma) {
      // Se a subcategoria tiver ID, excluir do banco de dados
      if (subcategoria.id) {
        this.subcategoriasService
          .deleteSubcategoria(subcategoria.id)
          .subscribe({
            next: () => {
              // Remover da lista local
              this.subcategorias = this.subcategorias.filter(
                (s) => s.id !== subcategoria.id
              );
              this.snackBar.open(
                'Subcategoria excluída com sucesso',
                'Fechar',
                {
                  duration: 3000,
                  horizontalPosition: 'end',
                  verticalPosition: 'bottom',
                }
              );
            },
            error: (err) => {
              console.error('Erro ao excluir subcategoria:', err);
              this.snackBar.open('Erro ao excluir subcategoria', 'Fechar', {
                duration: 3000,
                horizontalPosition: 'end',
                verticalPosition: 'bottom',
                panelClass: ['error-snackbar'],
              });
            },
          });
      } else {
        // Se não tiver ID, só remove da lista local
        this.subcategorias = this.subcategorias.filter(
          (s) => s !== subcategoria
        );
      }
    }
  }

  private sincronizarSubcategorias(): void {
    // Atualiza o FormArray com as subcategorias carregadas da API
    this.categoriaForm.get('subcategorias').setValue(this.subcategorias);
    this.categoriaForm.get('subcategorias').updateValueAndValidity();
  }
}
