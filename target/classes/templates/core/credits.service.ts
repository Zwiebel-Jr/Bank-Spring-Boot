import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {Client} from "../shared/models/client";
import {HttpClient} from "@angular/common/http";
import {Operation} from "../shared/models/operation";

@Injectable({
  providedIn: 'root'
})
export class CreditsService {

  url = 'http://localhost:8080';

  constructor(private http: HttpClient) {
  }

  getAllCredits(): Observable<Operation[]> {
    return this.http.get<Operation[]>(this.url + '/rest/credit');
  }

  getAllCreditsByClientId(id: number): Observable<Operation[]> {
    return this.http.get<Operation[]>(this.url + '/rest/credit/client/' + id);
  }

  getCredit(id: number): Observable<Operation> {
    return this.http.get<Operation>(this.url + '/rest/credit/' + id);
  }

  getCalculatedCredit(id: number): Observable<any> {
    return this.http.get<any>(this.url + '/rest/credit/calculated/' + id);
  }

  saveCredit(client_id: number, credit: Operation): Observable<Operation> {
    return this.http.post<Operation>(this.url + '/rest/credit/' + client_id, credit);
  }

  removeCredit(id: number): Observable<{}> {
    return this.http.delete(this.url + '/rest/credit/' + id);
  }
}
