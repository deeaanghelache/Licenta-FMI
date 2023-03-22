import { TestBed } from '@angular/core/testing';

import { CityTagService } from './city-tag.service';

describe('CityTagService', () => {
  let service: CityTagService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CityTagService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
