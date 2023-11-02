import { FriendId } from "./friend-id";
import { FriendStatus } from "./friend-status";
import { User } from "./user";

export class Friend {

  id: FriendId;
  friendStatus: FriendStatus;
  friend: User;
  user: User;

  constructor(
    id: FriendId = new FriendId,
    friendStatus: FriendStatus = new FriendStatus,
    friend: User = new User(),
    user: User = new User()
  ){
    this.id = id;
    this.friendStatus = friendStatus;
    this.friend = friend;
    this.user = user;
  }

}
