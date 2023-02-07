import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminGuardGuard } from './guards/adminGuard/admin-guard.guard';
import { AdminComponent } from './pages/admin/admin.component';
import { CitiesComponent } from './pages/cities/cities.component';
import { HomepageComponent } from './pages/homepage/homepage.component';
import { LoginComponent } from './pages/login/login.component';
import { Page405Component } from './pages/page405/page405.component';
import { RegisterComponent } from './pages/register/register.component';
import { WishlistComponent } from './pages/wishlist/wishlist.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: '/homepage',
    pathMatch: 'full'
  },
  {
    path: 'homepage',
    component: HomepageComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'wishlist',
    component: WishlistComponent
  },
  {
    path: 'cities',
    component: CitiesComponent
  },
  {
    path: 'admin',
    component: AdminComponent,
    canActivate: [AdminGuardGuard]
  },
  {
    path: 'page405',
    component: Page405Component
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
