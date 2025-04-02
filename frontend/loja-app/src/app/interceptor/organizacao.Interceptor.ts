import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable()
export class OrganizacaoInterceptor implements HttpInterceptor {
  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    const organizacaoId = localStorage.getItem('organizacaoId');

    if (organizacaoId) {
      const modifiedReq = req.clone({
        headers: req.headers.set('X-Organizacao-ID', organizacaoId),
      });
      return next.handle(modifiedReq);
    }

    return next.handle(req);
  }
}
