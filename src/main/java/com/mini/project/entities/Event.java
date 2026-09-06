package com.mini.project.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Event extends BaseClass{
    private String title;
    private Date eventDate;
    private String description;

    @ManyToOne
    private Branch branch;
}
