import { transition } from '@angular/animations';
import { Component, OnInit } from '@angular/core';
import { Subject, takeUntil } from 'rxjs';
import { MainServiceService } from '../shared/main-service.service';
import { Transaction } from '../shared/models/transaction.model';

@Component({
  selector: 'app-balance-preview',
  templateUrl: './balance-preview.component.html',
  styleUrls: ['./balance-preview.component.scss'],
})
export class BalancePreviewComponent implements OnInit {
  $unsubscribe = new Subject();

  transactionList$: Transaction[];

  constructor(private mainService: MainServiceService) {}

  ngOnInit(): void {
    this.mainService.currentTransactionListState$.subscribe((transitions) => {
      this.transactionList$ = transitions;
    });
  }

  isIncome(amount: string): boolean {
    return amount.includes('-');
  }
}
