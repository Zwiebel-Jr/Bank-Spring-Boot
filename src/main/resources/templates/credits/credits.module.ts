import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CreditsRoutingModule } from './credits-routing.module';
import { CreditsComponent } from './credits.component';
import {SharedModule} from "../shared/shared.module";
import { EditCreditComponent } from './edit-credit/edit-credit.component';
import { CreditComponent } from './credit/credit.component';


@NgModule({
  declarations: [CreditsComponent, EditCreditComponent, CreditComponent],
  imports: [
    CommonModule,
    CreditsRoutingModule,
    SharedModule
  ]
})
export class CreditsModule { }
