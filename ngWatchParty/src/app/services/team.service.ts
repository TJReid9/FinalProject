import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Team } from '../models/team';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class TeamService {

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


  index(): Observable<Team[]> {
    return this.http.get<Team[]>(this.url + 'watchparties/teams').pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('PartyService.index(): error retrieving List of Partys: ' + err)
        );
      })
    );
  }


  show(teamId: number): Observable<Team> {

    return this.http.get<Team>(this.url + 'watchparties/teams/'+ teamId, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('TeamService.show(): error retrieving Team: ' + err)
        );
      })
    );
  }

  create(team: Team): Observable<Team> {
    console.log(Team)
    return this.http.post<Team>(this.url + 'watchparties/teams/', team, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
           () => new Error( 'TeamService.create(): error creating Team: ' + err )
        );
      })
    );
  }

  update(teamId: number, team: Team): Observable<Team> {
    return this.http.put<Team>(this.url + 'watchparties/teams/' + teamId, team, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
           () => new Error( 'TeamService.update(): error updating Team: ' + err )
        );
      })
    );
  }

  destroy(teamId: number) : Observable<void> {
    return this.http.delete<void>(this.url + 'watchparties/teams/'+ teamId, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
           () => new Error( 'PartyService.destroy(): error deleting Party: ' + err )
        );
      })
    );
  }

  removeFavTeam(teamId: number, userId: number) : Observable<void> {
    return this.http.put<void>(this.url + 'watchparties/users/'+ userId + '/teams/' + teamId, null, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
           () => new Error( 'TeamsService.Put(): error Removing Favorite Team: ' + err )
        );
      })
    );
  }
}
