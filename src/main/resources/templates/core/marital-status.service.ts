import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {MaritalStatus} from "../shared/models/client";

@Injectable({
  providedIn: 'root'
})
export class MaritalStatusService {
  url = 'http://localhost:8080/rest/maritalStatus';

  constructor(private http: HttpClient) {
  }

  getAllMaritalStatuses(): Observable<MaritalStatus[]> {
    return this.http.get<MaritalStatus[]>(this.url);
  }
}
