import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Disability} from "../shared/models/client";

@Injectable({
  providedIn: 'root'
})
export class DisabilityService {
  url = 'http://localhost:8080/rest/disability';

  constructor(private http: HttpClient) {
  }

  getAllDisabilities(): Observable<Disability[]> {
    return this.http.get<Disability[]>(this.url);
  }
}
