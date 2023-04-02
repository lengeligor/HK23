import { Component, OnInit } from '@angular/core';
import { Chart, registerables, ArcElement } from '../../../node_modules/chart.js/';


import { DoughnutController } from '../../../node_modules/chart.js/';
Chart.register(DoughnutController); // Register the doughnut 
Chart.register(LineController); // Register the doughnut controller
import { CategoryScale } from 'chart.js';

Chart.register(LinearScale);
Chart.register(CategoryScale);
Chart.register(ArcElement);
Chart.register(PointElement);
import { LineController, LinearScale, PointElement, LineElement } from 'chart.js';

Chart.register(LineController, LinearScale, PointElement, LineElement);



@Component({
  selector: 'app-main-content',
  templateUrl: './main-content.component.html',
  styleUrls: ['./main-content.component.scss']
})
export class MainContentComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
    const canvas = <HTMLCanvasElement>document.getElementById('donutChart');
    const ctx = canvas.getContext('2d');

    const data = {
      labels: ['Label 1', 'Label 2', 'Label 3'],
      datasets: [
        {
          data: [10, 20, 30],
          backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56'],
          hoverBackgroundColor: ['#FF6384', '#36A2EB', '#FFCE56'],
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
    const myChart = new Chart(ctx2, {
      type: 'line',
      data: {
        
        labels: ['Jan', 'Feb', 'Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'],
        datasets: [{
          label: 'My First Dataset',
          data: [5, 10, 11,12,13,14,15,14,13,12,11,7,6,3],
          borderColor: 'rgb(75, 192, 192)',
          tension: 0.1
        }]
      }
    });

    


  }
 

 
  }


