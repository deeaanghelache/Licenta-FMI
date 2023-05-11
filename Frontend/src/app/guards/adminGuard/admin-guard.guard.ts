import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminGuardGuard implements CanActivate {
  constructor(private router: Router){}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    
    if ((sessionStorage.getItem('admin') === 'admin') && (sessionStorage.getItem('logged') === 'true')){
      // if the user is admin, he can access the admin page
      return true;
    }
    else {
      // if not, "hide" the admin page from him
      this.router.navigate(['/page401']);
      return false;
    }
  }
}