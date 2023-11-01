import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Venue } from '../models/venue';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class VenueService {


  private url = environment.baseUrl + 'api/';

  constructor(
    private http: HttpClient,
    private auth: AuthService
  ) { }

  index(): Observable<Venue[]> {
    return this.http.get<Venue[]>(this.url + 'watchparties/venues').pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('VenueService.index(): error retrieving List of Venues: ' + err)
        );
      })
    );
  }


  show(venueId: number): Observable<Venue> {
    return this.http.get<Venue>(this.url + 'watchparties/venues/'+ venueId, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('VenueService.show(): error retrieving Venue: ' + err)
        );
      })
    );
  }

  create(venue: Venue): Observable<Venue> {
    console.log(venue)
    return this.http.post<Venue>(this.url + 'watchparties/venues', venue, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
           () => new Error( 'VenueService.create(): error creating Venue: ' + err )
        );
      })
    );
  }

  update(venueId: number, venue: Venue): Observable<Venue> {
    return this.http.put<Venue>(this.url + 'watchparties/venues/' + venueId, venue, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
           () => new Error( 'VenueService.update(): error updating Venue: ' + err )
        );
      })
    );
  }

  destroy(venueId: number) : Observable<void> {
    return this.http.delete<void>(this.url + 'watchparties/venues/'+ venueId, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
           () => new Error( 'VenueService.destroy(): error deleting Venue: ' + err )
        );
      })
    );
  }

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

}
