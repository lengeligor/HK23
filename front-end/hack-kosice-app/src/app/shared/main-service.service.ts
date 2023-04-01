import { Injectable } from '@angular/core';
import { BehaviorSubject, map, Observable, tap } from 'rxjs';
import { DataService } from '../core/data.service';
import { Person } from './models/person.model';
import { Transaction } from './models/transaction.model';

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

  constructor(private dataService: DataService) {}

  setCurrentPerson(person: Person): void {
    this._currentPerson.next(person);
  }

  setTransactions(transactionList: Transaction[]): void {
    this._transactionsList.next(transactionList);
  }

  getUser(id: number): Observable<Person> {
    return this.dataService.get<Person>(`${this.restApi}person/${id}`).pipe(
      map((res) => {
        console.log('Aho');
        return res;
      }),
      tap((res) => {
        this.setCurrentPerson(res);
      })
    );
  }

  getUserTransactions(id: number): Observable<Transaction[]> {
    return this.dataService
      .get<Transaction[]>(`${this.restApi}transaction/person/${id}`)
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
}
