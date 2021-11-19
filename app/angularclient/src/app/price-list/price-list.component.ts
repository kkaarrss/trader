import { Component, OnInit } from '@angular/core';
import {PriceService} from "../../service/price.service";
import {Price} from "../price";
import {timer} from "rxjs";
import {switchMap} from "rxjs/operators";

@Component({
  selector: 'app-price-list',
  templateUrl: './price-list.component.html',
  styleUrls: ['./price-list.component.css']
})
export class PriceListComponent implements OnInit {
  prices!: Price[];
  previousPrices!: Price[];

  constructor(private priceService: PriceService) {
  }

  ngOnInit(): void {
    timer(0, 2000).pipe(switchMap(() => this.priceService.findAll())).subscribe(data => {
      this.previousPrices = this.prices;
      this.prices = data;
    });
  }

}
