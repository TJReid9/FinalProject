import { UserService } from 'src/app/services/user.service';
import { AfterViewInit, Component, OnInit } from '@angular/core';
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
import { PartyGoer } from 'src/app/models/party-goer';
import { ActivatedRoute, Router } from '@angular/router';
import { MapGeocoder } from '@angular/google-maps';
import { PartyCommentService } from 'src/app/services/party-comment.service';
import { PartyComment } from 'src/app/models/party-comment';

@Component({
  selector: 'app-parties',
  templateUrl: './parties.component.html',
  styleUrls: ['./parties.component.css'],
})
export class PartiesComponent implements OnInit {
  parties: Party[] = [];
  partyComments: PartyComment[] = [];
  newComment: PartyComment = new PartyComment();
  pc: PartyComment = new PartyComment();
  selectedParty: Party | null = null;
  addNewParty: Party | null = null;
  newParty: Party = new Party();
  newAddress: Address = new Address();
  newVenue: Venue = new Venue();
  venues: Venue[] = [];
  venue: Venue = new Venue();
  teams: Team[] = [];
  addresses: Address[] = [];
  loggedInUser: User = new User();
  newTeam: Team = new Team();
  showComplete: boolean = false;
  today = new Date();
  users: User[] = [];
  editUser: User | null = null;
  partyGoers: PartyGoer[] = [];
  pg: PartyGoer = new PartyGoer();
  selectedVenue: Venue | null = null;
  editParty: Party | null  = null;
  newPartyGoer: PartyGoer | null = null



  constructor(
    private partyService: PartyService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private auth: AuthService,
    private incompletePipe: IncompletePipe,
    private venueService: VenueService,
    private addressService: AddressService,
    private teamService: TeamService,
    private userService: UserService,
    private geocoder: MapGeocoder,
    private partyCommentService: PartyCommentService
  ) {}

  loggedIn(): boolean {
    return this.auth.checkLogin();
  }

  ngOnInit(): void {
    this.loadAddress();
    this.loadVenue();
    this.loadParties();
    this.loadTeams();
    this.loadUser();
    this.setLoggedInUser()


    console.log(this.venues);
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
                this.loadComments(party.id);
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
        this.checkCompleted(parties);
      },
      error: (problem) => {
        console.error('HomeComponent.loadParties(): error loading Parties:');
        console.error(problem);
      },
    });
  }

  loadComments(partyId: number) {
    this.partyCommentService.index(partyId).subscribe({
      next: (partyComments) => {
        console.log(partyComments);
        this.partyComments = partyComments;
      },
      error: (problem) => {
        console.error('HomeComponent.loadParties(): error loading Parties:');
        console.error(problem);
      },
    });
  }

  checkCompleted(parties: Party[]) {
    parties.forEach((party) => {
      if (party.partyDate < this.today) {
        party.completed = true;
      }
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

  reloadParties(): void {
    this.partyService.index().subscribe({
      next: (parties) => {
        this.parties = parties;
      },
      error: (problem) => {
        console.error(
          'PartiesComponent.reloadParties(): error loading Party: '
        );
        console.error(problem);
      },
    });
  }

  displayParty(party: Party): void {
    this.selectedParty = party;
  }

  displayAllParties(): void {
    this.selectedParty = null;
    this.loadParties();
  }

  setEditParty() {
    this.editParty = Object.assign({}, this.selectedParty);
    console.log(this.editParty);
  }

  displayAddParty(party: Party) {
    this.addNewParty = party;
  }

  getPartyCount(): number {
    return this.incompletePipe.transform(this.parties, false).length;
  }

  updateParty(party: Party, id: number) {
    console.log(party);
    this.partyService.update(id, party).subscribe({
      next: (parties) => {
        this.selectedParty = this.editParty;
        this.reloadParties();
        this.editParty = null;
      },
      error: (problem) => {
        console.error(
          'PartiesComponent.reloadParties(): error loading party: '
        );
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
        this.reloadParties();
        this.addNewParty = null;
      },
      error: (nojoy) => {
        console.error(
          'PartiesComponent.reloadParties(): error loading party: '
        );
        console.error(nojoy);
      },
    });
  }

setLoggedInUser(){
  this.auth.getLoggedInUser().subscribe({
    next: (user) => {
      this.loggedInUser = user;
    },
    error: (err) => {
      console.error('UserComponent.setLoggedInUser: error getting logged in user')
    }
  });
}

  addComment(comment: PartyComment, party: Party): void {
    comment.party = party;
    comment.user = this.loggedInUser;
    this.partyCommentService.create(comment, party.id).subscribe({
      next: (result) => {
        this.reloadParties();
      },
      error: (nojoy) => {
        console.error('PartiesComponent.reloadParties(): error loading party: ');
        console.error(nojoy);
      },
    });
  }

  deleteParty(id: number) {
    this.partyService.destroy(id).subscribe({
      next: (result) => {
        this.reloadParties();
        this.selectedParty = null;
      },
      error: (nojoy) => {
        console.error('PartiesComponent.reloadParties(): error loading party:');
        console.error(nojoy);
      },
    });
  }

addUserToParty( partyId: number): void {
  this.partyService.addSelfToParty(partyId).subscribe({
    next: (result) => {
       this.partyGoers.push(result);
        this.reloadParties();
        this.loadParties();
      location.reload();

    },
    error: (nojoy) => {
      console.error('PartiesComponent.reload(): error loading party: ');
      console.error(nojoy);
    },
  });
}

}
