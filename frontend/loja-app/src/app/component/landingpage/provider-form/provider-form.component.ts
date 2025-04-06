import { Component } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule } from "@angular/forms";
import { TranslateModule } from "@ngx-translate/core";
import { FontAwesomeModule } from "@fortawesome/angular-fontawesome";
import {
  faEnvelope,
  faPhone,
  faUser,
  faBriefcase,
} from "@fortawesome/free-solid-svg-icons";

@Component({
  selector: "app-provider-form",
  standalone: true,
  imports: [CommonModule, FormsModule, TranslateModule, FontAwesomeModule],
  templateUrl: "./provider-form.component.html",
  styleUrls: ["./provider-form.component.scss"],
})
export class ProviderFormComponent {
  // Font Awesome icons
  faEnvelope = faEnvelope;
  faPhone = faPhone;
  faUser = faUser;
  faBriefcase = faBriefcase;

  formData = {
    name: "",
    email: "",
    phone: "",
    service: "",
    message: "",
  };

  onSubmit() {
    console.log("Form submitted:", this.formData);
    // Aqui você pode implementar a lógica para enviar os dados do formulário
  }
}
