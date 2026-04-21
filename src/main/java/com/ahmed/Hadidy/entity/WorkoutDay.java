package com.ahmed.Hadidy.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
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

    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_plan_id")
    private WorkoutPlan workoutPlan;


    @OneToMany(mappedBy = "workoutDay")
    private Set<Exercise> exercises = new HashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalExercises() {
        return totalExercises;
    }

    public void setTotalExercises(int totalExercises) {
        this.totalExercises = totalExercises;
    }

    public int getTotalRepeat() {
        return totalRepeat;
    }

    public void setTotalRepeat(int totalRepeat) {
        this.totalRepeat = totalRepeat;
    }

    public double getExpectedTime() {
        return expectedTime;
    }

    public void setExpectedTime(double expectedTime) {
        this.expectedTime = expectedTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public WorkoutPlan getWorkoutPlan() {
        return workoutPlan;
    }

    public void setWorkoutPlan(WorkoutPlan workoutPlan) {
        this.workoutPlan = workoutPlan;
    }

    public Set<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(Set<Exercise> exercises) {
        this.exercises = exercises;
    }
}
