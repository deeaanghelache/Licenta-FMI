import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { CityService } from 'src/app/services/city/city.service';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.scss']
})
export class HomepageComponent implements OnInit {
  public admin:boolean = false;
  public logged:boolean = false;
  public cities = []

  constructor(private cityService:CityService, public translate: TranslateService) {
    this.translate.addLangs(['en', 'ro'])
    this.translate.setDefaultLang('en');
    this.translate.use('en');
   }

  ngOnInit(): void {
    this.checkIfLoggedIn();
    this.checkIfAdmin();
    this.getAllCities();
  }

  checkIfLoggedIn(){
    if ("loggedUserEmail" in sessionStorage){
      this.logged = true;
    }
  }

  checkIfAdmin(){
    if (sessionStorage.getItem("admin") === "admin"){
      this.admin = true;
    }
  }

  logout(){
    sessionStorage.clear();
    this.admin = false;
    this.logged = false;
  }

  getAllCities(){
    this.cityService.getAllCities().subscribe((response:any) => {
      this.cities = response;
    })
  }

  goToTop(){
    window.scrollTo(0, 0);
  }

  switchAppsLanguage(language: string) {
    console.log(language);
    this.translate.use(language);
  }
}
