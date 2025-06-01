import { CanActivateFn } from '@angular/router';

export const ProduitGuard: CanActivateFn = (route, state) => {
  return true;
};
