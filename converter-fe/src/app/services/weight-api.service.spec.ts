import { TestBed, async, inject } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { WeightApiService } from './weight-api.service';

describe('WeightApiService', () => {
  let service: WeightApiService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({ 
      imports: [
        HttpClientTestingModule
      ],
      providers: [
        WeightApiService
      ],
    });
    service = TestBed.get(WeightApiService);
    httpMock = TestBed.get(HttpTestingController);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should fetch unit types as an Observable', async(inject([HttpTestingController, WeightApiService],
    (httpClient: HttpTestingController, service: WeightApiService) => {

      const conversionItem = [
        "WeightUnit1", "WeightUnit2", "WeightUnit3"
      ];

      service.getUnits()
        .subscribe((unitTypes: any) => {
          expect(unitTypes.length).toBe(3);
        });

      let req = httpMock.expectOne(service.baseUrl + '/units');
      expect(req.request.method).toBe("GET");

      req.flush(conversionItem);
      httpMock.verify();

    })));    

  it('should fetch conversions as an Observable', async(inject([HttpTestingController, WeightApiService],
    (httpClient: HttpTestingController, service: WeightApiService) => {

      const conversionItem = [
        {
          "unit": "WeightUnit1",
          "value": 1
        },
        {
          "unit": "WeightUnit2",
          "value": 2
        },
        {
          "unit": "WeighthUnit3",
          "value": 3
        }
      ];


      service.getConversions("unitFrom", 1.0)
        .subscribe((conversions: any) => {
          expect(conversions.length).toBe(3);
        });

      let req = httpMock.expectOne(service.baseUrl + '?unit=unitFrom&value=1');
      expect(req.request.method).toBe("GET");

      req.flush(conversionItem);
      httpMock.verify();

    })));    
});
