import { User } from 'src/app/models/user';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.development';
import { AuthService } from './auth.service';
import { Observable, catchError, throwError } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  private url = environment.baseUrl + 'api/watchparties/';
  constructor(
    private http: HttpClient,
    private auth: AuthService
  ) { }

  index(): Observable<User[]> {
    return this.http.get<User[]>(this.url + 'users').pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('PartyService.index(): error retrieving List of Partys: ' + err)
        );
      })
    );
  }


  show(userId: number): Observable<User> {
    return this.http.get<User>(this.url + 'users/'+ userId).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('PartyService.show(): error retrieving Party: ' + err)
        );
      })
    );
  }

  create(party: User): Observable<User> {
    console.log(party)
    return this.http.post<User>(this.url + 'users/', party).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
           () => new Error( 'UserService.create(): error creating Party: ' + err )
        );
      })
    );
  }

  update(userId: number, user: User): Observable<User> {
    return this.http.put<User>(this.url + 'users/' + userId, user, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
           () => new Error( 'UserService.update(): error updating User: ' + err )
        );
      })
    );
  }

  destroy(userId: number) : Observable<void> {
    return this.http.delete<void>(this.url + 'users/'+ userId, this.getHttpOptions()).pipe(
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

