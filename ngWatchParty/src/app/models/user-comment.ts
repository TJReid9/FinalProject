import { User } from "./user";

export class UserComment {
  id: number;
  comment: string;
  photoUrl: string;
  enabled: boolean;
  createDate: string;
  updateDate: string;
  user: User | null = null;
  userCommentReply: UserComment | null = null;

  constructor(
    id: number = 0,
    comment: string = '',
    photoUrl: string = '',
    enabled: boolean = true,
    createDate: string = '',
    updateDate: string = '',
    user: User = new User,
    userCommentReply: UserComment = new UserComment
  ) {
    this.id = id;
    this. comment = comment;
    this.photoUrl = photoUrl;
    this.enabled = enabled;
    this.createDate = createDate;
    this.updateDate = updateDate;
    this.user = user;
    this.userCommentReply = userCommentReply;
  }
}
