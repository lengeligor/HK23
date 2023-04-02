import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import {
  Chart,
  registerables,
  ArcElement,
} from '../../../node_modules/chart.js/';

import { DoughnutController } from '../../../node_modules/chart.js/';
import { MainServiceService } from '../shared/main-service.service';
import { UserReport } from '../shared/models/userReport.model';
Chart.register(DoughnutController); // Register the doughnut controller

Chart.register(ArcElement);

@Component({
  selector: 'app-main-content',
  templateUrl: './main-content.component.html',
  styleUrls: ['./main-content.component.scss'],
})
export class MainContentComponent implements OnInit {
  userReport$: UserReport;

  constructor(private mainService: MainServiceService) {}

  ngOnInit(): void {
    const data = {
      labels: ['Red', 'Blue', 'Yellow'],
      datasets: [
        {
          label: 'My First Dataset',
          data: [300, 50, 100],
          backgroundColor: [
            'rgb(255, 99, 132)',
            'rgb(54, 162, 235)',
            'rgb(255, 205, 86)',
          ],
          hoverOffset: 4,
        },
      ],
    };

    const myChart = new Chart('myChart', {
      type: 'doughnut',
      data: data,
    });

    this.mainService.currentUserReportState$.subscribe((report) => {
      this.userReport$ = report;
      if (this.userReport$)
        console.log('from main component:' + this.userReport$.message);
    });
  }
}
