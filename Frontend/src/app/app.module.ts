import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { SharedInfoModule } from './modules/shared-info/shared-info.module';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { HomepageComponent } from './pages/homepage/homepage.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CitiesComponent } from './pages/cities/cities.component';
import { WishlistComponent } from './pages/wishlist/wishlist.component';
import { AdminComponent } from './pages/admin/admin.component';
import { Page405Component } from './pages/page405/page405.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { BlogComponent } from './pages/blog/blog.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { UsersComponent } from './pages/admin/menuPages/users/users.component';
import { AirportsComponent } from './pages/admin/menuPages/airports/airports.component';
import { TagsComponent } from './pages/admin/menuPages/tags/tags.component';
import { LandmarksComponent } from './pages/admin/menuPages/landmarks/landmarks.component';
import { CitiesAdminComponent } from './pages/admin/menuPages/cities-admin/cities-admin.component';
import { CityInfoComponent } from './pages/cities/city-info/city-info.component';
import { ButtonDirective } from './directives/button.directive';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomepageComponent,
    CitiesComponent,
    WishlistComponent,
    AdminComponent,
    Page405Component,
    ProfileComponent,
    BlogComponent,
    UsersComponent,
    AirportsComponent,
    TagsComponent,
    LandmarksComponent,
    CitiesAdminComponent,
    CityInfoComponent,
    ButtonDirective,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule, 
    HttpClientModule,
    SharedInfoModule,
    NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
