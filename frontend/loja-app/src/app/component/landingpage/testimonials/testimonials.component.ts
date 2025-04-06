import { Component, OnInit } from "@angular/core";
import { CommonModule } from "@angular/common"; // Import CommonModule
import { FontAwesomeModule } from "@fortawesome/angular-fontawesome";
import { TranslateModule } from "@ngx-translate/core";
import { faQuoteLeft, faStar } from "@fortawesome/free-solid-svg-icons";
import { TestimonialsService } from "../../../services/testimonials.service";

@Component({
  selector: "app-testimonials",
  standalone: true,
  imports: [CommonModule, FontAwesomeModule, TranslateModule], // Add CommonModule here
  templateUrl: "./testimonials.component.html",
  styleUrls: ["./testimonials.component.scss"],
  providers: [TestimonialsService],
})
export class TestimonialsComponent implements OnInit {
  faQuoteLeft = faQuoteLeft;
  faStar = faStar;
  testimonials: any[] = [];

  constructor(private testimonialsService: TestimonialsService) {}

  ngOnInit() {
    this.testimonialsService.getTestimonials().subscribe((data) => {
      this.testimonials = data;
    });
  }
}
