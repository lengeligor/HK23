import { Component, OnInit } from '@angular/core';
import { faPaperPlane } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-side-advisor',
  templateUrl: './side-advisor.component.html',
  styleUrls: ['./side-advisor.component.scss']
})
export class SideAdvisorComponent implements OnInit {

  faPaperPlane = faPaperPlane;


  constructor() { }

  ngOnInit(): void {
  }

}
