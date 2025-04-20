import { Component, Input } from '@angular/core';
import { faApple, faGooglePlay } from '@fortawesome/free-brands-svg-icons';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { TranslateModule } from '@ngx-translate/core';

@Component({
  selector: 'app-hero',
  templateUrl: './hero.component.html',
  styleUrls: ['./hero.component.scss'],
  standalone: true,
  imports: [FontAwesomeModule, TranslateModule],
})
export class HeroComponent {
  @Input() isMobile = false;
  faApple = faApple;
  faGooglePlay = faGooglePlay;
}
