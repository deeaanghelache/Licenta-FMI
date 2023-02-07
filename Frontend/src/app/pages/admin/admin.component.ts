import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent implements OnInit {
  public users: any[] = [];

  constructor(private activatedRoute: ActivatedRoute, private userService:UserService) { }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe((params: any) => {
      console.log('params', params);
    });
    this.activatedRoute.queryParams.subscribe((queryParams: any) => {
      console.log('queryParams', queryParams);
    });
    this.getAllUsersFromDatabase();
  }

  getAllUsersFromDatabase(){
    this.userService.getAllUsers().subscribe((response:any) => {
      // response is any array of users
      this.users = response;
    })
  }
}
