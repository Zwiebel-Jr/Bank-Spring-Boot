import { Component, OnInit } from './core';
import {ActivatedRoute, Router} from "./router";
import {Operation} from "../../shared/models/operation";
import {CreditsService} from "../../core/credits.service";
import * as Highcharts from "highcharts";

//@Component({
 // selector: 'app-credit',
//  templateUrl: './credit.component.html',
//  styleUrls: ['./credit.component.scss']
//})
class CreditComponent implements OnInit {

  credit: Operation = {};
  calculatedValues: number[];
  highcharts = Highcharts;
  chartOptions: any;

  constructor(private router: Router, private route: ActivatedRoute,
              private creditsService: CreditsService) { }

  ngOnInit() {
    this.creditsService.getCredit(+this.route.snapshot.paramMap.get('id')).subscribe((credit: Operation) => {
      this.credit = credit;
      this.getValues(credit.id);
    });
  }

  getValues(id: number) {
    this.creditsService.getCalculatedCredit(id).subscribe(calculatedValues => {
      this.calculatedValues = calculatedValues;
      this.chartOptions = {
        chart: {
          type: "spline"
        },
        title: {
          text: "Сумма По Месяцам"
        },
        yAxis: {
          title:{
            text: this.credit.type.currency
          }
        },
        tooltip: {
          valueSuffix: this.credit.type.currency
        },
        series: [
          {
            name: "Тело Кредита",
            data: this.calculatedValues["Body"]
          },
          {
            name: "Проценты по Кредиту",
            data: this.calculatedValues["Percent"]
          }
        ]
      };
    })
  }
}
