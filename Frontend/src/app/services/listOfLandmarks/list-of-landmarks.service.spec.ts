import { TestBed } from '@angular/core/testing';

import { ListOfLandmarksService } from './list-of-landmarks.service';

describe('ListOfLandmarksService', () => {
  let service: ListOfLandmarksService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ListOfLandmarksService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
