import { Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  public currentUsername: string = '';
  public admin:boolean = false;
  public logged:boolean = false;

  constructor(private router: Router) { }

  getUsername(){
    this.currentUsername = sessionStorage.getItem("username") as string;
  }

  ngOnInit(): void {
    this.getUsername();
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
    this.router.navigateByUrl("/homepage");
  }
}
