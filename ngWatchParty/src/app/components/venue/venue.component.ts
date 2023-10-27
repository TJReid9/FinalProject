import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Venue } from 'src/app/models/venue';
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
              private auth: AuthService
              ) {}

  selectedVenue: Venue|null = null;
  newVenue: Venue = new Venue();
  editVenue: Venue|null = null;
  showCompleted: boolean = false;
  venues: Venue[] = [];

  ngOnInit(): void{
    if(!this.auth.checkLogin()){
      this.router.navigateByUrl('/invalidURL');
    }
    this.reload();
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
        this.reload();
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
        this.reload();
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
        this.reload();
      },
      error: (nojoy) => {
        console.error('VenueListHttpComponent.addVenue(): error deleting Venue:');
        console.error(nojoy);
      },
    });
  }

  reload(): void {
    this.venueService.index().subscribe(
      {
        next: (venues) => {
          this.venues = venues;
        },
        error: (problem) => {
          console.error('VenueListComponent.reload(): error loading venues:');
          console.error(problem);
        }
      }
    );
  }




}
