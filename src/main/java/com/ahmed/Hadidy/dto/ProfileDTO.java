package com.ahmed.Hadidy.dto;

import java.time.LocalDate;

public class ProfileDTO {

    private String fullName;

    private double weight ;

    private double height  ;

    private boolean isPublic;

    private double gymPrice ;

    private String goal ;

    private Long userId ;

    private LocalDate startSubscribe ;

    private LocalDate endSubscribe ;

    private String profilePicture;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
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
