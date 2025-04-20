import { Component, Input } from '@angular/core';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import {
  faBolt,
  faBroom,
  faCouch,
  faPaintBrush,
  faPaintRoller,
  faShower,
  faTools,
  faTree,
  faWater,
  faWrench,
} from '@fortawesome/free-solid-svg-icons';
import { TranslateModule } from '@ngx-translate/core';

@Component({
  selector: 'app-services',
  standalone: true,
  imports: [FontAwesomeModule, TranslateModule],
  templateUrl: './services.component.html',
  styleUrls: ['./services.component.scss'],
})
export class ServicesComponent {
  @Input() isMobile = false;

  faWrench = faWrench;
  faPaintRoller = faPaintRoller;
  faBolt = faBolt;
  faShower = faShower;
  faTree = faTree;
  faBroom = faBroom;
  faWater = faWater;
  faCouch = faCouch;
  faTools = faTools;
  faPaintBrush = faPaintBrush;
}
