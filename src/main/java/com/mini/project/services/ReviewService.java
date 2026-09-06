package com.mini.project.services;

import com.mini.project.entities.Author;
import com.mini.project.entities.Review;
import com.mini.project.exceptions.ResourceNotFoundException;
import com.mini.project.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    ReviewRepository reviewRepository;
    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    //Add service
    public Long addReview(Integer rating, String comment, Date reviewDate){
        Review review =  new Review();
        review.setIsActive(true);
        review.setCreatedDate(new Date());
        review.setRating(rating);
        review.setComment(comment);
        review.setReviewDate(reviewDate);
        review = reviewRepository.save(review);
        return review.getId();
    }

    //Get All Reviews service
    public List<Review> getAllReviews() {
        return reviewRepository.getAllReviews();
    }

    //Get Review By Id service
    public Review getById(Long id) {
        Optional<Review> review = reviewRepository.findById(id);
        if (review.isPresent() && review.get().getIsActive()) {
            return review.get();
        }
        throw new ResourceNotFoundException("Review not found with id: " + id);
    }

    //Update service
    public Review updateReview(Long id, Integer updateRating, String updateComment, Date updateReviewDate) throws Exception{
        Review reviewToUpdate = reviewRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Review not found with id: " + id));

        if (!reviewToUpdate.getIsActive()) {
            throw new ResourceNotFoundException(
                    "Review not found with id: " + id);
        }
        reviewToUpdate.setUpdatedDate(new Date());
        reviewToUpdate.setRating(updateRating);
        reviewToUpdate.setComment(updateComment);
        reviewToUpdate.setReviewDate(updateReviewDate);
        reviewToUpdate = reviewRepository.save(reviewToUpdate);
        return reviewToUpdate;
    }

    //Delete service
    public Boolean deleteById(Long id){
        Review deleteReview = reviewRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Review not found with id: " + id));

        if (!deleteReview.getIsActive()) {
            throw new ResourceNotFoundException(
                    "Review not found with id: " + id);
        }
        deleteReview.setIsActive(false);
        deleteReview.setUpdatedDate(new Date());
        reviewRepository.save(deleteReview);
        return true;
    }

    public Double getAverageRating(Long bookId) {
        return reviewRepository.getAverageRating(bookId);
    }
}
