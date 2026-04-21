package com.ahmed.Hadidy.entity;
import com.ahmed.Hadidy.Role;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id ;

    @Column(name = "username")
    private String username ;

    @Column(name = "gmail")
    private String gmail ;

    @Column(name = "password")
    private String password ;

    @OneToMany(cascade = CascadeType.ALL)
    private Role role ;

    @OneToOne
    @JoinColumn(name = "profile_id")
    private Profile profile ;


    @OneToMany(mappedBy = "user")
    private Set<WorkoutPlan> workoutPlan = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<DietPlan> dietPlan = new HashSet<>() ;

    @OneToMany(mappedBy = "user")
    private Set<Supplement> supplement = new HashSet<>() ;

    @OneToMany(mappedBy = "user")
    private Set<ProgressTracking> progressTracking = new HashSet<>() ;

    @Column(name = "is_active")
    private boolean isActive ;

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }


    public Set<WorkoutPlan> getWorkoutPlan() {
        return workoutPlan;
    }

    public void setWorkoutPlan(Set<WorkoutPlan> workoutPlan) {
        this.workoutPlan = workoutPlan;
    }

    public Set<DietPlan> getDietPlan() {
        return dietPlan;
    }

    public void setDietPlan(Set<DietPlan> dietPlan) {
        this.dietPlan = dietPlan;
    }

    public Set<Supplement> getSupplement() {
        return supplement;
    }

    public void setSupplement(Set<Supplement> supplement) {
        this.supplement = supplement;
    }

    public Set<ProgressTracking> getProgressTracking() {
        return progressTracking;
    }

    public void setProgressTracking(Set<ProgressTracking> progressTracking) {
        this.progressTracking = progressTracking;
    }
}
