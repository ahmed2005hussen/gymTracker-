package com.ahmed.Hadidy.entity;

import jakarta.persistence.*;

@Entity
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(name = "name")
    private String name;

    @Column(name ="description")
    private String description ;

    @Column(name ="repeat")
    private int repeat ;

    @Column(name ="sets")
    private int sets ;

    @Column(name ="picture")
    private String picture;


    @ManyToOne
    @JoinColumn(name = "id")
    private WorkoutDay workoutDay;


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRepeat() {
        return repeat;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public WorkoutDay getWorkoutDay() {
        return workoutDay;
    }

    public void setWorkoutDay(WorkoutDay workoutDay) {
        this.workoutDay = workoutDay;
    }
}
