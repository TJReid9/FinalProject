import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  newUser: User = new User();

  constructor(
    private auth: AuthService,
    private router: Router,

  ){}


  register(newUser: User): void {
    console.log('Registering user:');
    console.log(newUser);
    this.auth.register(newUser).subscribe({
      next: (registeredUser) => {
        this.auth.login(newUser.username, newUser.password).subscribe({
          next: (loggedInUser) => {
            this.router.navigateByUrl('/users');
          },
          error: (problem) => {
            console.error('RegisterComponent.register(): Error logging in user:');
            console.error(problem);
          }
        });
      },
      error: (fail) => {
        console.error('RegisterComponent.register(): Error registering account');
        console.error(fail);
      }
    });
  }


}
