import { DirectMessage } from './../../models/direct-message';
import { DirectMessagesService } from './../../services/direct-messages.service';
import { AddressService } from './../../services/address.service';
import { Address } from './../../models/address';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { AuthService } from 'src/app/services/auth.service';
import { User } from 'src/app/models/user';


@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit{

loggedInUser: User = new User();
user: User = new User();
editUser: User | null = null;
editAddress: Address = new Address();
messages: Array<DirectMessage> = [];

constructor(
  private userService: UserService,
  private addressService: AddressService,
  private activatedRoute: ActivatedRoute,
  private router: Router,
  private auth: AuthService,
  private dmService: DirectMessagesService
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
                console.log(user);
                if (user != this.loggedInUser){
                  this.user = user;
                  this.loadMessages(user.id);
                }
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

loadMessages(userId: number) {
  this.dmService.index(userId).subscribe({
    next: (messages: DirectMessage[]) => {
      this.messages = messages;
      console.log(messages);
      console.log(this.messages);
    },
    error: (problem) => {
      console.error('userMessages.load(): error loading Messages:');
      console.error(problem);
    },
  });
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

setCurrentUser(userId: number){
  this.user = this.getUser(userId);
}

setEditUser(){
  this.editUser = Object.assign({}, this.loggedInUser);
}

clearEditAddress(){
  this.editAddress = new Address();
}

setEditAddress(){
  this.editAddress = Object.assign({}, this.loggedInUser.address);
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

updateAddress(address: Address){
  this.addressService.update(address.id, address).subscribe({
  next: (updatedAddress) => {
    this.loggedInUser.address = updatedAddress;
    this.editAddress = new Address();
  },
  error: (problem) => {
    console.error('UsersComponenet error')
    console.log(problem);
  }
});
}


getUser(id: number): any{
  this.userService.show(id).subscribe({
    next: (user) => {
      return user;
    },
    error: (problem) => {
      console.error('UsersComponet error');
      console.log(problem);
    }
  })
}


deleteUser(id: number){
  let user: User = this.getUser(id);
  user.enabled = false;
  this.userService.update(id, user).subscribe({
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
