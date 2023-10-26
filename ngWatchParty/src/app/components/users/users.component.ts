import { Address } from './../../models/address';
import { ActivatedRoute, Router } from '@angular/router';
import { Component } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { AuthService } from 'src/app/services/auth.service';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent {

loggedInUser: User = new User();
selectedUser: User | null = null;
editUser: User | null = null;


constructor(
  private userService: UserService,
  private activatedRoute: ActivatedRoute,
  private router: Router,
  private auth: AuthService
){}

ngOnInit(): void{
  this.setLoggedInUser();
  this.activatedRoute.paramMap.subscribe({
    next: (params) => {
      let userIdStr = params.get('userId');
      if (userIdStr){
        let userId = parseInt(userIdStr);
        if (isNaN(userId)){
          this.router.navigateByUrl("/invalidUser");
        }else{
          this.userService.show(userId).subscribe({
            next: (user) => {
              this.selectedUser = user;
            },
            error: (err) => {
              console.log('UserListHttpComponent.show(), error getting user');
              this.router.navigateByUrl("/invalidUser");
            }

          })
        }
      }
    }
  })
}

setLoggedInUser(){
  this.auth.getLoggedInUser().subscribe({
    next: (user) => {
      this.loggedInUser = user;
      console.log(user);
    },
    error: (err) => {
      console.error('UserComponent.setLoggedInUser: error getting logged in user')
    }
  });
}

setEditUser(){
  this.editUser = Object.assign({}, this.loggedInUser);
}

updateUser(user: User){
  this.userService.update(user.id, user).subscribe({
    next: (updatedUser) => {
      this.editUser = null;
      this.setLoggedInUser();
    },
    error: (problem) => {
      console.error('UsersComponenet error')
      console.log(problem);
    }
  });
}


deleteUser(id: number){
  this.userService.destroy(id).subscribe({
    next: (result) => {
      this.router.navigateByUrl('/home');
    },
    error: (err) => {
      console.error('Users Componenet: could not delete users')
      console.log(err)
    }
  })
}
}
