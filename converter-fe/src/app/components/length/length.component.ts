import { Component, OnInit, OnChanges } from '@angular/core';
import { LengthApiService } from '../../services/length-api.service';
import { ConversionResponse } from '../../models/conversion-response';

@Component({
  selector: 'app-length',
  templateUrl: './length.component.html',
  styleUrls: ['./length.component.css']
})
export class LengthComponent implements OnInit, OnChanges {
  units:string[];
  unit:string;
  value:number;
  result:ConversionResponse[];

  constructor(private lengthApiService:LengthApiService) { }

  ngOnInit() {
    this.value = 1;

    this.ngOnChanges();
  }

  ngOnChanges() {
    this.lengthApiService.getUnits().subscribe(units => {this.units = units},
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
        this.lengthApiService.getConversions(this.unit, this.value).subscribe(result => {this.result = result});
      }    
  }  

}
