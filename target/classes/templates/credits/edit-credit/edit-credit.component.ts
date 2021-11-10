import {Component, OnInit, ViewChild} from '@angular/core';
import {Operation} from "../../shared/models/operation";
import {ActivatedRoute, Router} from "@angular/router";
import {CreditsService} from "../../core/credits.service";
import {Client} from "../../shared/models/client";
import {ClientsService} from "../../core/clients.service";
import {NgForm} from "@angular/forms";
import {CreditType} from "../../shared/models/credit-type";
import {CreditTypeService} from "../../core/credit-type.service";

@Component({
  selector: 'app-edit-credit',
  templateUrl: './edit-credit.component.html',
  styleUrls: ['./edit-credit.component.scss']
})
export class EditCreditComponent implements OnInit {

  creditType: CreditType;
  creditTypes: CreditType[];
  validToDelete = false;
  credit: Operation;
  clients: Client[];
  currencies = ['BYN'];
  @ViewChild('generalForm', {static: true}) generalForm: NgForm;

  constructor(private router: Router, private route: ActivatedRoute,
              private creditsService: CreditsService, private clientsService: ClientsService,  private creditTypeService: CreditTypeService) { }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    this.creditsService.getCredit(+id).subscribe((credit: Operation) => {
      if (credit) {
        console.log(credit);
        this.credit = credit;
        this.validToDelete = true;
      } else {
        this.credit = {};
        this.credit.client = {};
        this.credit.type = {};
      }
    });
    this.clientsService.getAllClients().subscribe((clients: Client[]) => {
      this.clients = clients;
    });
    this.creditTypeService.getAllCreditTypes().subscribe(creditTypes => {
      this.creditTypes = creditTypes;
    });
  }

  creditTypeChange(id: string): void {
    this.creditTypeService.getCreditType(+id).subscribe(creditType => {
      this.creditType = creditType;
    })
  }

  save(): void {
    this.generalForm.form.markAsPristine();
    this.creditsService.saveCredit(this.credit.client.id, this.credit).subscribe();
  }

  delete(): void {
    this.creditsService.removeCredit(this.credit.id).subscribe(data => {
      this.router.navigate(['credits']);
    });
  }

  back(): void {
    this.router.navigate(['credits']);
  }
}
