import { Component, OnInit, OnChanges } from '@angular/core';
import { WeightApiService } from '../../services/weight-api.service';
import { ConversionResponse } from '../../models/conversion-response';

@Component({
  selector: 'app-weight',
  templateUrl: './weight.component.html',
  styleUrls: ['./weight.component.css']
})
export class WeightComponent implements OnInit, OnChanges {
  units:string[];
  unit:string;
  value:number;
  result:ConversionResponse[];

  constructor(private weightApiService:WeightApiService) { }

  ngOnInit() {
    this.value = 1;

    this.ngOnChanges();
  }

  ngOnChanges() {
    this.weightApiService.getUnits().subscribe(units => {this.units = units},
      error => console.log("Error: ", error),
      () => {this.unitsLoaded()});
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
        this.weightApiService.getConversions(this.unit, this.value).subscribe(result => {this.result = result});
      }    
  } 
}
