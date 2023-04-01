import { Component, OnInit } from '@angular/core';
import { faHome, faTable, faWallet, faClipboardList, faChartLine } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-side-navigation',
  templateUrl: './side-navigation.component.html',
  styleUrls: ['./side-navigation.component.scss'],
})
export class SideNavigationComponent implements OnInit {
  
  faHome = faHome;
  faClipboardList = faClipboardList;
  faWallet = faWallet;
  faChartLine = faChartLine;
  constructor() {}

  ngOnInit(): void {}
}
