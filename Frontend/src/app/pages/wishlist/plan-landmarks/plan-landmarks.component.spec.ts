import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PlanLandmarksComponent } from './plan-landmarks.component';

describe('PlanLandmarksComponent', () => {
  let component: PlanLandmarksComponent;
  let fixture: ComponentFixture<PlanLandmarksComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PlanLandmarksComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PlanLandmarksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
