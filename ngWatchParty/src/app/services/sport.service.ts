import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment.development';
import { Sport } from '../models/sport';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class SportService {

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


  index(): Observable<Sport[]> {
    return this.http.get<Sport[]>(this.url + 'watchparties/sports', this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('SportService.index(): error retrieving List of Sport: ' + err)
        );
      })
    );
  }


  show(sportId: number): Observable<Sport> {

    return this.http.get<Sport>(this.url + 'watchparties/sports/'+ sportId, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('SportService.show(): error retrieving Sport: ' + err)
        );
      })
    );
  }

  create(sport: Sport): Observable<Sport> {
    console.log(Sport)
    return this.http.post<Sport>(this.url + 'watchparties/sports/', sport, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
           () => new Error( 'SportService.create(): error creating Sport: ' + err )
        );
      })
    );
  }

  update(sportId: number, sport: Sport): Observable<Sport> {
    return this.http.put<Sport>(this.url + 'watchparties/sports/' + sportId, sport, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
           () => new Error( 'SportService.update(): error updating Sport: ' + err )
        );
      })
    );
  }

  destroy(sportId: number) : Observable<void> {
    return this.http.delete<void>(this.url + 'watchparties/sports/'+ sportId, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
           () => new Error( 'SportService.destroy(): error deleting Sport: ' + err )
        );
      })
    );
  }
}

