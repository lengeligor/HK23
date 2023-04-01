import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-chat-message',
  templateUrl: './chat-message.component.html',
  styleUrls: ['./chat-message.component.scss'],
})
export class ChatMessageComponent implements OnInit {
  messages: { key: boolean; value: string }[] = [];

  constructor() {
    this.messages.push({ key: true, value: 'Hello' });
    this.messages.push({ key: false, value: 'Hi' });
    this.messages.push({ key: false, value: 'How are you?' });
    this.messages.push({ key: true, value: 'I am good, thanks' });
  }

  ngOnInit(): void {}
}
