import {
  HTTP_INTERCEPTORS,
  HttpClient,
  HttpClientModule,
} from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
// Material imports
import { MatButtonModule } from '@angular/material/button';
// ... outros imports do Material ...

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';
import { NgbModalModule, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {
  TranslateLoader,
  TranslateModule,
  TranslateService,
} from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { AppRoutingModule } from './app-routing.module';

// Componentes
import { BackLogComponent } from './backlog/backlog.component';
// ... outros imports de componentes ...

// Interceptors e serviços
import { MatCardModule } from '@angular/material/card';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatPaginatorIntl } from '@angular/material/paginator';
import { ChatComponent } from './component/chat/chat.component';
import { AuthInterceptor } from './interceptor/auth.interceptor';
import { CustomPaginatorIntl } from './shared/service/custom-paginator-intl';

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}

@NgModule({
  // Para componentes não-standalone
  declarations: [
    // Se algum dos seus componentes não é standalone, coloque aqui
    // ... outros componentes standalone ...
  ],

  imports: [
    // Módulos Angular
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule,
    AppRoutingModule, // apenas uma vez!

    // Módulos do Angular Material (sem duplicatas)
    MatButtonModule,
    MatCardModule,
    MatCheckboxModule,
    // ... outros módulos do Material ...

    // Bootstrap
    NgbModule,
    NgbModalModule,

    // Traduções
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient],
      },
      defaultLanguage: 'pt-BR',
    }),

    // Standalone Components
    ChatComponent,
    BackLogComponent,
  ],

  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    { provide: MatPaginatorIntl, useClass: CustomPaginatorIntl },
  ],
})
export class AppModule {
  constructor(private translate: TranslateService) {
    this.translate.setDefaultLang('pt-BR');
    this.translate.use('pt-BR');
  }
}
