import { Component, OnInit } from '@angular/core';
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
  public roadtrip = false;
  public planLandmarks = false;

  constructor(private cityService:CityService, private userService:UserService, private cityListService:CityListService) { }

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

  openRoadtrip(city:any){
    this.roadtrip = true;
    this.chosenCity = city;
  }

  closeRoadtrip(){
    this.roadtrip = false;
  }

  logout(){
    sessionStorage.clear();
    this.admin = false;
    this.logged = false;
  }
}
