package com.mini.project.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Loan extends BaseClass{
    private Date loanDate;
    private Date dueDate;
    private Date returnDate;
    private Boolean isReturned;

    @ManyToOne
    private Member member;

    @ManyToOne
    private Book book;
}
