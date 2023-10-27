import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.development';
import { AuthService } from './auth.service';
import { Observable, catchError, throwError } from 'rxjs';
import { Venue } from '../models/venue';
import { VenueRating } from '../models/venue-rating';

@Injectable({
  providedIn: 'root'
})
export class VenueRatingService {

  private url = environment.baseUrl + 'api/';

  constructor(
    private http: HttpClient,
    private auth: AuthService
  ) { }

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

  index(): Observable<VenueRating[]> {
    return this.http.get<VenueRating[]>(this.url + 'watchparties/venueRatings', this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('VenueRatigService.index(): error retrieving List of Venue Ratings: ' + err)
        );
      })
    );
  }

  create(venueRating: VenueRating): Observable<VenueRating> {
    console.log(venueRating)
    return this.http.post<VenueRating>(this.url + 'watchparties/venueRatings', venueRating, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
           () => new Error( 'VenueRatingService.create(): error creating Venue Rating: ' + err )
        );
      })
    );
  }

  update(venueRatingId: number, venueRating: VenueRating): Observable<VenueRating> {
    return this.http.put<VenueRating>(this.url + 'watchparties/venueRatings' + venueRatingId, venueRating, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
           () => new Error( 'VenueRatingService.update(): error updating Venue Rating: ' + err )
        );
      })
    );
  }

  destroy(venueRatingId: number) : Observable<void> {
    return this.http.delete<void>(this.url + 'watchparties/venueRatings/'+ venueRatingId, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
           () => new Error( 'VenueRatingService.destroy(): error deleting Venue Rating: ' + err )
        );
      })
    );
  }
}
