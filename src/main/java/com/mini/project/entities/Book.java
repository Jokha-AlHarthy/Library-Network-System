package com.mini.project.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Book extends BaseClass{
    private String title;
    private String isbn;
    private Integer totalCopies;
    private Integer availableCopies;
    @ManyToOne
    private Branch branch;

    @ManyToOne
    private Author author;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Publisher publisher;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Loan> loans;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Review> reviews;
}
