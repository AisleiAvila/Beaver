import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TranslateModule } from '@ngx-translate/core';

@Component({
  selector: 'app-client-details',
  templateUrl: './client-details.component.html',
  styles: [
    `
      .client-details-modal {
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
        max-width: 600px;
        max-height: 90vh;
        overflow-y: auto;
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
        color: #333;
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

      .detail-row {
        margin-bottom: 16px;
        display: flex;
      }

      .label {
        font-weight: 500;
        min-width: 120px;
        color: #555;
      }

      .value {
        color: #333;
      }

      .modal-footer {
        padding: 16px 20px;
        border-top: 1px solid #eee;
        display: flex;
        justify-content: flex-end;
      }

      .btn-primary {
        padding: 8px 16px;
        border-radius: 4px;
        border: none;
        cursor: pointer;
        font-weight: 500;
        background-color: #4285f4;
        color: white;
      }

      .btn-primary:hover {
        background-color: #3367d6;
      }
    `,
  ],
  standalone: true,
  imports: [CommonModule, TranslateModule],
})
export class ClientDetailsComponent {
  @Input() client: any;
  @Output() close = new EventEmitter<void>();

  onClose(): void {
    this.close.emit();
  }
}
