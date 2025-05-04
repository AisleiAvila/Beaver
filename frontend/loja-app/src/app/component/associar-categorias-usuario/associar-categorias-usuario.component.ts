import { Component, inject, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatButtonModule } from '@angular/material/button';
import { UsuarioSubcategoriaService } from 'src/app/service/usuario-subcategoria.service';
import { UsuarioSubcategoria } from 'src/app/model/usuarioSubcategoria.model';

@Component({
  selector: 'app-associar-categorias-usuario',
  standalone: true,
  imports: [CommonModule, FormsModule, MatCheckboxModule, MatButtonModule],
  templateUrl: './associar-categorias-usuario.component.html',
  styleUrls: ['./associar-categorias-usuario.component.scss'],
})
export class AssociarCategoriasUsuarioComponent implements OnInit {
  usuarioSubcategoriaService = inject(UsuarioSubcategoriaService);
  ativo = true;

  categorias: any[] = [];
  selecionadas: UsuarioSubcategoria[] = [];

  constructor(
    public dialogRef: MatDialogRef<AssociarCategoriasUsuarioComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    // Buscar categorias/subcategorias e associações aqui se necessário
  }

  ngOnInit(): void {
    this.usuarioSubcategoriaService
      .getUsuarioSubcategoriaByUsuarioId(this.data.usuarioId, this.ativo)
      .subscribe((res) => {
        this.selecionadas = res;
      });
  }

  onCategoriaChange(categoria: any) {
    if (categoria.selected) {
      if (!this.selecionadas.some((c) => c.id === categoria.id)) {
        this.selecionadas.push(categoria);
      }
    } else {
      this.selecionadas = this.selecionadas.filter(
        (c) => c.id !== categoria.id
      );
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
    // Salvar associações no backend
    this.dialogRef.close(this.selecionadas);
  }

  cancelar() {
    this.dialogRef.close(false);
  }
}
