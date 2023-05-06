import { Component, Input, OnInit } from '@angular/core';
import { AirportService } from 'src/app/services/airport/airport.service';
import { CityTagService } from 'src/app/services/cityTag/city-tag.service';

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

  constructor(private cityTagService:CityTagService, private airportService:AirportService) { }

  ngOnInit(): void {
    this.getTagsForCurrentCity();
    this.getAirportsForCurrentCity();
  }

  getTagsForCurrentCity(){
    this.cityTagService.getTagsForCurrentCity(this.city['cityId']).subscribe((response:any) => {
      this.tags = response;
    })
  }

  getAirportsForCurrentCity(){
    this.airportService.getAllAirportsForGivenCity(this.city['cityId']).subscribe((response:any) => {
      console.log(response);
      this.airports = response;
    })
  }
}
