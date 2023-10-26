import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.development';
import { AuthService } from './auth.service';
import { Observable, catchError, throwError } from 'rxjs';
import { Address } from '../models/address';

@Injectable({
  providedIn: 'root'
})
export class AddressService {

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

  index(): Observable<Address[]> {
    return this.http.get<Address[]>(this.url + 'watchparties').pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('AddressService.index(): error retrieving List of Addresses: ' + err)
        );
      })
    );
  }

  show(addressId: number): Observable<Address> {
    return this.http.get<Address>(this.url + 'watchparties/'+ addressId, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('AddressService.show(): error retrieving Address: ' + err)
        );
      })
    );
  }

  create(address: Address): Observable<Address> {
    console.log(address)
    return this.http.post<Address>(this.url + 'watchparties', address, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
           () => new Error( 'AddressService.create(): error creating Address: ' + err )
        );
      })
    );
  }

  update(addressId: number, address: Address): Observable<Address> {
    return this.http.put<Address>(this.url + 'watchparties/' + addressId, address ,this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
           () => new Error( 'AddressService.update(): error updating Address: ' + err )
        );
      })
    );
  }

  destroy(addressId: number) : Observable<void> {
    return this.http.delete<void>(this.url + 'watchparties/'+ addressId, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
           () => new Error( 'PartyService.destroy(): error deleting Party: ' + err )
        );
      })
    );
  }

}
