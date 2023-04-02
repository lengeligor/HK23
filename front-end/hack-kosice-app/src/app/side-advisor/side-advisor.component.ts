import { Component, OnInit } from '@angular/core';
import { faPaperPlane } from '@fortawesome/free-solid-svg-icons';
import { Subject, take, takeUntil } from 'rxjs';
import { MainServiceService } from '../shared/main-service.service';
import { chatResponse } from '../shared/models/chatResponse.model';
import { Message } from '../shared/models/message.model';
import { UserReport } from '../shared/models/userReport.model';

@Component({
  selector: 'app-side-advisor',
  templateUrl: './side-advisor.component.html',
  styleUrls: ['./side-advisor.component.scss'],
})
export class SideAdvisorComponent implements OnInit {
  faPaperPlane = faPaperPlane;

  $unsubscribe = new Subject();

  chatResponse$: chatResponse;

  chatMessage: Message = {
    message: ''
  };

  message: string = '';

  messages: { key: boolean; value: string }[] = [];

  userReport$: UserReport;

  isTyping = true;

  constructor(private mainService: MainServiceService) {}

  ngOnInit(): void {
    this.isTyping = true
    this.mainService.currentUserReportState$.subscribe((report) => {
      this.userReport$ = report;
      if (this.userReport$)
        this.messages.push({ key: false, value: this.userReport$.message});
        this.isTyping= false
    });
  }

  private _postChatMessage(message: string): void {
    this.chatMessage.message = message;
    this.isTyping = true;
    this.mainService
      .postMessageAdviser(this.chatMessage)
      .pipe(take(1))
      .subscribe((chatResponse) => {
        this.chatResponse$ = chatResponse;
        console.log(chatResponse);
        this.messages.push({ key: false, value: this.chatResponse$.choices[0].text});
        this.isTyping = false;
      });
  }

  onSendMessage(): void {
    this._postChatMessage(this.message);
    this.messages.push({ key: true, value: this.message });
    this.message = '';
    console.log(this.messages);
    
  }
}
