package com.mini.project.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Review extends BaseClass{
    private Integer rating;
    private String comment;
    private Date reviewDate;

    @ManyToOne
    private Member member;

    @ManyToOne
    private Book book;
}
