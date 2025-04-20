import { Component, inject } from '@angular/core';
import {
  faGlobe,
  faBars,
  faChevronDown,
  faUser,
} from '@fortawesome/free-solid-svg-icons';
import { faApple, faGooglePlay } from '@fortawesome/free-brands-svg-icons';
import { TranslationService } from '../../../service/translation.service';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { TranslateModule } from '@ngx-translate/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header-landingpage',
  templateUrl: './header-landingpage.component.html', // Alterado de template para templateUrl
  styleUrls: ['./header-landingpage.component.scss'],
  standalone: true,
  imports: [FontAwesomeModule, TranslateModule],
})
export class HeaderLandingpageComponent {
  router = inject(Router);

  // Nome da classe corrigido para combinar com o nome do arquivo
  isMobileMenuOpen = false;

  // Font Awesome icons
  faGlobe = faGlobe;
  faBars = faBars;
  faChevronDown = faChevronDown;
  faUser = faUser;
  faApple = faApple;
  faGooglePlay = faGooglePlay;

  constructor(private translationService: TranslationService) {}

  toggleMobileMenu() {
    this.isMobileMenuOpen = !this.isMobileMenuOpen;
  }

  setLanguage(lang: string) {
    this.translationService.setLanguage(lang);
    this.isMobileMenuOpen = false;
  }

  navigateToLogin(event: Event): void {
    event.preventDefault();
    this.router.navigate(['/login']);
  }
}
