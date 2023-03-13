import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-cities',
  templateUrl: './cities.component.html',
  styleUrls: ['./cities.component.scss']
})
export class CitiesComponent implements OnInit {
  public admin:boolean = false;
  public logged:boolean = false;
  // public cities = [];
  public cities = [
    { name: "Paris", image: "", currency: "euro", country: "France"},
    { name: "Paris", image: "", currency: "euro", country: "France"},
    { name: "Paris", image: "", currency: "euro", country: "France"},
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

  constructor() { }

  ngOnInit(): void {
    this.checkIfLoggedIn();
    this.checkIfAdmin();

    // TODO: get all cities from database
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

}
