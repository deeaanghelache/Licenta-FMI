import { TestBed } from '@angular/core/testing';

import { JournalPostService } from './journal-post.service';

describe('JournalPostService', () => {
  let service: JournalPostService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(JournalPostService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
