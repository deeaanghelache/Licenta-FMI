import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent implements OnInit {
  public admin:boolean = false;
  public logged:boolean = false;
  public usersActive:boolean = false;
  public airportsActive:boolean = false;
  public citiesActive:boolean = false;
  public tagsActive:boolean = false;
  public landmarksActive:boolean = false;

  constructor(private activatedRoute: ActivatedRoute, private userService:UserService, private router:Router) { }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe((params: any) => {
    });
    this.activatedRoute.queryParams.subscribe((queryParams: any) => {
    });
    this.checkIfLoggedIn();
  }

  checkIfLoggedIn(){
    if ("loggedUserEmail" in sessionStorage){
      this.logged = true;
    }
  }

  setUsersActive(){
    if (this.usersActive === false){
      this.usersActive = true;
      this.tagsActive = false;
      this.airportsActive = false;
      this.landmarksActive = false;
      this.citiesActive = false;
    }
    else {
      this.usersActive = false;
    }
  }

  setAirportsActive(){
    if (this.airportsActive === false){
      this.usersActive = false;
      this.tagsActive = false;
      this.airportsActive = true;
      this.landmarksActive = false;
      this.citiesActive = false;
    }
    else {
      this.airportsActive = false;
    }
  }

  setLandmarksActive(){
    if (this.landmarksActive === false){
      this.usersActive = false;
      this.tagsActive = false;
      this.airportsActive = false;
      this.landmarksActive = true;
      this.citiesActive = false;
    }
    else {
      this.landmarksActive = false;
    }

  }

  setTagsActive(){
    if (this.tagsActive === false){
      this.usersActive = false;
      this.tagsActive = true;
      this.airportsActive = false;
      this.landmarksActive = false;
      this.citiesActive = false;
    }
    else {
      this.tagsActive = false;
    }

  }

  setCitiesActive(){
    if (this.citiesActive === false){
      this.usersActive = false;
      this.tagsActive = false;
      this.airportsActive = false;
      this.landmarksActive = false;
      this.citiesActive = true;
    }
    else {
      this.citiesActive = false;
    }
  }

  logout(){
    sessionStorage.clear();
    this.admin = false;
    this.logged = false;
    this.router.navigateByUrl("/homepage");
  }
}
