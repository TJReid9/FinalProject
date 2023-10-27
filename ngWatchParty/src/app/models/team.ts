import { Sport } from './sport';
export class Team {

  id: number;
  name: string;
  logoUrl: string;
  teamWebsiteUrl: string;
  sport: Sport | null = null;

  constructor(
    id: number = 0,
    name: string = '',
    logoUrl: string = '',
    teamWebsiteUrl: string = '',
    sport: Sport = new Sport
  ){
    this.id = id;
    this.name = name;
    this.logoUrl = logoUrl;
    this.teamWebsiteUrl = teamWebsiteUrl;
    this.sport = sport;

  }
}
