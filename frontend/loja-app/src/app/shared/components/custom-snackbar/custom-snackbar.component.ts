import { Component, inject } from '@angular/core';
import {
  MAT_SNACK_BAR_DATA,
  MatSnackBarRef,
} from '@angular/material/snack-bar';
import { SnackbarData } from 'src/app/model/snackbarData.model';

@Component({
  selector: 'app-custom-snackbar',
  templateUrl: './custom-snackbar.component.html',
  styleUrls: ['./custom-snackbar.component.scss'],
  standalone: true,
})
export class CustomSnackbarComponent {
  snackBarRef = inject<MatSnackBarRef<CustomSnackbarComponent>>(MatSnackBarRef);
  data = inject<SnackbarData>(MAT_SNACK_BAR_DATA);
}
