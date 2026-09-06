package com.mini.project.repositories;

import com.mini.project.entities.Author;
import com.mini.project.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("SELECT r FROM Review r WHERE r.isActive=true")
    List<Review> getAllReviews();

    @Query("SELECT r FROM Review r WHERE r.isActive=true AND r.id=:review")
    Review getById(@Param("review") Long id);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.book.id=:bookId AND r.isActive=true")
    Double getAverageRating(@Param("bookId") Long bookId);
}
