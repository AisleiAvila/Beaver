import { CommonModule } from '@angular/common';
import {
  Component,
  EventEmitter,
  inject,
  Input,
  OnDestroy,
  OnInit,
  Output,
} from '@angular/core';
import {
  ActivatedRoute,
  NavigationEnd,
  Router,
  RouterModule,
} from '@angular/router';
import { filter, Subscription } from 'rxjs';
import { MenuComponent } from '../menu/menu.component';
import { DeviceService } from 'src/app/shared/service/device.service';

@Component({
  selector: 'app-body',
  templateUrl: './body.component.html',
  styleUrls: ['./body.component.scss'],
  standalone: true,
  imports: [CommonModule, MenuComponent, RouterModule],
})
export class BodyComponent implements OnInit, OnDestroy {
  router = inject(Router);
  route = inject(ActivatedRoute);
  deviceService = inject(DeviceService);

  @Input() isExpanded = false;
  @Output() expansionChange = new EventEmitter<boolean>();
  showMenu = true;

  isMobile = false;
  private subscription = new Subscription();

  ngOnInit() {
    this.router.events
      .pipe(filter((event) => event instanceof NavigationEnd))
      .subscribe(() => {
        this.checkRoute();
      });
    this.checkRoute();

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

  checkRoute() {
    const currentRoute = this.router.url;
    // Ocultar menu tanto para login quanto para landingpage
    this.showMenu = !(
      currentRoute.includes('login') || currentRoute.includes('landingpage')
    );
  }

  toggleMenu() {
    this.isExpanded = !this.isExpanded;
    this.expansionChange.emit(this.isExpanded);
  }

  onExpansionChange(isExpanded: boolean) {
    this.isExpanded = isExpanded;
  }
}
