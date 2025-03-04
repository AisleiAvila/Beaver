import {
  HTTP_INTERCEPTORS,
  HttpClient,
  HttpClientModule,
} from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatChipsModule } from '@angular/material/chips';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatListModule } from '@angular/material/list';
import {
  MatPaginatorIntl,
  MatPaginatorModule,
} from '@angular/material/paginator';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';
import { MatTabsModule } from '@angular/material/tabs';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatTooltipModule } from '@angular/material/tooltip';
import { BrowserModule } from '@angular/platform-browser';
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
import { BackLogComponent } from './backlog/backlog.component';
import { BodyComponent } from './component/body/body.component';
import { CadastroCategoriaComponent } from './component/categorias/cadastro-categoria/cadastro-categoria.component';
import { CategoriasComponent } from './component/categorias/categorias.component';
import { ChatComponent } from './component/chat/chat.component';
import { DashboardComponent } from './component/dashboard/dashboard.component';
import { HeaderComponent } from './component/header/header.component';
import { HomePageComponent } from './component/home-page/home-page.component';
import { LembrarSenhaComponent } from './component/lembrar-senha/lembrar-senha.component';
import { LoginComponent } from './component/login/login.component';
import { MenuComponent } from './component/menu/menu.component';
import { NovaSenhaComponent } from './component/nova-senha/nova-senha.component';
import { OrganizacaoComponent } from './component/organizacao/organizacao.component';
import { PrivacyComponent } from './component/privacy/privacy.component';
import { ProdutosComponent } from './component/produtos/produtos.component';
import { TermsComponent } from './component/terms/terms.component';
import { UsuariosComponent } from './component/usuarios/usuarios.component';
import { AuthInterceptor } from './interceptor/auth.interceptor';
import { CustomSnackbarComponent } from './shared/components/custom-snackbar/custom-snackbar.component';
import { CustomPaginatorIntl } from './shared/service/custom-paginator-intl';
import { GeolocalizacaoService } from './services/geolocalizacao.service';
import { AppComponent } from './app.component';
import { AgendamentoComponent } from './component/agendamento/agendamento.component';
import { GeolocalizacaoComponent } from './component/geolocalizacao/geolocalizacao.component';

/**
 * Factory para criar o loader de traduções
 * @param http Cliente HTTP para carregar arquivos de tradução
 * @returns Loader de traduções configurado
 */
export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}

/**
 * Módulo principal da aplicação.
 * Responsável por configurar e inicializar os recursos globais.
 */
@NgModule({
  // Não declarar componentes standalone
  declarations: [],

  /**
   * Imports de módulos necessários para a aplicação:
   * - AppRoutingModule: Configuração de rotas
   * - BrowserModule: Recursos essenciais do browser
   * - HttpClientModule: Requisições HTTP
   * - Material Modules: Componentes do Angular Material
   * - TranslateModule: Internacionalização
   */
  imports: [
    BrowserModule, // Adicionar BrowserModule para bootstrap
    AppComponent, // AppComponent é standalone e deve estar nos imports
    BackLogComponent,
    BodyComponent,
    ChatComponent,
    DashboardComponent,
    HeaderComponent,
    HomePageComponent,
    LembrarSenhaComponent,
    LoginComponent,
    MenuComponent,
    NovaSenhaComponent,
    OrganizacaoComponent,
    PrivacyComponent,
    ProdutosComponent,
    TermsComponent,
    UsuariosComponent,
    CustomSnackbarComponent,
    CategoriasComponent,
    CadastroCategoriaComponent,
    AppRoutingModule,
    FormsModule,
    BrowserAnimationsModule,
    RouterModule,
    HttpClientModule,
    MatSlideToggleModule,
    MatTableModule,
    MatTabsModule,
    MatToolbarModule,
    MatCardModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatCheckboxModule,
    NgbModalModule,
    NgbModule,
    MatSidenavModule,
    MatListModule,
    MatIconModule,
    ReactiveFormsModule,
    MatNativeDateModule,
    MatDatepickerModule,
    MatTooltipModule,
    MatPaginatorModule,
    MatSortModule,
    MatChipsModule,
    AgendamentoComponent,
    GeolocalizacaoComponent,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient],
      },
      defaultLanguage: 'pt-BR',
    }),
    MatProgressSpinnerModule,
    MatDialogModule,
  ],

  /**
   * Providers globais da aplicação:
   * - AuthInterceptor: Intercepta requisições HTTP para adicionar token
   * - CustomPaginatorIntl: Customiza textos do paginador
   */
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    { provide: MatPaginatorIntl, useClass: CustomPaginatorIntl },
    GeolocalizacaoService,
  ],

  // Remover bootstrap para componentes standalone
})
export class AppModule {
  /**
   * Construtor que configura o idioma padrão da aplicação
   * @param translate Serviço de tradução
   */
  constructor(private translate: TranslateService) {
    this.translate.setDefaultLang('pt-BR');
    this.translate.use('pt-BR');
  }
}
