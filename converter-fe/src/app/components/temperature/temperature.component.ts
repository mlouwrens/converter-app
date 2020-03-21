import { Component, OnInit, OnChanges, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';

import { TempApiService } from '../../services/temp-api.service';
import { ConversionResponse } from '../../models/conversion-response';

@Component({
  selector: 'app-temperature',
  templateUrl: './temperature.component.html',
  styleUrls: ['./temperature.component.css']
})
export class TemperatureComponent implements OnInit, OnDestroy {
  units:string[];
  unit:string;
  value:number;
  result:ConversionResponse[];
  subscription:Subscription = new Subscription();

  constructor(private tempApiService:TempApiService) { }

  ngOnInit() {
    this.value = 1;

    const subscription = this.tempApiService.getUnits().subscribe(units => {this.units = units},
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
        const subscription = this.tempApiService.getConversions(this.unit, this.value).subscribe(result => {this.result = result});

        this.subscription.add(subscription);
      }    
  }  
}
