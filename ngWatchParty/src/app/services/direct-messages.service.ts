import { DirectMessage } from './../models/direct-message';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { AuthService } from './auth.service';
import { environment } from 'src/environments/environment.development';


@Injectable({
  providedIn: 'root'
})
export class DirectMessagesService {

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

  index(): Observable<DirectMessage[]> {
    return this.http.get<DirectMessage[]>(this.url + 'watchparties/directMessages', this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('DirectMessageService.index(): error retrieving List of Messages: ' + err)
        );
      })
    );
  }

  show(partyCommentId: number): Observable<DirectMessage> {
    return this.http.get<DirectMessage>(this.url + 'watchparties/directMessages/'+ partyCommentId, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('PartCommentService.show(): error retrieving Party Comments: ' + err)
        );
      })
    );
  }

  create(partyComment: DirectMessage): Observable<DirectMessage> {
    console.log(partyComment)
    return this.http.post<DirectMessage>(this.url + 'watchparties/directMessages/', partyComment, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
           () => new Error( 'PartCommentService.create(): error creating Party Comments: ' + err )
        );
      })
    );
  }

  update(partyCommentId: number, partyRating: DirectMessage): Observable<DirectMessage> {
    return this.http.put<DirectMessage>(this.url + 'watchparties/directMessages/' + partyCommentId, partyRating ,this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
           () => new Error( 'PartyRatingService.update(): error updating Party Rating: ' + err )
        );
      })
    );
  }

  destroy(partyCommentId: number) : Observable<void> {
    return this.http.delete<void>(this.url + 'watchparties/directMessages/'+ partyCommentId, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
           () => new Error( 'PartyRatingService.destroy(): error deleting Party Rating: ' + err )
        );
      })
    );
  }
}
