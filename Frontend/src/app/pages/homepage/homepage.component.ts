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
  public language:any;
  public cityNameAttribute = 'nameEng';

  constructor(private cityService:CityService, public translate: TranslateService) {
    this.translate.addLangs(['en', 'ro'])
    this.translate.setDefaultLang('en');
    this.getLanguageFromSessionStorage();
    this.translate.use(this.language);
  }

  ngOnInit(): void {
    this.checkIfLoggedIn();
    this.checkIfAdmin();
    this.getAllCities();
  }

  getLanguageFromSessionStorage(){
    if ("language" in sessionStorage){
      this.language = sessionStorage.getItem("language");
    }
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
    if (language === "ro"){
      this.cityNameAttribute = 'nameRom';
    } else {
      this.cityNameAttribute = 'nameEng';
    }
    sessionStorage.setItem("language", language);
    this.translate.use(language);
  }
}
