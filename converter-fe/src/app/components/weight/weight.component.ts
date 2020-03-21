import { Component, OnInit, OnChanges, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';

import { WeightApiService } from '../../services/weight-api.service';
import { ConversionResponse } from '../../models/conversion-response';

@Component({
  selector: 'app-weight',
  templateUrl: './weight.component.html',
  styleUrls: ['./weight.component.css']
})
export class WeightComponent implements OnInit, OnDestroy {
  units:string[];
  unit:string;
  value:number;
  result:ConversionResponse[];
  subscription:Subscription = new Subscription();

  constructor(private weightApiService:WeightApiService) { }

  ngOnInit() {
    this.value = 1;

    const subscription = this.weightApiService.getUnits().subscribe(units => {this.units = units},
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
        const subscription = this.weightApiService.getConversions(this.unit, this.value).subscribe(result => {this.result = result});

        this.subscription.add(subscription);
      }    
  } 
}
