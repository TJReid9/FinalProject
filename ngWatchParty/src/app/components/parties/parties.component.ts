import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Party } from 'src/app/models/party';
import { Venue } from 'src/app/models/venue';
import { AuthService } from 'src/app/services/auth.service';
import { PartyService } from 'src/app/services/party.service';
import { VenueService } from 'src/app/services/venue.service';

@Component({
  selector: 'app-parties',
  templateUrl: './parties.component.html',
  styleUrls: ['./parties.component.css']
})
export class PartiesComponent implements OnInit{

  parties: Party[] = [];
  selectedParty: Party | null = null;
  editParty: Party | null = null;
  addNewParty: Party | null = null;
  newParty: Party = new Party();
  venues: Venue[] = [];
  // venue: Venue | null = new Venue();

  constructor(private partyService: PartyService,
    private activatedRoute: ActivatedRoute,
    private router: Router, private auth: AuthService, private venueService: VenueService){}

    loggedIn(): boolean {
      return this.auth.checkLogin();
    }

  ngOnInit(): void {
    this.loadParties();
    this.loadVenue();
    console.log(this.venues)
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
      console.error('HomeComponent.loadParties(): error loading Parties:');
      console.error(problem);
    },
  });
}

loadVenue() {
  this.venueService.index().subscribe({
    next: (venues) => {
      this.venues = venues;
    },
    error: (problem) => {
      console.error('HomeComponent.loadParties(): error loading Parties:');
      console.error(problem);
    },
  });
}

reload(): void{
 this.partyService.index().subscribe({
  next: (parties) => {
  this.parties = parties;
},
error: (problem) => {
  console.error('PartiesComponent.reload(): error loading Party: ');
  console.error(problem);
},
});
}


displayParty(party: Party): void {
  this.selectedParty = party;
}

displayAllParties(): void{
  this.selectedParty = null;
}

setEditParty() {
  this.editParty = Object.assign({}, this.selectedParty);
}

displayAddNewWorkout(party: Party){
  this.addNewParty = party;
}


updateParty(party: Party, id: number) {
  console.log(party);
  this.partyService.update(id,party).subscribe({
    next: (parties) => {
      this.selectedParty = this.editParty;
      this.reload();
      this.editParty = null;
    },
    error: (problem) => {
      console.error('PartiesComponent.reload(): error loading party: ');
      console.error(problem);
    },
  });
}

addParty(party: Party): void {
  this.partyService.create(party).subscribe({
    next: (result) => {
      this.reload();
      this.newParty = new Party();
    },
    error: (nojoy) => {
      console.error('PartiesComponent.reload(): error loading party: ');
      console.error(nojoy);
    },
  });
}

deleteParty(id: number) {
  this.partyService.destroy(id).subscribe({
    next: (result) => {
      this.reload();

    },
    error: (nojoy) => {
      console.error('PartiesComponent.reload(): error loading party:');
      console.error(nojoy);
    },
  });
}
}
