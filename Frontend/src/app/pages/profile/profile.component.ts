import { Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  currentUsername: string = '';

  constructor() { }

  getUsername(){
    this.currentUsername = sessionStorage.getItem("username") as string;
  }

  ngOnInit(): void {
    this.getUsername();
  }

}
