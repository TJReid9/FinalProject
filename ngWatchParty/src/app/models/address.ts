export class Address {

  id: number;
  street: string;
  city: string;
  state: string;
  zip: string;
  enabled: boolean;

  constructor(
    id: number = 0,
    street: string = '',
    city: string = '',
    state: string = '',
    zip: string = '',
    enabled: boolean = true
  ){
    this.id = id;
    this.street = street;
    this.city = city;
    this.state = state;
    this.zip = zip;
    this.enabled = enabled;
  }

}
