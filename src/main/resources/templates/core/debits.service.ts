import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Operation} from "../shared/models/operation";

@Injectable({
  providedIn: 'root'
})
export class DebitsService {

  url = 'http://localhost:8080';

  constructor(private http: HttpClient) {
  }

  getAllDebits(): Observable<Operation[]> {
    return this.http.get<Operation[]>(this.url + '/rest/debit');
  }

  getAllDebitsByClientId(id: number): Observable<Operation[]> {
    return this.http.get<Operation[]>(this.url + '/rest/debit/client/' + id);
  }

  getDebit(id: number): Observable<Operation> {
    return this.http.get<Operation>(this.url + '/rest/debit/' + id);
  }

  getCalculatedDebit(id: number): Observable<any> {
    return this.http.get<any>(this.url + '/rest/debit/calculated/' + id);
  }

  saveDebit(client_id: number, debit: Operation): Observable<Operation> {
    return this.http.post<Operation>(this.url + '/rest/debit/' + client_id, debit);
  }

  removeDebit(id: number): Observable<{}> {
    return this.http.delete(this.url + '/rest/debit/' + id);
  }
}
