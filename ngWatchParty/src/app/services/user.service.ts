import { User } from 'src/app/models/user';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';
import { Observable, catchError, throwError } from 'rxjs';
import { Friend } from '../models/friend';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  private url = environment.baseUrl + 'api/';
  constructor(
    private http: HttpClient,
    private auth: AuthService
  ) { }

  index(): Observable<User[]> {

    return this.http.get<User[]>(this.url + 'watchparties/users/', this.getHttpOptions()).pipe(

      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('PartyService.index(): error retrieving List of Partys: ' + err)
        );
      })
    );
  }


  show(userId: number): Observable<User> {

    return this.http.get<User>(this.url + 'watchparties/users/' + userId, this.getHttpOptions()).pipe(

      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('UserService.show(): error retrieving User: ' + err)
        );
      })
    );
  }

  create(user: User): Observable<User> {
    console.log(user)

    return this.http.post<User>(this.url + 'watchparties/users', user).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
           () => new Error( 'UserService.create(): error creating User: ' + err )
        );
      })
    );
  }

  update(userId: number, user: User): Observable<User> {
    user.friends = [];
    return this.http.put<User>(this.url + 'watchparties/users/' + userId, user, this.getHttpOptions()).pipe(

      catchError((err: any) => {
        console.error(err);
        return throwError(
           () => new Error( 'UserService.update(): error updating User: ' + err )
        );
      })
    );
  }

  destroy(userId: number) : Observable<void> {

    return this.http.delete<void>(this.url + 'watchparties/users/'+ userId, this.getHttpOptions()).pipe(

      catchError((err: any) => {
        console.error(err);
        return throwError(
           () => new Error( 'PartyService.destroy(): error deleting Party: ' + err )
        );
      })
    );
  }

  addFriend(friendUserId: number): Observable<Friend> {
    return this.http.post<Friend>(this.url + 'watchparties/' + friendUserId + '/friends', null, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
           () => new Error( 'FriendService.create(): error creating Friend: ' + err )
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

