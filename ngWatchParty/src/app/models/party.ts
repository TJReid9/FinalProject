import { Address } from "./address";
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
  constructor(
    id: number = 0,
    title: string = '',
    partyDate: Date = new Date (2023-10-30),
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

  }
}
