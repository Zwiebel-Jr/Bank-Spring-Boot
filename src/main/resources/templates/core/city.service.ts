import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {City} from "../shared/models/client";

@Injectable({
  providedIn: 'root'
})
export class CityService {
  url = 'http://localhost:8080/rest/city';

  constructor(private http: HttpClient) {
  }

  getAllCities(): Observable<City[]> {
    return this.http.get<City[]>(this.url);
  }
}
