import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminGuardGuard } from './guards/adminGuard/admin-guard.guard';
import { AdminComponent } from './pages/admin/admin.component';
import { AirportsComponent } from './pages/admin/menuPages/airports/airports.component';
import { CitiesAdminComponent } from './pages/admin/menuPages/cities-admin/cities-admin.component';
import { LandmarksComponent } from './pages/admin/menuPages/landmarks/landmarks.component';
import { TagsComponent } from './pages/admin/menuPages/tags/tags.component';
import { UsersComponent } from './pages/admin/menuPages/users/users.component';
import { BlogComponent } from './pages/blog/blog.component';
import { CitiesComponent } from './pages/cities/cities.component';
import { HomepageComponent } from './pages/homepage/homepage.component';
import { LoginComponent } from './pages/login/login.component';
import { Page405Component } from './pages/page405/page405.component';
import { ProfileComponent } from './pages/profile/profile.component';
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
    canActivate: [AdminGuardGuard],
    children: [
      // This pages open inside the admin page
      {
        path: 'users',
        component: UsersComponent
      },
      {
        path: 'airports',
        component: AirportsComponent
      },
      {
        path: 'tags',
        component: TagsComponent
      },
      {
        path: 'landmarks',
        component: LandmarksComponent
      },
      {
        path: 'citiesAdmin',
        component: CitiesAdminComponent
      }
    ]
  },
  {
    path: 'page405',
    component: Page405Component
  },
  {
    path: 'blog',
    component: BlogComponent
  },
  {
    path: 'profile',
    component: ProfileComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
