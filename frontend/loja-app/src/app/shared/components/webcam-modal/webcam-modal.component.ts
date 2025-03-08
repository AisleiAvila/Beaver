import { CommonModule } from '@angular/common';
import {
  Component,
  ElementRef,
  Inject,
  OnDestroy,
  OnInit,
  ViewChild,
} from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import {
  MatDialogModule,
  MatDialogRef,
  MAT_DIALOG_DATA,
} from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { TranslateModule } from '@ngx-translate/core';

@Component({
  selector: 'app-webcam-modal',
  templateUrl: './webcam-modal.component.html',
  styleUrls: ['./webcam-modal.component.scss'],
  standalone: true,
  imports: [
    CommonModule,
    MatDialogModule,
    MatButtonModule,
    MatIconModule,
    TranslateModule,
  ],
})
export class WebcamModalComponent implements OnInit, OnDestroy {
  @ViewChild('videoElement') videoElement: ElementRef;
  @ViewChild('canvas') canvas: ElementRef;

  videoStream: MediaStream | null = null;
  showWebcam = true;
  capturedImage: string | null = null;
  hasMultipleCameras = false;
  currentDeviceId: string | null = null;
  mediaDevices: MediaDeviceInfo[] = [];
  errorMessage: string | null = null;

  constructor(
    public dialogRef: MatDialogRef<WebcamModalComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {}

  async ngOnInit(): Promise<void> {
    try {
      // Check if multiple cameras are available
      const devices = await navigator.mediaDevices.enumerateDevices();
      this.mediaDevices = devices.filter(
        (device) => device.kind === 'videoinput'
      );
      this.hasMultipleCameras = this.mediaDevices.length > 1;

      // Start camera
      await this.startCamera();
    } catch (error) {
      console.error('Error initializing webcam:', error);
      this.errorMessage =
        'Failed to initialize camera. Please check permissions and try again.';
    }
  }

  ngOnDestroy(): void {
    this.stopCamera();
  }

  async startCamera(deviceId?: string): Promise<void> {
    try {
      // Stop any existing stream
      this.stopCamera();

      // Get media stream with optional device ID
      const constraints: MediaStreamConstraints = {
        video: deviceId ? { deviceId: { exact: deviceId } } : true,
        audio: false,
      };

      this.videoStream = await navigator.mediaDevices.getUserMedia(constraints);

      // Wait for the component to be rendered
      setTimeout(() => {
        if (this.videoElement && this.videoElement.nativeElement) {
          this.videoElement.nativeElement.srcObject = this.videoStream;
          this.currentDeviceId = deviceId || null;
          this.errorMessage = null;
        }
      }, 100);
    } catch (error) {
      console.error('Error accessing webcam:', error);
      this.errorMessage = 'Failed to access camera. Please check permissions.';
    }
  }

  stopCamera(): void {
    if (this.videoStream) {
      this.videoStream.getTracks().forEach((track) => track.stop());
      this.videoStream = null;
    }
  }

  captureImage(): void {
    if (!this.videoElement || !this.canvas) return;

    const video = this.videoElement.nativeElement;
    const canvas = this.canvas.nativeElement;
    const context = canvas.getContext('2d');

    // Set canvas dimensions to match video
    canvas.width = video.videoWidth;
    canvas.height = video.videoHeight;

    // Draw video frame to canvas
    context.drawImage(video, 0, 0, canvas.width, canvas.height);

    // Get image data
    this.capturedImage = canvas.toDataURL('image/png');
    this.showWebcam = false;
  }

  retakePhoto(): void {
    this.capturedImage = null;
    this.showWebcam = true;
  }

  useImage(): void {
    this.dialogRef.close(this.capturedImage);
  }

  cancel(): void {
    this.dialogRef.close();
  }

  async switchCamera(): Promise<void> {
    // Find the next camera in the list
    if (this.mediaDevices.length <= 1) return;

    const currentIndex = this.mediaDevices.findIndex(
      (device) => device.deviceId === this.currentDeviceId
    );

    const nextIndex = (currentIndex + 1) % this.mediaDevices.length;
    await this.startCamera(this.mediaDevices[nextIndex].deviceId);
  }
}
