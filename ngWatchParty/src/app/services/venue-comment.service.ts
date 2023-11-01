import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class VenueCommentService {
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
}

//   index(): Observable<VenueComment[]> {
//     return this.http.get<VenueComment[]>(this.url + 'watchparties/partyComments').pipe(
//       catchError((err: any) => {
//         console.log(err);
//         return throwError(
//           () => new Error('PartCommentService.index(): error retrieving List of Venue Comments: ' + err)
//         );
//       })
//     );
//   }

//   show(venueCommentId: number): Observable<VenueComment> {
//     return this.http.get<VenueComment>(this.url + 'watchparties/partyComments/'+ venueCommentId, this.getHttpOptions()).pipe(
//       catchError((err: any) => {
//         console.log(err);
//         return throwError(
//           () => new Error('PartCommentService.show(): error retrieving Venue Comments: ' + err)
//         );
//       })
//     );
//   }

//   create(partyComment: VenueComment): Observable<VenueComment> {
//     console.log(partyComment)
//     return this.http.post<VenueComment>(this.url + 'watchparties/partyComments/', partyComment, this.getHttpOptions()).pipe(
//       catchError((err: any) => {
//         console.error(err);
//         return throwError(
//            () => new Error( 'PartCommentService.create(): error creating Venue Comments: ' + err )
//         );
//       })
//     );
//   }

//   update(venueCommentId: number, partyRating: VenueComment): Observable<VenueComment> {
//     return this.http.put<VenueComment>(this.url + 'watchparties/partyRatings/' + venueCommentId, partyRating ,this.getHttpOptions()).pipe(
//       catchError((err: any) => {
//         console.error(err);
//         return throwError(
//            () => new Error( 'VenueRatingService.update(): error updating Venue Rating: ' + err )
//         );
//       })
//     );
//   }

//   destroy(venueCommentId: number) : Observable<void> {
//     return this.http.delete<void>(this.url + 'watchparties/partyRatings/'+ venueCommentId, this.getHttpOptions()).pipe(
//       catchError((err: any) => {
//         console.error(err);
//         return throwError(
//            () => new Error( 'VenueRatingService.destroy(): error deleting Venue Rating: ' + err )
//         );
//       })
//     );
//   }
// }d
