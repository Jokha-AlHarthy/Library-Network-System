package com.mini.project.controllers;

import com.mini.project.entities.Review;
import com.mini.project.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("review")
public class ReviewController {
    ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("add")
    public Long addReview(@RequestParam Integer rating,
                          @RequestParam String comment,
                          @RequestParam Date reviewDate) {
        return reviewService.addReview(rating, comment, reviewDate);
    }

    @GetMapping("getAll")
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("getById")
    public Review getById(@RequestParam Long id) {
        return reviewService.getById(id);
    }

    @PutMapping("update")
    public Review updateReview(@RequestParam Long id,
                               @RequestParam Integer updateRating,
                               @RequestParam String updateComment,
                               @RequestParam Date updateReviewDate) throws Exception {
        return reviewService.updateReview(id, updateRating,
                updateComment, updateReviewDate);
    }

    @DeleteMapping("deleteById")
    public Boolean deleteReview(@RequestParam Long id) {
        return reviewService.deleteById(id);
    }
}
