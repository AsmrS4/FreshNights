package com.project.pet.services.review;

import com.project.pet.dao.BookingRepository;
import com.project.pet.dao.EventRepository;
import com.project.pet.dao.ReviewRepository;
import com.project.pet.domain.entities.BookingEntity;
import com.project.pet.domain.entities.EventEntity;
import com.project.pet.domain.entities.ReviewEntity;
import com.project.pet.domain.entities.UserEntity;
import com.project.pet.domain.models.Review;
import com.project.pet.domain.requests.ReviewRequest;
import com.project.pet.domain.responses.RatingResponse;
import com.project.pet.helpers.mappers.ReviewMapper;
import com.project.pet.helpers.user.UserContextManager;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{
    private final ReviewRepository reviewRepository;
    private final EventRepository eventRepository;
    private final BookingRepository bookingRepository;
    private final ReviewMapper reviewMapper;
    @Override
    public Review createReview(UUID eventId, ReviewRequest request) throws BadRequestException {
        EventEntity event = findEventById(eventId);
        if(!couldWriteReview(eventId)) {
            throw new AccessDeniedException("Вы не можете написать отзыв. Отсутствуют данные о билете на мероприятие");
        }
        UserEntity user = UserContextManager.getUserFromContext();
        ReviewEntity newReview = reviewMapper.mapToEntity(request, user, event);
        try {
            reviewRepository.save(newReview);
        } catch (Exception ex) {
            throw new BadRequestException("Вы можете написать не более одного отзыва к этому мероприятию");
        }
        return reviewMapper.mapToReview(newReview);
    }

    @Override
    public Set<Review> retrieveReviews(UUID eventId) {
        EventEntity event = findEventById(eventId);
        return reviewMapper.mapToReviews(event.getReviews());
    }

    @Override
    public Review updateReview(UUID reviewId, ReviewRequest request) {
        ReviewEntity reviewEntity = findReviewById(reviewId);
        reviewEntity.setText(request.getText());
        reviewEntity.setRating(reviewEntity.getRating());
        reviewRepository.save(reviewEntity);
        return reviewMapper.mapToReview(reviewEntity);
    }

    @Override
    public Review deleteReview(UUID reviewId) {
        ReviewEntity reviewEntity = findReviewById(reviewId);
        reviewRepository.delete(reviewEntity);
        return null;
    }

    @Override
    public RatingResponse getTotalRating(UUID eventId) {
        EventEntity event = findEventById(eventId);
        int reviewsCount = event.getReviews().size();
        int ratingCount = event.getReviews()
                .stream()
                .map(ReviewEntity::getRating)
                .reduce(0, Integer::sum);
        return new RatingResponse((double) ratingCount /reviewsCount);
    }

    private ReviewEntity findReviewById(UUID reviewId) {
        return  reviewRepository.findById(reviewId).orElseThrow(
                () -> new NoSuchElementException("Отзыв не найден")
        );
    }

    private boolean couldWriteReview(UUID eventId) {
        EventEntity event = findEventById(eventId);
        UserEntity user = UserContextManager.getUserFromContext();
        BookingEntity booking = bookingRepository.findBookingByOwnerAndEvent(user, event).orElse(null);

        return booking != null;
    }

    private EventEntity findEventById(UUID eventId) {
        return eventRepository.findEventById(eventId).orElseThrow(
                ()-> new NoSuchElementException("Событие не найдено")
        );
    }
}
