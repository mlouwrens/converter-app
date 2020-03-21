import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { LengthComponent } from './length.component';
import { LengthApiService } from '../../services/length-api.service';
 
class MockResponse {


}

describe('LengthComponent', () => {
  let component: LengthComponent;
  let fixture: ComponentFixture<LengthComponent>;
  let apiService: LengthApiService;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LengthComponent ],
      providers: [ LengthApiService ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LengthComponent);
    apiService = TestBed.get(LengthApiService);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
