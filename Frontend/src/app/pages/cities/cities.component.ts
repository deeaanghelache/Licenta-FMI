import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { CityService } from 'src/app/services/city/city.service';
import { CityListService } from 'src/app/services/cityList/city-list.service';
import { TagService } from 'src/app/services/tag/tag.service';
import { UserService } from 'src/app/services/user/user.service';

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

  constructor(private tagService: TagService, private cityService: CityService, private userService:UserService, private cityListService:CityListService) { }

  ngOnInit(): void {
    this.checkIfLoggedIn();
    this.getEmail();
    this.getUserByEmail(this.currentEmail);
    this.checkIfAdmin();
    this.getAllTags();
    this.getAllCities();
  }

  getEmail(){
    this.currentEmail = sessionStorage.getItem("loggedUserEmail") as string;
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

  getUserByEmail(email:string){
    this.userService.getUserByEmail(email).subscribe((response:any) => {
      this.currentId = response.userId;
      this.currentUser = response;
      this.getFavourite(this.currentId);
    })
  }

  addFavourite(city:any) {
    console.log(city.cityId);
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
}
