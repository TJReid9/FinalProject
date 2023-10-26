export class Venue {
  id: number;
  name: string;
  phone: string;
  description: string;
  imageUrl: string;
  enabled: boolean;
  websiteUrl: boolean;

  constructor(
    id: number = 0,
    name: string = '',
    phone: string = '',
    description: string = '',
    imageUrl: string = '',
    enabled: boolean = true,
    websiteUrl: boolean = true

  ){
    this.id = id;
    this.name = name;
    this.phone = phone;
    this.description = description;
    this.imageUrl = imageUrl;
    this.enabled = enabled;
    this.websiteUrl = websiteUrl;
  }
}
