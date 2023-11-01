import { Component } from '@angular/core';
import { Route, Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent {

  loggedInUser: User = new User();

  user: User = new User;
  newUser: User | null = null;
  public isCollapsed = true;

  constructor(
   private auth: AuthService, private router: Router,
   private userService: UserService
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

  setLoggedInUser(){
    this.auth.getLoggedInUser().subscribe({
      next: (user) => {
        this.loggedInUser = user;

        if (this.user.id == 0){
          this.user = this.loggedInUser;
          }
      },
      error: (err) => {
        console.error('UserComponent.setLoggedInUser: error getting logged in user')
      }
    });
  }
}
