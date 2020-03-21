import { Component, OnInit } from '@angular/core';
import { Router, ɵROUTER_PROVIDERS } from '@angular/router';
import { environment } from '../environments/environment';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [ɵROUTER_PROVIDERS]
})
export class AppComponent {
  title = 'converter-fe';

  constructor(private router: Router) { }

  redirect(selectedType:string) {
      this.router.navigateByUrl('/' + selectedType);
  }   
}
