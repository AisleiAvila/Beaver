import { CommonModule } from '@angular/common';
import { Component, inject, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { DeviceService } from 'src/app/shared/service/device.service';
import { HeaderLandingpageComponent } from './header/header-landingpage.component';
import { HeroComponent } from './hero/hero.component';
import { HowItWorksComponent } from './how-it-works/how-it-works.component';
import { ServicesComponent } from './services/services.component';
import { TestimonialsComponent } from './testimonials/testimonials.component';
import { FooterLandingpageComponent } from './footer/footer-landingpage.component';

@Component({
  selector: 'app-landingpage',
  standalone: true,
  imports: [
    CommonModule,
    FooterLandingpageComponent,
    HeaderLandingpageComponent,
    HeroComponent,
    HowItWorksComponent,
    ServicesComponent,
    TestimonialsComponent,
  ],
  templateUrl: './landingpage.component.html',
  styleUrls: ['./landingpage.component.scss'],
})
export class LandingPageComponent implements OnInit, OnDestroy {
  deviceService = inject(DeviceService);
  isMobile = false;
  private subscription = new Subscription();

  ngOnInit(): void {
    this.subscription.add(
      this.deviceService.isMobile$.subscribe((isMobile) => {
        this.isMobile = isMobile;
        console.log('LandingPage - dispositivo móvel:', isMobile);
      })
    );
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
