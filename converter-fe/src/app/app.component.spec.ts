import { TestBed, async, ComponentFixture, inject } from '@angular/core/testing';
import { Router } from '@angular/router';

import { AppComponent } from './app.component';

class MockRouter {
  navigateByUrl(url: string) { return url; }
}

describe('AppComponent', () => {
  let app: AppComponent;
  let fixture: ComponentFixture<AppComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        AppComponent
      ],
      providers: [
        { provide: Router, useClass: MockRouter }
    ]
    }).compileComponents().then(() => {
      fixture = TestBed.createComponent(AppComponent);
      app = fixture.componentInstance;
    });
  }));

  it('should create the app', () => {
    expect(app).toBeTruthy();
  });   

  it(`should have as title 'converter-fe'`, () => {
    expect(app.title).toEqual('converter-fe');
  });
});
