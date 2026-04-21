package com.ahmed.Hadidy.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "profile")
public class Profile {

    private Long id ;

    private String fullName;

    private double weight ;

    private double height  ;

    private boolean isPublic;

    private double gypePrice ;

    private String goal ;

    @OneToOne(mappedBy = "profile")
    private User user ;

    private LocalDate startSubscribe ;

    private LocalDate endSubscribe ;

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

    public double getGypePrice() {
        return gypePrice;
    }

    public void setGypePrice(double gypePrice) {
        this.gypePrice = gypePrice;
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
