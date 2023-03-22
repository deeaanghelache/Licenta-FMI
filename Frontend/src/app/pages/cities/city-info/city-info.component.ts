import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-city-info',
  templateUrl: './city-info.component.html',
  styleUrls: ['./city-info.component.scss']
})
export class CityInfoComponent implements OnInit {
  @Input() city: any;

  constructor() { }

  ngOnInit(): void {
    console.log(this.city)
  }

  // Solved scrolling bug with: https://stackoverflow.com/questions/46658522/how-to-smooth-scroll-to-page-anchor-in-angular-4-without-plugins-properly
  jumpToSection($element: any): void {
    console.log($element);
    $element.scrollIntoView({behavior: "smooth", block: "start", inline: "nearest"});
  }
}
