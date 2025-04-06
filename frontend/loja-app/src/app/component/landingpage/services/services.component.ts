import { Component } from "@angular/core";
import { FontAwesomeModule } from "@fortawesome/angular-fontawesome";
import { TranslateModule } from "@ngx-translate/core";
import {
  faWrench,
  faPaintRoller,
  faBolt,
  faShower,
  faTree,
  faBroom,
  faWater,
  faCouch,
  faTools,
  faPaintBrush,
} from "@fortawesome/free-solid-svg-icons";

@Component({
  selector: "app-services",
  standalone: true,
  imports: [FontAwesomeModule, TranslateModule],
  templateUrl: "./services.component.html",
  styleUrls: ["./services.component.scss"],
})
export class ServicesComponent {
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
