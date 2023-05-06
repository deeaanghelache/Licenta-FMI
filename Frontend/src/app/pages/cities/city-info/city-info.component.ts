import { Component, Input, OnInit} from '@angular/core';
import { AirportService } from 'src/app/services/airport/airport.service';
import { CityService } from 'src/app/services/city/city.service';
import { CityListService } from 'src/app/services/cityList/city-list.service';
import { CityTagService } from 'src/app/services/cityTag/city-tag.service';
import { LandmarkService } from 'src/app/services/landmark/landmark.service';

@Component({
  selector: 'app-city-info',
  templateUrl: './city-info.component.html',
  styleUrls: ['./city-info.component.scss']
})
export class CityInfoComponent implements OnInit {
  public tags = [];
  public airports = [];
  public landmarks = [];

  @Input() city: any;
  @Input() favourites: any;
  @Input() loggedUser: any;
  @Input() currentUserId: any;

  constructor(private cityService:CityService, private cityTagService:CityTagService, private airportService:AirportService, private landmarkService:LandmarkService, private cityListService:CityListService) { }

  ngOnInit(): void {
    this.getTagsForCurrentCity();
    this.getAirportsForCurrentCity();
    this.getLandmarksForCurrentCity();
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
}
