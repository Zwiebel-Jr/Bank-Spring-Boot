import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CreditsComponent} from "./credits.component";
import {EditCreditComponent} from "./edit-credit/edit-credit.component";
import {CreditComponent} from "./credit/credit.component";


const routes: Routes = [
  {path: '', component: CreditsComponent},
  {path: 'client/:id', component: CreditsComponent},
  {path: ':id', component: CreditComponent},
  {path: 'edit/:id', component: EditCreditComponent},
  {path: 'add/credit', component: EditCreditComponent},
  {path: '**', redirectTo: ''}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CreditsRoutingModule { }
