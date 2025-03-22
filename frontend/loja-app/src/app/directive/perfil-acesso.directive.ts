import { Directive, ElementRef, inject, Input, OnInit } from '@angular/core';
import { AuthService } from '../service/auth.service';

@Directive({
  selector: '[appPerfilAcesso]',
  standalone: true,
})
export class PerfilAcessoDirective implements OnInit {
  el = inject(ElementRef);
  authService = inject(AuthService);

  @Input('appPerfilAcesso') perfisPermitidos: string[] = [];

  ngOnInit() {
    const perfilUsuario = this.authService.getPerfilUsuario();
    if (!this.perfisPermitidos.includes(perfilUsuario)) {
      this.el.nativeElement.remove();
    }
  }
}
