import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { CityService } from 'src/app/services/city/city.service';
import { CityListService } from 'src/app/services/cityList/city-list.service';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-wishlist',
  templateUrl: './wishlist.component.html',
  styleUrls: ['./wishlist.component.scss']
})
export class WishlistComponent implements OnInit {
  public logged:boolean = false;
  public admin:boolean = false;
  public currentId: number = 0;
  public currentEmail = '';
  public cityLists = [];
  public chosenCity:any;
  public language:any;
  public displayLandmarkPlanning: boolean = false;
  public planLandmarks = false;
  public cityNameAttribute = 'nameEng';
  public cityCountryAttribute = 'countryEng';

  constructor(private router:Router, public translate: TranslateService, private cityService:CityService, private userService:UserService, private cityListService:CityListService) {
    this.translate.addLangs(['en', 'ro'])
    this.translate.setDefaultLang('en');
    this.getLanguageFromSessionStorage();
    this.translate.use(this.language);
   }

  ngOnInit(): void {
    this.checkIfLoggedIn();
    this.checkIfAdmin();
    this.getEmail();
    this.getUserByEmail(this.currentEmail);
  }

  getEmail(){
    this.currentEmail = sessionStorage.getItem("loggedUserEmail") as string;
  }

  getUserByEmail(email:string){
    this.userService.getUserByEmail(email).subscribe((response:any) => {
      this.currentId = response.userId;
      this.getAllCityLists(this.currentId);
    })
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

  getAllCityLists(userId:any){
    this.cityService.getFavouriteCities(userId).subscribe((response:any) => {
      console.log(response);
      this.cityLists = response;
    })
  }

  deleteFunction(cityId:any){
    this.deleteCityList(cityId, this.currentId);
  }

  deleteCityList(cityId:any, userId:any){
    this.cityListService.deleteCityList(cityId, userId).subscribe((response:any) => {
      console.log(response);
      window.location.reload();
    });
  }

  getDistanceMatrix(){
    this.cityService.getDistanceMatrix().subscribe((response:any) => {
      console.log("Distance Matrix");
      console.log(response);
    })
  }

  openPlanningLandmarks(city:any){
    this.displayLandmarkPlanning = true;
    this.chosenCity = city;
  }

  closePlanningLandmarks(){
    this.displayLandmarkPlanning = false;
  }

  logout(){
    sessionStorage.clear();
    this.admin = false;
    this.logged = false;
    this.router.navigateByUrl('/homepage');
  }

  getLanguageFromSessionStorage(){
    if ("language" in sessionStorage){
      this.language = sessionStorage.getItem("language");
    }
  }

  switchAppsLanguage(language: string) {
    if (language === "ro"){
      this.cityNameAttribute = 'nameRom';
      this.cityCountryAttribute = 'countryRom';
    } else {
      this.cityNameAttribute = 'nameEng';
      this.cityCountryAttribute = 'countryEng';
    }
    sessionStorage.setItem("language", language);
    this.translate.use(language);
  }
}
