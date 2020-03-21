import { TestBed } from '@angular/core/testing';

import { LengthApiService } from './length-api.service';

describe('LengthApiService', () => {
  let service: LengthApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LengthApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
