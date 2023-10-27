import { Address } from "./address";

export class Venue {
  id: number;
  name: string;
  phone: string;
  description: string;
  imageUrl: string;
  enabled: boolean;
  websiteUrl: string;
  address: Address;


  constructor(
    id: number = 0,
    name: string = '',
    phone: string = '',
    description: string = '',
    imageUrl: string = '',
    enabled: boolean = true,
    websiteUrl: string = '',
    address: Address = new Address

  ){
    this.id = id;
    this.name = name;
    this.phone = phone;
    this.description = description;
    this.imageUrl = imageUrl;
    this.enabled = enabled;
    this.websiteUrl = websiteUrl;
    this.address = address;
  }
}
