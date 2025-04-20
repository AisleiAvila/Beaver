import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import {
  faHouseUser,
  faMobileAlt,
  faScrewdriverWrench,
  faUserShield,
} from '@fortawesome/free-solid-svg-icons';
import { TranslateModule } from '@ngx-translate/core';

@Component({
  selector: 'app-how-it-works',
  standalone: true,
  imports: [CommonModule, FontAwesomeModule, TranslateModule],
  templateUrl: './how-it-works.component.html',
  styleUrls: ['./how-it-works.component.scss'],
})
export class HowItWorksComponent {
  @Input() isMobile = false;

  steps = [
    {
      icon: faMobileAlt,
      title: 'howItWorks.step1.title',
      description: 'howItWorks.step1.description',
      index: 1,
    },
    {
      icon: faScrewdriverWrench,
      title: 'howItWorks.step2.title',
      description: 'howItWorks.step2.description',
      index: 2,
    },
    {
      icon: faUserShield,
      title: 'howItWorks.step3.title',
      description: 'howItWorks.step3.description',
      index: 3,
    },
    {
      icon: faHouseUser,
      title: 'howItWorks.step4.title',
      description: 'howItWorks.step4.description',
      index: 4,
    },
  ];
}
