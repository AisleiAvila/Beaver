import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LayoutService {
  private menuCollapsedSubject = new BehaviorSubject<boolean>(false);
  menuState$ = this.menuCollapsedSubject.asObservable();

  toggleMenu(isCollapsed: boolean) {
    this.menuCollapsedSubject.next(isCollapsed);
  }
}
