import { CommonModule } from '@angular/common';
import { Component, inject, OnDestroy, OnInit } from '@angular/core';
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
import { Subscription } from 'rxjs';
import { StatusServico } from 'src/app/enum/status-servico.enum';
import { CategoriaRequest } from 'src/app/interfaces/categoria-request.interface';
import { Categoria } from 'src/app/model/categoria.model';
import { CategoriasService } from 'src/app/service/categorias.service';
import { LayoutService } from 'src/app/services/layout.service';
import { DeviceService } from 'src/app/shared/service/device.service';

// Interface local para garantir subcategorias e expanded
interface CategoriaComSubcategorias extends Categoria {
  subcategorias: any[]; // Substitua 'any' pelo tipo correto se souber
  expanded?: boolean;
}

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
export class CategoriasComponent implements OnInit, OnDestroy {
  categoriasService = inject(CategoriasService);
  router = inject(Router);
  snackBar = inject(MatSnackBar);
  deviceService = inject(DeviceService);
  private layoutService = inject(LayoutService);

  categorias: CategoriaComSubcategorias[] = [];
  filtroNome = '';
  displayedColumns: string[] = [
    'expand',
    'nome',
    'descricao',
    'status',
    'acoes',
  ];

  statusOptions: StatusServico[] = [];
  statusSelecionados: StatusServico[] = [];
  allStatusSelected = false;

  isMobile = false;
  private subscription = new Subscription();
  private layoutSubscription: Subscription;

  isMenuCollapsed = false;

  totalCategorias = 0;
  pageSize = 10;
  pageIndex = 0;

  dataSourceExpandido: any[] = [];

  ngOnInit(): void {
    this.loadCategorias();
    this.loadStatusOptions();

    this.subscription.add(
      this.deviceService.isMobile$.subscribe((isMobile) => {
        this.isMobile = isMobile;
        console.log('CategoriasComponent - dispositivo móvel:', isMobile);
      })
    );

    // Assinar ao serviço de layout para detectar mudanças no menu
    this.layoutSubscription = this.layoutService.menuState$.subscribe(
      (isCollapsed) => {
        this.isMenuCollapsed = isCollapsed;
        // Forçar detecção de alterações para atualizar o layout
        setTimeout(() => {
          // Add meaningful logic here if needed, or remove this setTimeout entirely
        }, 0);
      }
    );

    this.atualizarDataSourceExpandido();
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }

  loadCategorias(): void {
    const params: CategoriaRequest = {
      nome: this.filtroNome,
      status:
        this.statusSelecionados.length > 0
          ? this.statusSelecionados
          : undefined,
      limit: this.pageSize,
      offset: this.pageIndex * this.pageSize,
      withSubcategorias: true, // Garante que subcategorias venham do backend
    };

    this.categoriasService.getCategorias(params).subscribe({
      next: (data: any[]) => {
        this.categorias = data.map((cat: any) => ({
          ...cat,
          subcategorias: Array.isArray(cat.subcategorias)
            ? cat.subcategorias
            : [],
          expanded: false,
        }));
        // Ajuste totalCategorias conforme resposta do backend, se disponível
        this.totalCategorias = Array.isArray(data) ? data.length : 0;
        this.atualizarDataSourceExpandido();
      },
      error: (error) => {
        if (error.status === 404) {
          this.categorias = [];
          this.totalCategorias = 0;
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
          this.categorias = [];
          this.totalCategorias = 0;
          this.snackBar.open('Erro ao pesquisar categorias', 'Fechar', {
            duration: 5000,
            horizontalPosition: 'end',
            verticalPosition: 'bottom',
            panelClass: ['error-snackbar'],
          });
          console.error('Erro ao pesquisar categorias:', error);
        }
        this.atualizarDataSourceExpandido();
      },
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
      limit: this.pageSize,
      offset: this.pageIndex * this.pageSize,
      withSubcategorias: true,
    };
    alert('Entrei: ');
    this.categoriasService.getCategorias(params).subscribe({
      next: (data: Categoria[]) => {
        this.categorias = data.map((cat) => ({
          ...cat,
          subcategorias: cat.subcategorias || [],
          expanded: false,
        }));
        this.totalCategorias = data.length; // Exemplo: ajuste conforme necessário
        this.atualizarDataSourceExpandido();
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
        this.atualizarDataSourceExpandido();
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

  onPageChange(event: any): void {
    this.pageIndex = event.pageIndex;
    this.pageSize = event.pageSize;
    this.loadCategorias();
  }

  toggleExpand(element: CategoriaComSubcategorias): void {
    // Sempre altere o expanded na lista original de categorias
    const categoria = this.categorias.find((cat) => cat.id === element.id);
    if (categoria) {
      categoria.expanded = !categoria.expanded;
      this.atualizarDataSourceExpandido();
    }
  }

  atualizarDataSourceExpandido(): void {
    this.dataSourceExpandido = [];
    for (const cat of this.categorias) {
      this.dataSourceExpandido.push({ tipo: 'categoria', ...cat });
      if (cat.expanded && cat.subcategorias && cat.subcategorias.length) {
        for (const sub of cat.subcategorias) {
          this.dataSourceExpandido.push({ tipo: 'subcategoria', ...sub });
        }
      }
    }
  }

  isCategoria = (index: number, row: any) => row.tipo === 'categoria';
  isSubcategoria = (index: number, row: any) => row.tipo === 'subcategoria';
}
