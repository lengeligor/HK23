import { Component, OnInit } from '@angular/core';
import { faHome, faPlus, faWallet, faClipboardList, faChartLine } from '@fortawesome/free-solid-svg-icons';

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
  faPlus = faPlus;

  isLearnMoreToggled = false;

  constructor() {}

  ngOnInit(): void {}

  onClickToogleMore(){
    this.isLearnMoreToggled = !this.isLearnMoreToggled;
  }



}


function change(id:string) {
  var b1 =document.getElementById('btn1');
  var b2 =document.getElementById('btn2');
  var b3 =document.getElementById('btn3');
  var b4 =document.getElementById('btn4');

  if (id==='1'){
    b1.classList.remove("active-link");
    b2.classList.add("active-link");
    b3.classList.add("active-link");
    b4.classList.add("active-link");

  }else if(id==='2'){b1.classList.add("active-link");
  b2.classList.remove("active-link");
  b3.classList.add("active-link");
  b4.classList.add("active-link");


  }else if(id==='3'){b1.classList.add("active-link");
  b2.classList.add("active-link");
  b3.classList.remove("active-link");
  b4.classList.add("active-link");


  }else if(id==='4'){b1.classList.add("active-link");
  b2.classList.add("active-link");
  b3.classList.add("active-link");
  b4.classList.remove("active-link");


  }
}

