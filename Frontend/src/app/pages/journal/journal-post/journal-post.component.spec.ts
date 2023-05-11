import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JournalPostComponent } from './journal-post.component';

describe('JournalPostComponent', () => {
  let component: JournalPostComponent;
  let fixture: ComponentFixture<JournalPostComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ JournalPostComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(JournalPostComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
