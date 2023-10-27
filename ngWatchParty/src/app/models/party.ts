import { Address } from "./address";
import { Team } from "./team";
import { Venue } from "./venue";

export class Party {
  id: number;
  title: string;
  partyDate: string;
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

  constructor(
    id: number = 0,
    title: string = '',
    partyDate: string = '',
    startTime: string = '',
    description: string = '',
    completed: boolean = false,
    enabled: boolean = true,
    imageUrl: string = '',
    createDate: string = '',
    updateDate: string = '',
    venue: Venue = new Venue(),
    address: Address = new Address(),
    team: Team = new Team()


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

  }
}
