import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';
import { Observable, catchError, throwError } from 'rxjs';
import { PartyRating } from '../models/party-rating';

@Injectable({
  providedIn: 'root'
})
export class PartyRatingService {

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

  index(): Observable<PartyRating[]> {
    return this.http.get<PartyRating[]>(this.url + 'watchparties/partyRatings', this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('PartRatingService.index(): error retrieving List of Party Ratings: ' + err)
        );
      })
    );
  }

  show(partyRatingId: number): Observable<PartyRating> {
    return this.http.get<PartyRating>(this.url + 'watchparties/partyRatings/'+ partyRatingId, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('PartyRatingService.show(): error retrieving Party Rating: ' + err)
        );
      })
    );
  }

  create(partyRating: PartyRating): Observable<PartyRating> {
    console.log(partyRating)
    return this.http.post<PartyRating>(this.url + 'watchparties/partyRatings/', partyRating, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
           () => new Error( 'PartyService.create(): error creating Party Rating: ' + err )
        );
      })
    );
  }

  update(partyRatingId: number, partyRating: PartyRating): Observable<PartyRating> {
    return this.http.put<PartyRating>(this.url + 'watchparties/partyRatings/' + partyRatingId, partyRating ,this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
           () => new Error( 'PartyRatingService.update(): error updating Party Rating: ' + err )
        );
      })
    );
  }

  destroy(partyRatingId: number) : Observable<void> {
    return this.http.delete<void>(this.url + 'watchparties/partyRatings/'+ partyRatingId, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
           () => new Error( 'PartyRatingService.destroy(): error deleting Party Rating: ' + err )
        );
      })
    );
  }
}
