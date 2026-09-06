package com.mini.project.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Branch {
    private String name;
    private String location;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Books> books;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Members> members;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Staff> staffs;
}
