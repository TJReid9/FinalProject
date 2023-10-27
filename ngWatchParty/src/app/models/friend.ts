import { User } from "./user";

export class Friend {

  id: number;
  // friendStatus: FriendStatus | null = null;
  friend: User | null = null;
  user: User | null = null;

  constructor(
    id: number = 0,
    // friendStatus: FriendStatus = new FriendStatus;
    friend: User = new User,
    user: User = new User
  ){
    this.id = id;
    // this.friendStatus = friendStatus;
    this.friend = friend;
    this.user = user;
  }

}
