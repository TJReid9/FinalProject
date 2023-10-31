import { Address } from "./address";
import { PartyComment } from "./party-comment";
import { PartyGoer } from "./party-goer";
import { Team } from "./team";
import { User } from "./user";
import { Venue } from "./venue";

export class Party {
  id: number;
  title: string;
  partyDate: Date;
  startTime: string;
  description: string;
  completed: boolean;
  enabled: boolean;
  imageUrl: string;
  createDate: string;
  updateDate: string;
  venue: Venue;
  address: Address;
  team: Team;
  user: User;
  partyGoers: PartyGoer[];
  partyComments: PartyComment[];
  userId: number;


  constructor(
    id: number = 0,
    title: string = '',
    partyDate: Date = new Date (),
    startTime: string = '',
    description: string = '',
    completed: boolean = false,
    enabled: boolean = true,
    imageUrl: string = '',
    createDate: string = '',
    updateDate: string = '',
    venue: Venue = new Venue(),
    address: Address = new Address(),
    team: Team = new Team(),
    user: User = new User(),
    partyGoers: PartyGoer[] = [],
    partyComments: PartyComment[] = [],
    userId: number = 0
  ){
    this.id = id;
    this.title = title;
    this.partyDate = partyDate;
    this.startTime = startTime;
    this.description = description;
    this.completed = completed;
    this.enabled = enabled;
    this.imageUrl = imageUrl;
    this.createDate = createDate;
    this.updateDate = updateDate;
    this.venue = venue;
    this.address = address;
    this.team = team;
    this.user = user;
    this.partyGoers = partyGoers;
    this.partyComments = partyComments;
    this.userId = userId;
  }
}
