import { Party } from "./party";
import { User } from "./user";

export class PartyGoer {
  id: number;
  userId: number;
  partyId: number;
  user: User;
  party: Party;

  constructor(
    id: number = 0,
    userId: number = 0,
  partyId: number = 0,
  user: User = new User(),
  party: Party = new Party()
  ){
    this.id = id;
    this.partyId = partyId;
    this.userId = userId;
    this.user = user;
    this.party = party;
  }


}
