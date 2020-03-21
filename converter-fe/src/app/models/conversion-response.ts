import { Injectable } from "@angular/core";
import { Adapter } from "./adapter";

export class ConversionResponse {
    constructor(
      public unit:string,
      public value:number
    ) {}
  }

  @Injectable({
    providedIn: "root"
  })
  export class ConversionResponseAdapter implements Adapter<ConversionResponse> {
    adapt(conversion: any): ConversionResponse {
      return new ConversionResponse(conversion.unit, conversion.value);
    }
  }  