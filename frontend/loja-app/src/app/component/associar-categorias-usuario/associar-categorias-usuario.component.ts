import { Component, inject, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatButtonModule } from '@angular/material/button';
import { UsuarioSubcategoriaService } from 'src/app/service/usuario-subcategoria.service';
import { UsuarioSubcategoria } from 'src/app/model/usuarioSubcategoria.model';
import { CategoriasService } from 'src/app/service/categorias.service';
import { CategoriaRequest } from 'src/app/interfaces/categoria-request.interface';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Usuario } from '../../model/usuario.model';

@Component({
  selector: 'app-associar-categorias-usuario',
  standalone: true,
  imports: [CommonModule, FormsModule, MatCheckboxModule, MatButtonModule],
  templateUrl: './associar-categorias-usuario.component.html',
  styleUrls: ['./associar-categorias-usuario.component.scss'],
})
export class AssociarCategoriasUsuarioComponent implements OnInit {
  usuarioSubcategoriaService = inject(UsuarioSubcategoriaService);
  categoriasService = inject(CategoriasService);
  snackBar = inject(MatSnackBar);
  ativo = true;

  categorias: any[] = [];
  selecionadas: UsuarioSubcategoria[] = [];
  associadas: UsuarioSubcategoria[] = [];

  constructor(
    public dialogRef: MatDialogRef<AssociarCategoriasUsuarioComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    // Buscar categorias/subcategorias e associações aqui se necessário
  }

  ngOnInit(): void {
    this.loadCategorias();
    this.usuarioSubcategoriaService
      .getUsuarioSubcategoriaByUsuarioId(this.data.usuario.id, this.ativo)
      .subscribe((res) => {
        // Fazendo cópias profundas para evitar referência compartilhada
        this.selecionadas = JSON.parse(JSON.stringify(res));
        this.associadas = JSON.parse(JSON.stringify(res));
      });
  }

  loadCategorias(): void {
    const params: CategoriaRequest = {
      limit: 0,
      offset: 0,
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
      },
      error: (error) => {
        if (error.status === 404) {
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

  onCategoriaChange(categoria: any) {
    if (categoria.selected) {
      // Seleciona a categoria, se ainda não estiver selecionada
      if (!this.selecionadas.some((c) => c.id === categoria.id)) {
        this.selecionadas.push(categoria);
      }
      // Seleciona todas as subcategorias da categoria
      if (Array.isArray(categoria.subcategorias)) {
        categoria.subcategorias.forEach((sub: any) => {
          if (!this.selecionadas.some((s) => s.id === sub.id)) {
            this.selecionadas.push(sub);
          }
          sub.selected = true; // Atualiza o estado visual, se necessário
        });
      }
    } else {
      // Remove a categoria
      this.selecionadas = this.selecionadas.filter(
        (c) => c.id !== categoria.id
      );
      // Remove todas as subcategorias da categoria
      if (Array.isArray(categoria.subcategorias)) {
        categoria.subcategorias.forEach((sub: any) => {
          this.selecionadas = this.selecionadas.filter((s) => s.id !== sub.id);
          sub.selected = false; // Atualiza o estado visual, se necessário
        });
      }
    }
  }

  onSubcategoriaChange(sub: any) {
    if (sub.selected) {
      if (!this.selecionadas.some((s) => s.id === sub.id)) {
        this.selecionadas.push(sub);
      }
    } else {
      this.selecionadas = this.selecionadas.filter((s) => s.id !== sub.id);
    }
  }

  salvar() {
    // filtrar apenas as subcategorias que estão associadas
    const subcategoriasAssociadas: number[] = this.associadas
      .filter((item) => !item.subcategoria && item.id !== undefined)
      .map((item) => item.id);

    // filtrar apenas os itens do array selecionadas que são subcategorias (ou seja, não possuem a propriedade 'subcategoria')
    const subcategoriasSelecionadas: number[] = this.selecionadas
      .filter((item) => !item.subcategoria && item.id !== undefined)
      .map((item) => item.id);

    // Salvar associações no backend
    this.usuarioSubcategoriaService.salvarAssociacoes(
      subcategoriasSelecionadas,
      subcategoriasAssociadas,
      this.data.usuario.id
    );
  }

  cancelar() {
    this.dialogRef.close(false);
  }
}
