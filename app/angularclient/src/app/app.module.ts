import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { PriceListComponent } from './price-list/price-list.component';
import {PriceService} from "../service/price.service";
import {HttpClientModule} from "@angular/common/http";

@NgModule({
  declarations: [
    AppComponent,
    PriceListComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule
  ],
  providers: [PriceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
