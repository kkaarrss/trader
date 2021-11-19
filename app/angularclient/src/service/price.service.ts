import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {Price} from "../app/price";

@Injectable({
  providedIn: 'root'
})
export class PriceService {
  private priceUrl: string;

  constructor(private http: HttpClient) {
    this.priceUrl = 'http://localhost:8080/price';
  }

  public findAll(): Observable<Price[]> {
    prices: new Observable<Price[]>();
    let prices = this.http.get<Price[]>(this.priceUrl);
    console.log('hallo');
    return prices;
  }
}
