import { TestBed } from '@angular/core/testing';

import { PartyRatingService } from './party-rating.service';

describe('PartyRatingService', () => {
  let service: PartyRatingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PartyRatingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
