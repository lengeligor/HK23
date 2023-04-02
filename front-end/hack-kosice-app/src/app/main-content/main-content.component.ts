import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import {
  Chart,
  registerables,
  ArcElement,
} from '../../../node_modules/chart.js/';

import { DoughnutController } from '../../../node_modules/chart.js/';
Chart.register(DoughnutController); // Register the doughnut
Chart.register(LineController); // Register the doughnut controller
import { CategoryScale } from 'chart.js';

Chart.register(LinearScale);
Chart.register(CategoryScale);
Chart.register(ArcElement);
Chart.register(PointElement);
import {
  LineController,
  LinearScale,
  PointElement,
  LineElement,
} from 'chart.js';
import { UserReport } from '../shared/models/userReport.model';
import { MainServiceService } from '../shared/main-service.service';
import { Transaction } from '../shared/models/transaction.model';

Chart.register(LineController, LinearScale, PointElement, LineElement);

@Component({
  selector: 'app-main-content',
  templateUrl: './main-content.component.html',
  styleUrls: ['./main-content.component.scss'],
})
export class MainContentComponent implements OnInit {
  userReport$: UserReport;
  userYearBalance$: { key: string; value: number }[];

  transactionList$: Transaction[];

  constructor(private mainService: MainServiceService) {}

  ngOnInit(): void {
    this.mainService.currentUserReportState$.subscribe((report) => {
      this.userReport$ = report;
    });

    this.mainService.currentUserYearBalanceState$.subscribe((yearBalance) => {
      this.userYearBalance$ = yearBalance;
    });

    this.mainService.currentTransactionListState$.subscribe((transitions) => {
      this.transactionList$ = transitions;
    });

    const canvas = <HTMLCanvasElement>document.getElementById('donutChart');
    const ctx = canvas.getContext('2d');

    var data = {
      labels: ['Label 1', 'Label 2', 'Label 3'],
      datasets: [
        {
          data: [
            this.userReport$.utilities,
            this.userReport$.shopping,
            this.userReport$.food,
            this.userReport$.transportation,
          ],
          backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56', '#6756ff'],
          hoverBackgroundColor: ['#FF6384', '#36A2EB', '#FFCE56', '#6756ff'],
        },
      ],
    };

    const options = {
      responsive: true,
      maintainAspectRatio: false,
      legend: {
        position: 'right',
      },
    };

    const donutChart = new Chart(ctx, {
      type: 'doughnut',
      data: data,
      options: options,
    });

    const ctx2 = document.getElementById('lineChart') as HTMLCanvasElement;
    ctx2.height = 200;

    const keys = Object.keys(this.userYearBalance$);

    keys.sort((a, b) => {
      if (a < b) {
        return -1;
      } else if (a > b) {
        return 1;
      } else {
        return 0;
      }
    });

    const values = keys.map((key) => this.userYearBalance$[key]);
    const lastValue: number = values.slice(-1)[0];

    const myChart = new Chart(ctx2, {
      type: 'line',
      data: {
        labels: keys,
        datasets: [
          {
            label: 'My First Dataset',
            data: values,
            borderColor: this.getChartColor(lastValue),
            tension: 0.1,
          },
        ],
      },
    });
  }

  getChartColor(lastValue: number): string {
    if (lastValue < 0) {
      return 'red';
    }
    if (lastValue > 4000) {
      return 'green';
    }
    return 'orange';
  }
}
