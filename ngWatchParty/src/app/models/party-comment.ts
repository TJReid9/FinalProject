import { Party } from "./party";
import { User } from "./user";

export class PartyComment {

  id: number;
  comment: string;
  photoUrl: string;
  enabled: boolean;
  createDate: string;
  updateDate: string;
  party: Party;
  user: User;
  // partyCommentReply: PartyComment | null = null;

  constructor(
  id: number = 0,
  comment: string = '',
  photoUrl: string = '',
  enabled: boolean = true,
  createDate: string = '',
  updateDate: string = '',
  party: Party = new Party(),
  user: User = new User(),
  // partyCommentReply: PartyComment = new PartyComment()

  ){
    this.id = id;
    this.comment = comment;
    this.photoUrl = photoUrl;
    this.enabled = enabled;
    this.createDate = createDate;
    this.updateDate = updateDate;
    this.party = party;
    this.user = user;
    // this.partyCommentReply = partyCommentReply;
  }
}
