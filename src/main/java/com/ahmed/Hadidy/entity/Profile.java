package com.ahmed.Hadidy.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long id ;

    @Column(name="full_name")
    private String fullName;

    @Column(name="weight")
    private double weight ;

    @Column(name="height")
    private double height  ;

    @Column(name="is_public")
    private boolean isPublic;

    @Column(name="gym_price")
    private double gymPrice ;

    @Column(name="goal")
    private String goal ;

    @OneToOne(mappedBy = "profile")
    private User user ;

    @Column(name="start_subscribe")
    private LocalDate startSubscribe ;

    @Column(name="end_subscribe")
    private LocalDate endSubscribe ;

    @Column(name="profile_picture")
    private String profilePicture;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public double getGymPrice() {
        return gymPrice;
    }

    public void setGymPrice(double gymPrice) {
        this.gymPrice = gymPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getStartSubscribe() {
        return startSubscribe;
    }

    public void setStartSubscribe(LocalDate startSubscribe) {
        this.startSubscribe = startSubscribe;
    }

    public LocalDate getEndSubscribe() {
        return endSubscribe;
    }

    public void setEndSubscribe(LocalDate endSubscribe) {
        this.endSubscribe = endSubscribe;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }
}
