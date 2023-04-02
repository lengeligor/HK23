import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, Subject, takeUntil } from 'rxjs';
import { MainServiceService } from './shared/main-service.service';
import { Person } from './shared/models/person.model';
import { Transaction } from './shared/models/transaction.model';
import { UserReport } from './shared/models/userReport.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit, OnDestroy {
  title = 'hack-kosice-app';
  currentUserId: number = 1;

  $unsubscribe = new Subject();

  person$: Person;
  transactionList$: Transaction[];
  userReport$: UserReport;

  constructor(
    private mainService: MainServiceService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this._getUser(this.currentUserId);
    this._getUserReport(this.currentUserId);
  }

  ngOnDestroy(): void {
    this.$unsubscribe.next;
    this.$unsubscribe.complete()
  }

  private _getUser(id: number): void{
    this.mainService.getUser(id)
    .pipe(takeUntil(this.$unsubscribe))
    .subscribe((user) => {
      this.person$ = user;
       console.log(user);
    });
  }

  private _getUserReport(id: number): void{
    this.mainService.getUserReport(id)
    .pipe(takeUntil(this.$unsubscribe))
    .subscribe((report) => {
      this.userReport$ = report;
       console.log(report);
    });
  }

  
}
