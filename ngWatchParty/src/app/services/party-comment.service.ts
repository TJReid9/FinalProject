import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';
import { Observable, catchError, throwError } from 'rxjs';
import { PartyComment } from '../models/party-comment';

@Injectable({
  providedIn: 'root'
})
export class PartyCommentService {

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

  index(partyId: number): Observable<PartyComment[]> {
    return this.http.get<PartyComment[]>(this.url + 'watchparties/' + partyId + '/partyComments',  this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('PartCommentService.index(): error retrieving List of Party Comments: ' + err)
        );
      })
    );
  }

  show(partyId: number, partyCommentId: number): Observable<PartyComment> {
    return this.http.get<PartyComment>(this.url + 'watchparties/' + partyId + '/partyComments/'+ partyCommentId, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('PartCommentService.show(): error retrieving Party Comments: ' + err)
        );
      })
    );
  }

  create(partyId: number, partyComment: PartyComment): Observable<PartyComment> {
    console.log(partyComment)
    return this.http.post<PartyComment>(this.url + 'watchparties/' + partyId + '/partyComments', partyComment, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
           () => new Error( 'PartCommentService.create(): error creating Party Comments: ' + err )
        );
      })
    );
  }

  update(partyId: number, partyCommentId: number): Observable<PartyComment> {
    return this.http.put<PartyComment>(this.url + 'watchparties/' + partyId + '/partyComments' + partyCommentId,this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
           () => new Error( 'PartyRatingService.update(): error updating Party Rating: ' + err )
        );
      })
    );
  }

  destroy(partyId: number, partyCommentId: number) : Observable<void> {
    return this.http.delete<void>(this.url + 'watchparties/' + partyId + '/partyComments/'+ partyCommentId, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
           () => new Error( 'PartyRatingService.destroy(): error deleting Party Rating: ' + err )
        );
      })
    );
  }
}
