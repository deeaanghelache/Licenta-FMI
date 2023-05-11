import { TestBed } from '@angular/core/testing';

import { LoggedUserGuardGuard } from './logged-user-guard.guard';

describe('LoggedUserGuardGuard', () => {
  let guard: LoggedUserGuardGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(LoggedUserGuardGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
