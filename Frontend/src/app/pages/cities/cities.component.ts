import { Component, OnInit} from '@angular/core';
import { CityService } from 'src/app/services/city/city.service';
import { CityListService } from 'src/app/services/cityList/city-list.service';
import { TagService } from 'src/app/services/tag/tag.service';
import { UserService } from 'src/app/services/user/user.service';
import * as leafletModule from 'leaflet';
import { TranslateService } from '@ngx-translate/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cities',
  templateUrl: './cities.component.html',
  styleUrls: ['./cities.component.scss']
})
export class CitiesComponent implements OnInit {
  public admin:boolean = false;
  public logged:boolean = false;
  public cities = [];
  public currentCity: any;
  public currentTagForFilter: number = 0;
  public tags = [];
  public display: boolean = false;
  public displayTags: boolean = true;
  public currentEmail:string = '';
  public currentId:number = 0;
  public currentUser!:any;
  public currentFavs:any = [];
  public map!:leafletModule.Map;
  private defaultMapLat = 44.439663;
  private defaultMapLong = 26.096306;
  private defaultMapZoom = 15;
  public cityNameAttribute = 'nameEng';
  public tagNameAttribute = 'tagNameEng';
  public cityCountryAttribute = 'countryEng';
  public language:any;

  constructor(private router:Router, private tagService: TagService, private cityService: CityService, private userService:UserService, private cityListService:CityListService, public translate: TranslateService) {
    this.translate.addLangs(['en', 'ro'])
    this.translate.setDefaultLang('en');
    this.getLanguageFromSessionStorage();
    this.translate.use(this.language);
   }

  ngOnInit(): void {
    this.map = leafletModule.map('map').setView([this.defaultMapLat, this.defaultMapLong], this.defaultMapZoom);

    leafletModule.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(this.map);

    this.checkIfLoggedIn();
    this.checkIfAdmin();
    this.getAllTags();
    this.getAllCities();
  }

  getEmail(){
    this.currentEmail = sessionStorage.getItem("loggedUserEmail") as string;
    this.getUserByEmail(this.currentEmail);
  }

  checkIfLoggedIn(){
    if ("loggedUserEmail" in sessionStorage){
      this.logged = true;
      this.getEmail();
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

  getAllTags(){
    this.tagService.getAllTags().subscribe((response:any) => {
      this.tags = response;
    })
  }

  getAllCities(){
    this.cityService.getAllCities().subscribe((response:any) => {
      this.cities = response;
    })
  }

  show(city:any){
    this.getFavourite(this.currentId);
    console.log(this.currentFavs);
    this.showCityInfos(city);
  }

  showCityInfos(city:any){
    this.display = true;
    this.displayTags = false;
    this.currentCity = city;
    let lat = city['latitude'];
    let long = city['longitude'];
    this.map.setView([lat, long], 13);
  }

  closeCityInfos(){
    this.display = false;
    this.displayTags = true;
    this.map.setView([this.defaultMapLat, this.defaultMapLong], this.defaultMapZoom);
    this.getFavourite(this.currentId);
  }

  getTagIdByName(tagName:any){
    console.log(tagName);
    this.tagService.getTagIdByName(tagName).subscribe((response:any) => {
      this.currentTagForFilter = response;
    })
  }

  filterByTag(tagName:string){
    for (let index = 0; index < this.tags.length; index++){
      if (this.tags[index]['tagNameEng'] === tagName) {
        this.currentTagForFilter = this.tags[index]['tagId'];
      }
    }
  
    this.cityService.getAllCitiesForAGivenTag(this.currentTagForFilter).subscribe((response:any) => {
      this.cities = response;
    })
  }

  allButton() {
    this.getAllCities();
  }

  getUserByEmail(email:string){
    this.userService.getUserByEmail(email).subscribe((response:any) => {
      this.currentId = response.userId;
      this.currentUser = response;
      this.getFavourite(this.currentId);
    })
  }

  addFavourite(city:any) {
    this.cityListService.addCityListForGivenUser(city.cityId, this.currentId).subscribe((response:any) => {
      window.location.reload();
    })
  }

  getFavourite(userId:any) {
    this.cityService.getFavouriteCities(userId).subscribe((response:any) => {
      this.currentFavs = response;
      console.log(this.currentFavs);
    })
  }

  isFavourite(city:any) {
    for (let current of this.currentFavs){
      if (current.cityId === city.cityId) {
        return true;
      }
    }
    return false;
  }

  searchCity(searchName:any) {
    if (searchName != ""){
      this.cityService.searchCitiesByNameContainsWord(searchName).subscribe ((response:any) => {
        console.log(response);
        this.cities = response;
      })
    } else {
      this.getAllCities();
    }
  }

  sortByNameAscending(){
    this.cities = this.cities.sort((city1, city2) => {
      if (city1['nameEng'] < city2['nameEng']) {
        return -1; 
      } else if (city1['nameEng'] > city2['nameEng']) {
        return 1; 
      } else {
        return 0; 
      }
    });
  }

  sortByNameDescending(){
    this.cities = this.cities.sort((city1, city2) => {
      if (city1['nameEng'] > city2['nameEng']) {
        return -1; 
      } else if (city1['nameEng'] < city2['nameEng']) {
        return 1; 
      } else {
        return 0; 
      }
    });
  }

  getLanguageFromSessionStorage(){
    if ("language" in sessionStorage){
      this.language = sessionStorage.getItem("language");
    }
  }

  switchAppsLanguage(language: string) {
    if (language === "ro"){
      this.cityNameAttribute = 'nameRom';
      this.tagNameAttribute = 'tagNameRom';
      this.cityCountryAttribute = 'countryRom';
    } else {
      this.cityNameAttribute = 'nameEng';
      this.tagNameAttribute = 'tagNameEng';
      this.cityCountryAttribute = 'countryEng';
    }
    sessionStorage.setItem("language", language);
    this.translate.use(language);
  }
}
