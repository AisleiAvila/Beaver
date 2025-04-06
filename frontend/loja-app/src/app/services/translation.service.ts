import { Injectable } from "@angular/core";
import { TranslateService } from "@ngx-translate/core";

@Injectable({
  providedIn: "root",
})
export class TranslationService {
  constructor(private translate: TranslateService) {
    // Define o idioma padrão
    translate.setDefaultLang("pt");

    // Tenta obter o idioma do navegador
    const browserLang = translate.getBrowserLang();
    translate.use(browserLang?.match(/pt|en|es/) ? browserLang : "pt");
  }

  setLanguage(lang: string) {
    this.translate.use(lang);
  }

  getCurrentLang(): string {
    return this.translate.currentLang;
  }
}
