import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminGuardGuard } from './guards/adminGuard/admin-guard.guard';
import { AdminComponent } from './pages/admin/admin.component';
import { AirportsComponent } from './pages/admin/menuPages/airports/airports.component';
import { CitiesAdminComponent } from './pages/admin/menuPages/cities-admin/cities-admin.component';
import { LandmarksComponent } from './pages/admin/menuPages/landmarks/landmarks.component';
import { TagsComponent } from './pages/admin/menuPages/tags/tags.component';
import { UsersComponent } from './pages/admin/menuPages/users/users.component';
import { JournalComponent } from './pages/journal/journal.component';
import { CitiesComponent } from './pages/cities/cities.component';
import { HomepageComponent } from './pages/homepage/homepage.component';
import { LoginComponent } from './pages/login/login.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { RegisterComponent } from './pages/register/register.component';
import { WishlistComponent } from './pages/wishlist/wishlist.component';
import { LoggedUserGuardGuard } from './guards/loggedUserGuard/logged-user-guard.guard';
import { Page401Component } from './pages/page401/page401.component';

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
    component: WishlistComponent,
    canActivate: [LoggedUserGuardGuard],
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
    path: 'page401',
    component: Page401Component
  },
  {
    path: 'journal',
    component: JournalComponent,
    canActivate: [LoggedUserGuardGuard],
  },
  {
    path: 'profile',
    component: ProfileComponent,
    canActivate: [LoggedUserGuardGuard],
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
