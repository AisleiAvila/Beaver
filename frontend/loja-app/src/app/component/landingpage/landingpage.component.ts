import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeroComponent } from './hero/hero.component';
import { HowItWorksComponent } from './how-it-works/how-it-works.component';
import { ServicesComponent } from './services/services.component';
import { TestimonialsComponent } from './testimonials/testimonials.component';
import { FooterComponent } from '../footer/footer.component';
import { HeaderComponent } from '../header/header.component';

@Component({
  selector: 'app-landingpage',
  standalone: true,
  imports: [
    CommonModule,
    HeroComponent,
    HowItWorksComponent,
    ServicesComponent,
    TestimonialsComponent,
    FooterComponent,
    HeaderComponent,
  ],
  templateUrl: './landingpage.component.html',
  styleUrls: ['./landingpage.component.scss'],
})
export class LandingPageComponent {
  // Add any necessary logic or properties here
}
