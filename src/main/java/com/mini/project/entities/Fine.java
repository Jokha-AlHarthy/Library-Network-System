package com.mini.project.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Fine extends BaseClass{
    private Double amount;
    private String reason;
    private String status;
    private Date issuedDate;

    @ManyToOne
    private Member member;

    @ManyToOne
    private Loan loan;
}
