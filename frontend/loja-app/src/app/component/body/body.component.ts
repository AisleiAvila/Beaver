import { CommonModule } from '@angular/common';
import {
  Component,
  EventEmitter,
  inject,
  Input,
  OnInit,
  Output,
} from '@angular/core';
import {
  ActivatedRoute,
  NavigationEnd,
  Router,
  RouterModule,
} from '@angular/router';
import { filter } from 'rxjs';
import { MenuComponent } from '../menu/menu.component';

@Component({
  selector: 'app-body',
  templateUrl: './body.component.html',
  styleUrls: ['./body.component.scss'],
  standalone: true,
  imports: [CommonModule, MenuComponent, RouterModule],
})
export class BodyComponent implements OnInit {
  router = inject(Router);
  route = inject(ActivatedRoute);

  @Input() isExpanded = false;
  @Output() expansionChange = new EventEmitter<boolean>();
  showMenu = true;

  ngOnInit() {
    this.router.events
      .pipe(filter((event) => event instanceof NavigationEnd))
      .subscribe(() => {
        this.checkRoute();
      });
    this.checkRoute();
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
