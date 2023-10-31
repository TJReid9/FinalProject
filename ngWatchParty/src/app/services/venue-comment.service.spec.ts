import { TestBed } from '@angular/core/testing';

import { VenueCommentService } from './venue-comment.service';

describe('VenueCommentService', () => {
  let service: VenueCommentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VenueCommentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
