import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment.development';
import { Friend } from '../models/friend';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class FriendService {

  private url = environment.baseUrl + 'api/';

  constructor(
    private http: HttpClient,
    private auth: AuthService
  ) { }

  index(): Observable<Friend[]> {

    return this.http.get<Friend[]>(this.url + 'watchparties/friends').pipe(

      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('FriendService.index(): error retrieving List of Friends: ' + err)
        );
      })
    );
  }

  show(friendId: number): Observable<Friend> {
    return this.http.get<Friend>(this.url + 'watchparties/friends/' + friendId).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('FriendService.show(): error retrieving Friend: ' + err)
        );
      })
    );
  }

  create(friend: Friend): Observable<Friend> {
    console.log(friend)
    return this.http.post<Friend>(this.url + 'watchparties/friends', friend, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
           () => new Error( 'FriendService.create(): error creating Friend: ' + err )
        );
      })
    );
  }

  update(friendId: number, friend: Friend): Observable<Friend> {
    return this.http.put<Friend>(this.url + 'watchparties/friends/' + friendId, friend, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
           () => new Error( 'FriendService.update(): error updating Friend: ' + err )
        );
      })
    );
  }

  destroy(friendId: number) : Observable<void> {
    return this.http.delete<void>(this.url + 'watchparties/friends/'+ friendId, this.getHttpOptions()).pipe(

      catchError((err: any) => {
        console.error(err);
        return throwError(
           () => new Error( 'PartyService.destroy(): error deleting Party: ' + err )
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

