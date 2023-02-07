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
import { ListCardsComponent } from './pages/reusableComponents/list-cards/list-cards.component';
import { AdminComponent } from './pages/admin/admin.component';
import { Page405Component } from './pages/page405/page405.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { BlogComponent } from './pages/blog/blog.component';
import { UserComponent } from './details/user/user.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomepageComponent,
    CitiesComponent,
    WishlistComponent,
    ListCardsComponent,
    AdminComponent,
    Page405Component,
    ProfileComponent,
    BlogComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule, 
    HttpClientModule,
    SharedInfoModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
