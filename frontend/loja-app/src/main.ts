import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/app.component';
import { importProvidersFrom } from '@angular/core';
import { AppModule } from './app/app.module';
import { provideAnimations } from '@angular/platform-browser/animations';
import { provideRouter } from '@angular/router';
import { routes } from './app/app-routing.module';

// Inicializar o AppComponent como aplicação standalone
bootstrapApplication(AppComponent, {
  providers: [
    importProvidersFrom(AppModule), // Importar providers do AppModule
    provideAnimations(), // Prover animações
    provideRouter(routes), // Prover rotas
  ],
}).catch((err) => console.error('Erro ao inicializar aplicação:', err));
