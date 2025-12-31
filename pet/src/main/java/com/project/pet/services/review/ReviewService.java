package com.project.pet.services.review;

import com.project.pet.domain.models.Review;
import com.project.pet.domain.requests.ReviewRequest;
import com.project.pet.domain.responses.RatingResponse;
import org.apache.coyote.BadRequestException;

import java.util.Set;
import java.util.UUID;

public interface ReviewService {
    Review createReview(UUID eventId, ReviewRequest request) throws BadRequestException;
    Set<Review> retrieveReviews(UUID eventId);
    Review updateReview(UUID reviewId, ReviewRequest request);
    Review deleteReview(UUID reviewId);
    RatingResponse getTotalRating(UUID eventId);
}
