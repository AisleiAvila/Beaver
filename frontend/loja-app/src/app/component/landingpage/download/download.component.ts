import { Component } from "@angular/core";
import { faApple, faGooglePlay } from "@fortawesome/free-brands-svg-icons";
import { FontAwesomeModule } from "@fortawesome/angular-fontawesome";
import { TranslateModule } from "@ngx-translate/core";

@Component({
  selector: "app-download",
  standalone: true,
  imports: [FontAwesomeModule, TranslateModule],
  templateUrl: "./download.component.html",
  styleUrls: ["./download.component.scss"],
})
export class DownloadComponent {
  faApple = faApple;
  faGooglePlay = faGooglePlay;
}
