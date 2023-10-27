import { TestBed } from '@angular/core/testing';

import { PartyCommentService } from './party-comment.service';

describe('PartyCommentService', () => {
  let service: PartyCommentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PartyCommentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
