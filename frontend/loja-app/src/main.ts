import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/app.component';
import { importProvidersFrom } from '@angular/core';
import { AppModule } from './app/app.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { provideRouter } from '@angular/router';
import { routes } from './app/app-routing.module'; // Ajuste o caminho conforme sua estrutura

// Inicialização para componente standalone
bootstrapApplication(AppComponent, {
  providers: [
    importProvidersFrom(AppModule, BrowserAnimationsModule),
    provideRouter(routes),
  ],
}).catch((err) => console.error(err));
