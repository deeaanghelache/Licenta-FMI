import { Component, ElementRef, Input, OnInit, Renderer2, ViewChild} from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { AirportService } from 'src/app/services/airport/airport.service';
import { CityService } from 'src/app/services/city/city.service';
import { CityListService } from 'src/app/services/cityList/city-list.service';
import { CityTagService } from 'src/app/services/cityTag/city-tag.service';
import { LandmarkService } from 'src/app/services/landmark/landmark.service';
import * as leafletModule from 'leaflet';

@Component({
  selector: 'app-city-info',
  templateUrl: './city-info.component.html',
  styleUrls: ['./city-info.component.scss']
})
export class CityInfoComponent implements OnInit {
  public tags = [];
  public airports = [];
  public landmarks = [];
  public language:any;
  public cityNameAttribute = 'nameEng';
  public tagNameAttribute = 'tagNameEng';
  public historyAttribute = 'briefHistoryEng';
  public airportNameAttribute = 'nameEng';

  @Input() city: any;
  @Input() favourites: any;
  @Input() loggedUser: any;
  @Input() currentUserId: any;
  @Input() map: any;

  @ViewChild("top") Top!: ElementRef;

  constructor(private cityService:CityService, private cityTagService:CityTagService, private airportService:AirportService, private landmarkService:LandmarkService, private cityListService:CityListService, public translate: TranslateService, private renderer: Renderer2, private elementRef: ElementRef) { 
    this.translate.addLangs(['en', 'ro'])
    this.translate.setDefaultLang('en');
    this.getLanguageFromSessionStorage();
    this.translate.use(this.language);
  }

  ngOnInit(): void {
    this.getTagsForCurrentCity();
    this.getAirportsForCurrentCity();
    this.getLandmarksForCurrentCity();
    this.scrollIntoView();
  }

  getTagsForCurrentCity(){
    this.cityTagService.getTagsForCurrentCity(this.city['cityId']).subscribe((response:any) => {
      this.tags = response;
    })
  }

  getAirportsForCurrentCity(){
    this.airportService.getAllAirportsForGivenCity(this.city['cityId']).subscribe((response:any) => {
      this.airports = response;
    })
  }

  getLandmarksForCurrentCity(){
    this.landmarkService.getAllLandmarksForGivenCity(this.city['cityId']).subscribe((response:any) => {
      this.landmarks = response;
    })
  }

  isFavourite() {
    for (let current of this.favourites){
      if (current.cityId === this.city.cityId) {
        return true;
      }
    }
    return false;
  }

  getFavourite(userId:any) {
    this.cityService.getFavouriteCities(userId).subscribe((response:any) => {
      this.favourites = response;
    })
  }

  addFavourite() {
    this.cityListService.addCityListForGivenUser(this.city['cityId'], this.currentUserId).subscribe((response:any) => {
      this.getFavourite(this.currentUserId);
    })
  }

  removeFavourite() {
    this.cityListService.deleteCityList(this.city['cityId'], this.currentUserId).subscribe((response:any) => {
      this.getFavourite(this.currentUserId);
    })
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
      this.airportNameAttribute = 'nameRom';
      this.historyAttribute = 'briefHistoryRom';
    } else {
      this.cityNameAttribute = 'nameEng';
      this.tagNameAttribute = 'tagNameEng';
      this.airportNameAttribute = 'nameEng';
      this.historyAttribute = 'briefHistoryEng';
    }
    sessionStorage.setItem("language", language);
    this.translate.use(language);
  }

  goToLocation(latitude:any, longitude:any, name:any){
    this.map.setView([latitude, longitude], 13);
    leafletModule.marker([latitude, longitude]).addTo(this.map)
    .bindPopup(name)
    .openPopup();
  }

  scrollIntoView() {
    const topElement = this.elementRef.nativeElement;
    topElement.scrollIntoView({ behavior: 'smooth', block: 'start' });
  }
}
