import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subject, takeUntil } from 'rxjs';
import { MainServiceService } from './shared/main-service.service';
import { Person } from './shared/models/person.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit, OnDestroy {
  title = 'hack-kosice-app';

  $unsubscribe = new Subject();

  person$: Person;

  constructor(
    private mainService: MainServiceService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.mainService.getUser(1)
      .pipe(takeUntil(this.$unsubscribe))
      .subscribe((user) => {
        this.person$ = user;
        console.log(user);
      });
  }

  ngOnDestroy(): void {
    this.$unsubscribe.next;
  }
}
