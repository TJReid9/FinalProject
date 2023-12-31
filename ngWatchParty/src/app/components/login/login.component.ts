import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  loginUser: User = new User();

  constructor(private auth: AuthService, private router: Router) {}

  login(loginUser: User): void {
    console.log('logging in');
    console.log(loginUser);
    this.auth.login(loginUser.username, loginUser.password).subscribe({
      next: (loggedInUser) => {
        this.router.navigateByUrl('/users');
      },

      error: (denied) => {
        console.error('LoginComponent.login: Error logging in user:');
        console.error('');
      },
    });
  }

}
