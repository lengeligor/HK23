import { Component, OnInit } from '@angular/core';
import { faBell, faLongArrowAltRight  } from '@fortawesome/free-solid-svg-icons';


@Component({
  selector: 'app-upper-navigation',
  templateUrl: './upper-navigation.component.html',
  styleUrls: ['./upper-navigation.component.scss']
})
export class UpperNavigationComponent implements OnInit {

  faBell = faBell;
  faLongArrowAltRight=faLongArrowAltRight;

  constructor() { }

  ngOnInit(): void {
  }

}
