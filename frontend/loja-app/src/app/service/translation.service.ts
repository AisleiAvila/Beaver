import { Injectable } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

@Injectable({
  providedIn: 'root',
})
export class TranslationService {
  constructor(private translate: TranslateService) {
    // Define o idioma padrão
    translate.setDefaultLang('pt');

    // Tenta obter o idioma do navegador
    const browserLang = translate.getBrowserLang();
    const useLang = browserLang?.match(/pt|en|es/) ? browserLang : 'pt';

    console.log('TranslationService - usando idioma:', useLang);
    translate.use(useLang);

    // Verificar se as traduções estão carregando
    this.translate.get('login.title').subscribe(
      (text) => console.log('Tradução carregada:', text),
      (error) => console.error('Erro ao carregar tradução:', error)
    );
  }

  setLanguage(lang: string) {
    console.log('Alterando idioma para:', lang);
    this.translate.use(lang);
  }

  getCurrentLang(): string {
    return this.translate.currentLang;
  }
}
