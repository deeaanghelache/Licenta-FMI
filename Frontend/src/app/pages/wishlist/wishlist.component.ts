import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-wishlist',
  templateUrl: './wishlist.component.html',
  styleUrls: ['./wishlist.component.scss']
})
export class WishlistComponent implements OnInit {
  public logged:boolean = false;
  public admin:boolean = false;

  constructor() { }

  ngOnInit(): void {
    this.checkIfLoggedIn();
    this.checkIfAdmin();
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
