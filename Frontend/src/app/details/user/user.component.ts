import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {
  @Input() firstName: string = '';
  @Input() lastName: string = '';
  @Input() email: string = '';
  @Input() username: string = '';
  
  @Output() onDelete: EventEmitter<any> = new EventEmitter<any>();

  constructor(private userService: UserService) { }

  ngOnInit(): void {
  }

  deleteUserByEmail(email: string){
    this.userService.deleteUserByEmail(email).subscribe((response:any) => {
      window.location.reload();
    })
  }

}
