import { Injectable } from '@angular/core';
import { BehaviorSubject, map, Observable, tap } from 'rxjs';
import { DataService } from '../core/data.service';
import { chatResponse } from './models/chatResponse.model';
import { Message } from './models/message.model';
import { Person } from './models/person.model';
import { Transaction } from './models/transaction.model';
import { UserReport } from './models/userReport.model';

@Injectable({
  providedIn: 'root',
})
export class MainServiceService {
  //http://localhost:8083/hk/api/transaction/person/1
  restApi: string = 'http://localhost:8083/hk/api/';

  //current user
  private readonly _currentPerson = new BehaviorSubject<Person>(null);
  readonly currentPersonState$ = this._currentPerson.asObservable();

  //current user transactions list
  private readonly _transactionsList = new BehaviorSubject<Transaction[]>(null);
  readonly currentTransactionListState$ = this._transactionsList.asObservable();

  //chatResponse
  private readonly _chatResponse = new BehaviorSubject<chatResponse>(null);
  readonly currentChatResponseState$ = this._chatResponse.asObservable();

  //user report
  private readonly _currentUserReport = new BehaviorSubject<UserReport>(null);
  readonly currentUserReportState$ = this._currentUserReport.asObservable();

  //user year balance
  private readonly _currentUserYearBalance = new BehaviorSubject<{ key: string; value: number }[]>(null);
  readonly currentUserYearBalanceState$ = this._currentUserYearBalance.asObservable();

  constructor(private dataService: DataService) {}

  setCurrentPerson(person: Person): void {
    this._currentPerson.next(person);
  }

  setCurrentUserReport(report: UserReport): void {
    this._currentUserReport.next(report);
  }

  setTransactions(transactionList: Transaction[]): void {
    this._transactionsList.next(transactionList);
  }

  setChatResponse(chatResponse: chatResponse): void {
    this._chatResponse.next(chatResponse);
  }

  setUserYearBalance(yearBalance: { key: string; value: number }[]): void {
    this._currentUserYearBalance.next(yearBalance);
  }

  getUser(id: number): Observable<Person> {
    return this.dataService.get<Person>(`${this.restApi}person/${id}`).pipe(
      map((res) => {
        // console.log('Aho');
        return res;
      }),
      tap((res) => {
        this.setCurrentPerson(res);
      })
    );
  }

  getUserReport(id: number): Observable<UserReport> {
    return this.dataService
      .get<UserReport>(`${this.restApi}/transaction/person/${id}/get-report`)
      .pipe(
        map((res) => {
          // console.log('Aho');
          return res;
        }),
        tap((res) => {
          this.setCurrentUserReport(res);
        })
      );
  }

  getUserTransactions(id: number): Observable<Transaction[]> {
    return this.dataService
      .get<Transaction[]>(
        `${this.restApi}transaction/person/${id}?page=0&size=13`
      )
      .pipe(
        map((res) => {
          // console.log(res);
          return res;
        }),
        tap((res) => {
          this.setTransactions(res);
        })
      );
  }

  getUserYearBalance(id: number): Observable<{ key: string; value: number }[]> {
    return this.dataService
      .get<{ key: string; value: number }[]>(
        `${this.restApi}transaction/person/${id}/year-balance`
      )
      .pipe(
        map((res) => {
          // console.log(res);
          return res;
        }),
        tap((res) => {
          this.setUserYearBalance(res);
        })
      );
  }

  postMessageAdviser(message: Message): any {
    return this.dataService
      .post<chatResponse>(`${this.restApi}bot/send`, message)
      .pipe(
        map((res) => {
          // console.log(res);
          return res;
        }),
        tap((res) => {
          this.setChatResponse(res);
        })
      );
  }
}
