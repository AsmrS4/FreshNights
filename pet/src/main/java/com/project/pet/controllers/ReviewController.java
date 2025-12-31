package com.project.pet.controllers;

import com.project.pet.domain.requests.ReviewRequest;
import com.project.pet.domain.responses.RatingResponse;
import com.project.pet.services.review.ReviewService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/review")
@SecurityRequirement(name = "bearerAuth")
public class ReviewController {
    private final ReviewService reviewService;
    public ReviewController(@Autowired ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    @PostMapping("/{eventId}")
    public ResponseEntity<?> createReview(@PathVariable UUID eventId, @RequestBody @Valid ReviewRequest request) throws BadRequestException {
        return ResponseEntity.ok(reviewService.createReview(eventId, request));
    }
    @GetMapping("/{eventId}")
    public ResponseEntity<?> retrieveReviews(@PathVariable UUID eventId) {
        return ResponseEntity.ok(reviewService.retrieveReviews(eventId));
    }
    @PutMapping("/{reviewId}")
    public ResponseEntity<?> updateReview(@PathVariable UUID reviewId, @RequestBody @Valid ReviewRequest request) {
        return ResponseEntity.ok(reviewService.updateReview(reviewId, request));
    }
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable UUID reviewId) {
        return ResponseEntity.ok(reviewService.deleteReview(reviewId));
    }
    @GetMapping("/{eventId}/total")
    public ResponseEntity<RatingResponse> getTotalRating(UUID eventId) {
        return ResponseEntity.ok(reviewService.getTotalRating(eventId));
    }
}
