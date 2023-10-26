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
    updateDate: string = ''

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

  }
}
