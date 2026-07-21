package com.ahmed.Hadidy.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(name = "name")
    private String name ;

    @Column(name = "total_exercises")
    private int totalExercises;

    @Column(name = "total_repeat")
    private int totalRepeat;

    @Column(name = "expected_time")
    private double expectedTime ;

    @Column(name = "description")
    private String description ;

    @Column(name = "image")
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_plan_id")
    private WorkoutPlan workoutPlan;


    @OneToMany(mappedBy = "workoutDay")
    private Set<Exercise> exercises = new HashSet<>();

}
