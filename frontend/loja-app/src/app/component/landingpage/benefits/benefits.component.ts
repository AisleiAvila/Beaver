import { Component } from "@angular/core";
import {
  faClock,
  faShieldAlt,
  faUserCheck,
  faHandHoldingUsd,
} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeModule } from "@fortawesome/angular-fontawesome";
import { TranslateModule } from "@ngx-translate/core";

@Component({
  selector: "app-benefits",
  standalone: true,
  imports: [FontAwesomeModule, TranslateModule],
  templateUrl: "./benefits.component.html",
  styleUrls: ["./benefits.component.scss"],
})
export class BenefitsComponent {
  faShieldAlt = faShieldAlt;
  faClock = faClock;
  faUserCheck = faUserCheck;
  faHandHoldingUsd = faHandHoldingUsd;
}
