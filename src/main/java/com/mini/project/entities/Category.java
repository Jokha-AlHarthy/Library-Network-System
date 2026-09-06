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
public class Category extends BaseClass{
    private String name;
    private String description;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Book> books;
}
