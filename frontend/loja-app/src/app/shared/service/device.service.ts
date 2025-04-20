import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class DeviceService {
  private isMobileSubject = new BehaviorSubject<boolean>(false);
  public isMobile$ = this.isMobileSubject.asObservable();

  constructor(private breakpointObserver: BreakpointObserver) {
    this.checkScreenSize();
  }

  private checkScreenSize(): void {
    this.breakpointObserver
      .observe([Breakpoints.HandsetPortrait, Breakpoints.TabletPortrait])
      .pipe(
        map((result) => result.matches),
        shareReplay()
      )
      .subscribe((isMobile) => {
        this.isMobileSubject.next(isMobile);
        console.log('Dispositivo móvel:', isMobile);
      });
  }

  // Método auxiliar para componentes que preferem acesso síncrono
  public get isMobile(): boolean {
    return this.isMobileSubject.value;
  }
}
