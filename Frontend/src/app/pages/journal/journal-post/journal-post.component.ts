import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-journal-post',
  templateUrl: './journal-post.component.html',
  styleUrls: ['./journal-post.component.scss']
})
export class JournalPostComponent implements OnInit {
  @Input() post:any;

  constructor() { }

  ngOnInit(): void {
  }

}
