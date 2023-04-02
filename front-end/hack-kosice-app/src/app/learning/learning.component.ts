import { Component, OnInit } from '@angular/core';
import { Subject, take, takeUntil } from 'rxjs';
import { MainServiceService } from '../shared/main-service.service';
import { Transaction } from '../shared/models/transaction.model';
import { Message } from '../shared/models/message.model';


interface Container {
  title: string;
  text: string;
  isHidden: boolean;
}

@Component({
  selector: 'app-learning',
  templateUrl: './learning.component.html',
  styleUrls: ['./learning.component.scss']
})
export class LearningComponent implements OnInit {

  chatResponseMessage$: Message;

  containers: Container[] = [];
  
    toggleHidden(container: Container): void {
      if(container.text===''){
        this._getAnswer(container.title);
      }
      container.isHidden = !container.isHidden;
    }
  
 

  constructor(private mainService: MainServiceService,) { }

  ngOnInit(): void {
    const containers = document.querySelectorAll('.container');

    this._getQuestionsList();

  }

  

  private _getQuestionsList(): void{
    this.mainService.getQuestions()
    .pipe()
    .subscribe((questions) => {
      for (let i = 0; i < questions.length; i++) {
        var container: Container = { title: questions[i], text: '', isHidden: true };
        this.containers.push(container);
      }
      console.log(this.containers);
    });
  }


  private _getAnswer(q:string): void{
    this.mainService.getAnswer(q)
    .pipe(take(1))
    .subscribe((answer) => {
      this.chatResponseMessage$ = answer;

      const find = this.containers.find(obj => obj.title === q);
      if(find){
        if(find.text==='')
          find.text = this.chatResponseMessage$.message;
      }
      console.log(this.chatResponseMessage$);
    });
  }

  
  

}




