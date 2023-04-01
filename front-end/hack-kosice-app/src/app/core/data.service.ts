import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable()
export class DataService {
  constructor(private readonly http: HttpClient) {}

  get<T>(url: string, options?: { params?: any }): Observable<T> {
    return this.http.get<T>(url, options);
  }

  post<T>(
    url: string,
    body: any | null,
    options?: { params?: any }
  ): Observable<T> {
    return this.http.post<T>(url, body, options);
  }

  delete<T>(url: string): Observable<T> {
    return this.http.delete<T>(url);
  }

  patch<T>(url: string, body: any | null): Observable<T> {
    return this.http.patch<T>(url, body);
  }
}
