import { Pipe, PipeTransform } from '@angular/core';
import { Team } from '../models/team';

@Pipe({
  name: 'sportsFilter'
})
export class SportsFilterPipe implements PipeTransform {

  transform(teams: Team[], sportId: number): Team[] {
    let result: Team[] = [];
    for (const team of teams) {
      if (team.sport?.id === sportId) {
        result.push(team)
      }

    }


    return result;
  }

}
