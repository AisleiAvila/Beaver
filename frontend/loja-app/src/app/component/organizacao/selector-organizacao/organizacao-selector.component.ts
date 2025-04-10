import { Component, Inject, OnDestroy, OnInit } from '@angular/core';
import {
  MAT_DIALOG_DATA,
  MatDialogModule,
  MatDialogRef,
} from '@angular/material/dialog';

import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatListOption, MatSelectionList } from '@angular/material/list';
import { MatRadioChange, MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { TranslateModule } from '@ngx-translate/core';
import { OrganizacaoWrapper } from 'src/app/wrapper/0rganizacao-wrapper.interface';

interface Organizacao {
  organizacoes: {
    organizacoes: {
      id: number;
      nome: string;
      nif: string;
      email: string;
      website: string;
      setorAtividade: string;
      missao: string;
      representante_legal: string | null;
      cargo: string;
      numeroRegistoComercial: string | null;
      dataRegisto: string | null;
    }[];
    totalRecords: number | null;
  }[];
}

@Component({
  selector: 'app-organizacao-selector',
  templateUrl: './organizacao-selector.component.html',
  styleUrls: ['./organizacao-selector.component.scss'],
  standalone: true,
  imports: [
    CommonModule,
    MatButtonModule,
    MatRadioModule,
    FormsModule,
    TranslateModule,
    MatDialogModule,
    MatSelectModule,
    MatSelectionList,
    MatListOption,
  ],
})
export class OrganizacaoSelectorComponent implements OnInit, OnDestroy {
  organizacaoSelecionadaId: number | null = null;

  constructor(
    public dialogRef: MatDialogRef<OrganizacaoSelectorComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { organizacoes: Organizacao[] }
  ) {
    // Impedir fechamento ao clicar fora
    this.dialogRef.disableClose = true;

    this.dialogRef.keydownEvents().subscribe((event) => {
      if (event.key === 'Escape') {
        event.preventDefault();
        event.stopPropagation();
      }
    });

    // Impedir que clicks no backdrop fechem o modal
    this.dialogRef.backdropClick().subscribe(() => {
      console.log('Backdrop click detected and prevented');
    });
  }

  ngOnDestroy(): void {
    console.log('Modal de seleção de organização destruído');
  }

  ngOnInit(): void {
    console.log(
      'Modal de seleção de organização aberto data--> ',
      JSON.stringify(this.data.organizacoes)
    );
  }

  onSelectionChange(event: MatRadioChange): void {
    this.organizacaoSelecionadaId = event.value;
  }

  selecionarOrganizacao(organizacao: OrganizacaoWrapper): void {
    console.log('Organizacao selecionada:', JSON.stringify(organizacao));
    this.dialogRef.close(organizacao);
  }

  cancelar(): void {
    console.log('Seleção cancelada pelo usuário');
    this.dialogRef.close(null);
  }
}
