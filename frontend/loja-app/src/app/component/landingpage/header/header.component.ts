import { Component } from "@angular/core";
import {
  faGlobe,
  faBars,
  faChevronDown,
  faUser,
} from "@fortawesome/free-solid-svg-icons";
import { faApple, faGooglePlay } from "@fortawesome/free-brands-svg-icons";
import { TranslationService } from "../../../services/translation.service";
import { FontAwesomeModule } from "@fortawesome/angular-fontawesome";
import { TranslateModule } from "@ngx-translate/core";

@Component({
  selector: "app-header",
  standalone: true,
  imports: [FontAwesomeModule, TranslateModule],
  template: `
    <header class="bg-white shadow-md">
      <nav class="container mx-auto px-4 py-4">
        <div class="flex justify-between items-center">
          <div class="flex items-center">
            <a href="#" class="text-2xl font-bold text-blue-600">BeaverFix</a>
          </div>

          <div class="hidden md:flex items-center space-x-8">
            <a href="#" class="text-gray-600 hover:text-blue-600">{{
              "header.home" | translate
            }}</a>
            <a href="#" class="text-gray-600 hover:text-blue-600">{{
              "header.howItWorks" | translate
            }}</a>
            <a href="#" class="text-gray-600 hover:text-blue-600">{{
              "header.services" | translate
            }}</a>
            <a href="#" class="text-gray-600 hover:text-blue-600">{{
              "header.testimonials" | translate
            }}</a>
            <a href="#" class="text-gray-600 hover:text-blue-600">{{
              "header.providers" | translate
            }}</a>
            <a href="#" class="text-gray-600 hover:text-blue-600">{{
              "header.download" | translate
            }}</a>
          </div>

          <div class="hidden md:flex items-center space-x-4">
            <button class="relative group">
              <fa-icon
                [icon]="faGlobe"
                class="text-gray-600 hover:text-blue-600"
              ></fa-icon>
              <div
                class="absolute right-0 mt-2 w-48 bg-white rounded-md shadow-lg py-1 hidden group-hover:block"
              >
                <button
                  (click)="setLanguage('pt')"
                  class="block w-full text-left px-4 py-2 text-sm text-gray-700 hover:bg-gray-100"
                >
                  Português
                </button>
                <button
                  (click)="setLanguage('en')"
                  class="block w-full text-left px-4 py-2 text-sm text-gray-700 hover:bg-gray-100"
                >
                  English
                </button>
                <button
                  (click)="setLanguage('es')"
                  class="block w-full text-left px-4 py-2 text-sm text-gray-700 hover:bg-gray-100"
                >
                  Español
                </button>
              </div>
            </button>
            <a
              href="#"
              class="inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md text-white bg-blue-600 hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500"
            >
              <fa-icon [icon]="faUser" class="mr-2"></fa-icon>
              {{ "header.login" | translate }}
            </a>
          </div>

          <div class="md:hidden">
            <button
              (click)="toggleMobileMenu()"
              class="text-gray-600 hover:text-blue-600"
            >
              <fa-icon [icon]="faBars"></fa-icon>
            </button>
          </div>
        </div>

        <div class="md:hidden" [class.hidden]="!isMobileMenuOpen">
          <div class="px-2 pt-2 pb-3 space-y-1">
            <a
              href="#"
              class="block px-3 py-2 text-gray-600 hover:text-blue-600"
              >{{ "header.home" | translate }}</a
            >
            <a
              href="#"
              class="block px-3 py-2 text-gray-600 hover:text-blue-600"
              >{{ "header.howItWorks" | translate }}</a
            >
            <a
              href="#"
              class="block px-3 py-2 text-gray-600 hover:text-blue-600"
              >{{ "header.services" | translate }}</a
            >
            <a
              href="#"
              class="block px-3 py-2 text-gray-600 hover:text-blue-600"
              >{{ "header.testimonials" | translate }}</a
            >
            <a
              href="#"
              class="block px-3 py-2 text-gray-600 hover:text-blue-600"
              >{{ "header.providers" | translate }}</a
            >
            <a
              href="#"
              class="block px-3 py-2 text-gray-600 hover:text-blue-600"
              >{{ "header.download" | translate }}</a
            >
            <a
              href="#"
              class="block px-3 py-2 text-gray-600 hover:text-blue-600"
              >{{ "header.login" | translate }}</a
            >
            <div class="px-3 py-2">
              <button
                class="flex items-center text-gray-600 hover:text-blue-600"
              >
                <fa-icon [icon]="faGlobe" class="mr-2"></fa-icon>
                <span>{{ "header.language" | translate }}</span>
                <fa-icon [icon]="faChevronDown" class="ml-2"></fa-icon>
              </button>
              <div class="mt-2 space-y-1">
                <button
                  (click)="setLanguage('pt')"
                  class="block w-full text-left px-3 py-2 text-sm text-gray-600 hover:text-blue-600"
                >
                  Português
                </button>
                <button
                  (click)="setLanguage('en')"
                  class="block w-full text-left px-3 py-2 text-sm text-gray-600 hover:text-blue-600"
                >
                  English
                </button>
                <button
                  (click)="setLanguage('es')"
                  class="block w-full text-left px-3 py-2 text-sm text-gray-600 hover:text-blue-600"
                >
                  Español
                </button>
              </div>
            </div>
          </div>
        </div>
      </nav>
    </header>
  `,
  styles: [],
})
export class HeaderComponent {
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
}
