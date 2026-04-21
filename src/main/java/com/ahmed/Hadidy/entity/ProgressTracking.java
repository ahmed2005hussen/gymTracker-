package com.ahmed.Hadidy.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Locale;

@Entity
public class ProgressTracking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(name = "weight")
    private double weight ;

    @Column(name = "height")
    private double height ;

    @Column(name = "note")
    private String note;

    @Column(name = "taken_data")
    private LocalDate takenDate ;

    @Column(name = "photo")
    private String photo;

    @Column(name = "is_public")
    private boolean isPublic ;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDate getTakenDate() {
        return takenDate;
    }

    public void setTakenDate(LocalDate takenDate) {
        this.takenDate = takenDate;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
