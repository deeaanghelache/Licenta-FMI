import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoggedUserGuardGuard implements CanActivate {
  constructor(private router: Router){}
  
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      if (sessionStorage.getItem('logged') === 'true'){
        // if the user is logged, he can access some of the websites pages
        return true;
      }
      else {
        // if not, "hide" the pages from him
        this.router.navigate(['/page401']);
        return false;
      }
  }
}
