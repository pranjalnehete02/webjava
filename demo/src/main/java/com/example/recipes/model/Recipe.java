package com.example.recipes.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recipeId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String ingredients;

    @Column(nullable = false)
    private String instructions;

    @Column(nullable = false)
    private String difficultyLevel;

    @Column(nullable = false)
    private String cuisineType;

    @Column(nullable = false)
    private Long author;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate = new Date();

    // Getters and Setters
}
