import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { TranslateModule } from '@ngx-translate/core';

import { ClientsComponent } from './clients.component';
import { ClientDetailsComponent } from './client-details/client-details.component';
import { AddClientComponent } from './add-client/add-client.component';
import { EditClientComponent } from './edit-client/edit-client.component';
import { DeleteClientConfirmationComponent } from './delete-client-confirmation/delete-client-confirmation.component';

@NgModule({
  declarations: [
    ClientsComponent,
    ClientDetailsComponent,
    AddClientComponent,
    EditClientComponent,
    DeleteClientConfirmationComponent,
  ],
  imports: [CommonModule, ReactiveFormsModule, RouterModule, TranslateModule],
  exports: [ClientsComponent],
})
export class ClientsModule {}
