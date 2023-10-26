import { Component } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent {
  public isCollapsed = false;

  constructor(
   private auth: AuthService,
  ){}

  loggedIn(): boolean {
    return this.auth.checkLogin();
  }

}
