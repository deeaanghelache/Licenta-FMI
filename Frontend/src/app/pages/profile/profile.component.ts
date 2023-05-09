import { Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  public admin:boolean = false;
  public logged:boolean = false;
  public currentFirstName:string = '';
  public currentLastName:string = '';
  public currentId:number = 0;
  public currentEmail:string = '';
  public currentUsername: string = '';
  public currentPhoto:string = '';
  public images = [
    "../../../assets/photos/pexels-anastasiya-vragova-6791741.jpg",
    "../../../assets/photos/pexels-esrageziyor-7473041.jpg",
    "../../../assets/photos/pexels-guillaume-hankenne-2792025.jpg",
    "../../../assets/photos/pexels-nicolas-2925146.jpg",
    "../../../assets/photos/pexels-spencer-davis-4353813.jpg",
    "../../../assets/photos/pexels-anastasiya-vragova-6791741.jpg",
    "../../../assets/photos/pexels-esrageziyor-7473041.jpg",
    "../../../assets/photos/pexels-guillaume-hankenne-2792025.jpg",
    "../../../assets/photos/pexels-nicolas-2925146.jpg",
    "../../../assets/photos/pexels-spencer-davis-4353813.jpg",
  ];

  constructor(private router: Router, private userService: UserService) { }

  getUsername(){
    this.currentUsername = sessionStorage.getItem("username") as string;
  }

  getEmail(){
    this.currentEmail = sessionStorage.getItem("loggedUserEmail") as string;
  }

  ngOnInit(): void {
    this.getUsername();
    this.getEmail();
    this.checkIfLoggedIn();
    this.checkIfAdmin();
    this.getUserByEmail(this.currentEmail);
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

  getUserByEmail(email:string){
    this.userService.getUserByEmail(email).subscribe((response:any) => {
      this.currentId = response.userId;
      this.currentFirstName = response.firstName;
      this.currentLastName = response.lastName;
      this.currentPhoto = response.photo;
      this.currentUsername = response.username;
    })
  }

  logout(){
    sessionStorage.clear();
    this.admin = false;
    this.logged = false;
    this.router.navigateByUrl("/homepage");
  }
}
