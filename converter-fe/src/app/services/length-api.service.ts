import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { ConversionResponse, ConversionResponseAdapter } from '../models/conversion-response';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LengthApiService {
  baseUrl:string = environment.baseUrl + '/length';

  constructor(private http:HttpClient, private adapter:ConversionResponseAdapter) { }

  getUnits() : Observable<string[]> {
    return this.http.get<string[]>(this.baseUrl + '/units');
  }

  getConversions(unit:string, value:number) : Observable<ConversionResponse[]> {
    var params = new HttpParams().set('unit', unit).set('value', value.toString());

    return this.http.get(this.baseUrl, {params})
                    .pipe(map((data: any[]) => data.map(conversion => this.adapter.adapt(conversion))));
  }  
}
