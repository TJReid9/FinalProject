import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Address } from 'src/app/models/address';
import { Venue } from 'src/app/models/venue';
import { AddressService } from 'src/app/services/address.service';
import { AuthService } from 'src/app/services/auth.service';
import { VenueService } from 'src/app/services/venue.service';

@Component({
  selector: 'app-venue',
  templateUrl: './venue.component.html',
  styleUrls: ['./venue.component.css']
})
export class VenueComponent implements OnInit{

  constructor(private venueService: VenueService,
              private activatedRoute: ActivatedRoute,
              private router: Router,
              private auth: AuthService,
              private addressService: AddressService
              ) {}

  selectedVenue: Venue|null = null;
  newVenue: Venue = new Venue();
  editVenue: Venue|null = null;
  showCompleted: boolean = false;
  venues: Venue[] = [];
  addresses: Address [] = [];
  addNewVenue: Venue | null = null;

  ngOnInit(): void{
    if(!this.auth.checkLogin()){
      this.router.navigateByUrl('/invalidURL');
    }
    this.loadAddress();
    this.loadVenue();
    this.activatedRoute.paramMap.subscribe(
      {
        next: (params) => {
          let venueIdStr = params.get("venueId");
          if(venueIdStr) {
            let venueId = parseInt(venueIdStr);
            if(isNaN(venueId)) {
              this.router.navigateByUrl('/invalidVenueId');
            } else {
              this.venueService.show(venueId).subscribe({
                next: (venue) => {
                  this.selectedVenue = venue;
                },
                error: (nojoy) => {
                  console.error('VenueListHttpComponent.show(): error getting Venue:');
                  this.router.navigateByUrl('/invalidVenueId');
                }
              });
            }
          }
        }
      }
    );
  }

  displayVenue(venue: Venue){
    this.selectedVenue = venue;
  }

  loggedIn(): boolean {
    return this.auth.checkLogin();
  }

  getVenueCount(): number {
    return this.venues.length;
  }

  displayTable() {
    this.selectedVenue = null;
  }

  addVenue(venue: Venue) {
    this.venueService.create(venue).subscribe({
      next: (result) => {
        this.newVenue = new Venue();
        this.loadVenue();
      },
      error: (nojoy) => {
        console.error('VenueListHttpComponent.addVenue(): error creating Venue:');
        console.error(nojoy);
      },
    });
  }

  displayAllVenues(): void{
    this.selectedVenue = null;
  }

  setEditVenue() {
    this.editVenue = Object.assign({}, this.selectedVenue);
  }

  updateVenue(id: number,venue: Venue) {
    this.venueService.update(id,venue).subscribe({
      next: (result) => {
        this.newVenue = new Venue();
        this.loadVenue();
      },
      error: (nojoy) => {
        console.error('VenueListHttpComponent.addVenue(): error updating Venue:');
        console.error(nojoy);
      },
    });
  }

  deleteVenue(id: number) {
    this.venueService.destroy(id).subscribe({
      next: (result) => {
        this.loadVenue();
      },
      error: (nojoy) => {
        console.error('VenueListHttpComponent.addVenue(): error deleting Venue:');
        console.error(nojoy);
      },
    });
  }

  loadVenue(): void {
    this.venueService.index().subscribe(
      {
        next: (venues) => {
          this.venues = venues;
        },
        error: (problem) => {
          console.error('VenueListComponent.loadVenue(): error loading venues:');
          console.error(problem);
        }
      }
    );
  }

  loadAddress(): void {
    this.addressService.index().subscribe(
      {
        next: (addresses) => {
          this.addresses = addresses;
        },
        error: (problem) => {
          console.error('VenueListComponent.loadVenue(): error loading venues:');
          console.error(problem);
        }
      }
    );
  }




}
