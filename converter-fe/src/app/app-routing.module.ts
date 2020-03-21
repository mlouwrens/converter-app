import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { DefaultComponent } from './components/default/default.component';
import { LengthComponent } from './components/length/length.component';
import { WeightComponent } from './components/weight/weight.component';
import { TemperatureComponent }  from './components/temperature/temperature.component';


const appRoutes: Routes = [  
  { path: 'length', component: LengthComponent },
  { path: 'weight', component: WeightComponent },
  { path: 'temperature', component: TemperatureComponent },
  { path: '**', component: DefaultComponent },
  //{ path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [
    RouterModule.forRoot(
      appRoutes,
      { enableTracing: true } // <-- debugging purposes only
    )
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }
