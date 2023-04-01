import { Injectable } from '@angular/core';
import { BehaviorSubject, map, Observable, tap } from 'rxjs';
import { DataService } from '../core/data.service';
import { Person } from './models/person.model';

@Injectable({
  providedIn: 'root',
})
export class MainServiceService {
  //http://localhost:8083/hk/api/transaction/person/1
  restApi: string = 'http://localhost:8083/hk/api/';

  //current user
  private readonly _currentPerson = new BehaviorSubject<Person>(null);
  readonly currentPersonState$ = this._currentPerson.asObservable();

  constructor(private dataService: DataService) {}

  setCurrentPerson(person: Person): void {
    this._currentPerson.next(person);
  }

  getUser(id: number): Observable<Person> {
    return this.dataService.get<Person>(`${this.restApi}person/${id}`).pipe(
      map((res) => {
        console.log("Aho");
        return res;
      }),
      tap((res) => {
        this.setCurrentPerson(res);
      })
    );
  }
}
