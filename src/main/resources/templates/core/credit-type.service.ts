import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {CreditType} from "../shared/models/credit-type";

@Injectable({
  providedIn: 'root'
})
export class CreditTypeService {
  url = 'http://localhost:8080/rest/credit/types';

  constructor(private http: HttpClient) {
  }

  getAllCreditTypes(): Observable<CreditType[]> {
    return this.http.get<CreditType[]>(this.url);
  }

  getCreditType(id: number): Observable<CreditType> {
    return this.http.get<CreditType>(this.url + '/' + id);
  }

  getAllCreditTypesByCurrency(currency: string): Observable<CreditType[]> {
    return this.http.get<CreditType[]>(this.url + '/all/' + currency);
  }

  getCreditTypeByCurrencyAndName(currency: string, name: string): Observable<CreditType[]> {
    return this.http.get<CreditType[]>(this.url + '/all/' + currency + '/' + name);
  }

  getAllCreditTypesByName(name: string): Observable<CreditType[]> {
    return this.http.get<CreditType[]>(this.url + '/' + name);
  }
}
