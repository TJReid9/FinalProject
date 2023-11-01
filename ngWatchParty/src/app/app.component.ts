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

}
