import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Party } from 'src/app/models/party';
import { PartyService } from 'src/app/services/party.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  parties: Party[] = [];
  selectedParty: Party | null = null;

  constructor(
    private partyService: PartyService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ){}

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe({
      next: (params) => {
        let partyIdStr = params.get('partyId');
        if (partyIdStr) {
          let partyId = parseInt(partyIdStr);
          if (isNaN(partyId)) {
            this.router.navigateByUrl('/invalidPartyId');
          } else {
            this.partyService.show(partyId).subscribe({
              next: (party) => {
                this.selectedParty = party;
              },
              error: (nojoy) => {
                console.error(
                  'SPartyListHttpComponent.show(): error getting Party:'
                );
                this.router.navigateByUrl('/invalidPartyId');
              },
            });
          }
        }
      },
    });
}

loadParties() {
  this.partyService.index().subscribe({
    next: (parties) => {
      this.parties = parties;
    },
    error: (problem) => {
      console.error('GameComponent.loadParties(): error loading Parties:');
      console.error(problem);
    },
  });
}

}
