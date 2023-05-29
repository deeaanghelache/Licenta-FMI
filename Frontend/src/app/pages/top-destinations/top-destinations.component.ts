import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { CityService } from 'src/app/services/city/city.service';
import { TranslateService } from '@ngx-translate/core';
import { Router } from '@angular/router';
import { CityRatingService } from 'src/app/services/cityRating/city-rating.service';

@Component({
  selector: 'app-top-destinations',
  templateUrl: './top-destinations.component.html',
  styleUrls: ['./top-destinations.component.scss']
})
export class TopDestinationsComponent implements OnInit{
  public admin:boolean = false;
  public logged:boolean = false;
  public language:any;
  public cityNameAttribute = 'nameEng';
  public cityCountryAttribute = 'countryEng';
  public topDestinations = [];

  constructor(private cityRatingService:CityRatingService, private router:Router, private cityService:CityService, public translate: TranslateService, private elementRef: ElementRef) {
    this.translate.addLangs(['en', 'ro'])
    this.translate.setDefaultLang('en');
    this.getLanguageFromSessionStorage();
    this.translate.use(this.language);
  }

  ngOnInit(): void {
    this.checkIfLoggedIn();
    this.checkIfAdmin();
    this.getTopDestinations();
    this.getNameAttributesFromSessionStorage();
  }

  getNameAttributesFromSessionStorage(){
    const cityNameAttribute = sessionStorage.getItem('cityNameAttribute');
    const cityCountryAttribute = sessionStorage.getItem('cityCountryAttribute');

    if (cityNameAttribute !== null && cityCountryAttribute !== null) {
      this.cityNameAttribute = cityNameAttribute;
      this.cityCountryAttribute = cityCountryAttribute;
    }
  }

  getTopDestinations(){
    this.cityRatingService.getTopDestinations().subscribe((response:any) => {
      console.log(response);
      this.topDestinations = response;
    })
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
    this.router.navigateByUrl('/homepage');
  }

  switchAppsLanguage(language: string) {
    if (language === "ro"){
      this.cityNameAttribute = 'nameRom';
      this.cityCountryAttribute = "countryRom";
      sessionStorage.setItem('cityNameAttribute', 'nameRom');
      sessionStorage.setItem('cityCountryAttribute', 'countryRom');
    } else {
      this.cityNameAttribute = 'nameEng';
      this.cityCountryAttribute = 'countryEng';
      sessionStorage.setItem('cityNameAttribute', 'nameEng');
      sessionStorage.setItem('cityCountryAttribute', 'countryEng');
    }
    sessionStorage.setItem("language", language);
    this.translate.use(language);
  }
}
