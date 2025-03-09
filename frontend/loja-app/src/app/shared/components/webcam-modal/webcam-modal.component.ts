import { Component, Inject, OnDestroy, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  MatDialogRef,
  MAT_DIALOG_DATA,
  MatDialogModule,
} from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { WebcamImage, WebcamInitError, WebcamModule } from 'ngx-webcam';
import { Observable, Subject } from 'rxjs';

@Component({
  selector: 'app-webcam-modal',
  standalone: true,
  imports: [CommonModule, MatDialogModule, MatButtonModule, WebcamModule],
  template: `
    <div class="webcam-container">
      <h2>Capturar Foto</h2>

      <div *ngIf="!capturedImage">
        <webcam
          [height]="400"
          [width]="400"
          [trigger]="triggerObservable"
          (imageCapture)="handleImage($event)"
          (initError)="handleInitError($event)"
        ></webcam>

        <div class="action-buttons">
          <button mat-raised-button color="primary" (click)="captureImage()">
            Tirar Foto
          </button>
          <button mat-button (click)="close()">Cancelar</button>
        </div>
      </div>

      <div *ngIf="capturedImage">
        <div class="snapshot">
          <img [src]="capturedImage.imageAsDataUrl" alt="Foto capturada" />
        </div>

        <div class="action-buttons">
          <button mat-raised-button color="primary" (click)="acceptImage()">
            Confirmar
          </button>
          <button mat-button (click)="retakeImage()">Nova Foto</button>
        </div>
      </div>
    </div>
  `,
  styles: [
    `
      .webcam-container {
        display: flex;
        flex-direction: column;
        align-items: center;
        padding: 20px;
      }

      .snapshot {
        margin: 20px 0;
        text-align: center;
      }

      .snapshot img {
        max-width: 400px;
        max-height: 400px;
      }

      .action-buttons {
        display: flex;
        justify-content: space-between;
        width: 100%;
        margin-top: 20px;
      }

      button {
        min-width: 120px;
      }
    `,
  ],
})
export class WebcamModalComponent implements OnInit, OnDestroy {
  private trigger: Subject<void> = new Subject<void>();
  capturedImage: WebcamImage | null = null;

  constructor(
    public dialogRef: MatDialogRef<WebcamModalComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {}

  ngOnInit(): void {
    // Opcionalmente, você pode solicitar permissão de câmera aqui
    navigator.mediaDevices
      .getUserMedia({ video: true })
      .catch((error) => console.error('Erro ao acessar webcam:', error));
  }

  ngOnDestroy(): void {
    this.trigger.complete();
  }

  get triggerObservable(): Observable<void> {
    return this.trigger.asObservable();
  }

  captureImage(): void {
    this.trigger.next();
  }

  handleImage(webcamImage: WebcamImage): void {
    console.log('Imagem capturada da webcam');
    this.capturedImage = webcamImage;
  }

  handleInitError(error: WebcamInitError): void {
    console.error('Erro ao inicializar webcam:', error);
  }

  retakeImage(): void {
    this.capturedImage = null;
  }

  acceptImage(): void {
    if (this.capturedImage) {
      this.dialogRef.close(this.capturedImage.imageAsDataUrl);
    }
  }

  close(): void {
    this.dialogRef.close();
  }
}
