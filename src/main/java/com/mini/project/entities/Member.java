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
public class Member extends BaseClass{
    private String name;
    private String email;
    private String phoneNumber;
    private String membershipType;

    @ManyToOne
    private Branch branch;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Loan> loans;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Fine> fines;

}
