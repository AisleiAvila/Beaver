import { Routes } from '@angular/router';
// ...existing imports...

export const routes: Routes = [
  // ...existing routes...
  {
    path: 'clients',
    loadComponent: () =>
      import('./component/clients/clients.component').then(
        (c) => c.ClientsComponent
      ),
  },
  // ...existing routes...
];
