import { TestBed, async, inject } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { LengthApiService } from './length-api.service';

describe('LengthApiService', () => {
  let service: LengthApiService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({ 
      imports: [
        HttpClientTestingModule
      ],
      providers: [
        LengthApiService
      ]
    });
    service = TestBed.get(LengthApiService);
    httpMock = TestBed.get(HttpTestingController);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should fetch unit types as an Observable', async(inject([HttpTestingController, LengthApiService],
    (httpClient: HttpTestingController, service: LengthApiService) => {

      const conversionItem = [
        "LengthUnit1", "LengthUnit2", "LengthUnit3"
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

  it('should fetch conversions as an Observable', async(inject([HttpTestingController, LengthApiService],
    (httpClient: HttpTestingController, service: LengthApiService) => {

      const conversionItem = [
        {
          "unit": "LengthUnit1",
          "value": 1
        },
        {
          "unit": "LengthUnit2",
          "value": 2
        },
        {
          "unit": "LengthUnit3",
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
