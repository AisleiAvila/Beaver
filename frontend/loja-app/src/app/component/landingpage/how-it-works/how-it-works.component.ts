import { Component } from "@angular/core";
import {
  faMobileAlt,
  faScrewdriverWrench,
  faUserShield,
  faHouseUser,
} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeModule } from "@fortawesome/angular-fontawesome";
import { TranslateModule } from "@ngx-translate/core";
import { CommonModule } from "@angular/common";

@Component({
  selector: "app-how-it-works",
  standalone: true,
  imports: [CommonModule, FontAwesomeModule, TranslateModule],
  templateUrl: "./how-it-works.component.html",
  styleUrls: ["./how-it-works.component.scss"],
})
export class HowItWorksComponent {
  steps = [
    {
      icon: faMobileAlt,
      title: "howItWorks.step1.title",
      description: "howItWorks.step1.description",
      index: 1,
    },
    {
      icon: faScrewdriverWrench,
      title: "howItWorks.step2.title",
      description: "howItWorks.step2.description",
      index: 2,
    },
    {
      icon: faUserShield,
      title: "howItWorks.step3.title",
      description: "howItWorks.step3.description",
      index: 3,
    },
    {
      icon: faHouseUser,
      title: "howItWorks.step4.title",
      description: "howItWorks.step4.description",
      index: 4,
    },
  ];
}
