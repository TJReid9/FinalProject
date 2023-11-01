import { DirectMessage } from 'src/app/models/direct-message';
import { DirectMessagesService } from './../../services/direct-messages.service';
import { AddressService } from './../../services/address.service';
import { Address } from './../../models/address';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { AuthService } from 'src/app/services/auth.service';
import { User } from 'src/app/models/user';
import { loadTranslations } from '@angular/localize';
import { FriendStatus } from 'src/app/models/friend-status';
import { Friend } from 'src/app/models/friend';
import { FriendService } from 'src/app/services/friend.service';
import { ParsedVariable } from '@angular/compiler';
import { FriendId } from 'src/app/models/friend-id';


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
messages: DirectMessage[] = []
sortedMessage: DirectMessage[][] = [];
friend: Friend = new Friend;
newFriend: Friend = new Friend;
friends: Friend[] = [];
newAddress: Address | null = null;

// messages: Array<DirectMessage> = [];
newMessage: DirectMessage = new DirectMessage();
editMessage: DirectMessage = new DirectMessage();
// selectedUser: User | null;

constructor(
  private userService: UserService,
  private addressService: AddressService,
  private activatedRoute: ActivatedRoute,
  private router: Router,
  private auth: AuthService,
  private dmService: DirectMessagesService,
  private friendService: FriendService
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
      this.messages.sort((b, a) => new Date(a.createDate).getTime() - new Date(b.createDate).getTime());
      console.log(messages);
      console.log(this.messages);
    },
    error: (problem) => {
      console.error('userMessages.load(): error loading Messages:');
      console.error(problem);
    },
  });
}

displayAddAddress(){
  this.newAddress = new Address();
  this.editUser = null;
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

setEditMessage(message: DirectMessage){
  this.editMessage = Object.assign({}, message)
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

addAddress(address: Address){
  this.addressService.create(address).subscribe({
  next: (createdAddress) => {
    this.loggedInUser.address = createdAddress;
    this.updateUser(this.loggedInUser);
    this.newAddress = null;
  },
  error: (problem) => {
    console.error('UsersComponenet error')
    console.log(problem);
  }
});
}

updateMessage(message: DirectMessage){
  this.dmService.update(message.id, message).subscribe({
  next: (updatedAddress) => {
    this.loadMessages(this.user.id);
    this.editMessage = new DirectMessage();
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

  this.loggedInUser.enabled = false;
  this.userService.update(this.loggedInUser.id, this.loggedInUser).subscribe({
    next: (result) => {

      this.logout();

    },
    error: (err) => {
      console.error('Users Componenet: could not delete users')
      console.log(err)
    }
  })
}


addFriend(newFriend: Friend): void {
  console.log(newFriend);
  this.friendService.create(newFriend).subscribe({
    next: (result) => {
       this.friend = new Friend();
        this.setLoggedInUser();
    },
    error: (nojoy) => {
      console.error('UserComponent.addFriend(): error adding Friend: ');
    }
  })
}


displayAddMessage(){
this.newMessage.sender = this.loggedInUser;
this.newMessage.recipient = this.user;
}

addMessage(onmessage: DirectMessage): void {
  console.log(onmessage);
  this.dmService.create(onmessage).subscribe({
    next: (result) => {
      this.newMessage = new DirectMessage();
      this.loadMessages(this.user.id);
    },
    error: (nojoy) => {
      console.error(
        'PartiesComponent.reloadParties(): error loading party: '
      );
      console.error(nojoy);
    },
  });
}


deleteMessage(id: number) {
  this.dmService.destroy(id).subscribe({
    next: (result) => {
      this.loadMessages(this.user.id);
    },
    error: (nojoy) => {
      console.error('PartiesComponent.reloadParties(): error loading party:');
      console.error(nojoy);
    },
  });
}

logout() {
  console.log('Logging out.');
  localStorage.removeItem('credentials');
  this.auth.logout();
  this.router.navigateByUrl('/home');
}

}


