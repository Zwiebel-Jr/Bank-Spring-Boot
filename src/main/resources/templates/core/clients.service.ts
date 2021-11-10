import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Client} from "../shared/models/client";

@Injectable()
export class ClientsService {

  url = 'http://localhost:8080';

  constructor(private http: HttpClient) {
  }

  getAllClients(): Observable<Client[]> {
    return this.http.get<Client[]>(this.url + '/rest/client');
  }

  getClient(id: number): Observable<Client> {
    return this.http.get<Client>(this.url + '/rest/client/' + id);
  }

  saveClient(client: Client): Observable<Client> {
    return this.http.post<Client>(this.url + '/rest/client', client);
  }

  removeClient(id: number): Observable<{}> {
    return this.http.delete(this.url + '/rest/client/' + id);
  }
}
