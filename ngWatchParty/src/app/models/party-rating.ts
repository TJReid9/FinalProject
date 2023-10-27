import { Party } from "./party";
import { User } from "./user";

export class PartyRating {

  id: number;
  comment: string;
  rating: number;
  createDate: string;
  lastUpdate: string;
  party: Party | null = null;
  user: User | null = null;

  constructor(
    id: number = 0,
    comment: string = '',
    rating: number = 0,
    createDate: string = '',
    lastUpdate: string = '',
    party: Party = new Party,
    user: User = new User
  ){
    this.id = id;
    this.comment = comment;
    this.rating = rating;
    this.createDate = createDate;
    this.lastUpdate = lastUpdate;
    this.party = party;
    this.user = user;
  }
}
