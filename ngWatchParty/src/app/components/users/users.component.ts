import { DirectMessagesService } from './../../services/direct-messages.service';
import { AddressService } from './../../services/address.service';
import { Address } from './../../models/address';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { AuthService } from 'src/app/services/auth.service';
import { User } from 'src/app/models/user';
import { DirectMessage } from 'src/app/models/direct-message';
import { loadTranslations } from '@angular/localize';
import { FriendStatus } from 'src/app/models/friend-status';
import { Friend } from 'src/app/models/friend';
import { FriendService } from 'src/app/services/friend.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit{

loggedInUser: User = new User();
selectedUser: User | null = null;
editUser: User | null = null;
editAddress: Address = new Address();
messages: DirectMessage[] = []
sortedMessage: DirectMessage[][] = [];
friend: Friend = new Friend;
newFriend: Friend = new Friend;
addNewFriend: Friend | null = null;
friends: Friend[] = [];


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
sortMessage(messages: DirectMessage[], user: User){
  messages.forEach(message => {
    console.log(user.id);
    console.log(message.recipient.id);
    if (message.sender.id == user.id || message.recipient.id ==  user.id){
    console.log(message);
    if (this.sortedMessage.length = 0){
      let messageOfTwo: DirectMessage[] = [];
        messageOfTwo.push(message);
      this.sortedMessage.push(messageOfTwo);
    }else{
      this.sortedMessage.forEach(messages=> {
        if((messages[0].recipient.id == message.recipient.id && messages[0].sender === message.recipient) ||
        (messages[0].sender === message.recipient && messages[0].recipient === message.recipient)){
          messages.push(message);
        }else{
          let messageOfTwo: DirectMessage[] = [];
          messageOfTwo.push(message);
          this.sortedMessage.push(messageOfTwo);
        }

      });
    }
  }
});

}

loadMessages() {
  this.dmService.index().subscribe({
    next: (messages) => {
      this.messages = messages;
      console.log(this.sortedMessage)
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
      this.loadMessages();
      this.sortMessage(this.messages, user);
    },
    error: (err) => {
      console.error('UserComponent.setLoggedInUser: error getting logged in user')
    }
  });
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


addFriend(newFriend: Friend): void {
  console.log(newFriend);
  this.friendService.create(newFriend).subscribe({
    next: (result) => {
       this.selectedUser = this.editUser;
       this.friend = new Friend();
        this.setLoggedInUser();
    },
    error: (nojoy) => {
      console.error('UserComponent.addFriend(): error adding Friend: ');
      console.error(nojoy);
    },
  });
}

displayNewFriendForm(friend: Friend){
  this.addNewFriend = friend;
}

}

