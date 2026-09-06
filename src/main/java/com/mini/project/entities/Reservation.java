package com.mini.project.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Reservation extends BaseClass{
    private Date reservationDate;
    private String status;

    @ManyToOne
    private Member member;

    @ManyToOne
    private Book book;
}
