import { Pipe, PipeTransform } from '@angular/core';
import { Party } from '../models/party';

@Pipe({
  name: 'incomplete'
})
export class IncompletePipe implements PipeTransform {

  transform(parties: Party[], showComplete: boolean): Party[] {
    if(showComplete){
      return parties;
    }
    let results: Party[] = [];

    for(let party of parties){
      if(!party.completed && new Date(party.partyDate) > new Date()){
        results.push(party)
      }
    }
    return results;
  }
}
