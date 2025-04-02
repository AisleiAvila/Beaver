import { inject, Injectable } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { OrganizacaoSelectorComponent } from '../component/organizacao/selector-organizacao/organizacao-selector.component';
import { MessageModalComponent } from '../shared/components/modal/message-modal/message-modal.component';
import { OrganizacaoWrapper } from '../wrapper/0rganizacao-wrapper.interface';

@Injectable({
  providedIn: 'root',
})
export class ModalCommunicationService {
  dialog = inject(MatDialog);

  abrirModal(mensagem: string, titulo: string): void {
    this.dialog.open(MessageModalComponent, {
      width: '400px', // Aumentado de 300px para 400px
      maxWidth: '90vw', // Limita a largura máxima em telas menores
      maxHeight: '90vh',
      data: { mensagem, titulo },
      enterAnimationDuration: '200ms',
      exitAnimationDuration: '200ms',
      disableClose: false,
      panelClass: 'custom-dialog-container',
      autoFocus: false,
    });
  }

  abrirModalSelecaoOrganizacao(
    organizacoes: OrganizacaoWrapper[]
  ): MatDialogRef<OrganizacaoSelectorComponent, OrganizacaoWrapper> {
    console.log('Abrindo modal de seleção de organização no serviço.');

    // Configurar com opções que garantem que o modal não seja fechado automaticamente
    const dialogRef = this.dialog.open(OrganizacaoSelectorComponent, {
      width: '500px',
      maxWidth: '90vw',
      maxHeight: '90vh',
      data: { organizacoes },
      disableClose: true, // Impede fechamento ao clicar fora
      closeOnNavigation: false, // Impede que a navegação feche o modal
      hasBackdrop: true, // Mantém o backdrop
      backdropClass: 'custom-backdrop', // Customiza o backdrop para ser mais óbvio
      autoFocus: true,
      panelClass: 'modal-persistente', // Classe para estilos adicionais
    });

    dialogRef.afterOpened().subscribe(() => {
      console.log('Modal foi aberto.');
    });

    dialogRef.beforeClosed().subscribe(() => {
      console.log('Modal está prestes a ser fechado.');
    });

    dialogRef.afterClosed().subscribe({
      next: (result) => {
        console.log('Modal fechado com resultado:', result);
      },
      error: (error) => {
        console.error('Erro ao fechar o modal:', error);
      },
      complete: () => {
        console.log('Modal foi completamente fechado.');
      },
    });

    return dialogRef;
  }
}
