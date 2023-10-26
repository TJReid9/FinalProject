import { Component } from '@angular/core';
import { Route, Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent {
  public isCollapsed = false;

  constructor(
   private auth: AuthService, private router: Router
  ){}

  loggedIn(): boolean {
    return this.auth.checkLogin();
  }


  goToLogin() {
    this.router.navigateByUrl('/login')
  }

  goToRegister() {
    this.router.navigateByUrl('/register')
  }
}
