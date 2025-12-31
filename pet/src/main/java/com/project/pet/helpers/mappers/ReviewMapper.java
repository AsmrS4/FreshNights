package com.project.pet.helpers.mappers;

import com.project.pet.domain.entities.EventEntity;
import com.project.pet.domain.entities.ReviewEntity;
import com.project.pet.domain.entities.UserEntity;
import com.project.pet.domain.models.Review;
import com.project.pet.domain.requests.ReviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ReviewMapper {
    @Autowired
    private UserMapper userMapper;
    public ReviewEntity mapToEntity(ReviewRequest request, UserEntity author, EventEntity event) {
        return ReviewEntity.builder()
                .author(author)
                .event(event)
                .text(request.getText())
                .rating(request.getRating())
                .build();
    }

    public Review mapToReview(ReviewEntity reviewEntity) {
        Review review = new Review();
        review.setId(reviewEntity.getId());
        review.setText(reviewEntity.getText());
        review.setRating(reviewEntity.getRating());
        review.setCreateTime(reviewEntity.getCreateTime());
        review.setAuthor(userMapper.mapToShort(reviewEntity.getAuthor()));

        return review;
    }

    public Set<Review> mapToReviews(Set<ReviewEntity> reviews) {
        return reviews.stream().map(this::mapToReview).collect(Collectors.toSet());
    }
}
