import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent implements OnInit {
  public users: any[] = [];
  public usersThatAreNotAdmins: any[] = [];
  public usersThatAreAdmins: any[] = [];
  public admin:boolean = false;
  public logged:boolean = false;


  constructor(private activatedRoute: ActivatedRoute, private userService:UserService, private router:Router) { }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe((params: any) => {
    });
    this.activatedRoute.queryParams.subscribe((queryParams: any) => {
    });
    this.getAllUsersFromDatabase();
    this.getAllAdmins();
    this.getAllUsers();
    this.checkIfLoggedIn();
  }

  checkIfLoggedIn(){
    if ("loggedUserEmail" in sessionStorage){
      this.logged = true;
    }
  }

  getAllUsersFromDatabase(){
    this.userService.getAllUsers().subscribe((response:any) => {
      // response is any array of users
      this.users = response;
    })
  }

  getAllUsers():void {
    // Users that are not admin
    this.userService.getAllUsersForAGivenRole(2).subscribe((response:any) => {
      this.usersThatAreNotAdmins = response;
    })
  }

  getAllAdmins():void{
    this.userService.getAllUsersForAGivenRole(1).subscribe((response:any) => {
      this.usersThatAreAdmins = response;
    })
  }

  logout(){
    sessionStorage.clear();
    this.admin = false;
    this.logged = false;
    this.router.navigateByUrl("/homepage");
  }
}
