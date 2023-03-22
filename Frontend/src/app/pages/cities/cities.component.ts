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
  public cities = [];
  public currentCity: any;
  public currentTagForFilter: number = 0;
  // public cities = [
  //   { name: "Paris", image: "", currency: "euro", country: "France"},
  //   { name: "Paris", image: "", currency: "euro", country: "France"},
  //   { name: "Paris111", image: "", currency: "euro", country: "France"},
  //   { name: "Paris", image: "", currency: "euro", country: "France"},
  //   { name: "Paris", image: "", currency: "euro", country: "France"},    
  //   { name: "Paris", image: "", currency: "euro", country: "France"},
  //   { name: "Paris", image: "", currency: "euro", country: "France"},
  //   { name: "Paris", image: "", currency: "euro", country: "France"},
  //   { name: "Paris", image: "", currency: "euro", country: "France"},
  //   { name: "Paris", image: "", currency: "euro", country: "France"},
  //   { name: "Paris", image: "", currency: "euro", country: "France"},    
  //   { name: "Paris", image: "", currency: "euro", country: "France"}
  // ];
  public tags = [];
  public display: boolean = false;
  public displayTags: boolean = true;

  constructor(private tagService: TagService, private cityService: CityService) { }

  ngOnInit(): void {
    this.checkIfLoggedIn();
    this.checkIfAdmin();
    this.getAllTags();
    this.getAllCities();
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
    })
  }

  getAllCities(){
    this.cityService.getAllCities().subscribe((response:any) => {
      this.cities = response;
    })
  }

  showCityInfos(city:any){
    this.display = true;
    this.displayTags = false;
    console.log(this.displayTags);
    this.currentCity = city;
  }

  closeCityInfos(){
    this.display = false;
    this.displayTags = true;
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
}
