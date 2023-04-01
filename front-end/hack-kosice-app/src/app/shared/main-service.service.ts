import { Injectable } from '@angular/core';
import { DataService } from '../core/data.service';

@Injectable({
  providedIn: 'root',
})
export class MainServiceService {
  //http://localhost:8083/hk/api/transaction/person/1
  restApi: string = 'http://localhost:8083/hk/api/';

  constructor(private dataService: DataService) {}
}
