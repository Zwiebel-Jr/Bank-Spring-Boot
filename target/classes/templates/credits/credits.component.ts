import { Component, OnInit } from '@angular/core';
import {Client} from "../shared/models/client";
import {ActivatedRoute, Router} from "@angular/router";
import {CreditsService} from "../core/credits.service";
import {Operation} from "../shared/models/operation";
import {ClientsService} from "../core/clients.service";

@Component({
  selector: 'app-credits',
  templateUrl: './credits.component.html',
  styleUrls: ['./credits.component.scss']
})
export class CreditsComponent implements OnInit {

  credits: Operation[] = [];

  constructor(private route: ActivatedRoute, private router: Router, private creditsService: CreditsService) { }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.creditsService.getAllCreditsByClientId(+id).subscribe( credits => {
        this.credits = credits;
      });
    } else {
      this.creditsService.getAllCredits().subscribe(credits => {
        this.credits = credits;
      });
    }
  }

  rowSelected(id: string) {
    this.router.navigate(['credits', id]);
  }
}
