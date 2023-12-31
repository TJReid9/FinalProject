import { DatePipe } from '@angular/common';
import { User } from "./user";

export class DirectMessage {
  id: number;
  createDate: Date;
  content: string;
  sender: User;
  recipient: User;

  constructor(
    id: number = 0,
    createDate: Date = new Date(),
    content: string = '',
    sender: User = new User(),
    recipient: User = new User
  ){
    this.id = id;
    this.createDate = createDate;
    this.content = content;
    this.sender = sender;
    this.recipient = recipient;
  }
}
