import { TestBed } from '@angular/core/testing';

import { CityRatingService } from './city-rating.service';

describe('CityRatingService', () => {
  let service: CityRatingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CityRatingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
