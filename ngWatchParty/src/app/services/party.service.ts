import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { Party } from '../models/party';
import { environment } from 'src/environments/environment.development';
import { AuthService } from './auth.service';
import { DatePipe } from '@angular/common';
import { PartyGoer } from '../models/party-goer';

@Injectable({
  providedIn: 'root'
})
export class PartyService {

  private url = environment.baseUrl + 'api/';

  constructor(
    private http: HttpClient,
    private auth: AuthService
  ) { }

  index(): Observable<Party[]> {
    return this.http.get<Party[]>(this.url + 'watchparties', ).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('PartyService.index(): error retrieving List of Partys: ' + err)
        );
      })
    );
  }


  show(partyId: number): Observable<Party> {
    return this.http.get<Party>(this.url + 'watchparties/' + partyId, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('PartyService.show(): error retrieving Party: ' + err)
        );
      })
    );
  }

  create(party: Party): Observable<Party> {
    console.log(party)
    return this.http.post<Party>(this.url + 'watchparties', party, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
           () => new Error( 'PartyService.create(): error creating Party: ' + err )
        );
      })
    );
  }

  update(partyId: number, party: Party): Observable<Party> {
    return this.http.put<Party>(this.url + 'watchparties/' + partyId, party ,this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
           () => new Error( 'PartyService.update(): error updating Party: ' + err )
        );
      })
    );
  }

  addSelfToParty(partyId: number): Observable<PartyGoer> {
    return this.http.put<PartyGoer>(this.url + 'watchparties/' + partyId + '/partyGoers', null ,this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
           () => new Error( 'PartyService.update(): error updating Party: ' + err )
        );
      })
    );
  }

  destroy(partyId: number) : Observable<void> {
    return this.http.delete<void>(this.url + 'watchparties/'+ partyId, this.getHttpOptions()).pipe(
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
