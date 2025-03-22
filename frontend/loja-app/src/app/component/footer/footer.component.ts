import { Component, inject } from '@angular/core';
import { TranslateService } from '@ngx-translate/core'; // Supondo que você esteja usando ngx-translate para internacionalização

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss'],
  standalone: true,
})
export class FooterComponent {
  translate = inject(TranslateService);

  changeLanguage(language: string) {
    this.translate.use(language);
  }
}
