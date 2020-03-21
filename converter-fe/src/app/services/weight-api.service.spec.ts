import { TestBed } from '@angular/core/testing';

import { WeightApiService } from './weight-api.service';

describe('WeightApiService', () => {
  let service: WeightApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WeightApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
