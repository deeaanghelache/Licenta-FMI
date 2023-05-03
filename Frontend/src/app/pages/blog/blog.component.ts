import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-blog',
  templateUrl: './blog.component.html',
  styleUrls: ['./blog.component.scss']
})
export class BlogComponent implements OnInit {
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
