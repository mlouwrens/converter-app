import { Component, OnInit, OnChanges } from '@angular/core';
import { TempApiService } from '../../services/temp-api.service';
import { ConversionResponse } from '../../models/conversion-response';

@Component({
  selector: 'app-temperature',
  templateUrl: './temperature.component.html',
  styleUrls: ['./temperature.component.css']
})
export class TemperatureComponent implements OnInit, OnChanges {
  units:string[];
  unit:string;
  value:number;
  result:ConversionResponse[];

  constructor(private tempApiService:TempApiService) { }

  ngOnInit() {
    this.value = 1;

    this.ngOnChanges();
  }

  ngOnChanges() {
    this.tempApiService.getUnits().subscribe(units => {this.units = units},
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
        this.tempApiService.getConversions(this.unit, this.value).subscribe(result => {this.result = result});
      }    
  }  
}
