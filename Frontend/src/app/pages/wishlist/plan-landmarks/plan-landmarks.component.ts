import { Component, Input, OnInit } from '@angular/core';
// import { CdkDragDrop, moveItemInArray } from '@angular/cdk/drag-drop';

@Component({
  selector: 'app-plan-landmarks',
  templateUrl: './plan-landmarks.component.html',
  styleUrls: ['./plan-landmarks.component.scss']
})
export class PlanLandmarksComponent implements OnInit {
  @Input() chosenCity: any;

  constructor() { }

  ngOnInit(): void {
  }

}
