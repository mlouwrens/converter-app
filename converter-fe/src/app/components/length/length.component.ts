import { Component, OnInit, OnChanges, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';

import { LengthApiService } from '../../services/length-api.service';
import { ConversionResponse } from '../../models/conversion-response';

@Component({
  selector: 'app-length',
  templateUrl: './length.component.html',
  styleUrls: ['./length.component.css']
})
export class LengthComponent implements OnInit, OnDestroy {
  units:string[];
  unit:string;
  value:number;
  result:ConversionResponse[];
  subscription:Subscription = new Subscription();

  constructor(private lengthApiService:LengthApiService) { }

  ngOnInit() {
    this.value = 1;

    const subscription = this.lengthApiService.getUnits().subscribe(units => {this.units = units},
      error => console.log("Error: ", error),
      () => {this.unitsLoaded()});

      this.subscription.add(subscription);
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }  

  // Set default selected value
  unitsLoaded(){
    if (this.units != null && 
      this.units.length > 0){
        this.unit = this.units[0];
        
        this.getResult();
    }    
  }
  
  // Get result if value and unit
  getResult(){
    if (this.value &&
      this.unit){                
        const subscription = this.lengthApiService.getConversions(this.unit, this.value).subscribe(result => {this.result = result});

        this.subscription.add(subscription);
      }    
  }  

}
