import { AfterViewInit, Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Address } from 'src/app/models/address';
import { Venue } from 'src/app/models/venue';
import { AddressService } from 'src/app/services/address.service';
import { AuthService } from 'src/app/services/auth.service';
import { VenueService } from 'src/app/services/venue.service';
import { MapGeocoder } from '@angular/google-maps';

@Component({
  selector: 'app-venue',
  templateUrl: './venue.component.html',
  styleUrls: ['./venue.component.css'],
})
export class VenueComponent implements OnInit, AfterViewInit {
  constructor(
    private venueService: VenueService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private auth: AuthService,
    private addressService: AddressService,
    private geocoder: MapGeocoder
  ) {}

  selectedVenue: Venue | null = null;
  selectedAddress: Address | null = null;
  newVenue: Venue = new Venue();
  editVenue: Venue | null = null;
  editAddress: Address | null = null;
  showCompleted: boolean = false;
  venues: Venue[] = [];
  addresses: Address[] = [];
  addNewVenue: Venue | null = null;
  newAddress: Address = new Address();
  address = '';
  latitude: number = 0;
  longitude: number = 0;
  display: any;

  center: google.maps.LatLngLiteral = {
    lat: 24,
    lng: 12,
  };
  zoom = 15;

  moveMap(event: google.maps.MapMouseEvent) {
    if (event.latLng != null) this.center = event.latLng.toJSON();
  }

  move(event: google.maps.MapMouseEvent) {
    if (event.latLng != null) this.display = event.latLng.toJSON();
  }

  ngAfterViewInit(): void {
    // this.center = {
    //   lat: this.latitude,
    //   lng: this.longitude,
    // };
    this.displayMap(this.address);

  }

  ngOnInit(): void {
    this.loadAddress();
    this.loadVenue();

    this.activatedRoute.paramMap.subscribe({
      next: (params) => {
        let venueIdStr = params.get('venueId');
        if (venueIdStr) {
          let venueId = parseInt(venueIdStr);
          if (isNaN(venueId)) {
            this.router.navigateByUrl('/invalidVenueId');
          } else {
            this.venueService.show(venueId).subscribe({
              next: (venue) => {
                this.selectedVenue = venue;
              },
              error: (nojoy) => {
                console.error(
                  'VenueListHttpComponent.show(): error getting Venue:'
                );
                this.router.navigateByUrl('/invalidVenueId');
              },
            });
          }
        }
      },
    });
  }

  displayMap(address: string) {
    console.log('venue address ' + address);
    this.geocoder.geocode({ address }).subscribe((results: any) => {
      console.log(results);
      let location = results.results[0].geometry.location;
      console.log(location);
      this.latitude = location.lat();
      console.log(this.latitude);
      this.longitude = location.lng();
      console.log(this.longitude);
      this.center = {lat: this.latitude, lng: this.longitude}
    });
  }

  displayVenue(venue: Venue) {
    this.selectedVenue = venue;

    console.log(`${this.latitude} ${this.longitude}`);
    this.displayMap(
      venue.address.street +
        ', ' +
        venue.address.city +
        ', ' +
        venue.address.state +
        ' ' +
        venue.address.zip
    );
  }

  linkMap(event: Event, venue: Venue): void {
    this.displayVenue(venue);
  }

  loggedIn(): boolean {
    return this.auth.checkLogin();
  }

  getVenueCount(): number {
    return this.venues.length;
  }

  displayAddNewVenue(venue: Venue) {
    this.addNewVenue = venue;
  }

  displayTable() {
    this.selectedVenue = null;
  }

  addVenue(venue: Venue) {
    this.venueService.create(venue).subscribe({
      next: (result) => {
        this.newAddress = new Address();
        this.addressService.create(this.newAddress);
        this.newVenue = new Venue();
        this.loadVenue();
      },
      error: (nojoy) => {
        console.error(
          'VenueListHttpComponent.addVenue(): error creating Venue:'
        );
        console.error(nojoy);
      },
    });
  }

  displayAllVenues(): void {
    this.selectedVenue = null;
  }

  setEditVenue() {
    this.editVenue = Object.assign({}, this.selectedVenue);
  }

  updateVenue(id: number, venue: Venue) {
    this.venueService.update(id, venue).subscribe({
      next: (result) => {
        this.newVenue = new Venue();
        this.loadVenue();
        this.editVenue = null;
        this.selectedVenue = null;
      },
      error: (nojoy) => {
        console.error(
          'VenueListHttpComponent.addVenue(): error updating Venue:'
        );
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
        console.error(
          'VenueListHttpComponent.addVenue(): error deleting Venue:'
        );
        console.error(nojoy);
      },
    });
  }

  loadVenue(): void {
    this.venueService.index().subscribe({
      next: (venues) => {
        this.venues = venues;
      },
      error: (problem) => {
        console.error('VenueListComponent.loadVenue(): error loading venues:');
        console.error(problem);
      },
    });
  }

  loadAddress(): void {
    this.addressService.index().subscribe({
      next: (addresses) => {
        this.addresses = addresses;
      },
      error: (problem) => {
        console.error('VenueListComponent.loadVenue(): error loading venues:');
        console.error(problem);
      },
    });
  }


  updateAddress(address: Address, id: number) {
    console.log(address);
    this.addressService.update(id, address).subscribe({
      next: (addresses) => {
        this.selectedAddress = this.editAddress;
        this.loadAddress();
        this.editAddress = null;
      },
      error: (problem) => {
        console.error('VenueComponent.reload(): error loading venue: ');
        console.error(problem);
      },
    });
  }
}
