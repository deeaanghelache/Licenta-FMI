import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchCityBarComponent } from './search-city-bar.component';

describe('SearchCityBarComponent', () => {
  let component: SearchCityBarComponent;
  let fixture: ComponentFixture<SearchCityBarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchCityBarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchCityBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
