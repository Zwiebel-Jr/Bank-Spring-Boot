import {NgModule} from '@angular/core';
import { CommonModule } from '@angular/common';
import {NavbarComponent} from "./home/navbar/navbar.component";
import { HomeComponent } from './home/home.component';
import {RouterModule} from "@angular/router";
import {ClientsService} from "./clients.service";
import {HttpClientModule} from "@angular/common/http";
import {SharedModule} from "../shared/shared.module";



@NgModule({
  declarations: [
    NavbarComponent,
    HomeComponent
  ],
  providers: [
    ClientsService
  ],
  imports: [
    HttpClientModule,
    CommonModule,
    RouterModule,
    SharedModule
  ]
})
export class CoreModule {
}
