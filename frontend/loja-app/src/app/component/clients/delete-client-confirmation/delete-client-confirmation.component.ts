import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TranslateModule } from '@ngx-translate/core';
import { ClientService } from '../../../services/client.service';

@Component({
  selector: 'app-delete-client-confirmation',
  templateUrl: './delete-client-confirmation.component.html',
  styles: [
    `
      .delete-confirmation-modal {
        position: fixed;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        background-color: rgba(0, 0, 0, 0.5);
        display: flex;
        align-items: center;
        justify-content: center;
        z-index: 1000;
      }

      .modal-content {
        background-color: white;
        border-radius: 8px;
        width: 90%;
        max-width: 450px;
        box-shadow: 0 4px 20px rgba(0, 0, 0, 0.15);
      }

      .modal-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 16px 20px;
        border-bottom: 1px solid #eee;
      }

      .modal-header h2 {
        margin: 0;
        font-size: 20px;
        color: #ea4335;
      }

      .close-btn {
        background: none;
        border: none;
        font-size: 24px;
        cursor: pointer;
        color: #666;
      }

      .close-btn:hover {
        color: #333;
      }

      .modal-body {
        padding: 20px;
      }

      .confirmation-message {
        font-size: 16px;
        margin-bottom: 16px;
        color: #333;
      }

      .client-info {
        font-weight: 500;
        color: #555;
        padding: 12px;
        background-color: #f5f5f5;
        border-radius: 4px;
        border-left: 4px solid #ea4335;
      }

      .modal-footer {
        padding: 16px 20px;
        border-top: 1px solid #eee;
        display: flex;
        justify-content: flex-end;
        gap: 10px;
      }

      .btn-secondary {
        padding: 10px 16px;
        border-radius: 4px;
        border: 1px solid #ddd;
        background-color: white;
        color: #555;
        cursor: pointer;
        font-weight: 500;
      }

      .btn-secondary:hover {
        background-color: #f5f5f5;
      }

      .btn-danger {
        padding: 10px 16px;
        border-radius: 4px;
        border: none;
        background-color: #ea4335;
        color: white;
        cursor: pointer;
        font-weight: 500;
      }

      .btn-danger:hover {
        background-color: #d73125;
      }

      .btn-danger:disabled {
        background-color: #f5b2ac;
        cursor: not-allowed;
      }
    `,
  ],
  standalone: true,
  imports: [CommonModule, TranslateModule],
})
export class DeleteClientConfirmationComponent {
  @Input() client: any;
  @Output() confirmDelete = new EventEmitter<void>();
  @Output() cancelDelete = new EventEmitter<void>();

  isDeleting = false;

  constructor(private clientService: ClientService) {}

  onConfirm(): void {
    if (this.client && this.client.id) {
      this.isDeleting = true;
      this.clientService.deleteClient(this.client.id).subscribe(
        () => {
          this.isDeleting = false;
          this.confirmDelete.emit();
        },
        (error) => {
          console.error('Error deleting client', error);
          this.isDeleting = false;
        }
      );
    }
  }

  onCancel(): void {
    this.cancelDelete.emit();
  }
}
