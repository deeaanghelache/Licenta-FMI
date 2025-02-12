import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { SharedInfoModule } from './modules/shared-info/shared-info.module';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { HomepageComponent } from './pages/homepage/homepage.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { CitiesComponent } from './pages/cities/cities.component';
import { WishlistComponent } from './pages/wishlist/wishlist.component';
import { AdminComponent } from './pages/admin/admin.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { JournalComponent } from './pages/journal/journal.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { UsersComponent } from './pages/admin/menuPages/users/users.component';
import { AirportsComponent } from './pages/admin/menuPages/airports/airports.component';
import { TagsComponent } from './pages/admin/menuPages/tags/tags.component';
import { LandmarksComponent } from './pages/admin/menuPages/landmarks/landmarks.component';
import { CitiesAdminComponent } from './pages/admin/menuPages/cities-admin/cities-admin.component';
import { CityInfoComponent } from './pages/cities/city-info/city-info.component';
import { ButtonDirective } from './directives/button.directive';
import { JournalPostComponent } from './pages/journal/journal-post/journal-post.component';
import { Page401Component } from './pages/page401/page401.component';
import { PlanLandmarksComponent } from './pages/wishlist/plan-landmarks/plan-landmarks.component';
import { DragDropModule } from '@angular/cdk/drag-drop';
import { TranslateModule, TranslateLoader } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { TopDestinationsComponent } from './pages/top-destinations/top-destinations.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomepageComponent,
    CitiesComponent,
    WishlistComponent,
    AdminComponent,
    Page401Component,
    ProfileComponent,
    JournalComponent,
    UsersComponent,
    AirportsComponent,
    TagsComponent,
    LandmarksComponent,
    CitiesAdminComponent,
    CityInfoComponent,
    ButtonDirective,
    JournalPostComponent,
    PlanLandmarksComponent,
    TopDestinationsComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule, 
    HttpClientModule,
    SharedInfoModule,
    NgbModule,
    DragDropModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}