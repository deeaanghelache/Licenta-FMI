import { Component, OnInit } from '@angular/core';
import { CityService } from 'src/app/services/city/city.service';
import { TagService } from 'src/app/services/tag/tag.service';

@Component({
  selector: 'app-cities',
  templateUrl: './cities.component.html',
  styleUrls: ['./cities.component.scss']
})
export class CitiesComponent implements OnInit {
  public admin:boolean = false;
  public logged:boolean = false;
  // public cities = [];
  public currentCity: any;
  public cities = [
    { name: "Paris", image: "", currency: "euro", country: "France"},
    { name: "Paris", image: "", currency: "euro", country: "France"},
    { name: "Paris111", image: "", currency: "euro", country: "France"},
    { name: "Paris", image: "", currency: "euro", country: "France"},
    { name: "Paris", image: "", currency: "euro", country: "France"},    
    { name: "Paris", image: "", currency: "euro", country: "France"},
    { name: "Paris", image: "", currency: "euro", country: "France"},
    { name: "Paris", image: "", currency: "euro", country: "France"},
    { name: "Paris", image: "", currency: "euro", country: "France"},
    { name: "Paris", image: "", currency: "euro", country: "France"},
    { name: "Paris", image: "", currency: "euro", country: "France"},    
    { name: "Paris", image: "", currency: "euro", country: "France"}
  ];
  public tags = [];
  public display: boolean = false;

  constructor(private tagService: TagService, private cityService: CityService) { }

  ngOnInit(): void {
    this.checkIfLoggedIn();
    this.checkIfAdmin();
    this.getAllTags();
    // this.getAllCities();
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

  getAllTags(){
    this.tagService.getAllTags().subscribe((response:any) => {
      this.tags = response;
      console.log(this.tags);
    })
  }

  // NETESTAT
  getAllCities(){
    this.cityService.getAllCities().subscribe((response:any) => {
      console.log(response);
      this.cities = response;
    })
  }

  showCityInfos(city:any){
    this.display = true;
    this.currentCity = city;
  }

  closeCityInfos(){
    this.display = false;
  }
}
