package com.mini.project.controllers;

import com.mini.project.dto.ReviewDTO;
import com.mini.project.entities.Review;
import com.mini.project.services.ReviewService;
import jakarta.validation.Valid;
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
    public Long addReview(@Valid @RequestBody ReviewDTO dto) {
        return reviewService.addReview(
                dto.getRating(),
                dto.getComment(),
                dto.getReviewDate());
    }

    @GetMapping("getAll")
    public List<ReviewDTO> getAllReviews() {
        List<ReviewDTO> reviews = ReviewDTO.convertToDTO(reviewService.getAllReviews());
        return reviews;
    }

    @GetMapping("getById")
    public ReviewDTO getById(@RequestParam Long id) {
        return ReviewDTO.convertToDTO(reviewService.getById(id));
    }

    @PutMapping("update")
    public ReviewDTO updateReview(@Valid @RequestBody ReviewDTO dto) throws Exception {
        return ReviewDTO.convertToDTO(reviewService.updateReview(
                dto.getReviewId(),
                dto.getRating(),
                dto.getComment(),
                dto.getReviewDate()));
    }

    @DeleteMapping("deleteById")
    public Boolean deleteReview(@RequestParam Long id) {
        return reviewService.deleteById(id);
    }
}
