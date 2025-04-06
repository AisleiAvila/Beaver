import { Component } from "@angular/core";
import { CommonModule } from "@angular/common";
import { TranslateModule } from "@ngx-translate/core";
import { FontAwesomeModule } from "@fortawesome/angular-fontawesome";
import {
  faTools,
  faWrench,
  faPaintRoller,
  faCouch,
  faBolt,
  faScrewdriver,
} from "@fortawesome/free-solid-svg-icons";

@Component({
  selector: "app-providers",
  standalone: true,
  imports: [CommonModule, TranslateModule, FontAwesomeModule],
  templateUrl: "./providers.component.html",
  styleUrls: ["./providers.component.scss"],
})
export class ProvidersComponent {
  faTools = faTools;
  faWrench = faWrench;
  faPaintRoller = faPaintRoller;
  faCouch = faCouch;
  faBolt = faBolt;
  faScrewdriver = faScrewdriver;

  providers = [
    {
      icon: faTools,
      title: "providerForm.services.repairs",
      description: "providers.services.repairs",
    },
    {
      icon: faWrench,
      title: "providerForm.services.plumbing",
      description: "providers.services.plumbing",
    },
    {
      icon: faPaintRoller,
      title: "providerForm.services.painting",
      description: "providers.services.painting",
    },
    {
      icon: faCouch,
      title: "providerForm.services.furniture",
      description: "providers.services.furniture",
    },
    {
      icon: faBolt,
      title: "providerForm.services.electrical",
      description: "providers.services.electrical",
    },
    {
      icon: faScrewdriver,
      title: "providerForm.services.installations",
      description: "providers.services.installations",
    },
  ];
}
