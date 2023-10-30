export class PartyGoer {
  id: number;
  userId: number;
  partyId: number;

  constructor(
    id: number = 0,
    userId: number = 0,
  partyId: number = 0
  ){
    this.id = id;
    this.partyId = partyId;
    this.userId = userId;
  }


}
