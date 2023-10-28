import { UserService } from 'src/app/services/user.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Address } from 'src/app/models/address';
import { Party } from 'src/app/models/party';
import { Team } from 'src/app/models/team';
import { Venue } from 'src/app/models/venue';
import { AddressService } from 'src/app/services/address.service';
import { AuthService } from 'src/app/services/auth.service';
import { PartyService } from 'src/app/services/party.service';
import { TeamService } from 'src/app/services/team.service';
import { VenueService } from 'src/app/services/venue.service';
import { User } from 'src/app/models/user';
import { IncompletePipe } from 'src/app/pipes/incomplete.pipe';

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
  newAddress: Address = new Address();
  newVenue: Venue = new Venue();
  venues: Venue[] = [];
  teams: Team[] = [];
  addresses: Address[] = [];
  loggedInUser: User = new User();
  newTeam: Team = new Team();
  showComplete: boolean = false;
  users: User[] = [];


  constructor(private partyService: PartyService,
    private activatedRoute: ActivatedRoute,
    private router: Router, private auth: AuthService,private incompletePipe: IncompletePipe, private venueService: VenueService, private addressService: AddressService,private teamService: TeamService, private userService: UserService){}

    loggedIn(): boolean {
      return this.auth.checkLogin();
    }

  ngOnInit(): void {
    this.loadAddress();
    this.loadVenue();
    this.loadParties();
    this.loadTeams();
    this.loadUser();

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

loadTeams() {
  this.teamService.index().subscribe({
    next: (teams) => {
      this.teams = teams;
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

loadAddress() {
  this.addressService.index().subscribe({
    next: (addresses) => {
      this.addresses = addresses;
    },
    error: (problem) => {
      console.error('HomeComponent.loadParties(): error loading Parties:');
      console.error(problem);
    },
  });
}

loadUser() {
  this.userService.index().subscribe({
    next: (users) => {
      this.users = users;
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

displayAddParty(party: Party){
  this.addNewParty = party;
}

getPartyCount(): number {

  return this.incompletePipe.transform(this.parties, false).length;
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
  console.log(party);
  this.partyService.create(party).subscribe({
    next: (result) => {
      this.newTeam = new Team();
      this.newVenue = new Venue();
      this.newParty = new Party();
      this.reload();
      this.addNewParty = null;
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
