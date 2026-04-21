package com.ahmed.Hadidy.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
public class WorkoutPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(name = "name")
    private String name ;

    @Column(name ="description")
    private String description ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "generate_date")
    private LocalDate generateDate ;

    @OneToMany(mappedBy = "workoutPlan")
    private Set<WorkoutDay> workoutDays = new HashSet<>();

    @Column(name = "is_public")
    private boolean isPublic ;


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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getGenerateDate() {
        return generateDate;
    }

    public void setGenerateDate(LocalDate generateDate) {
        this.generateDate = generateDate;
    }

    public Set<WorkoutDay> getWorkoutDays() {
        return workoutDays;
    }

    public void setWorkoutDays(Set<WorkoutDay> workoutDays) {
        this.workoutDays = workoutDays;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }
}
