import { Component } from '@angular/core';
import {
  faEnvelope,
  faPhone,
  faMapMarkerAlt,
} from '@fortawesome/free-solid-svg-icons';
import {
  faFacebook,
  faInstagram,
  faTwitter,
  faLinkedin,
} from '@fortawesome/free-brands-svg-icons';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { TranslateModule } from '@ngx-translate/core';

@Component({
  selector: 'app-footer-landingpage',
  standalone: true,
  imports: [FontAwesomeModule, TranslateModule],
  templateUrl: './footer-landingpage.component.html',
  styleUrls: ['./footer-landingpage.component.scss'],
})
export class FooterLandingpageComponent {
  // Ícones de contato
  faEnvelope = faEnvelope;
  faPhone = faPhone;
  faMapMarkerAlt = faMapMarkerAlt;

  // Ícones de redes sociais
  faFacebook = faFacebook;
  faInstagram = faInstagram;
  faTwitter = faTwitter;
  faLinkedin = faLinkedin;
}
