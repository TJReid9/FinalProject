export class Venue {
  id: number;
  name: string;
  phone: string;
  description: string;
  imageUrl: string;
  enabled: boolean;
  websiteUrl: string;

  constructor(
    id: number = 0,
    name: string = '',
    phone: string = '',
    description: string = '',
    imageUrl: string = '',
    enabled: boolean = true,
    websiteUrl: string = ''

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
