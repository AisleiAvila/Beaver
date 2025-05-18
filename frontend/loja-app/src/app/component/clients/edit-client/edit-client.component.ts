import { Component, Input, Output, EventEmitter, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  ReactiveFormsModule,
  FormBuilder,
  FormGroup,
  Validators,
} from '@angular/forms';
import { TranslateModule } from '@ngx-translate/core';
import { ClientService } from '../../../services/client.service';

@Component({
  selector: 'app-edit-client',
  templateUrl: './edit-client.component.html',
  styles: [
    `
      .edit-client-modal {
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

      .form-group {
        margin-bottom: 20px;
      }

      .form-group label {
        display: block;
        margin-bottom: 6px;
        font-weight: 500;
        color: #555;
      }

      .form-group input,
      .form-group textarea {
        width: 100%;
        padding: 10px;
        border: 1px solid #ddd;
        border-radius: 4px;
        font-size: 16px;
      }

      .form-group textarea {
        min-height: 80px;
        resize: vertical;
      }

      .error-message {
        color: #ea4335;
        font-size: 14px;
        margin-top: 5px;
      }

      .form-actions {
        display: flex;
        justify-content: flex-end;
        gap: 10px;
        margin-top: 20px;
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

      .btn-primary {
        padding: 10px 16px;
        border-radius: 4px;
        border: none;
        background-color: #4285f4;
        color: white;
        cursor: pointer;
        font-weight: 500;
      }

      .btn-primary:hover {
        background-color: #3367d6;
      }

      .btn-primary:disabled {
        background-color: #a1c2fa;
        cursor: not-allowed;
      }
    `,
  ],
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, TranslateModule],
})
export class EditClientComponent implements OnInit {
  @Input() client: any;
  @Output() clientUpdated = new EventEmitter<void>();
  @Output() cancel = new EventEmitter<void>();

  clientForm: FormGroup;
  isSubmitting = false;

  constructor(private fb: FormBuilder, private clientService: ClientService) {
    this.clientForm = this.fb.group({
      name: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      phone: ['', [Validators.required]],
      address: [''],
    });
  }

  ngOnInit(): void {
    if (this.client) {
      this.clientForm.patchValue({
        name: this.client.name,
        email: this.client.email,
        phone: this.client.phone,
        address: this.client.address,
      });
    }
  }

  onSubmit(): void {
    if (this.clientForm.valid) {
      this.isSubmitting = true;
      this.clientService
        .updateClient(this.client.id, this.clientForm.value)
        .subscribe(
          () => {
            this.isSubmitting = false;
            this.clientUpdated.emit();
          },
          (error) => {
            console.error('Error updating client', error);
            this.isSubmitting = false;
          }
        );
    }
  }

  onCancel(): void {
    this.cancel.emit();
  }
}
