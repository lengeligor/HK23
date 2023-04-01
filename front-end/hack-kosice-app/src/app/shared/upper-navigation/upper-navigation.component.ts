import { Component, OnInit } from '@angular/core';
import { faPaperPlane } from '@fortawesome/free-solid-svg-icons';


@Component({
  selector: 'app-upper-navigation',
  templateUrl: './upper-navigation.component.html',
  styleUrls: ['./upper-navigation.component.scss']
})
export class UpperNavigationComponent implements OnInit {

  faPaperPlane = faPaperPlane;

  constructor() { }

  ngOnInit(): void {
  }

}
