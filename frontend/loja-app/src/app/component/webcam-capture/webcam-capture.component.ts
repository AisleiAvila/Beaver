import {
  Component,
  ElementRef,
  OnDestroy,
  OnInit,
  ViewChild,
} from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { catchError, finalize } from 'rxjs/operators';
import { of, Subscription } from 'rxjs';

@Component({
  selector: 'app-webcam-capture',
  standalone: true,
  imports: [CommonModule, HttpClientModule],
  templateUrl: './webcam-capture.component.html',
  styleUrls: ['./webcam-capture.component.scss'],
})
export class WebcamCaptureComponent implements OnInit, OnDestroy {
  @ViewChild('videoElement') videoElement!: ElementRef<HTMLVideoElement>;

  private stream: MediaStream | null = null;
  private subscriptions: Subscription = new Subscription();

  capturedImage: string | null = null;
  isUploading = false;
  uploadStatus = '';

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.startCamera();
  }

  ngOnDestroy(): void {
    this.stopCamera();
    this.subscriptions.unsubscribe();
  }

  private async startCamera(): Promise<void> {
    try {
      const constraints: MediaStreamConstraints = {
        video: {
          width: { ideal: 1280 },
          height: { ideal: 720 },
          facingMode: 'user',
        },
      };

      this.stream = await navigator.mediaDevices.getUserMedia(constraints);

      if (this.videoElement && this.videoElement.nativeElement) {
        this.videoElement.nativeElement.srcObject = this.stream;
      }
    } catch (error) {
      console.error('Erro ao acessar a câmera:', error);
      this.uploadStatus =
        'Erro ao acessar a câmera. Por favor, verifique as permissões.';
    }
  }

  private stopCamera(): void {
    if (this.stream) {
      this.stream.getTracks().forEach((track) => track.stop());
      this.stream = null;
    }
  }

  captureImage(): void {
    if (!this.videoElement || !this.videoElement.nativeElement) {
      return;
    }

    const video = this.videoElement.nativeElement;
    const canvas = document.createElement('canvas');
    canvas.width = video.videoWidth;
    canvas.height = video.videoHeight;

    const context = canvas.getContext('2d');
    if (context) {
      context.drawImage(video, 0, 0, canvas.width, canvas.height);
      this.capturedImage = canvas.toDataURL('image/jpeg');

      // Pausa o stream de vídeo após a captura
      this.stopCamera();
    }
  }

  retake(): void {
    this.capturedImage = null;
    this.uploadStatus = '';
    this.startCamera();
  }

  sendImage(): void {
    if (!this.capturedImage) {
      return;
    }

    this.isUploading = true;
    this.uploadStatus = 'Enviando imagem...';

    // Converte a string base64 para blob para envio
    const base64Data = this.capturedImage.split(',')[1];
    const blob = this.base64ToBlob(base64Data, 'image/jpeg');

    const formData = new FormData();
    formData.append('image', blob, 'webcam-capture.jpg');

    // Substitua a URL abaixo pelo seu endpoint de API
    const uploadSubscription = this.http
      .post('/api/upload-image', formData)
      .pipe(
        catchError((error) => {
          console.error('Erro ao enviar imagem:', error);
          this.uploadStatus = 'Erro ao enviar imagem. Tente novamente.';
          return of(null);
        }),
        finalize(() => {
          this.isUploading = false;
        })
      )
      .subscribe((response) => {
        if (response) {
          this.uploadStatus = 'Imagem enviada com sucesso!';
          setTimeout(() => {
            this.retake();
          }, 2000);
        }
      });

    this.subscriptions.add(uploadSubscription);
  }

  private base64ToBlob(base64: string, mimeType: string): Blob {
    const byteCharacters = atob(base64);
    const byteArrays = [];

    for (let offset = 0; offset < byteCharacters.length; offset += 512) {
      const slice = byteCharacters.slice(offset, offset + 512);

      const byteNumbers = new Array(slice.length);
      for (let i = 0; i < slice.length; i++) {
        byteNumbers[i] = slice.charCodeAt(i);
      }

      const byteArray = new Uint8Array(byteNumbers);
      byteArrays.push(byteArray);
    }

    return new Blob(byteArrays, { type: mimeType });
  }
}
