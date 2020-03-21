import { TestBed, async, inject } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { TempApiService } from './temp-api.service';

describe('TempApiService', () => {
  let service: TempApiService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({ 
      imports: [
        HttpClientTestingModule
      ] 
    });
    service = TestBed.inject(TempApiService);
    httpMock = TestBed.get(HttpTestingController);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should fetch unit types as an Observable', async(inject([HttpTestingController, TempApiService],
    (httpClient: HttpTestingController, service: TempApiService) => {

      const conversionItem = [
        "TempUnit1", "TempUnit2"
      ];

      service.getUnits()
        .subscribe((unitTypes: any) => {
          expect(unitTypes.length).toBe(2);
        });

      let req = httpMock.expectOne(service.baseUrl + '/units');
      expect(req.request.method).toBe("GET");

      req.flush(conversionItem);
      httpMock.verify();

    })));    

  it('should fetch conversions as an Observable', async(inject([HttpTestingController, TempApiService],
    (httpClient: HttpTestingController, service: TempApiService) => {

      const conversionItem = [
        {
          "unit": "TempUnit1",
          "value": 1
        },
        {
          "unit": "TempUnit2",
          "value": 2
        }
      ];


      service.getConversions("unitFrom", 1.0)
        .subscribe((conversions: any) => {
          expect(conversions.length).toBe(3);
        });

      let req = httpMock.expectOne(service.baseUrl);
      expect(req.request.method).toBe("GET");

      req.flush(conversionItem);
      httpMock.verify();

    })));    
});
