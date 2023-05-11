import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-page401',
  templateUrl: './page401.component.html',
  styleUrls: ['./page401.component.scss']
})
export class Page401Component implements OnInit {

  constructor(private router:Router) { }

  ngOnInit(): void {
  }

  goHome(){
    this.router.navigateByUrl('/homepage');
  }
}
