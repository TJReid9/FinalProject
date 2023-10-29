import { Component } from '@angular/core';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'ngWatchParty';
  lat = 51.678418;
  lng = 7.809007;

  display: any;
    center: google.maps.LatLngLiteral = {
        lat: this.lat,
        lng: this.lng,
    };
    zoom = 6;

  constructor(private auth: AuthService){}
  ngOnInit() {
    this.tempTestDeleteMeLater(); // DELETE LATER!!!
  }

  tempTestDeleteMeLater() {
    this.auth.login('admin','test').subscribe({ // change username to match DB
      next: (data) => {
        console.log('Logged in:');
        console.log(data);
      },
      error: (fail) => {
        console.error('Error authenticating:')
        console.error(fail);
      }
    });
  }
}
