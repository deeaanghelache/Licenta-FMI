import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JournalComponent } from './journal.component';

describe('BlogComponent', () => {
  let component: JournalComponent;
  let fixture: ComponentFixture<JournalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ JournalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(JournalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
