import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {DebitType} from "../shared/models/debit-type";

@Injectable({
  providedIn: 'root'
})
export class DebitTypeService {
  url = 'http://localhost:8080/rest/debit/types';

  constructor(private http: HttpClient) {
  }

  getAllDebitTypes(): Observable<DebitType[]> {
    return this.http.get<DebitType[]>(this.url);
  }

  getDebitType(id: number): Observable<DebitType> {
    return this.http.get<DebitType>(this.url + '/' + id);
  }

  getAllDebitTypesByCurrency(currency: string): Observable<DebitType[]> {
    return this.http.get<DebitType[]>(this.url + '/all/' + currency);
  }

  getDebitTypeByCurrencyAndName(currency: string, name: string): Observable<DebitType[]> {
    return this.http.get<DebitType[]>(this.url + '/all/' + currency + '/' + name);
  }

  getAllDebitTypesByName(name: string): Observable<DebitType[]> {
    return this.http.get<DebitType[]>(this.url + '/' + name);
  }
}
