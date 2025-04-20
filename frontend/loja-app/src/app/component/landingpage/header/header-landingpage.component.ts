import { Component, inject } from '@angular/core';
import {
  faGlobe,
  faBars,
  faChevronDown,
  faChevronUp, // Added icon
  faUser,
  faCheck, // Added icon
} from '@fortawesome/free-solid-svg-icons';
import { faApple, faGooglePlay } from '@fortawesome/free-brands-svg-icons';
import { TranslationService } from '../../../service/translation.service';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { TranslateModule, TranslateService } from '@ngx-translate/core';
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
  isLanguageDropdownOpen = false; // Added property for dropdown state
  currentLanguage: string; // Added property to track current language

  // Font Awesome icons
  faGlobe = faGlobe;
  faBars = faBars;
  faChevronDown = faChevronDown;
  faChevronUp = faChevronUp; // Added icon
  faUser = faUser;
  faApple = faApple;
  faGooglePlay = faGooglePlay;
  faCheck = faCheck; // Added icon

  constructor(
    private translationService: TranslationService,
    private translate: TranslateService
  ) {
    // Initialize currentLanguage based on the currently set language
    this.currentLanguage = this.translate.currentLang || 'pt';
  }

  toggleMobileMenu() {
    this.isMobileMenuOpen = !this.isMobileMenuOpen;
  }

  toggleLanguageDropdown() {
    this.isLanguageDropdownOpen = !this.isLanguageDropdownOpen;
  }

  setLanguage(lang: string) {
    this.translate.use(lang);
    this.currentLanguage = lang; // Update current language
    this.isLanguageDropdownOpen = false; // Close dropdown after selection
  }

  navigateToLogin(event: Event): void {
    event.preventDefault();
    this.router.navigate(['/login']);
  }
}
