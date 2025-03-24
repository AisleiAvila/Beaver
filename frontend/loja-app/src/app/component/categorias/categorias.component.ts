import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatNativeDateModule, MatOptionModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSelectModule } from '@angular/material/select';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';
import { MatTooltipModule } from '@angular/material/tooltip';
import { Router } from '@angular/router';
import { NgbModalModule } from '@ng-bootstrap/ng-bootstrap';
import { TranslateModule } from '@ngx-translate/core';
import { StatusServico } from 'src/app/enum/status-servico.enum';
import { CategoriaRequest } from 'src/app/interfaces/categoria-request.interface';
import { Categoria } from 'src/app/model/categoria.model';
import { CategoriasService } from 'src/app/service/categorias.service';

@Component({
  selector: 'app-categorias',
  templateUrl: './categorias.component.html',
  styleUrls: ['./categorias.component.scss'],
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatDatepickerModule,
    MatSelectModule,
    MatButtonModule,
    MatIconModule,
    MatTooltipModule,
    MatNativeDateModule,
    MatCheckboxModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatSnackBarModule,
    MatOptionModule,
    NgbModalModule,
    TranslateModule,
  ],
})
export class CategoriasComponent implements OnInit {
  categoriasService = inject(CategoriasService);
  router = inject(Router);
  snackBar = inject(MatSnackBar);

  categorias: Categoria[] = [];
  filtroNome = '';
  displayedColumns: string[] = ['nome', 'descricao', 'status', 'acoes'];

  statusOptions: StatusServico[] = [];
  statusSelecionados: StatusServico[] = [];
  allStatusSelected = false;

  ngOnInit(): void {
    this.loadCategorias();
    this.loadStatusOptions();
  }

  loadCategorias(): void {
    const params: CategoriaRequest = {
      // Você pode adicionar valores padrão, como limit e offset se necessário
      limit: 50, // exemplo: limite de 50 categorias
      offset: 0,
    };
    this.categoriasService
      .getCategorias(params)
      .subscribe((data: Categoria[]) => {
        this.categorias = data;
      });
  }

  toggleAllStatus(): void {
    if (this.allStatusSelected) {
      this.statusSelecionados = [];
    } else {
      this.statusSelecionados = [...this.statusOptions];
    }
    this.allStatusSelected = !this.allStatusSelected;
  }

  pesquisarCategorias(): void {
    const params: CategoriaRequest = {
      nome: this.filtroNome, // Passa o filtro para o backend processar
      status:
        this.statusSelecionados.length > 0
          ? this.statusSelecionados
          : undefined,
      limit: 50,
      offset: 0,
    };
    this.categoriasService.getCategorias(params).subscribe({
      next: (data: Categoria[]) => {
        this.categorias = data;
      },
      error: (error) => {
        // Verificar se é erro 404
        if (error.status === 404) {
          // Tratar como lista vazia sem mostrar erro
          this.categorias = [];
          this.snackBar.open(
            'Nenhuma categoria encontrada com os filtros aplicados',
            'Fechar',
            {
              duration: 5000,
              horizontalPosition: 'end',
              verticalPosition: 'bottom',
            }
          );
        } else {
          // Para outros erros, mostrar mensagem de erro
          this.categorias = [];
          this.snackBar.open('Erro ao pesquisar categorias', 'Fechar', {
            duration: 5000,
            horizontalPosition: 'end',
            verticalPosition: 'bottom',
            panelClass: ['error-snackbar'],
          });
          console.error('Erro ao pesquisar categorias:', error);
        }
      },
    });
  }

  limparFiltros(): void {
    this.filtroNome = '';
    this.statusSelecionados = [];
    this.allStatusSelected = false;
    this.loadCategorias();
  }

  cadastrarCategoria(): void {
    this.router.navigate(['/cadastro-categoria']);
  }

  editarCategoria(categoriaId: number): void {
    this.router.navigate(['/cadastro-categoria', categoriaId]);
  }

  excluirCategoria(categoriaId: number): void {
    this.categoriasService.deleteCategoria(categoriaId).subscribe(() => {
      this.loadCategorias();
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

  cadastroCategoria(id: number, acao: string): void {
    this.router.navigate(['/cadastro-categoria', id], {
      state: { id, acao: acao },
    });
  }
}
