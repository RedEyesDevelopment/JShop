import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { SliderComponent } from './slider/slider.component';
import { RestServiceComponent } from './rest-service/rest-service.component';
import {Http, HttpModule} from '@angular/http';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SliderComponent
  ],
  imports: [
    BrowserModule,
    HttpModule
  ],
  providers: [RestServiceComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
