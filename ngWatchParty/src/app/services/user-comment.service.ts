import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment.development';
import { User } from '../models/user';
import { AuthService } from './auth.service';
import { UserComment } from '../models/user-comment';

@Injectable({
  providedIn: 'root'
})
export class UserCommentService {

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

  index(): Observable<UserComment[]> {
    return this.http.get<UserComment[]>(this.url + 'watchparties/userComments', this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('PartyService.index(): error retrieving List of Comments: ' + err)
        );
      })
    );
  }

  show(userCommentId: number): Observable<UserComment> {
    return this.http.get<UserComment>(this.url + 'watchparties/userComments/'+ userCommentId, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('UserCommentService.show(): error retrieving User Comment: ' + err)
        );
      })
    );
  }

  create(userComment: UserComment): Observable<UserComment> {
    console.log(userComment)
    return this.http.post<UserComment>(this.url + 'watchparties/userComments/', userComment, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
           () => new Error( 'UserCommentService.create(): error creating User Comments: ' + err )
        );
      })
    );
  }

  update(userCommentId: number, userComment: UserComment): Observable<UserComment> {
    return this.http.put<UserComment>(this.url + 'watchparties/userComments/' + userCommentId, userComment, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
           () => new Error( 'UserCommentService.update(): error updating User Comment: ' + err )
        );
      })
    );
  }

  destroy(userCommentId: number) : Observable<void> {
    return this.http.delete<void>(this.url + 'watchparties/userComments/'+ userCommentId, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
           () => new Error( 'UserCommentService.destroy(): error deleting User Comment: ' + err )
        );
      })
    );
  }
}
