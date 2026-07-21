package com.ahmed.Hadidy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name="recipe")
    private String recipe;

    @Column(name="photo")
    private String photo;

    @Column(name= "calories")
    private double calories;

    @Column(name="protein")
    private double protein;

    @Column(name ="carbs")
    private double carbs;

    @Column(name = "fats")
    private double fats;

    @Column(name = "time")
    private String time;

    @ManyToOne
    @JoinColumn(name = "diet_plan_id")
    private DietPlan dietPlan;

}
