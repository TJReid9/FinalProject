import { TestBed } from '@angular/core/testing';

import { VenueRatingService } from './venue-rating.service';

describe('VenueRatingService', () => {
  let service: VenueRatingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VenueRatingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
