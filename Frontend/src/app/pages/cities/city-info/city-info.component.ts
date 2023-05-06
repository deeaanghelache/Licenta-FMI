import { Component, Input, OnInit } from '@angular/core';
import { CityTagService } from 'src/app/services/cityTag/city-tag.service';

@Component({
  selector: 'app-city-info',
  templateUrl: './city-info.component.html',
  styleUrls: ['./city-info.component.scss']
})
export class CityInfoComponent implements OnInit {
  public tags = [];

  @Input() city: any;
  @Input() favourites: any;
  @Input() loggedUser: any;

  constructor(private cityTagService:CityTagService) { }

  ngOnInit(): void {
    this.getTagsForCurrentCity();
  }

  getTagsForCurrentCity(){
    this.cityTagService.getTagsForCurrentCity(this.city['cityId']).subscribe((response:any) => {
      this.tags = response;
    })
  }

  // Solved scrolling bug with: https://stackoverflow.com/questions/46658522/how-to-smooth-scroll-to-page-anchor-in-angular-4-without-plugins-properly
  jumpToSection($element: any): void {
    console.log($element);
    $element.scrollIntoView({behavior: "smooth", block: "start", inline: "nearest"});
  }
}
