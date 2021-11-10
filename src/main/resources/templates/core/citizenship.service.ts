import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Citizenship} from "../shared/models/client";

@Injectable({
  providedIn: 'root'
})
export class CitizenshipService {
  url = 'http://localhost:8080/rest/citizenship';

  constructor(private http: HttpClient) {
  }

  getAllCitizenships(): Observable<Citizenship[]> {
    return this.http.get<Citizenship[]>(this.url);
  }
}
