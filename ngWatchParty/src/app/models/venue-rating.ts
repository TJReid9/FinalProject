import { User } from "./user";
import { Venue } from "./venue";

export class VenueRating {
  id: number;
  rating: number;
  comment: string;
  createDate: string;
  updateDate: string;
  venue: Venue | null = null;
  user: User | null = null;

  constructor(
  id: number = 0,
  rating: number = 0,
  comment: string = '',
  createDate: string = '',
  updateDate: string = '',
  venue: Venue = new Venue,
  user: User = new User
  ){
    this.id = id;
    this.rating = rating;
    this.comment = comment;
    this.createDate = createDate;
    this.updateDate = updateDate;
    this.venue = venue;
    this.user = user;
  }

}
