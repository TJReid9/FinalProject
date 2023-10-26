import { Address } from "./address";

export class User {
  id: number;
  username: string;
  password: string;
  firstName: string;
  photoUrl: string;
  email: string;
  enabled: boolean;
  role: string;
  address: Address | null = null;
  createDate: string;
  updateDate: string;


  constructor(
    id: number = 0,
    username: string = '',
    password: string ='',
    email: string = '',
    enabled: boolean = true,
    role: string = 'stadard',
    address: Address | null = null,
    firstName: string = '',
    photoUrl: string = '',
    createDate: string = '',
    updateDate: string = ''
  ){
    this.id = id;
    this.username = username;
    this.password = password;
    this.email = email;
    this.enabled = enabled;
    this.role = role;
    this.address = address;
    this.createDate = createDate;
    this.updateDate = updateDate;
    this.firstName = firstName;
    this.photoUrl = photoUrl;
  }
}
