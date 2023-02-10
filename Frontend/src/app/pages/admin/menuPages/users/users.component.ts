import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {
  public users: any[] = [];
  public usersThatAreNotAdmins: any[] = [];
  public usersThatAreAdmins: any[] = [];

  constructor(private activatedRoute: ActivatedRoute, private userService:UserService, private router:Router) { }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe((params: any) => {
    });
    this.activatedRoute.queryParams.subscribe((queryParams: any) => {
    });
    this.getAllUsersFromDatabase();
    this.getAllAdmins();
    this.getAllUsers();
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

}
