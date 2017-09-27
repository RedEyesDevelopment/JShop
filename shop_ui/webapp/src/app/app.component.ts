import { Component } from '@angular/core';
import {RestServiceComponent} from './rest-service/rest-service.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styles: [`
  body {
    background-color: #f0f0f0;
  }
`]
})
export class AppComponent {

  constructor(private rest: RestServiceComponent) {
    this.rest.post('sf');
  }
}

